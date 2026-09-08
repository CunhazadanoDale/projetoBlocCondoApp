package com.condoapp.bloc.agendamento.service;

import com.condoapp.bloc.agendamento.dto.AgendamentoMapper;
import com.condoapp.bloc.agendamento.dto.AgendamentoRequestDTO;
import com.condoapp.bloc.agendamento.dto.AgendamentoResponseDTO;
import com.condoapp.bloc.agendamento.dto.AlterarAgendamentoDTO;
import com.condoapp.bloc.agendamento.entity.Agendamento;
import com.condoapp.bloc.agendamento.entity.Espaco;
import com.condoapp.bloc.agendamento.enums.StatusAgendamento;
import com.condoapp.bloc.agendamento.repository.AgendamentoRepository;
import com.condoapp.bloc.agendamento.repository.EspacoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AgendamentoServiceImpl implements AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final EspacoRepository espacoRepository;

    @Override
    public AgendamentoResponseDTO buscarPorUUID(UUID uuid) {

        Agendamento agendamento = agendamentoRepository.findByUuid(uuid)
                .orElseThrow( () -> new RuntimeException("Não encontrado"));

        return AgendamentoMapper.fromEntityToResponse(agendamento);
    }

    @Override
    @Transactional
    public AgendamentoResponseDTO criarAgendamento(AgendamentoRequestDTO agendamento) {

        Espaco espacoExiste = espacoRepository.findByUuid(agendamento.getEspacoUUID())
                .orElseThrow(() -> new RuntimeException("Espaço não encontrado"));

        List<Agendamento> disponivel = agendamentoRepository.findByDate(espacoExiste.getEspacoId(), StatusAgendamento.CANCELADO,
                agendamento.getInicio(), agendamento.getFim());

        boolean temConflitoHorario = disponivel.stream()
                .anyMatch(existente -> agendamento.getInicio().isBefore(existente.getFim()) &&
                        agendamento.getFim().isAfter(existente.getInicio()));

        if (temConflitoHorario) {
            throw new RuntimeException("O horario selecionado já está ocupado");
        }

        Agendamento agendamentoNovo = Agendamento.builder()
                .uuid(UUID.randomUUID())
                .espaco(espacoExiste)
                .nomeResponsavel(agendamento.getNomeResponsavel())
                .unidadeResponsavel(agendamento.getUnidadeResponsavel())
                .inicio(agendamento.getInicio())
                .fim(agendamento.getFim())
                .status(StatusAgendamento.PENDENTE)
                .criadoEm(LocalDateTime.now())
                .build();

        return AgendamentoMapper.fromEntityToResponse(agendamentoRepository.save(agendamentoNovo));
    }

    @Override
    public List<AgendamentoResponseDTO> listarAgendamentosDeCondominio(UUID condominioUUID) {

        List<Agendamento> agendamentoList = agendamentoRepository.findAgendamentoByCondominioUUID(condominioUUID, StatusAgendamento.CANCELADO);

        return agendamentoList.stream()
                .map(AgendamentoMapper::fromEntityToResponse)
                .toList();

    }

    @Override
    @Transactional
    public AgendamentoResponseDTO alterarAgendamentoStatusEObservacao(UUID agendamentoUUID, AlterarAgendamentoDTO alterarAgendamentoDTO) {

        Agendamento agendamentoFromDB = agendamentoRepository.findByUuid(agendamentoUUID)
                .orElseThrow(() -> new RuntimeException("Agendamento não existe"));

        agendamentoFromDB.setStatus(alterarAgendamentoDTO.getStatusAgendamento());
        agendamentoFromDB.setObservacao(alterarAgendamentoDTO.getObservacao());

        return AgendamentoMapper.fromEntityToResponse(agendamentoRepository.save(agendamentoFromDB));
    }

    @Override
    @Transactional
    public void cancelarAgendamento(UUID uuid) {
        Agendamento agendamento = agendamentoRepository.findByUuid(uuid)
                .orElseThrow(() -> new RuntimeException("agendamento não encontrado"));

        agendamento.setStatus(StatusAgendamento.CANCELADO);

        agendamentoRepository.save(agendamento);
        
    }

    @Override
    public List<AgendamentoResponseDTO> buscarDisponibilidade(Long espacoId, LocalDate date) {

        LocalDateTime inicio = date.atStartOfDay();
        LocalDateTime fim = date.plusDays(1).atStartOfDay();

        List<Agendamento> disponibilidade = agendamentoRepository.findByDate(espacoId, StatusAgendamento.CANCELADO, inicio, fim);

        return disponibilidade.stream()
                .map(AgendamentoMapper::fromEntityToResponse)
                .toList();
    }
}

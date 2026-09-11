package com.condoapp.bloc.agendamento.service;

import com.condoapp.bloc.agendamento.dto.*;
import com.condoapp.bloc.agendamento.entity.Agendamento;
import com.condoapp.bloc.agendamento.entity.Espaco;
import com.condoapp.bloc.agendamento.enums.StatusAgendamento;
import com.condoapp.bloc.agendamento.repository.AgendamentoRepository;
import com.condoapp.bloc.agendamento.repository.EspacoRepository;
import com.condoapp.bloc.auth.entity.Conta;
import com.condoapp.bloc.auth.enums.Role;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AgendamentoServiceImpl implements AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final EspacoRepository espacoRepository;

    @Override
    public AgendamentoResponseDTO buscarPorUUID(UUID uuid, Conta conta) {

        Agendamento agendamento = agendamentoRepository.findByUuid(uuid)
                .orElseThrow( () -> new RuntimeException("Não encontrado"));

        if (!validarSindico(conta) && !validarPertence(conta, agendamento.getEspaco().getCondominio().getUuid())
                && !validarMoradorDonoDoAgendamento(agendamento, conta)) {
            throw new RuntimeException("Não possui autoridade para atualizar este espaço");
        }


        return AgendamentoMapper.fromEntityToResponse(agendamento);
    }

    @Override
    @Transactional
    public AgendamentoResponseDTO criarAgendamento(AgendamentoRequestDTO agendamento, Conta conta) {

        Espaco espacoExiste = espacoRepository.findByUuid(agendamento.getEspacoUUID())
                .orElseThrow(() -> new RuntimeException("Espaço não encontrado"));

        List<Agendamento> disponivel = agendamentoRepository.findByDate(espacoExiste.getUuid(), StatusAgendamento.CANCELADO,
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
                .morador(conta.getMorador())
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
    public ConteudoPaginacao<AgendamentoResponseDTO> listarAgendamentosDeCondominio(Integer pageNumber, Integer pageSize,
                                                                       String sortBy, String sortOrder, UUID condominioUUID, Conta conta) {

        if (!validarSindico(conta) && !validarPertence(conta, condominioUUID)) {
            throw new RuntimeException("Não possui autoridade para atualizar este espaço");
        }

        Sort sortByAndOrder = sortOrder.equals("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);
        Page<Agendamento> agendamentoList = agendamentoRepository.findAgendamentoByCondominioUUID(condominioUUID, StatusAgendamento.CANCELADO, pageDetails);

        List<AgendamentoResponseDTO> agendamentoResponseDTOS = agendamentoList.stream()
                .map(AgendamentoMapper::fromEntityToResponse)
                .toList();

        return ConteudoPaginacao.de(agendamentoList, agendamentoResponseDTOS);

    }

    @Override
    @Transactional
    public AgendamentoResponseDTO alterarAgendamentoStatusEObservacao(UUID agendamentoUUID, AlterarAgendamentoDTO alterarAgendamentoDTO,
                                                                      Conta conta) {

        Agendamento agendamentoFromDB = agendamentoRepository.findByUuid(agendamentoUUID)
                .orElseThrow(() -> new RuntimeException("Agendamento não existe"));

        if (!validarSindico(conta) && !validarPertence(conta, agendamentoFromDB.getEspaco().getCondominio().getUuid())) {
            throw new RuntimeException("Não possui autoridade para atualizar este espaço");
        }

        agendamentoFromDB.setStatus(alterarAgendamentoDTO.getStatusAgendamento());
        agendamentoFromDB.setObservacao(alterarAgendamentoDTO.getObservacao());

        return AgendamentoMapper.fromEntityToResponse(agendamentoRepository.save(agendamentoFromDB));
    }

    @Override
    @Transactional
    public void cancelarAgendamento(UUID uuid, Conta conta) {

        Agendamento agendamento = agendamentoRepository.findByUuid(uuid)
                .orElseThrow(() -> new RuntimeException("agendamento não encontrado"));

        if (!validarSindico(conta) && !validarPertence(conta, agendamento.getEspaco().getCondominio().getUuid())
                && !validarMoradorDonoDoAgendamento(agendamento, conta)) {
            throw new RuntimeException("Não possui autoridade para atualizar este espaço");
        }

        agendamento.setStatus(StatusAgendamento.CANCELADO);

        agendamentoRepository.save(agendamento);
        
    }

    @Override
    public List<AgendamentoResponseDTO> buscarDisponibilidade(UUID espacoUUID, LocalDate date) {

        LocalDateTime inicio = date.atStartOfDay();
        LocalDateTime fim = date.plusDays(1).atStartOfDay();

        List<Agendamento> disponibilidade = agendamentoRepository.findByDate(espacoUUID, StatusAgendamento.CANCELADO, inicio, fim);

        return disponibilidade.stream()
                .map(AgendamentoMapper::fromEntityToResponse)
                .toList();
    }

    private boolean validarSindico(Conta conta) {
        boolean temAutoridade = conta.getAuthorities().stream()
                .map(auth -> Role.valueOf(auth.getAuthority().replace("ROLE_", "")))
                .anyMatch(Role::isSindico);

        return temAutoridade;
    }

    private boolean validarPertence(Conta conta, UUID condominioUUID) {

        if(conta.getMorador() == null || conta.getMorador().getCondominio() == null) {
            return false;
        }

        return Objects.equals(conta.getMorador().getCondominio().getUuid(), condominioUUID);
    }

    private boolean validarMoradorDonoDoAgendamento(Agendamento agendamento, Conta conta) {
        boolean ehMorador = conta.getAuthorities().stream()
                .map(auth -> Role.valueOf(auth.getAuthority().replace("ROLE_", "")))
                .anyMatch(Role::isMorador);

        if (!ehMorador) {
            return false;
        }

        return agendamento.getMorador().getMoradorId().equals(conta.getMorador().getMoradorId());
    }
}

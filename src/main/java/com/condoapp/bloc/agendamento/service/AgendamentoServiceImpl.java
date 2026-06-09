package com.condoapp.bloc.agendamento.service;

import com.condoapp.bloc.agendamento.entity.Agendamento;
import com.condoapp.bloc.agendamento.enums.StatusAgendamento;
import com.condoapp.bloc.agendamento.repository.AgendamentoRepository;
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

    @Override
    public Agendamento buscarPorUUID(UUID uuid) {
        return agendamentoRepository.findByUuid(uuid)
                .orElseThrow(() -> new RuntimeException("agendamento não encontrado"));
    }

    @Override
    public Agendamento criarAgendamento(Agendamento agendamento) {

        agendamento.setStatus(StatusAgendamento.PENDENTE);
        agendamento.setUuid(UUID.randomUUID());

        return agendamentoRepository.save(agendamento);
    }

    @Override
    public List<Agendamento> listarAgendamentosDeCondominio(UUID condominioId) {
        return agendamentoRepository.findAll();
    }

    @Override
    public Agendamento cancelarAgendamento(UUID uuid) {
        Agendamento agendamento = agendamentoRepository.findByUuid(uuid)
                .orElseThrow(() -> new RuntimeException("agendamento não encontrado"));

        agendamento.setStatus(StatusAgendamento.CANCELADO);
        return agendamentoRepository.save(agendamento);
    }

    @Override
    public List<Agendamento> buscarDisponibilidade(Long espacoId, LocalDate date) {

        LocalDateTime inicio = date.atStartOfDay();
        LocalDateTime fim = date.plusDays(1).atStartOfDay();

        List<Agendamento> disponibilidade = agendamentoRepository.findByDate(espacoId, StatusAgendamento.CANCELADO, inicio, fim);

        return disponibilidade;
    }
}

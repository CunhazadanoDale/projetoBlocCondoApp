package com.condoapp.bloc.agendamento.service;

import com.condoapp.bloc.agendamento.entity.Agendamento;
import com.condoapp.bloc.agendamento.enums.StatusAgendamento;
import com.condoapp.bloc.agendamento.repository.AgendamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        return null;
    }

    @Override
    public List<Agendamento> listarAgendamentosDeCondominio(UUID condominioId) {
        return List.of();
    }

    @Override
    public Agendamento cancelarAgendamento(UUID uuid) {
        Agendamento agendamento = agendamentoRepository.findByUuid(uuid)
                .orElseThrow(() -> new RuntimeException("agendamento não encontrado"));

        agendamento.setStatus(StatusAgendamento.CANCELADO);
        return agendamentoRepository.save(agendamento);
    }
}

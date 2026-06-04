package com.condoapp.bloc.agendamento.service;

import com.condoapp.bloc.agendamento.entity.Agendamento;
import com.condoapp.bloc.agendamento.repository.AgendamentoRepository;

import java.util.UUID;

public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;

    public AgendamentoService(AgendamentoRepository agendamentoRepository) {
        this.agendamentoRepository = agendamentoRepository;
    }


    public Agendamento buscarPorUUID(UUID uuid) {
        return agendamentoRepository.findByUuid(uuid)
                .orElseThrow(() -> new RuntimeException("agendamento não encontrado"));
    }
}

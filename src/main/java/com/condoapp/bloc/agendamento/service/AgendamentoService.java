package com.condoapp.bloc.agendamento.service;

import com.condoapp.bloc.agendamento.entity.Agendamento;

import java.util.UUID;

public interface AgendamentoService {
    Agendamento buscarPorUUID(UUID uuid);
}

package com.condoapp.bloc.agendamento.repository;

import com.condoapp.bloc.agendamento.entity.Agendamento;


import java.util.Optional;
import java.util.UUID;


public interface AgendamentoRepository{

    Optional<Agendamento> findByUuid(UUID uuid);
}

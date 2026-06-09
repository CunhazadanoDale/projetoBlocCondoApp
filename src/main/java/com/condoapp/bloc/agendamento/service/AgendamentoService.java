package com.condoapp.bloc.agendamento.service;

import com.condoapp.bloc.agendamento.entity.Agendamento;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface AgendamentoService {
    Agendamento buscarPorUUID(UUID uuid);
    Agendamento criarAgendamento(Agendamento agendamento);
    List<Agendamento> listarAgendamentosDeCondominio(UUID condominioId);
    Agendamento cancelarAgendamento(UUID uuid);
    List<Agendamento> buscarDisponibilidade(Long espacoId, LocalDate data);
}

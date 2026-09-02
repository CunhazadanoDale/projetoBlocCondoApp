package com.condoapp.bloc.agendamento.service;

import com.condoapp.bloc.agendamento.dto.AgendamentoRequestDTO;
import com.condoapp.bloc.agendamento.dto.AgendamentoResponseDTO;
import com.condoapp.bloc.agendamento.dto.AlterarAgendamentoDTO;
import com.condoapp.bloc.agendamento.entity.Agendamento;
import com.condoapp.bloc.agendamento.enums.StatusAgendamento;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface AgendamentoService {
    AgendamentoResponseDTO buscarPorUUID(UUID uuid);
    AgendamentoResponseDTO criarAgendamento(AgendamentoRequestDTO agendamento);
    List<AgendamentoResponseDTO> listarAgendamentosDeCondominio(UUID condominioId);
    AgendamentoResponseDTO alterarAgendamentoStatusEObservacao(UUID agendamentoUUID, AlterarAgendamentoDTO alterarAgendamentoDTO);
    void cancelarAgendamento(UUID uuid);
    List<AgendamentoResponseDTO> buscarDisponibilidade(Long espacoId, LocalDate data);
}

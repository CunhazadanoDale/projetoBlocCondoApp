package com.condoapp.bloc.agendamento.service;

import com.condoapp.bloc.agendamento.dto.AgendamentoRequestDTO;
import com.condoapp.bloc.agendamento.dto.AgendamentoResponseDTO;
import com.condoapp.bloc.agendamento.dto.AlterarAgendamentoDTO;
import com.condoapp.bloc.agendamento.dto.ConteudoPaginacao;
import com.condoapp.bloc.auth.entity.Conta;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface AgendamentoService {
    AgendamentoResponseDTO buscarPorUUID(UUID uuid, Conta conta);
    AgendamentoResponseDTO criarAgendamento(AgendamentoRequestDTO agendamento, Conta conta);
    ConteudoPaginacao<AgendamentoResponseDTO> listarAgendamentosDeCondominio(Integer pageNumber, Integer pageSize,
                                                                             String sortBy, String sortOrder, UUID condominioId, Conta conta);
    AgendamentoResponseDTO alterarAgendamentoStatusEObservacao(UUID agendamentoUUID, AlterarAgendamentoDTO alterarAgendamentoDTO, Conta conta);
    void cancelarAgendamento(UUID uuid, Conta conta);
    List<AgendamentoResponseDTO> buscarDisponibilidade(UUID espacoUUID, LocalDate data);
}

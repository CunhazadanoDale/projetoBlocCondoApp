package com.condoapp.bloc.agendamento.dto;

import com.condoapp.bloc.agendamento.enums.StatusAgendamento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AgendamentoResponseDTO {

    private UUID espacoUUID;
    private UUID agendamentoUUID;
    private String nomeResponsavel;
    private String unidadeResponsavel;

    private StatusAgendamento statusAgendamento;
    private LocalDateTime inicio;
    private LocalDateTime fim;
    private LocalDateTime criadoEm;

    private String observacao;



}

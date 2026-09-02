package com.condoapp.bloc.agendamento.dto;

import com.condoapp.bloc.agendamento.entity.Agendamento;

public class AgendamentoMapper {

    public static AgendamentoResponseDTO fromEntityToResponse(Agendamento agendamento) {
        return AgendamentoResponseDTO.builder()
                .espacoUUID(agendamento.getEspaco().getUuid())
                .agendamentoUUID(agendamento.getUuid())
                .nomeResponsavel(agendamento.getNomeResponsavel())
                .unidadeResponsavel(agendamento.getUnidadeResponsavel())
                .statusAgendamento(agendamento.getStatus())
                .inicio(agendamento.getInicio())
                .fim(agendamento.getFim())
                .criadoEm(agendamento.getCriadoEm())
                .observacao(agendamento.getObservacao())
                .build();
    }
}

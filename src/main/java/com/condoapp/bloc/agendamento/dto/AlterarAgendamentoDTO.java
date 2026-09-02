package com.condoapp.bloc.agendamento.dto;

import com.condoapp.bloc.agendamento.enums.StatusAgendamento;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlterarAgendamentoDTO {

    @NotNull
    private StatusAgendamento statusAgendamento;
    private String observacao;
}

package com.condoapp.bloc.agendamento.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EspacoRequestDTO {

    @NotBlank
    private String nome;
    private String descricao;

    @NotNull
    private Integer capacidade;

    @NotNull
    @Positive
    private Integer minHoras;

    @Positive
    private Integer cancelamentoMinHoras;

    @Positive
    private Integer limiteReservaSemana;
}

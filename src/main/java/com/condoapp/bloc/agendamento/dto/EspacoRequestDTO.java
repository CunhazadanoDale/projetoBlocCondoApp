package com.condoapp.bloc.agendamento.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EspacoRequestDTO {

    private Long condominioID;

    @NotBlank
    private String nome;
    private String descricao;

    @NotBlank
    private Integer capacidade;

    @NotBlank
    private Integer minHoras;
    private Integer cancelamentoMinHoras;
    private Integer limiteReservaSemana;
}

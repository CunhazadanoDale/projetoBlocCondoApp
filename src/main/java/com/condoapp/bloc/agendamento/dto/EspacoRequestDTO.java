package com.condoapp.bloc.agendamento.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EspacoRequestDTO {

    private Long condominioID;
    private String nome;
    private String descricao;
    private Integer capacidade;
    private Integer minHoras;
    private Integer cancelamentoMinHoras;
    private Integer limiteReservaSemana;
}

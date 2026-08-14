package com.condoapp.bloc.agendamento.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EspacoResponseDTO {

    private UUID uuid;
    private Long condominioID;
    private String nome;
    private String descricao;
    private Integer capacidade;
    private Integer minHoras;
    private Integer cancelamentoMinHoras;
    private Integer limiteReservaSemana;
    private boolean ativo;
}

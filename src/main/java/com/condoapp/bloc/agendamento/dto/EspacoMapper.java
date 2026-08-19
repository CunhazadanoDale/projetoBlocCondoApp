package com.condoapp.bloc.agendamento.dto;

import com.condoapp.bloc.agendamento.entity.Espaco;

public class EspacoMapper {

    public static EspacoResponseDTO fromEntityToResponse(Espaco espaco) {
        return EspacoResponseDTO.builder()
                .uuid(espaco.getUuid())
                .condominioID(espaco.getCondominio().getCondominioId())
                .nome(espaco.getNome())
                .descricao(espaco.getDescricao())
                .capacidade(espaco.getCapacidade())
                .minHoras(espaco.getAntecedenciaMinHoras())
                .cancelamentoMinHoras(espaco.getCancelamentoMinHoras())
                .limiteReservaSemana(espaco.getLimiteReservaSemana())
                .ativo(espaco.isAtivo())
                .build();
    }
}

package com.condoapp.bloc.morador.dto;

import com.condoapp.bloc.morador.entity.Morador;

public class MoradorMapper {

    public static MoradorResponseDTO fromEntityToResponse(Morador morador) {
        return MoradorResponseDTO.builder()
                .moradorUUID(morador.getUuid())
                .nomeCompleto(morador.getNomeCompleto())
                .unidade(morador.getUnidade())
                .bloco(morador.getBloco())
                .telefone(morador.getTelefone())
                .condominioUUID(morador.getCondominio().getUuid())
                .criadoEm(morador.getCriadoEm())
                .build();
    }
}

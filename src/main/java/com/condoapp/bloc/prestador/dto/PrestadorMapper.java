package com.condoapp.bloc.prestador.dto;

import com.condoapp.bloc.prestador.entity.Prestador;

public class PrestadorMapper {

    public static PrestadorResponseDTO toPrivateResponseDTO (Prestador prestador) {
        return PrestadorResponseDTO.builder()
                .prestadorUUID(prestador.getUuid())
                .nomeCompleto(prestador.getNomeCompleto())
                .cpfOuCnpj(prestador.getCpfOuCnpj())
                .telefone(prestador.getTelefone())
                .descricao(prestador.getDescricao())
                .saldo(prestador.getSaldo())
                .status(prestador.getStatus())
                .totalAvaliacoes(prestador.getTotalAvaliacoes())
                .avaliacaoMedia(prestador.getAvaliacaoMedia())
                .criadoEm(prestador.getCriadoEm())
                .build();
    }

    public static PublicPrestadorResponseDTO toPublicResponseDTO (Prestador prestador) {
        return PublicPrestadorResponseDTO.builder()
                .prestadorUUID(prestador.getUuid())
                .nomeCompleto(prestador.getNomeCompleto())
                .telefone(prestador.getTelefone())
                .descricao(prestador.getDescricao())
                .totalAvaliacoes(prestador.getTotalAvaliacoes())
                .avaliacaoMedia(prestador.getAvaliacaoMedia())
                .criadoEm(prestador.getCriadoEm())
                .build();
    }
}

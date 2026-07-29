package com.condoapp.bloc.morador.service;

import com.condoapp.bloc.auth.entity.Conta;
import com.condoapp.bloc.morador.dto.MoradorRequestDTO;
import com.condoapp.bloc.morador.dto.MoradorResponseDTO;
import com.condoapp.bloc.morador.entity.Morador;
import com.condoapp.bloc.morador.repository.MoradorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MoradorServiceImpl implements MoradorService{

    private final MoradorRepository moradorRepository;
    private final CondominioRepository condominioRepository;

    @Override
    public MoradorResponseDTO completarPerfil(Conta conta, MoradorRequestDTO dto) {
        Morador morador = Morador.builder()
                .nomeCompleto(dto.getNomeCompleto())
                .unidade(dto.getUnidade())
                .telefone(dto.getTelefone())
                .bloco(dto.getBloco())
                .condominio(dto.getCondominioId())
                .build();
    }

    @Override
    public MoradorResponseDTO buscarPorUUID(UUID uuid) {
        Morador morador = moradorRepository.findByUUID(uuid)
                .orElseThrow(() -> new RuntimeException("Nenhum morador encontrado"));

        return MoradorResponseDTO.builder()
                .moradorUUID(morador.getUuid())
                .nomeCompleto(morador.getNomeCompleto())
                .unidade(morador.getUnidade())
                .telefone(morador.getTelefone())
                .bloco(morador.getBloco())
                .condominioId(morador.getCondominio().getCondominioId())
                .criadoEm(morador.getCriadoEm())
                .build();
    }
}

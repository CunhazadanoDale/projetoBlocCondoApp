package com.condoapp.bloc.morador.service;

import com.condoapp.bloc.auth.entity.Conta;
import com.condoapp.bloc.condominio.entity.Condominio;
import com.condoapp.bloc.condominio.repository.CondominioRepository;
import com.condoapp.bloc.morador.dto.MoradorMapper;
import com.condoapp.bloc.morador.dto.MoradorRequestDTO;
import com.condoapp.bloc.morador.dto.MoradorResponseDTO;
import com.condoapp.bloc.morador.entity.Morador;
import com.condoapp.bloc.morador.repository.MoradorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MoradorServiceImpl implements MoradorService{

    private final MoradorRepository moradorRepository;
    private final CondominioRepository condominioRepository;

    @Override
    @Transactional
    public MoradorResponseDTO completarPerfil(Conta conta, MoradorRequestDTO dto) {

        if (conta.getMorador() != null) {
            throw new RuntimeException("Já existe morador para esta conta");
        }

        Condominio condominio = condominioRepository.findByUUID(dto.getCondominioUUID())
                .orElseThrow(() -> new RuntimeException("Condominio não encontrado"));

        Morador morador = Morador.builder()
                .uuid(UUID.randomUUID())
                .nomeCompleto(dto.getNomeCompleto())
                .unidade(dto.getUnidade())
                .telefone(dto.getTelefone())
                .bloco(dto.getBloco())
                .condominio(condominio)
                .conta(conta)
                .build();

        Morador novoMorador = moradorRepository.save(morador);
        conta.setMorador(novoMorador);

        return MoradorMapper.fromEntityToResponse(novoMorador);
    }

    @Override
    public MoradorResponseDTO buscarPorUUID(UUID uuid) {
        Morador morador = moradorRepository.findByUUID(uuid)
                .orElseThrow(() -> new RuntimeException("Nenhum morador encontrado"));

        return MoradorMapper.fromEntityToResponse(morador);
    }
}

package com.condoapp.bloc.morador.service;

import com.condoapp.bloc.auth.entity.Conta;

import java.util.UUID;

public interface MoradorService {
    MoradorResponseDTO completarPerfil(Conta conta, MoradorRequestDTO dto);
    MoradorResponseDTO buscarPorUUID (UUID uuid);
}

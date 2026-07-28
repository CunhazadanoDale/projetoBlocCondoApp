package com.condoapp.bloc.morador.service;

import com.condoapp.bloc.auth.entity.Conta;
import com.condoapp.bloc.morador.dto.MoradorRequestDTO;
import com.condoapp.bloc.morador.dto.MoradorResponseDTO;

import java.util.UUID;

public interface MoradorService {
    MoradorResponseDTO completarPerfil(Conta conta, MoradorRequestDTO dto);
    MoradorResponseDTO buscarPorUUID (UUID uuid);
}

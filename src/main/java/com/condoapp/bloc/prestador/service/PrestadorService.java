package com.condoapp.bloc.prestador.service;

import com.condoapp.bloc.auth.entity.Conta;
import com.condoapp.bloc.prestador.dto.PrestadorRequestDTO;
import com.condoapp.bloc.prestador.dto.PrestadorResponseDTO;

import java.util.UUID;

public interface PrestadorService {

    PrestadorResponseDTO completarPerfil(Conta conta, PrestadorRequestDTO prestadorRequestDTO);
    PrestadorResponseDTO buscarPorUUID(UUID uuid);
}

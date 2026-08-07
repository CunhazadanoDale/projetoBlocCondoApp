package com.condoapp.bloc.prestador.service;

import com.condoapp.bloc.auth.entity.Conta;
import com.condoapp.bloc.prestador.dto.PrestadorRequestDTO;
import com.condoapp.bloc.prestador.dto.PrestadorResponseDTO;
import com.condoapp.bloc.prestador.dto.PublicPrestadorResponseDTO;

import java.util.UUID;

public interface PrestadorService {

    PrestadorResponseDTO completarPerfil(Conta conta, PrestadorRequestDTO prestadorRequestDTO);
    PublicPrestadorResponseDTO buscarPorUUID(UUID uuid);
}

package com.condoapp.bloc.auth.service;

import com.condoapp.bloc.auth.dto.LoginRequestDTO;
import com.condoapp.bloc.auth.dto.LoginResponseDTO;
import com.condoapp.bloc.auth.dto.RegisterRequestDTO;

public interface AuthService {
    LoginResponseDTO logar(LoginRequestDTO dto);
    Void registrar(RegisterRequestDTO dto);
}

package com.condoapp.bloc.auth.service;

import com.condoapp.bloc.auth.dto.LoginRequestDTO;
import com.condoapp.bloc.auth.dto.LoginResponseDTO;
import com.condoapp.bloc.auth.dto.RegisterRequestDTO;
import com.condoapp.bloc.auth.repository.ContaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private ContaRepository contaRepository;
    private JwtService jwtService;

    @Override
    public LoginResponseDTO logar(LoginRequestDTO dto) {
        return null;
    }

    @Override
    public Void registrar(RegisterRequestDTO dto) {
        return null;
    }
}

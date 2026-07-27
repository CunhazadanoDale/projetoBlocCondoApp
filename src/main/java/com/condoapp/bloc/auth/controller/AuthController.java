package com.condoapp.bloc.auth.controller;

import com.condoapp.bloc.auth.dto.LoginRequestDTO;
import com.condoapp.bloc.auth.dto.LoginResponseDTO;
import com.condoapp.bloc.auth.dto.RegisterRequestDTO;
import com.condoapp.bloc.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login (@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        return ResponseEntity.ok(authService.logar(loginRequestDTO));
    }


    @PostMapping("/registrar")
    public ResponseEntity<LoginResponseDTO> register (@Valid @RequestBody RegisterRequestDTO registerRequestDTO) {
        return ResponseEntity.ok(authService.registrar(registerRequestDTO));
    }
}

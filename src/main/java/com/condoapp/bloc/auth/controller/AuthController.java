package com.condoapp.bloc.auth.controller;

import com.condoapp.bloc.auth.dto.LoginRequestDTO;
import com.condoapp.bloc.auth.dto.LoginResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login (@RequestBody LoginRequestDTO loginRequestDTO) {
        return ResponseEntity.ok()
    }
}

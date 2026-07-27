package com.condoapp.bloc.auth.service;

import com.condoapp.bloc.auth.dto.LoginRequestDTO;
import com.condoapp.bloc.auth.dto.LoginResponseDTO;
import com.condoapp.bloc.auth.dto.RegisterRequestDTO;
import com.condoapp.bloc.auth.entity.Conta;
import com.condoapp.bloc.auth.repository.ContaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final ContaRepository contaRepository;
    private final PasswordEncoder passwordEncoder;
    private final ContaDetailsService contaDetailsService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public LoginResponseDTO logar(LoginRequestDTO dto) {

        UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(
                dto.getEmail(),
                dto.getSenha()
        );

        Authentication authentication = authenticationManager.authenticate(user);
        Conta contaAuthenticada = (Conta) authentication.getPrincipal();
        String token = jwtService.gerarToken(contaAuthenticada);

        return LoginResponseDTO.builder()
                .token(token)
                .build();

    }

    @Override
    public LoginResponseDTO registrar(RegisterRequestDTO dto) {
        boolean contaExiste = contaRepository.existsByEmail(dto.getEmail());
        if (contaExiste) {
            throw new RuntimeException("Conta ja existente");
        }

        Conta novaConta = Conta.builder()
                .uuid(UUID.randomUUID())
                .email(dto.getEmail().trim())
                .senhaHash(passwordEncoder.encode(dto.getSenha().trim()))
                .role(dto.getTipoUsuario())
                .ativo(true)
                .build();

        Conta salvarConta =contaRepository.save(novaConta);

        return LoginResponseDTO.builder()
                .token(jwtService.gerarToken(salvarConta))
                .build();
    }
}

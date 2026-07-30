package com.condoapp.bloc.morador.controller;

import com.condoapp.bloc.auth.entity.Conta;
import com.condoapp.bloc.morador.dto.MoradorRequestDTO;
import com.condoapp.bloc.morador.dto.MoradorResponseDTO;
import com.condoapp.bloc.morador.service.MoradorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/moradores")
@RequiredArgsConstructor
public class MoradorController {

    private final MoradorService moradorService;


    @GetMapping("/{uuid}")
    public ResponseEntity<MoradorResponseDTO> buscarPorUUID(@PathVariable UUID uuid) {
        return ResponseEntity.ok(moradorService.buscarPorUUID(uuid));
    }

    @PostMapping()
    public ResponseEntity<MoradorResponseDTO> completarPerfil(@Valid @RequestBody MoradorRequestDTO moradorRequestDTO,
                                                              @AuthenticationPrincipal Conta conta,
                                                              UriComponentsBuilder uriBuilder) {
        MoradorResponseDTO morador = moradorService.completarPerfil(conta, moradorRequestDTO);

        URI uri = uriBuilder.path("/moradores/{uuid}")
                .buildAndExpand(morador.getMoradorUUID())
                .toUri();

        return ResponseEntity.created(uri).body(morador);
    }

}

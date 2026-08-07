package com.condoapp.bloc.prestador.controller;

import com.condoapp.bloc.auth.entity.Conta;
import com.condoapp.bloc.prestador.dto.PrestadorRequestDTO;
import com.condoapp.bloc.prestador.dto.PrestadorResponseDTO;
import com.condoapp.bloc.prestador.dto.PublicPrestadorResponseDTO;
import com.condoapp.bloc.prestador.service.PrestadorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/prestadores")
@RequiredArgsConstructor
public class PrestadorController {

    private final PrestadorService prestadorService;


    @GetMapping("/{uuid}")
    public ResponseEntity<PublicPrestadorResponseDTO> dadosDaConta(@PathVariable UUID uuid) {
        return ResponseEntity.ok(prestadorService.buscarPorUUID(uuid));
    }

    @PostMapping()
    public ResponseEntity<PrestadorResponseDTO> completarPerfil(@Valid @RequestBody PrestadorRequestDTO prestadorRequestDTO,
                                                                @AuthenticationPrincipal Conta conta,
                                                                UriComponentsBuilder builder) {
        PrestadorResponseDTO prestadorResponseDTO = prestadorService.completarPerfil(conta, prestadorRequestDTO);

        URI uri = builder.path("/prestadores/{uuid}")
                .buildAndExpand(prestadorResponseDTO.getPrestadorUUID())
                .toUri();

        return ResponseEntity.created(uri).body(prestadorResponseDTO);
    }
}

package com.condoapp.bloc.agendamento.controller;

import com.condoapp.bloc.agendamento.dto.EspacoRequestDTO;
import com.condoapp.bloc.agendamento.dto.EspacoResponseDTO;
import com.condoapp.bloc.agendamento.entity.Agendamento;
import com.condoapp.bloc.agendamento.entity.Espaco;
import com.condoapp.bloc.agendamento.service.AgendamentoService;
import com.condoapp.bloc.agendamento.service.EspacoService;
import com.condoapp.bloc.auth.entity.Conta;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/condominios")
@RequiredArgsConstructor
public class EspacoController {

    private final EspacoService espacoService;
    private final AgendamentoService agendamentoService;

    @GetMapping("/{uuid}/espacos")
    public ResponseEntity<List<EspacoResponseDTO>> listarEspacosDoCondominio(@PathVariable(name = "uuid") UUID condominioUuid) {
        return new ResponseEntity<>(espacoService.listarEspacos(condominioUuid), HttpStatus.OK);
    }

    @PostMapping("/espacos")
    public ResponseEntity<EspacoResponseDTO> cadastrarEspaco(@AuthenticationPrincipal Conta conta,
            @Valid @RequestBody EspacoRequestDTO espaco) {
        return new ResponseEntity<>(espacoService.criarEspaco(espaco, conta), HttpStatus.CREATED);
    }

    @PutMapping("/espacos/{espacoId}")
    public ResponseEntity<EspacoResponseDTO> atualizarEspaco(@PathVariable UUID espacoId,
                                                  @AuthenticationPrincipal Conta conta,
                                                  @Valid @RequestBody EspacoRequestDTO espaco) {
        return new ResponseEntity<>(espacoService.atualizarEspaco(espacoId, espaco, conta), HttpStatus.OK);
    }

    @GetMapping("/espacos/{espacoUUID}/disponibilidade")
    public ResponseEntity<List<Agendamento>> listarDisponibilidade(@PathVariable(name = "espacoUUID") UUID espacoId,
                                                                   @RequestParam LocalDate data) {
        return new ResponseEntity<>(agendamentoService.buscarDisponibilidade(espacoId, data), HttpStatus.OK);
    }
}

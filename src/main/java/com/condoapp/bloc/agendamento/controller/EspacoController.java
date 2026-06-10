package com.condoapp.bloc.agendamento.controller;

import com.condoapp.bloc.agendamento.entity.Espaco;
import com.condoapp.bloc.agendamento.service.EspacoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/condominios")
@RequiredArgsConstructor
public class EspacoController {

    private final EspacoService espacoService;

    @GetMapping("/{uuid}/espacos")
    public ResponseEntity<List<Espaco>> listarEspacosDoCondominio(@PathVariable(name = "uuid") UUID condominioUuid) {
        return new ResponseEntity<>(espacoService.listarEspacos(), HttpStatus.OK);
    }

    @PostMapping("/{uuid}/espacos")
    public ResponseEntity<Espaco> cadastrarEspaco(@PathVariable(name = "uuid") UUID condominioUuid,
                                                  @RequestBody Espaco espaco) {
        return new ResponseEntity<>(espacoService.criarEspaco(espaco), HttpStatus.CREATED);
    }

    @PutMapping("/{uuid}/espacos/{espacoId}")
    public ResponseEntity<Espaco> atualizarEspaco(@PathVariable(name = "uuid") UUID condominioUuid,
                                                  @PathVariable Long espacoId,
                                                  @RequestBody Espaco espaco) {
        return new ResponseEntity<>(espacoService.atualizarEspaco(espacoId, espaco), HttpStatus.OK);
    }
}

package com.condoapp.bloc.agendamento.controller;

import com.condoapp.bloc.agendamento.entity.Agendamento;
import com.condoapp.bloc.agendamento.service.AgendamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/condominios")
@RequiredArgsConstructor
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    @GetMapping("/{uuid}/agendamentos")
    public ResponseEntity<List<Agendamento>> listarAgendamentosDoCondominio(@PathVariable(name = "uuid") UUID condominioUuid) {
        return new ResponseEntity<>(agendamentoService.listarAgendamentosDeCondominio(condominioUuid), HttpStatus.OK);
    }

    @PostMapping("/{uuid}/espacos/{espacoId}/agendamentos")
    public ResponseEntity<Agendamento> criarReserva(@PathVariable(name = "uuid") UUID condominioUuid,
                                                    @PathVariable Long espacoId,
                                                    @RequestBody Agendamento agendamento
                                                    ) {
        return new ResponseEntity<>(agendamentoService.criarAgendamento(agendamento), HttpStatus.CREATED);
    }

    @DeleteMapping("/agendamentos/{agendamentoUuid}")
    public ResponseEntity<Void> cancelarReserva(@PathVariable UUID agendamentoUuid) {
        agendamentoService.cancelarAgendamento(agendamentoUuid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

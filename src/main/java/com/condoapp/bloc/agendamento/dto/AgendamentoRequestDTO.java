package com.condoapp.bloc.agendamento.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AgendamentoRequestDTO {

    @NotNull
    private UUID espacoUUID;

    @NotBlank
    private String nomeResponsavel;

    @NotBlank
    private String unidadeResponsavel;

    @NotNull
    private LocalDateTime inicio;

    @NotNull
    private LocalDateTime fim;
}

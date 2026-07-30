package com.condoapp.bloc.morador.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoradorRequestDTO {

    @NotBlank
    private String nomeCompleto;

    @NotBlank
    private UUID condominioUUID;
    @NotBlank
    private String unidade;
    private String bloco;
    private String telefone;
}

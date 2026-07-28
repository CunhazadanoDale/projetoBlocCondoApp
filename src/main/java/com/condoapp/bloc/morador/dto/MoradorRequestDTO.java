package com.condoapp.bloc.morador.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoradorRequestDTO {

    @NotBlank
    private String nomeCompleto;

    @NotBlank
    private Long condominioId;
    @NotBlank
    private String unidade;
    private String bloco;
    private String telefone;
}

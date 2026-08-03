package com.condoapp.bloc.prestador.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PrestadorRequestDTO {

    @NotBlank
    private String nomeCompleto;

    @NotBlank
    private String cpfOuCnpj;
    private String telefone;
    private String descricao;
}

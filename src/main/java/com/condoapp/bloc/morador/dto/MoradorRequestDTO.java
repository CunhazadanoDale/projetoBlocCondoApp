package com.condoapp.bloc.morador.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoradorRequestDTO {
    private String nomeCompleto;
    private Long condominioId;
    private String unidade;
    private String bloco;
    private String telefone;
}

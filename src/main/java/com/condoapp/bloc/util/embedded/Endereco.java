package com.condoapp.bloc.util.embedded;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Endereco {

    private String cep;
    private String nomeRua;
    private String bairro;
    private String cidade;
    private String estado;
    private String numero;
    private String complemento;
}

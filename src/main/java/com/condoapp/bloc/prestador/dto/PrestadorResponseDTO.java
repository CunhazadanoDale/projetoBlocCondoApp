package com.condoapp.bloc.prestador.dto;

import com.condoapp.bloc.prestador.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PrestadorResponseDTO {

    private UUID prestadorUUID;
    private String nomeCompleto;
    private String cpfOuCnpj;
    private String telefone;
    private String descricao;
    private BigDecimal saldo;
    private Status status;
    private Integer totalAvaliacoes;
    private BigDecimal avaliacaoMedia;
    private LocalDateTime criadoEm;
}

package com.condoapp.bloc.morador.dto;

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
public class MoradorResponseDTO {
    private UUID moradorUUID;
    private String nomeCompleto;
    private String unidade;
    private String telefone;
    private String bloco;
    private UUID condominioUUID;
    private LocalDateTime criadoEm;
}

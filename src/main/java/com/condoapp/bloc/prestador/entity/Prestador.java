package com.condoapp.bloc.prestador.entity;

import com.condoapp.bloc.auth.entity.Conta;
import com.condoapp.bloc.prestador.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_prestador")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Prestador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prestadorId;

    @Column(unique = true, nullable = false, updatable = false)
    private UUID uuid;

    @OneToOne
    @JoinColumn(name = "conta_id")
    private Conta conta;

    @Column(nullable = false, length = 60)
    private String nomeCompleto;

    @Column(unique = true, length = 20, nullable = false)
    private String cpfOuCnpj;

    private String telefone;
    private String descricao;
    private BigDecimal saldo = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Status status;

    private BigDecimal avaliacaoMedia;
    private Integer totalAvaliacoes;

    @CreationTimestamp
    private LocalDateTime criadoEm;
}

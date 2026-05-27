package com.condoapp.bloc.pagamentos.entity;

import com.condoapp.bloc.pagamentos.enums.StatusSaque;
import com.condoapp.bloc.prestador.entity.Prestador;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_saque")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Saque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long saqueId;
    @Column(nullable = false, unique = true, updatable = false)
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "prestador_id", nullable = false)
    private Prestador prestador;

    @Column(nullable = false)
    private BigDecimal valor;
    @Column(nullable = false)
    private String chavePix;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "status")
    private StatusSaque statusSaque;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime solicitadoEm;

    private LocalDateTime processadoEm;
    private String erroMensagem;
}

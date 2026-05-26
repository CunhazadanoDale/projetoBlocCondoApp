package com.condoapp.bloc.prestador.entity;

import com.condoapp.bloc.morador.entity.Morador;
import com.condoapp.bloc.prestador.enums.StatusContrato;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_contratacao")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Contratacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contratacaoId;

    @Column(nullable = false, updatable = false, unique = true)
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "morador_id", nullable = false)
    private Morador morador;

    @ManyToOne
    @JoinColumn(name = "prestador_id", nullable = false)
    private Prestador prestador;

    @ManyToOne
    @JoinColumn(name = "servico_id", nullable = false)
    private ServicoOferecido servicoOferecido;

    @Column(nullable = false)
    private BigDecimal valorCombinado;

    @Column(nullable = false, name = "descricao")
    private String descricaoSolicitacao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusContrato statusContrato;

    private LocalDateTime dataSolicitada;
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime criadoEm;
    private LocalDateTime concluidoEm;
}

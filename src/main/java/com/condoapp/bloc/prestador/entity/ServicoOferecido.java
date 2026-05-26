package com.condoapp.bloc.prestador.entity;

import com.condoapp.bloc.prestador.enums.CategoriaServico;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "tb_servico_oferecido")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServicoOferecido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long servicoId;

    @Column(nullable = false, unique = true, updatable = false)
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "prestador_id")
    private Prestador prestador;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private CategoriaServico categoriaServico;

    @Column(nullable = false, length = 100)
    private String titulo;
    @Column(nullable = false)
    private String descricao;
    private BigDecimal valorHora;
    private BigDecimal valorFixo;
    @Column(nullable = false)
    private boolean ativo;
}

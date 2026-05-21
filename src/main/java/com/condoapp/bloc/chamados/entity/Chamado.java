package com.condoapp.bloc.chamados.entity;

import com.condoapp.bloc.chamados.enums.CategoriaChamado;
import com.condoapp.bloc.chamados.enums.StatusChamado;
import com.condoapp.bloc.chamados.enums.UrgenciaChamado;
import com.condoapp.bloc.condominio.entity.Condominio;
import com.condoapp.bloc.morador.entity.Morador;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_chamado")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Chamado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chamadoId;

    @Column(updatable = false, unique = true, nullable = false)
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "condominio_id", nullable = false)
    private Condominio condominio;

    @ManyToOne
    @JoinColumn(name = "morador_id")
    private Morador morador;

    @Column(length = 60, nullable = false)
    private String nomeRequerente;
    @Column(length = 60, nullable = false)
    private String unidadeRequerente;
    @Column(nullable = false, length = 20)
    private String titulo;
    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false, name = "categoria_chamado", length = (30))
    @Enumerated(EnumType.STRING)
    private CategoriaChamado categoriaChamado;

    @Column(nullable = false, name = "urgencia_chamado", length = (30))
    @Enumerated(EnumType.STRING)
    private UrgenciaChamado urgenciaChamado;

    @Column(nullable = false, name = "status_chamado", length = (30))
    @Enumerated(EnumType.STRING)
    private StatusChamado statusChamado;

    @CreationTimestamp
    @Column(nullable = false, name = "criado_em")
    private LocalDateTime criadoEm;

    @Column(name = "resolvido_em")
    private LocalDateTime resolvidoEm;

}

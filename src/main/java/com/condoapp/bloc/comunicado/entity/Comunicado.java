package com.condoapp.bloc.comunicado.entity;

import com.condoapp.bloc.comunicado.enums.EscopoTipo;
import com.condoapp.bloc.condominio.entity.Condominio;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_comunicado")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comunicado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comunicadoId;

    @Column(updatable = false, unique = true, nullable = false)
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "condominio_id", nullable = false)
    private Condominio condominio;

    @Column(length = 60, nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String conteudo;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private EscopoTipo escopoTipo;
    private String escopoValor;

    private LocalDateTime expiraEm;

    @Column(nullable = false)
    private Boolean ativo;

    @CreationTimestamp
    private LocalDateTime criadoEm;
}

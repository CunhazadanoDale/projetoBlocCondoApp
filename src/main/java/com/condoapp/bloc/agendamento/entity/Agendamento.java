package com.condoapp.bloc.agendamento.entity;

import com.condoapp.bloc.agendamento.enums.StatusAgendamento;
import com.condoapp.bloc.condominio.entity.Condominio;
import com.condoapp.bloc.morador.entity.Morador;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_agendamento")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long agendamentoId;

    @Column(updatable = false, unique = true, nullable = false)
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "espaco_id", nullable = false)
    private Espaco espaco;

    @ManyToOne
    @JoinColumn(name = "morador_id")
    private Morador morador;

    @Column(length = 60, nullable = false)
    private String nomeResponsavel;

    @Column(length = 60, nullable = false)
    private String unidadeResponsavel;

    @Column(nullable = false)
    private LocalDateTime inicio;
    @Column(nullable = false)
    private LocalDateTime fim;

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private StatusAgendamento status;
    private String observacao;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime criadoEm;
}

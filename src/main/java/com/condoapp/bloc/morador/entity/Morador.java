package com.condoapp.bloc.morador.entity;

import com.condoapp.bloc.auth.entity.Conta;
import com.condoapp.bloc.condominio.entity.Condominio;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_morador")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Morador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long moradorId;

    @Column(unique = true, updatable = false, nullable = false)
    private UUID uuid;

    @Column(nullable = false, length = 60)
    private String nomeCompleto;

    @OneToOne
    @JoinColumn(name = "conta_id")
    private Conta conta;

    @ManyToOne
    @JoinColumn(name = "condominio_id", nullable = false)
    private Condominio condominio;

    private String unidade;

    private String bloco;

    private String telefone;

    @CreationTimestamp
    private LocalDateTime criadoEm;
}

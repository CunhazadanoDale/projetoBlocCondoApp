package com.condoapp.bloc.espaco.entity;

import com.condoapp.bloc.condominio.entity.Condominio;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "tb_espaco")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Espaco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long espacoId;

    @Column(unique = true, updatable = false, nullable = false)
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "condominio_id", nullable = false)
    private Condominio condominio;

    @Column(length = 60, nullable = false)
    private String nome;
    private String descricao;
    private Integer capacidade;

    @Column(name = "antecedencia_min_horas")
    private Integer antecedenciaMinHoras;

    @Column(name = "cancelamento_min_horas")
    private Integer cancelamentoMinHoras;

    @Column(name = "limite_reserva_semana")
    private Integer limiteReservaSemana;

    @Column(nullable = false)
    private boolean ativo;

    @Version
    @Column(name = "version", nullable = false)
    private Long version;
}

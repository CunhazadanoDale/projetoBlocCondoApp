package com.condoapp.bloc.chamados.entity;

import com.condoapp.bloc.chamados.enums.AutorRole;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_comentario_chamado")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ComentarioChamado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comentarioId;

    @ManyToOne
    @JoinColumn(name = "chamado_id", nullable = false)
    private Chamado chamado;

    @Column(length = 60, nullable = false, name = "autor_nome")
    private String autorNome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "autor_role", length = 30)
    private AutorRole autorRole;

    @Column(nullable = false)
    private String texto;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime criadoEm;
}

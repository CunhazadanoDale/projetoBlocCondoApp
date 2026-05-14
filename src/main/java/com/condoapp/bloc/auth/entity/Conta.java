package com.condoapp.bloc.auth.entity;

import com.condoapp.bloc.auth.enums.Role;
import com.condoapp.bloc.morador.entity.Morador;
import com.condoapp.bloc.prestador.entity.Prestador;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_conta")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contaId;

    @Column(nullable = false, unique = true, updatable = false)
    private UUID uuid;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String senhaHash;

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean ativo;

    @CreationTimestamp
    private LocalDateTime criadoEm;

    @OneToOne(mappedBy = "conta")
    private Morador morador;

    @OneToOne(mappedBy = "conta")
    private Prestador prestador;
}

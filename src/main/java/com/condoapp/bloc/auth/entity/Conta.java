package com.condoapp.bloc.auth.entity;

import com.condoapp.bloc.auth.enums.Role;
import com.condoapp.bloc.morador.entity.Morador;
import com.condoapp.bloc.prestador.entity.Prestador;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_conta")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Conta implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contaId;

    @Column(nullable = false, unique = true, updatable = false)
    private UUID uuid;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (role == null) return List.of();
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public @Nullable String getPassword() {
        return this.getSenhaHash();
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.isAtivo();
    }
}

package com.condoapp.bloc.condominio.entity;

import com.condoapp.bloc.util.embedded.Endereco;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_condominio")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Condominio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long condominioId;

    @Column(unique = true, nullable = false, updatable = false)
    private UUID uuid;

    @Column(nullable = false)
    @Size(max = 60)
    private String nomeCondominio;

    @Column(nullable = false, length = 14, unique = true)
    private String cnpjCondominio;

    @Embedded
    private Endereco endereco;

    private String telefoneCondominio;
    private String emailSindico;

    private String senhaAtual;
    private LocalDate senhaExpiraEm;

    private Boolean ativo;

    @CreationTimestamp
    private LocalDateTime criadoEm;

    @PrePersist
    private void gerarUuid() {
        if (uuid == null) {
            uuid = UUID.randomUUID();
        }
    }
}

package com.condoapp.bloc.pagamentos.entity;

import com.condoapp.bloc.pagamentos.enums.TipoCreditor;
import com.condoapp.bloc.prestador.entity.Contratacao;
import com.condoapp.bloc.prestador.entity.Prestador;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_movimentacao_saldo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovimentacaoSaldo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movimentacaoId;

    @ManyToOne
    @JoinColumn(name = "prestador_id", nullable = false)
    private Prestador prestador;

    @ManyToOne
    @JoinColumn(name = "contratacao_id")
    private Contratacao contratacao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "tipo_credito")
    private TipoCreditor tipoCredito;

    @Column(nullable = false)
    private BigDecimal valor;
    @Column(nullable = false)
    private String descricao;

    @Column(name = "id_evento_gateway", unique = true)
    private String idEventoGateway;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime criadoEm;
}

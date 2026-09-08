package com.condoapp.bloc.agendamento.repository;

import com.condoapp.bloc.agendamento.entity.Agendamento;
import com.condoapp.bloc.agendamento.enums.StatusAgendamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    Optional<Agendamento> findByUuid(UUID uuid);

    @Query("SELECT a FROM Agendamento a WHERE a.espaco.uuid = :espacoUUID AND a.status <> :status AND a.inicio < :fim AND a.fim > :inicio")
    List<Agendamento> findByDate(@Param("espacoUUID") UUID espacoUUID, StatusAgendamento status, LocalDateTime inicio, LocalDateTime fim);

    @Query("SELECT a FROM Agendamento a WHERE a.espaco.condominio.uuid = :condominioUUID AND a.status <> :status")
    Page<Agendamento> findAgendamentoByCondominioUUID(@Param("condominioUUID") UUID condominioUUID,
                                                      @Param("status") StatusAgendamento status, Pageable pageable);
}

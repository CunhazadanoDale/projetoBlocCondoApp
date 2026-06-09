package com.condoapp.bloc.agendamento.repository;

import com.condoapp.bloc.agendamento.entity.Agendamento;
import com.condoapp.bloc.agendamento.enums.StatusAgendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    Optional<Agendamento> findByUuid(UUID uuid);

    @Query("SELECT a FROM Agendamento a WHERE a.espaco.espacoId = :espacoId AND a.status <> :status AND a.inicio < :fim AND a.fim > :inicio")
    List<Agendamento> findByDate(Long espacoId, StatusAgendamento status, LocalDateTime inicio, LocalDateTime fim);
}

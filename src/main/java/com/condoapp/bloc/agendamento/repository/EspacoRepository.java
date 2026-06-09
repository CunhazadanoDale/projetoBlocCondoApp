package com.condoapp.bloc.agendamento.repository;

import com.condoapp.bloc.agendamento.entity.Espaco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EspacoRepository extends JpaRepository<Espaco, Long> {

    Optional<Espaco> findByUuid(UUID uuid);
}

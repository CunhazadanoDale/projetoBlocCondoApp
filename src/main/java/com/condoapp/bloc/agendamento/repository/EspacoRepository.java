package com.condoapp.bloc.agendamento.repository;

import com.condoapp.bloc.agendamento.entity.Espaco;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EspacoRepository extends JpaRepository<Espaco, Long> {

    Optional<Espaco> findByUuid(UUID uuid);

    @Query("SELECT e Espaco e WHERE e.condominio.condominio_id = :condominioID AND e.ativo = true")
    List<Espaco> findAllActiveByCondominioID(@Param("condominioID") Long condominioID);
}

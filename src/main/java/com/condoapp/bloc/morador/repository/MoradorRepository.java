package com.condoapp.bloc.morador.repository;

import com.condoapp.bloc.morador.entity.Morador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MoradorRepository extends JpaRepository<Morador, Long> {

    Optional<Morador> findByUUID(UUID uuid);
}

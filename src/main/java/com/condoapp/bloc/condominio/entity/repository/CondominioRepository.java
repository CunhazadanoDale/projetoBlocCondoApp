package com.condoapp.bloc.condominio.entity.repository;

import com.condoapp.bloc.condominio.entity.Condominio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CondominioRepository extends JpaRepository<Condominio, Long> {

    Optional<Condominio> findByUUID(UUID uuid);
}

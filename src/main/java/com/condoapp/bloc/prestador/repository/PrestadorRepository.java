package com.condoapp.bloc.prestador.repository;

import com.condoapp.bloc.prestador.entity.Prestador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PrestadorRepository extends JpaRepository<Prestador, Long> {

    Optional<Prestador> findByUUID(UUID uuid);
    boolean existsByCpfOuCnpj(String cpfOuCnpj);
}

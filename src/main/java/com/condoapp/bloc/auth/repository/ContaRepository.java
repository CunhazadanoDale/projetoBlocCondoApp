package com.condoapp.bloc.auth.repository;

import com.condoapp.bloc.auth.entity.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

    Optional<Conta> findByEmail(String email);
}

package com.condoapp.bloc.prestador.repository;

import com.condoapp.bloc.prestador.entity.Prestador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestadorRepository extends JpaRepository<Prestador, Long> {

    boolean existsByCpfOuCnpj(String cpfOuCnpj);
}

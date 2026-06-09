package com.condoapp.bloc.agendamento;

import com.condoapp.bloc.agendamento.repository.AgendamentoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

@SpringBootTest
@Testcontainers
@ActiveProfiles("test")
public class AgendamentoIntegrationsTest {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Container
    private static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16");

    @DynamicPropertySource
    private static void configureProperties(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }


    @Test
    public void agendamentoQueNaoExiste() {
        Long idInexistente = 99L;

        Optional<?> resultado = agendamentoRepository.findById(idInexistente);

        Assertions.assertTrue(resultado.isEmpty());
    }
}

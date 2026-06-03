package com.condoapp.bloc.agendamento;

import org.junit.jupiter.api.Test;


import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class ImplementadorTestesAgendamento {

    private final ValidadorDeInicioFim validadorDeInicioFim = new ValidadorDeInicioFim();


    @Test
    public void aDataDeveSerValida() {

        boolean resultado = validadorDeInicioFim
                .validaSeFimEstaAposInicio(LocalDateTime.parse("2026-02-04T00:00:00"), LocalDateTime.parse("2026-03-04T00:00:00"));

        assertThat(resultado).isTrue();
    }

    @Test
    public void aDataDeveSerInvalida() {

        boolean resultado = validadorDeInicioFim
                .validaSeFimEstaAposInicio(LocalDateTime.parse("2026-03-04T00:00:00"), LocalDateTime.parse("2026-02-04T00:00:00"));

        assertThat(resultado).isFalse();
    }

    @Test
    public void aDataEhIgual() {
        boolean resultado = validadorDeInicioFim
                .validaSeFimEstaAposInicio(LocalDateTime.parse("2026-03-04T00:00:00"), LocalDateTime.parse("2026-03-04T00:00:00"));

        assertThat(resultado).isFalse();
    }
}

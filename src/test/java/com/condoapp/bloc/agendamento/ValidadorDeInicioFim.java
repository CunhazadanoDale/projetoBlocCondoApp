package com.condoapp.bloc.agendamento;


import java.time.LocalDateTime;

public class ValidadorDeInicioFim {

    public boolean validaSeFimEstaAposInicio(LocalDateTime inicio, LocalDateTime fim) {
        if (inicio == null || fim == null ||inicio.isAfter(fim) || inicio.isEqual(fim)) {
            return false;
        }

        return true;
    }
}

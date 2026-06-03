package com.condoapp.bloc.agendamento;


import java.time.LocalDateTime;

public class ValidadorDeInicioFim {

    public boolean validaSeFimEstaAposInicio(LocalDateTime inicio, LocalDateTime fim) {
        return fim.isAfter(inicio) && fim != null && inicio != null;
    }
}

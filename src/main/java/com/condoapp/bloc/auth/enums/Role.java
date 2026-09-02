package com.condoapp.bloc.auth.enums;

public enum Role {
    MORADOR,
    PRESTADOR,
    SINDICO,
    ADMIN;

    public Boolean isSindico() {
        return this == Role.SINDICO;
    }
}


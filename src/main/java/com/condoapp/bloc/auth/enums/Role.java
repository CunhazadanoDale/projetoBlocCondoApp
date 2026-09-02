package com.condoapp.bloc.auth.enums;

public enum Role {
    MORADOR,
    PRESTADOR,
    SINDICO,
    ADMIN;

    public Boolean isSindicoOrAdmin() {
        return this == Role.ADMIN || this == Role.SINDICO;
    }
}


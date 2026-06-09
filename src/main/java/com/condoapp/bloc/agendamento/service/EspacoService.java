package com.condoapp.bloc.agendamento.service;

import com.condoapp.bloc.agendamento.entity.Espaco;

import java.util.List;

public interface EspacoService {

    List<Espaco> listarEspacos();
    Espaco criarEspaco(Espaco espaco);
    Espaco atualizarEspaco(Long espacoId, Espaco espaco);
    Espaco gradeDeDisponibilidadeEspaco(Long espacoId);
}

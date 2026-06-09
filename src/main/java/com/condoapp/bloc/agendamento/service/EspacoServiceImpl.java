package com.condoapp.bloc.agendamento.service;

import com.condoapp.bloc.agendamento.entity.Espaco;
import com.condoapp.bloc.agendamento.repository.EspacoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EspacoServiceImpl implements EspacoService {

    private final EspacoRepository espacoRepository;

    @Override
    public List<Espaco> listarEspacos() {
        return List.of();
    }

    @Override
    public Espaco criarEspaco(Espaco espaco) {
        return null;
    }

    @Override
    public Espaco atualizarEspaco(Long espacoId, Espaco espaco) {
        return null;
    }

    @Override
    public Espaco gradeDeDisponibilidadeEspaco(Long espacoId) {
        return null;
    }
}

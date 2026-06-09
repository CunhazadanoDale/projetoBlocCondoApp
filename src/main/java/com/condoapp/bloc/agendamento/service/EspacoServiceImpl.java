package com.condoapp.bloc.agendamento.service;

import com.condoapp.bloc.agendamento.entity.Agendamento;
import com.condoapp.bloc.agendamento.entity.Espaco;
import com.condoapp.bloc.agendamento.repository.EspacoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        return espacoRepository.save(espaco);
    }

    @Override
    public Espaco atualizarEspaco(Long espacoId, Espaco espaco) {
        Espaco espacoDoBancoDeDados = espacoRepository.findById(espacoId)
                .orElseThrow(() -> new RuntimeException("Espaco não encontrado"));

        espacoDoBancoDeDados.setDescricao(espaco.getDescricao());
        espacoDoBancoDeDados.setLimiteReservaSemana(espaco.getLimiteReservaSemana());
        espacoDoBancoDeDados.setVersion(espaco.getVersion());

        return espacoRepository.save(espacoDoBancoDeDados);
    }


}

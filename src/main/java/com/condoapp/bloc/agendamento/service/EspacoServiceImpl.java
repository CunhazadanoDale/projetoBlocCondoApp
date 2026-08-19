package com.condoapp.bloc.agendamento.service;

import com.condoapp.bloc.agendamento.dto.EspacoRequestDTO;
import com.condoapp.bloc.agendamento.dto.EspacoResponseDTO;
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
    public List<EspacoResponseDTO> listarEspacos() {
        return List.of();
    }

    @Override
    public EspacoResponseDTO criarEspaco(Espaco espaco) {
        return espacoRepository.save(espaco);
    }

    @Override
    public EspacoResponseDTO atualizarEspaco(Long espacoId, EspacoRequestDTO espaco) {
        Espaco espacoDoBancoDeDados = espacoRepository.findById(espacoId)
                .orElseThrow(() -> new RuntimeException("Espaco não encontrado"));

        espacoDoBancoDeDados.setDescricao(espaco.getDescricao());
        espacoDoBancoDeDados.setLimiteReservaSemana(espaco.getLimiteReservaSemana());

        return espacoRepository.save(espacoDoBancoDeDados);
    }


}

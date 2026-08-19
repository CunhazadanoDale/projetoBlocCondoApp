package com.condoapp.bloc.agendamento.service;

import com.condoapp.bloc.agendamento.dto.EspacoRequestDTO;
import com.condoapp.bloc.agendamento.dto.EspacoResponseDTO;
import com.condoapp.bloc.agendamento.entity.Agendamento;
import com.condoapp.bloc.agendamento.entity.Espaco;

import java.time.LocalDate;
import java.util.List;

public interface EspacoService {

    List<EspacoResponseDTO> listarEspacos(Long condominioID);
    EspacoResponseDTO criarEspaco(EspacoRequestDTO espaco);
    EspacoResponseDTO atualizarEspaco(Long espacoId, EspacoRequestDTO espaco);
}

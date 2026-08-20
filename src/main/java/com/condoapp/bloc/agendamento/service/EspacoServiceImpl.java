package com.condoapp.bloc.agendamento.service;

import com.condoapp.bloc.agendamento.dto.EspacoMapper;
import com.condoapp.bloc.agendamento.dto.EspacoRequestDTO;
import com.condoapp.bloc.agendamento.dto.EspacoResponseDTO;
import com.condoapp.bloc.agendamento.entity.Espaco;
import com.condoapp.bloc.agendamento.repository.EspacoRepository;
import com.condoapp.bloc.condominio.entity.Condominio;
import com.condoapp.bloc.condominio.repository.CondominioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EspacoServiceImpl implements EspacoService {

    private final EspacoRepository espacoRepository;
    private final CondominioRepository condominioRepository;

    @Override
    public List<EspacoResponseDTO> listarEspacos(Long condominioID) {

        List<Espaco> espacosAtivos = espacoRepository.findAllActiveByCondominioID(condominioID);

        return espacosAtivos.stream()
                .map(espaco -> EspacoMapper.fromEntityToResponse(espaco))
                .toList();
    }

    @Transactional
    @Override
    public EspacoResponseDTO criarEspaco(EspacoRequestDTO espaco) {
        if(espaco.getNome() == null || espaco.getNome().trim() == "") {
            throw new IllegalArgumentException("Nome do espaco vazio");
        }

        Condominio condominioFromDatabase = condominioRepository.findById(espaco.getCondominioID())
                .orElseThrow(() -> new RuntimeException("Condominio nao encontrado"));

        Espaco novoEspaco = Espaco.builder()
                .uuid(UUID.randomUUID())
                .condominio(condominioFromDatabase)
                .nome(espaco.getNome())
                .descricao(espaco.getDescricao())
                .capacidade(espaco.getCapacidade())
                .antecedenciaMinHoras(espaco.getMinHoras())
                .cancelamentoMinHoras(espaco.getCancelamentoMinHoras())
                .limiteReservaSemana(espaco.getLimiteReservaSemana())
                .build();


        return EspacoMapper.fromEntityToResponse(novoEspaco);
    }

    @Transactional
    @Override
    public EspacoResponseDTO atualizarEspaco(Long espacoId, EspacoRequestDTO espaco) {
        Espaco espacoDoBancoDeDados = espacoRepository.findById(espacoId)
                .orElseThrow(() -> new RuntimeException("Espaco não encontrado"));

        espacoDoBancoDeDados.setDescricao(espaco.getDescricao());
        espacoDoBancoDeDados.setLimiteReservaSemana(espaco.getLimiteReservaSemana());
        espacoDoBancoDeDados.setAntecedenciaMinHoras(espaco.getMinHoras());
        espacoDoBancoDeDados.setCancelamentoMinHoras(espaco.getCancelamentoMinHoras());
        espacoDoBancoDeDados.setCapacidade(espaco.getCapacidade());

        espacoRepository.save(espacoDoBancoDeDados);

        return EspacoMapper.fromEntityToResponse(espacoDoBancoDeDados);
    }


}

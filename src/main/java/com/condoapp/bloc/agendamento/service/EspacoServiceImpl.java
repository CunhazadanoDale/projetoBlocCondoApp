package com.condoapp.bloc.agendamento.service;

import com.condoapp.bloc.agendamento.dto.EspacoMapper;
import com.condoapp.bloc.agendamento.dto.EspacoRequestDTO;
import com.condoapp.bloc.agendamento.dto.EspacoResponseDTO;
import com.condoapp.bloc.agendamento.entity.Espaco;
import com.condoapp.bloc.agendamento.repository.EspacoRepository;
import com.condoapp.bloc.auth.entity.Conta;
import com.condoapp.bloc.auth.enums.Role;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EspacoServiceImpl implements EspacoService {

    private final EspacoRepository espacoRepository;

    @Override
    public List<EspacoResponseDTO> listarEspacos(UUID condominioUUID) {

        List<Espaco> espacosAtivos = espacoRepository.findAllActiveByCondominioUUID(condominioUUID);

        return espacosAtivos.stream()
                .map(espaco -> EspacoMapper.fromEntityToResponse(espaco))
                .toList();
    }

    @Transactional
    @Override
    public EspacoResponseDTO criarEspaco(EspacoRequestDTO espaco, Conta conta) {
        if (!validarSindico(conta)) {
            throw new RuntimeException("Não possui permissão para criar determinado espaço");
        }

        Espaco novoEspaco = Espaco.builder()
                .uuid(UUID.randomUUID())
                .condominio(conta.getMorador().getCondominio())
                .nome(espaco.getNome())
                .descricao(espaco.getDescricao())
                .capacidade(espaco.getCapacidade())
                .antecedenciaMinHoras(espaco.getMinHoras())
                .cancelamentoMinHoras(espaco.getCancelamentoMinHoras())
                .limiteReservaSemana(espaco.getLimiteReservaSemana())
                .build();

        espacoRepository.save(novoEspaco);


        return EspacoMapper.fromEntityToResponse(novoEspaco);
    }

    @Transactional
    @Override
    public EspacoResponseDTO atualizarEspaco(UUID espacoId, EspacoRequestDTO espaco,
                                             Conta conta) {

        if (!validarSindico(conta)) {
            throw new RuntimeException("Não possui autoridade para atualizar este espaço");
        }

        Espaco espacoDoBancoDeDados = espacoRepository.findByUuid(espacoId)
                .orElseThrow(() -> new RuntimeException("Espaco não encontrado"));

        if (!Objects.equals(
                conta.getMorador().getCondominio().getUuid(),
                espacoDoBancoDeDados.getCondominio().getUuid())) {
            throw new RuntimeException("Não possui ligação com este espaço");
        }

        espacoDoBancoDeDados.setDescricao(espaco.getDescricao());
        espacoDoBancoDeDados.setLimiteReservaSemana(espaco.getLimiteReservaSemana());
        espacoDoBancoDeDados.setAntecedenciaMinHoras(espaco.getMinHoras());
        espacoDoBancoDeDados.setCancelamentoMinHoras(espaco.getCancelamentoMinHoras());
        espacoDoBancoDeDados.setCapacidade(espaco.getCapacidade());

        espacoRepository.save(espacoDoBancoDeDados);

        return EspacoMapper.fromEntityToResponse(espacoDoBancoDeDados);
    }


    private boolean validarSindico(Conta conta) {
        boolean temAutoridade = conta.getAuthorities().stream()
                .map(auth -> Role.valueOf(auth.getAuthority().replace("ROLE_", "")))
                .anyMatch(Role::isSindico);

        return temAutoridade;
    }


}

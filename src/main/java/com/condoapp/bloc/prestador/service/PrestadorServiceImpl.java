package com.condoapp.bloc.prestador.service;

import com.condoapp.bloc.auth.entity.Conta;
import com.condoapp.bloc.auth.repository.ContaRepository;
import com.condoapp.bloc.prestador.dto.PrestadorMapper;
import com.condoapp.bloc.prestador.dto.PrestadorRequestDTO;
import com.condoapp.bloc.prestador.dto.PrestadorResponseDTO;
import com.condoapp.bloc.prestador.entity.Prestador;
import com.condoapp.bloc.prestador.enums.Status;
import com.condoapp.bloc.prestador.repository.PrestadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PrestadorServiceImpl implements PrestadorService{

    private final ContaRepository contaRepository;
    private final PrestadorRepository prestadorRepository;

    @Override
    @Transactional
    public PrestadorResponseDTO completarPerfil(Conta conta, PrestadorRequestDTO prestadorRequestDTO) {

        if (conta.getPrestador() != null) {
            throw new RuntimeException("Já existe prestador vinculado a esta conta");
        }

        boolean existePrestadorComCpfOuCnpj = prestadorRepository.existsByCpfOuCnpj(prestadorRequestDTO.getCpfOuCnpj());
        if (existePrestadorComCpfOuCnpj) {
            throw new RuntimeException("Já existe um prestador com essas credenciais");
        }

        Prestador prestador = Prestador.builder()
                .uuid(UUID.randomUUID())
                .nomeCompleto(prestadorRequestDTO.getNomeCompleto())
                .cpfOuCnpj(prestadorRequestDTO.getCpfOuCnpj())
                .telefone(prestadorRequestDTO.getTelefone())
                .descricao(prestadorRequestDTO.getDescricao())
                .status(Status.PENDENTE)
                .conta(conta)
                .build();

        Prestador prestadorSalvo = prestadorRepository.save(prestador);
        conta.setPrestador(prestadorSalvo);

        return PrestadorMapper.toPrivateResponseDTO(prestadorSalvo);
    }

    @Override
    public PrestadorResponseDTO buscarPorUUID(UUID uuid) {
        Prestador prestadorFromDB = prestadorRepository.findByUUID(uuid)
                .orElseThrow(() -> new RuntimeException("Nenhum prestador encontrado"));

        return PrestadorMapper.toPrivateResponseDTO(prestadorFromDB);
    }
}

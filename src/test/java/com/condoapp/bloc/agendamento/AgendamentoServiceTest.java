package com.condoapp.bloc.agendamento;

import com.condoapp.bloc.agendamento.entity.Agendamento;
import com.condoapp.bloc.agendamento.entity.Espaco;
import com.condoapp.bloc.agendamento.enums.StatusAgendamento;
import com.condoapp.bloc.agendamento.repository.AgendamentoRepository;
import com.condoapp.bloc.agendamento.service.AgendamentoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
public class AgendamentoServiceTest {

    @Mock
    private AgendamentoRepository agendamentoRepository;

    @InjectMocks
    private AgendamentoService agendamentoService;

    @Test
    public void deveAcharOAgendamento() {

        UUID uuiDdoTeste = UUID.randomUUID();

        Mockito.when(agendamentoRepository.findByUuid(uuiDdoTeste))
                .thenReturn(Optional.of(Agendamento.builder()
                        .agendamentoId(1L)
                        .uuid(uuiDdoTeste)
                        .espaco(Espaco.builder().build())
                        .nomeResponsavel("Cunha")
                        .unidadeResponsavel("Cuia")
                        .inicio(LocalDateTime.now())
                        .fim(LocalDateTime.now())
                        .status(StatusAgendamento.CONFIRMADO)
                        .observacao("")
                        .criadoEm(LocalDateTime.now())
                        .build())
                );

        Agendamento agendamentoPorUuid = agendamentoService.buscarPorUUID(uuiDdoTeste);

        assertThat(agendamentoPorUuid).isInstanceOf(Agendamento.class);
        assertThat(agendamentoPorUuid).isNotNull();
        assertThat(agendamentoPorUuid.getNomeResponsavel()).isEqualToIgnoringCase("cunha");
        assertThat(agendamentoPorUuid.getUuid()).isEqualTo(uuiDdoTeste);
    }

    @Test
    public void naoDeveEncontrarOAgendamento() {
        UUID uuidDoTeste = UUID.randomUUID();

        Mockito.when(agendamentoRepository.findByUuid(uuidDoTeste))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> agendamentoService.buscarPorUUID(uuidDoTeste)).isInstanceOf(RuntimeException.class)
                .hasMessage("agendamento não encontrado");
    }
}

package com.condoapp.bloc.agendamento.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConteudoPaginacao<T> {

    private List<T> content;
    private Integer pageNumber;
    private Integer pageSize;
    private Long totalElements;
    private Integer totalPages;
    private boolean lastPage;


    public static <T, E> ConteudoPaginacao<T> de(Page<E> paginaSpring, List<T> conteudoDto) {
        return ConteudoPaginacao.<T>builder()
                .content(conteudoDto)
                .pageNumber(paginaSpring.getNumber())
                .pageSize(conteudoDto.size())
                .totalElements(paginaSpring.getTotalElements())
                .totalPages(paginaSpring.getTotalPages())
                .lastPage(paginaSpring.isLast())
                .build();
    }
}

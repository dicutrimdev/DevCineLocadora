package com.domain.devcinelocadora.mapper;

import com.domain.devcinelocadora.dto.FilmeDTO;
import com.domain.devcinelocadora.entities.Filme;

public class FilmeMapper {
    public static FilmeDTO toDTO(Filme filme) {
        if (filme == null) {
            return null;
        }
        return FilmeDTO.builder()
                .id(filme.getId())
                .titulo(filme.getTitulo())
                .genero(filme.getGenero())
                .diretor(filme.getDiretor())
                .ano(filme.getAno())
                .estoque(filme.getEstoque())
                .lancamento(filme.getLancamento())
                .build();
    }

    public static Filme toEntity(FilmeDTO dto) {
        if (dto == null) {
            return null;
        }
        return Filme.builder()
                .id(dto.getId())
                .titulo(dto.getTitulo())
                .genero(dto.getGenero())
                .diretor(dto.getDiretor())
                .ano(dto.getAno())
                .estoque(dto.getEstoque())
                .lancamento(dto.getLancamento())
                .build();
    }
}

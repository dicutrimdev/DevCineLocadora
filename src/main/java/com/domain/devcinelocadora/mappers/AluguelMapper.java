package com.domain.devcinelocadora.mappers;

import com.domain.devcinelocadora.entities.Filme;
import com.domain.devcinelocadora.entities.Aluguel;
import com.domain.devcinelocadora.entities.Cliente;
import com.domain.devcinelocadora.dto.AluguelRequestDTO;
import com.domain.devcinelocadora.dto.AluguelResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class AluguelMapper {
    public static Aluguel toEntity(AluguelRequestDTO dto, Cliente cliente, List<Filme> filmes) {
        if (dto == null || cliente == null || filmes == null) {
            return null;
        }
        return Aluguel.builder().cliente(cliente).filmes(filmes).build();
    }

    public static AluguelResponseDTO toResponseDTO(Aluguel aluguel) {
        if (aluguel == null) {
            return null;
        }
        return AluguelResponseDTO.builder()
                .id(aluguel.getId())
                .cliente(ClienteMapper.toDTO(aluguel.getCliente()))
                .filmes(aluguel.getFilmes().stream()
                        .map(FilmeMapper::toDTO)
                        .collect(Collectors.toList()))
                .valor(aluguel.getValor())
                .dataAluguel(aluguel.getDataAluguel())
                .dataDevolucao(aluguel.getDataDevolucao())
                .build();
    }
}

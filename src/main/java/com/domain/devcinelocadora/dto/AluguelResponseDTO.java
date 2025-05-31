package com.domain.devcinelocadora.dto;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;
import java.time.LocalDate;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AluguelResponseDTO {
    private Long id;
    private ClienteDTO cliente;
    private List<FilmeDTO> filmes;
    private BigDecimal valor;
    private LocalDate dataAluguel;
    private LocalDate dataDevolucao;
}

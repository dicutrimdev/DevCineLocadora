package com.domain.devcinelocadora.dto;

import lombok.Data;
import lombok.Builder;

import java.util.List;
import java.time.LocalDate;
import java.math.BigDecimal;

@Data
@Builder
public class NotaAluguelDTO {
    private String clienteNome;
    private String clienteEmail;
    private LocalDate dataAluguel;
    private LocalDate dataDevolucao;
    private BigDecimal valorTotal;
    private List<ItemFilmeDTO> filmes;

    @Data
    @Builder
    public static class ItemFilmeDTO {
        private String titulo;
        private String genero;
        private String diretor;
    }
}

package com.domain.devcinelocadora.dto;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilmeDTO {
    private Long id;
    private String titulo;
    private String genero;
    private String diretor;
    private Integer ano;
    private Integer estoque;
    private Boolean lancamento;
}

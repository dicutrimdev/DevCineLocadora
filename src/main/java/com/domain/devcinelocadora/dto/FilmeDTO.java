package com.domain.devcinelocadora.dto;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilmeDTO {

    private Long id;

    @NotBlank(message = "O título é obrigatório")
    private String titulo;

    @NotBlank(message = "O gênero é obrigatório")
    private String genero;

    @NotBlank(message = "O diretor é obrigatório")
    private String diretor;

    @NotNull(message = "O ano é obrigatório")
    @Min(value = 1900, message = "Ano inválido")
    @Max(value = 2100, message = "Ano inválido")
    private Integer ano;

    @NotNull(message = "O estoque é obrigatório")
    @Min(value = 0, message = "O estoque não pode ser negativo")
    private Integer estoque;

    @NotNull(message = "O campo de lançamento é obrigatório")
    private Boolean lancamento;
}

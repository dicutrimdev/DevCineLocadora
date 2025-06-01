package com.domain.devcinelocadora.dto;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AluguelRequestDTO {

    @NotNull(message = "O ID do cliente é obrigatório")
    private Long clienteId;

    @NotNull(message = "A lista de IDs dos filmes é obrigatória")
    @Size(min = 1, message = "É necessário alugar pelo menos um filme")
    private List<Long> filmesIds;
}

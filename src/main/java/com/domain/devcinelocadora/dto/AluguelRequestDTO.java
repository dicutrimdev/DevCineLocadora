package com.domain.devcinelocadora.dto;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AluguelRequestDTO {
    private Long clienteId;
    private List<Long> filmesIds;
}

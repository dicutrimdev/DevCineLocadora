package com.domain.devcinelocadora.mappers;

import com.domain.devcinelocadora.dto.ClienteDTO;
import com.domain.devcinelocadora.entities.Cliente;

public class ClienteMapper {
    public static ClienteDTO toDTO(Cliente cliente) {
        if (cliente == null) {
            return null;
        }
        return ClienteDTO.builder()
                .id(cliente.getId())
                .cpf(cliente.getCpf())
                .nome(cliente.getNome())
                .email(cliente.getEmail())
                .telefone(cliente.getTelefone())
                .identidade(cliente.getIdentidade())
                .build();
    }

    public static Cliente toEntity(ClienteDTO dto) {
        if (dto == null) {
            return null;
        }
        return Cliente.builder()
                .id(dto.getId())
                .cpf(dto.getCpf())
                .nome(dto.getNome())
                .email(dto.getEmail())
                .telefone(dto.getTelefone())
                .identidade(dto.getIdentidade())
                .build();
    }
}

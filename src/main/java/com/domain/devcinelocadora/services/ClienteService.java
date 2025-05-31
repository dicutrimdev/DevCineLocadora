package com.domain.devcinelocadora.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.domain.devcinelocadora.dto.ClienteDTO;
import com.domain.devcinelocadora.entities.Cliente;
import com.domain.devcinelocadora.mappers.ClienteMapper;
import org.springframework.transaction.annotation.Transactional;
import com.domain.devcinelocadora.repositories.ClienteRepository;
import com.domain.devcinelocadora.exceptions.ClienteNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository clienteRepository;

    @Transactional
    public ClienteDTO criarCliente(ClienteDTO dto) {
        var cliente = ClienteMapper.toEntity(dto);
        var clienteSalvo = clienteRepository.save(cliente);
        return ClienteMapper.toDTO(clienteSalvo);
    }

    @Transactional(readOnly = true)
    public ClienteDTO buscarPorId(Long id) {
        var cliente = getClienteOrThrow(id);
        return ClienteMapper.toDTO(cliente);
    }

    @Transactional(readOnly = true)
    public List<ClienteDTO> listarTodos() {
        return clienteRepository.findAll().stream().map(ClienteMapper::toDTO).toList();
    }

    @Transactional
    public ClienteDTO atualizarCliente(Long id, ClienteDTO dto) {
        var clienteEncontrado = getClienteOrThrow(id);
        copyFromDto(dto, clienteEncontrado);
        var clienteAtualizado = clienteRepository.save(clienteEncontrado);
        return ClienteMapper.toDTO(clienteAtualizado);
    }

    @Transactional
    public void deletarCliente(Long id) {
        getClienteOrThrow(id);
        clienteRepository.deleteById(id);
    }

    private static void copyFromDto(ClienteDTO dto, Cliente clienteEncontrado) {
        clienteEncontrado.setNome(dto.getNome());
        clienteEncontrado.setEmail(dto.getEmail());
        clienteEncontrado.setTelefone(dto.getTelefone());
        clienteEncontrado.setIdentidade(dto.getIdentidade());
    }

    private Cliente getClienteOrThrow(Long id) {
        return clienteRepository.findById(id).orElseThrow(
                () -> new ClienteNotFoundException("Cliente não encontrado no sistema" + id)
        );
    }
}

package com.domain.devcinelocadora.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import com.domain.devcinelocadora.dto.ClienteDTO;
import org.springframework.web.bind.annotation.*;
import com.domain.devcinelocadora.services.ClienteService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteDTO> criarCliente(@RequestBody ClienteDTO dto) {
        var response = clienteService.criarCliente(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> buscarPorId(@PathVariable Long id) {
        var response = clienteService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listarTodos() {
        var response = clienteService.listarTodos();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> atualizarCliente(@PathVariable Long id,
                                                       @RequestBody ClienteDTO dto) {
        var response = clienteService.atualizarCliente(id, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        clienteService.deletarCliente(id);
        return ResponseEntity.noContent().build();
    }
}

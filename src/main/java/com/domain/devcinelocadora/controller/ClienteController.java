package com.domain.devcinelocadora.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.*;
import com.domain.devcinelocadora.dto.ClienteDTO;
import com.domain.devcinelocadora.services.ClienteService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@Tag(name = "Cliente",
        description = "Endpoints para gerenciamento de clientes na locadora")
@RequiredArgsConstructor

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    @Operation(summary = "Cadastrar um novo cliente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cliente cadastrado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos para o cliente")
    })
    public ResponseEntity<ClienteDTO> criarCliente(@Valid @RequestBody ClienteDTO dto) {
        var response = clienteService.criarCliente(dto);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Buscar cliente pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    public ResponseEntity<ClienteDTO> buscarPorId(
            @Parameter(description = "ID do cliente a ser buscado", example = "1") @PathVariable Long id) {
        var response = clienteService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }


    @GetMapping
    @Operation(summary = "Listar todos os clientes")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de clientes retornada com sucesso")
    })
    public ResponseEntity<List<ClienteDTO>> listarTodos() {
        var response = clienteService.listarTodos();
        return ResponseEntity.ok(response);
    }


    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um cliente pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos para atualização")
    })
    public ResponseEntity<ClienteDTO> atualizarCliente(@Parameter(description = "ID do cliente a ser atualizado", example = "1")
                                                       @PathVariable Long id,
                                                       @Valid @RequestBody ClienteDTO dto) {
        var response = clienteService.atualizarCliente(id, dto);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar um cliente pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Cliente deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    public ResponseEntity<Void> deletarCliente(
            @Parameter(description = "ID do cliente a ser deletado", example = "1") @PathVariable Long id) {
        clienteService.deletarCliente(id);
        return ResponseEntity.noContent().build();
    }
}

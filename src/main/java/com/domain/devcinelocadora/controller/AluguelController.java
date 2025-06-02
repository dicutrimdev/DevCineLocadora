package com.domain.devcinelocadora.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.domain.devcinelocadora.dto.NotaAluguelDTO;
import com.domain.devcinelocadora.dto.AluguelRequestDTO;
import com.domain.devcinelocadora.dto.AluguelResponseDTO;
import com.domain.devcinelocadora.services.AluguelService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@Tag(
        name = "Aluguel",
        description = "Endpoints para gerenciamento de aluguéis de filmes")
@RequiredArgsConstructor

@RestController
@RequestMapping("/api/alugueis")
public class AluguelController {

    private final AluguelService aluguelService;


    @PostMapping
    @Operation(summary = "Criar um novo aluguel de filmes")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Aluguel criado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente ou filme não encontrado"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos ou estoque insuficiente")
    })
    public ResponseEntity<AluguelResponseDTO> criarAluguel(@Valid @RequestBody AluguelRequestDTO dto) {
        var response = aluguelService.criarAluguel(dto);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Buscar aluguel pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Aluguel encontrado"),
            @ApiResponse(responseCode = "404", description = "Aluguel não encontrado")
    })
    public ResponseEntity<AluguelResponseDTO> buscarPorId(
            @Parameter(description = "ID do aluguel a ser buscado", example = "1") @PathVariable Long id) {
        var response = aluguelService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }


    @GetMapping
    @Operation(summary = "Listar todos os aluguéis")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de aluguéis retornada com sucesso")
    })
    public ResponseEntity<List<AluguelResponseDTO>> listarTodos() {
        var response = aluguelService.listarTodos();
        return ResponseEntity.ok(response);
    }


    @PatchMapping("/{id}/finalizar")
    public ResponseEntity<NotaAluguelDTO> finalizarAluguel(@PathVariable Long id) {
        var nota = aluguelService.finalizarAluguel(id);
        return ResponseEntity.ok(nota);
    }
}

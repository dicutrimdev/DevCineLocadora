package com.domain.devcinelocadora.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import com.domain.devcinelocadora.dto.FilmeDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import com.domain.devcinelocadora.services.FilmeService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@Tag(
        name = "Cliente",
        description = "Endpoints para gerenciamento de filmes na locadora")
@RequiredArgsConstructor

@RestController
@RequestMapping("/api/filmes")
public class FilmeController {

    private final FilmeService filmeService;

    @PostMapping
    @Operation(summary = "Cadastrar um novo filme")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Filme cadastrado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos para o filme")
    })
    public ResponseEntity<FilmeDTO> criarFilme(@Valid @RequestBody FilmeDTO dto) {
        var response = filmeService.criarFilme(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar filme pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Filme encontrado"),
            @ApiResponse(responseCode = "404", description = "Filme não encontrado")
    })
    public ResponseEntity<FilmeDTO> buscarPorId(@PathVariable Long id) {
        var response = filmeService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Listar todos os filmes")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de filmes retornada com sucesso")
    })
    public ResponseEntity<List<FilmeDTO>> listarTodos() {
        var response = filmeService.listarTodos();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um filme pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Filme atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Filme não encontrado"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos para atualização")
    })
    public ResponseEntity<FilmeDTO> atualizarFilme(@PathVariable Long id,
                                                   @Valid @RequestBody FilmeDTO dto) {
        var response = filmeService.atualizarFilme(id, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar um filme pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Filme deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Filme não encontrado")
    })
    public ResponseEntity<Void> deletarFilme(@PathVariable Long id) {
        filmeService.deletarFilme(id);
        return ResponseEntity.noContent().build();
    }
}

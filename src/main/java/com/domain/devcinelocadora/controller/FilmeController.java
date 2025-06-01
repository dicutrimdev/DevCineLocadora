package com.domain.devcinelocadora.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import com.domain.devcinelocadora.dto.FilmeDTO;
import org.springframework.web.bind.annotation.*;
import com.domain.devcinelocadora.services.FilmeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/filmes")
public class FilmeController {

    private final FilmeService filmeService;

    @PostMapping
    public ResponseEntity<FilmeDTO> criarFilme(@RequestBody FilmeDTO dto) {
        var response = filmeService.criarFilme(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmeDTO> buscarPorId(@PathVariable Long id) {
        var response = filmeService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<FilmeDTO>> listarTodos() {
        var response = filmeService.listarTodos();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FilmeDTO> atualizarFilme(@PathVariable Long id,
                                                   @RequestBody FilmeDTO dto) {
        var response = filmeService.atualizarFilme(id, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFilme(@PathVariable Long id) {
        filmeService.deletarFilme(id);
        return ResponseEntity.noContent().build();
    }
}

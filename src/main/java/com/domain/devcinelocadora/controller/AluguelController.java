package com.domain.devcinelocadora.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.domain.devcinelocadora.dto.AluguelRequestDTO;
import com.domain.devcinelocadora.dto.AluguelResponseDTO;
import com.domain.devcinelocadora.services.AluguelService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/alugueis")
public class AluguelController {

    private final AluguelService aluguelService;

    @PostMapping
    public ResponseEntity<AluguelResponseDTO> criarAluguel(@RequestBody AluguelRequestDTO dto) {
        var response = aluguelService.criarAluguel(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AluguelResponseDTO> buscarPorId(@PathVariable Long id) {
        var response = aluguelService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<AluguelResponseDTO>> listarTodos() {
        var response = aluguelService.listarTodos();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAluguel(@PathVariable Long id) {
        aluguelService.deletarAluguel(id);
        return ResponseEntity.noContent().build();
    }
}

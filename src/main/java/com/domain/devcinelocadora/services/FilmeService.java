package com.domain.devcinelocadora.services;

import com.domain.devcinelocadora.dto.FilmeDTO;
import com.domain.devcinelocadora.entities.Filme;
import com.domain.devcinelocadora.exceptions.FilmeNotFoundException;
import com.domain.devcinelocadora.mappers.FilmeMapper;
import com.domain.devcinelocadora.repositories.FilmeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmeService {
    private final FilmeRepository filmeRepository;

    @Transactional
    public FilmeDTO criarFilme(FilmeDTO dto) {
        var filme = FilmeMapper.toEntity(dto);
        var filmeSalvo = filmeRepository.save(filme);
        return FilmeMapper.toDTO(filmeSalvo);
    }

    @Transactional(readOnly = true)
    public FilmeDTO buscarPorId(Long id) {
        var filme = getFilmeOrThrow(id);
        return FilmeMapper.toDTO(filme);
    }

    @Transactional(readOnly = true)
    public List<FilmeDTO> listarTodos() {
        return filmeRepository.findAll().stream()
                .map(FilmeMapper::toDTO)
                .toList();
    }

    @Transactional
    public FilmeDTO atualizarFilme(Long id, FilmeDTO dto) {
        var filmeEncontrado = getFilmeOrThrow(id);
        copyFromDto(dto, filmeEncontrado);
        var filmeAtualizado = filmeRepository.save(filmeEncontrado);
        return FilmeMapper.toDTO(filmeAtualizado);
    }

    @Transactional
    public void deletarFilme(Long id) {
        getFilmeOrThrow(id);
        filmeRepository.deleteById(id);
    }

    private static void copyFromDto(FilmeDTO dto, Filme filmeEncontrado) {
        filmeEncontrado.setTitulo(dto.getTitulo());
        filmeEncontrado.setGenero(dto.getGenero());
        filmeEncontrado.setDiretor(dto.getDiretor());
        filmeEncontrado.setAno(dto.getAno());
        filmeEncontrado.setEstoque(dto.getEstoque());
        filmeEncontrado.setLancamento(dto.getLancamento());
    }

    private Filme getFilmeOrThrow(Long id) {
        return filmeRepository.findById(id)
                .orElseThrow(() -> new FilmeNotFoundException("Filme não encontrado com ID: " + id));
    }
}

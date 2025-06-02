package com.domain.devcinelocadora.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.domain.devcinelocadora.exceptions.*;
import com.domain.devcinelocadora.entities.Filme;
import com.domain.devcinelocadora.entities.Aluguel;
import com.domain.devcinelocadora.dto.NotaAluguelDTO;
import com.domain.devcinelocadora.mappers.AluguelMapper;
import com.domain.devcinelocadora.dto.AluguelRequestDTO;
import com.domain.devcinelocadora.dto.AluguelResponseDTO;
import com.domain.devcinelocadora.repositories.FilmeRepository;
import org.springframework.transaction.annotation.Transactional;
import com.domain.devcinelocadora.repositories.AluguelRepository;
import com.domain.devcinelocadora.repositories.ClienteRepository;

import java.util.List;
import java.time.LocalDate;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AluguelService {
    private final FilmeRepository filmeRepository;
    private final ClienteRepository clienteRepository;
    private final AluguelRepository aluguelRepository;


    private static final int PRAZO_PADRAO_DIAS = 5;
    private static final BigDecimal PRECO_POR_FILME = new BigDecimal("5.00");


    @Transactional
    public AluguelResponseDTO criarAluguel(AluguelRequestDTO dto) {
        var cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new ClienteNotFoundException("Cliente não encontrado"));

        List<Filme> filmes = filmeRepository.findAllById(dto.getFilmesIds());

        if (filmes.size() != dto.getFilmesIds().size()) {
            throw new FilmeNotFoundException("Um ou mais filmes não foram encontrados");
        }

        for (Filme filme : filmes) {
            if (filme.getEstoque() <= 0) {
                throw new EstoqueInsuficienteException("Filme indisponível: " + filme.getTitulo());
            }
            filme.setEstoque(filme.getEstoque() - 1);
        }

        BigDecimal valorTotal = PRECO_POR_FILME.multiply(BigDecimal.valueOf(filmes.size()));

        var aluguel = Aluguel.builder()
                .cliente(cliente)
                .filmes(filmes)
                .valor(valorTotal)
                .dataAluguel(LocalDate.now())
                .dataDevolucao(LocalDate.now().plusDays(PRAZO_PADRAO_DIAS))
                .build();

        var aluguelSalvo = aluguelRepository.save(aluguel);
        return AluguelMapper.toResponseDTO(aluguelSalvo);
    }


    @Transactional(readOnly = true)
    public AluguelResponseDTO buscarPorId(Long id) {
        Aluguel aluguel = getOrElseThrow(id);
        return AluguelMapper.toResponseDTO(aluguel);
    }


    @Transactional(readOnly = true)
    public List<AluguelResponseDTO> listarTodos() {
        return aluguelRepository.findAll()
                .stream()
                .map(AluguelMapper::toResponseDTO)
                .toList();
    }


    @Transactional
    public NotaAluguelDTO finalizarAluguel(Long aluguelId) {
        var aluguel = aluguelRepository.findById(aluguelId)
                .orElseThrow(() -> new ResourceNotFoundException("Aluguel não encontrado com ID: " + aluguelId));

        if (Boolean.TRUE.equals(aluguel.getFinalizado())) {
            throw new IllegalStateException("Este aluguel já foi finalizado.");
        }

        aluguel.setFinalizado(true);
        aluguel.setDataDevolucao(LocalDate.now());
        aluguelRepository.save(aluguel);

        for (Filme filme : aluguel.getFilmes()) {
            filme.setEstoque(filme.getEstoque() + 1);
            filmeRepository.save(filme);
        }
        return getNotaAluguelDTO(aluguel);
    }


    private static NotaAluguelDTO getNotaAluguelDTO(Aluguel aluguel) {
        return NotaAluguelDTO.builder()
                .clienteNome(aluguel.getCliente().getNome())
                .clienteEmail(aluguel.getCliente().getEmail())
                .dataAluguel(aluguel.getDataAluguel())
                .dataDevolucao(aluguel.getDataDevolucao())
                .valorTotal(aluguel.getValor())
                .filmes(aluguel.getFilmes().stream().map(filme ->
                        NotaAluguelDTO.ItemFilmeDTO.builder()
                                .titulo(filme.getTitulo())
                                .genero(filme.getGenero())
                                .diretor(filme.getDiretor())
                                .build()
                ).toList())
                .build();
    }


    private Aluguel getOrElseThrow(Long id) {
        return aluguelRepository.findById(id)
                .orElseThrow(() -> new AluguelNotFoundException("Aluguel não encontrado"));
    }
}

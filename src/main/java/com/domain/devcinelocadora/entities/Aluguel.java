package com.domain.devcinelocadora.entities;

import lombok.*;
import jakarta.persistence.*;

import java.util.List;
import java.time.LocalDate;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

@Entity
@Table(name = "tb_aluguel")
public class Aluguel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal valor;
    private LocalDate dataAluguel;
    private LocalDate dataDevolucao;

    @Column(nullable = false)
    private Boolean finalizado = false;

    @ManyToOne
    private Cliente cliente;

    @ManyToMany
    @JoinTable(name = "tb_aluguel_filme",
            joinColumns = @JoinColumn(name = "aluguel_id"),
            inverseJoinColumns = @JoinColumn(name = "filme_id"))
    private List<Filme> filmes;
}

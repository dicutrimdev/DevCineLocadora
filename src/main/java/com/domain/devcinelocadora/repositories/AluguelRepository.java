package com.domain.devcinelocadora.repositories;

import com.domain.devcinelocadora.entities.Aluguel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AluguelRepository extends JpaRepository<Aluguel, Long> {
}

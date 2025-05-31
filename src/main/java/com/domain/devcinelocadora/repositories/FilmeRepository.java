package com.domain.devcinelocadora.repositories;

import com.domain.devcinelocadora.entities.Filme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmeRepository extends JpaRepository<Filme, Long> {
}

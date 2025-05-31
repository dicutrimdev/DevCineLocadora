package com.domain.devcinelocadora.repositories;

import com.domain.devcinelocadora.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}

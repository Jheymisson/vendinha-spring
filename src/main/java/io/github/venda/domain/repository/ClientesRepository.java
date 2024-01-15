package io.github.venda.domain.repository;

import io.github.venda.domain.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClientesRepository extends JpaRepository<ClienteEntity, Integer> {

}

package io.github.venda.domain.repository;

import io.github.venda.domain.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutosRepository extends JpaRepository<ProdutoEntity, Integer> {

}

package io.github.venda.domain.repository;

import io.github.venda.domain.entity.ClienteEntity;
import io.github.venda.domain.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidosRepository extends JpaRepository<PedidoEntity, Integer> {

    List<PedidoEntity> findByCliente(ClienteEntity clienteEntity);

}

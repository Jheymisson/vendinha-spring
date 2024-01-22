package io.github.venda.domain.repository;

import io.github.venda.domain.entity.ClienteEntity;
import io.github.venda.domain.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PedidosRepository extends JpaRepository<PedidoEntity, Integer> {

    List<PedidoEntity> findByClienteEntity(ClienteEntity clienteEntity);

    @Query("SELECT P FROM PedidoEntity P LEFT JOIN FETCH P.itens WHERE P.id = :id")
    Optional<PedidoEntity> findByIdFetchItens(@Param("id") Integer id);

}

package io.github.venda.domain.repository;

import io.github.venda.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Clientes extends JpaRepository<Cliente, Integer> {

    @Query(value = "SELECT c FROM Cliente c WHERE c.nome like :nome")
    List<Cliente> encontrarPorNome1( @Param("nome") String nome);

    @Query(value = "SELECT * FROM cliente c WHERE c.nome like '%:nome%' ", nativeQuery = true)
    List<Cliente> encontrarPorNome2( @Param("nome") String nome);

    @Modifying
    void deleteByNome(String nome);

    @Modifying
    void deleteById(Integer id);

    @Query("SELECT c FROM Cliente c LEFT JOIN FETCH c.pedidos WHERE c.id = :id")
    Cliente findClienteFetchPedidos(@Param("id") Integer id);



}

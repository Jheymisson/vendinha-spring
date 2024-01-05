package io.github.venda.domain.repository;

import io.github.venda.domain.entity.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class Clientes {

    private static String SELECIONAR_TODOS = "SELECT * FROM Cliente ";

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public Cliente salvar(Cliente cliente){
        entityManager.persist(cliente);
        return cliente;
    }

    @Transactional
    public Cliente atualizar(Cliente cliente){
        entityManager.merge(cliente);
        return cliente;
    }

    @Transactional
    public void deletarPorNome(Cliente cliente){
        if (!entityManager.contains(cliente)) {
            cliente = entityManager.merge(cliente);
        }
        entityManager.remove(cliente);
        /*
            cliente = entityManager.contains(cliente) ? cliente : entityManager.merge(cliente);
            entityManager.remove(cliente);
         */
    }

    @Transactional
    public void deletarPorId(Integer id){
        Cliente cliente = entityManager.find(Cliente.class, id);
        deletarPorNome(cliente);
    }

    @Transactional(readOnly = true)
    public List<Cliente> buscarPorNome(String nome){
        String jpql = "SELECT c FROM Cliente c WHERE c.nome like :nome ";
        TypedQuery<Cliente> query = entityManager.createQuery(jpql, Cliente.class);
        query.setParameter("nome", "%"+nome+"%");
        return query.getResultList();
    }

    public List<Cliente> obterTodos(){
        return entityManager.createQuery("FROM Cliente ", Cliente.class).getResultList();
    }

}

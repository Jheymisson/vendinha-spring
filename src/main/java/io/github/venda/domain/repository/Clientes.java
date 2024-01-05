package io.github.venda.domain.repository;

import io.github.venda.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class Clientes {

    private static String INSERIR_CLIENTES = "INSERT INTO CLIENTE (nome) VALUES (?) ";
    private static String SELECIONAR_TODOS = "SELECT * FROM CLIENTE ";
    private static String ATUALIZAR_CLIENTE = "UPDATE CLIENTE SET nome = ? WHERE id = ? ";
    private static String EXCLUIR_CLIENTE = "DELETE FROM CLIENTE WHERE id = ? ";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Cliente salvar(Cliente cliente){
        jdbcTemplate.update( INSERIR_CLIENTES, new Object[]{cliente.getNome()} );
        return cliente;
    }

    public Cliente atualizar(Cliente cliente){
        jdbcTemplate.update(ATUALIZAR_CLIENTE, new Object[]{
                cliente.getNome(), cliente.getId()
        });
        return cliente;
    }

    public void deletar(Cliente cliente){
        deletar(cliente.getId());
    }

    public void deletar(Integer id){
        jdbcTemplate.update(EXCLUIR_CLIENTE, new Object[]{id});
    }

    public List<Cliente> buscarPorNome(String nome){
        return jdbcTemplate.query(SELECIONAR_TODOS.concat("WHERE nome like ?"),
                new Object[]{"%"+nome+"%"},
                obterClienteMapper());
    }

    public List<Cliente> obterTodos(){
        return jdbcTemplate.query(SELECIONAR_TODOS, obterClienteMapper());
    }

    private static RowMapper<Cliente> obterClienteMapper() {
        return new RowMapper<Cliente>() {
            @Override
            public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
                Integer id = rs.getInt("id");
                String nome = rs.getString("nome");
                return new Cliente(id, nome);
            }
        };
    }


}

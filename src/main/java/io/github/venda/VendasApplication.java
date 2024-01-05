package io.github.venda;

import io.github.venda.domain.entity.Cliente;
import io.github.venda.domain.repository.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class VendasApplication {

    @Bean
    public CommandLineRunner init(@Autowired Clientes clientes){
        return args -> {
            clientes.salvar(new Cliente("Jheymisson"));
            clientes.salvar(new Cliente("Julia"));
            clientes.salvar(new Cliente("Ludmilo"));

            List<Cliente> todosClientes = clientes.obterTodos();
            todosClientes.forEach(System.out::println);

            todosClientes.forEach(c -> {
                c.setNome(c.getNome() + " Silva.");
                clientes.atualizar(c);
            });

            clientes.buscarPorNome("dmi").forEach(System.out::println);

            clientes.obterTodos().forEach(c -> {
                clientes.deletarPorNome(c);
            });

            todosClientes = clientes.obterTodos();
            if(todosClientes.isEmpty()) {
                System.out.println("Nnehum cliente encontrado!");
            }
            else{
                todosClientes.forEach(System.out::println);
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }

}

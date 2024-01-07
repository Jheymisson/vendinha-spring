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
            clientes.save(new Cliente("Jheymisson"));
            clientes.save(new Cliente("Julia"));
            clientes.save(new Cliente("Ludmilo"));

            List<Cliente> todosClientes = clientes.findAll();
            todosClientes.forEach(System.out::println);

            List<Cliente> buscaPorNome1 = clientes.encontrarPorNome1("miss");
            buscaPorNome1.forEach(System.out::println);

            List<Cliente> buscaPorNome2 = clientes.encontrarPorNome2("dmi");
            buscaPorNome2.forEach(System.out::println);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }

}

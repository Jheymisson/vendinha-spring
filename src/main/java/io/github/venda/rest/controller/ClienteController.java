package io.github.venda.rest.controller;

import io.github.venda.domain.entity.Cliente;
import io.github.venda.domain.repository.Clientes;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private Clientes clientes;

    public ClienteController(Clientes clientes){
        this.clientes = clientes;
    }

    @GetMapping("/{id}")
    public Cliente buscarClienteById( @PathVariable Integer id ){
       return clientes.findById(id).orElseThrow( () ->
           new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente nao encontrado"));
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvarCliente( @RequestBody Cliente cliente){
        return clientes.save(cliente);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarCliente(@PathVariable Integer id) {
        clientes.findById(id).ifPresentOrElse(
                clientes::delete,
                () -> { throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"); }
        );
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity atualizarCliente(@PathVariable Integer id,
                                           @RequestBody Cliente cliente){
        return clientes.findById(id).map( clienteExistente -> {
            cliente.setId(clienteExistente.getId());
            clientes.save(cliente);
            return ResponseEntity.noContent().build();
        }).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente nao encontrado"));
    }

    @GetMapping("/")
    public List<Cliente> buscarCliente(Cliente filtro) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example exemplo = Example.of(filtro, matcher);
        return clientes.findAll(exemplo);
    }


}

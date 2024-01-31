package io.github.venda.rest.controller;

import io.github.venda.domain.entity.ClienteEntity;
import io.github.venda.domain.repository.ClientesRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private ClientesRepository clientesRepository;

    public ClienteController(ClientesRepository clientes){
        this.clientesRepository = clientes;
    }

    @GetMapping("/{id}")
    public ClienteEntity buscarClienteById(@PathVariable Integer id ){
       return clientesRepository.findById(id).orElseThrow( () ->
           new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente nao encontrado"));
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteEntity salvarCliente(@RequestBody @Valid ClienteEntity clienteEntity){
        return clientesRepository.save(clienteEntity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarCliente(@PathVariable Integer id) {
        clientesRepository.findById(id).ifPresentOrElse(
                clientesRepository::delete,
                () -> { throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"); }
        );
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity atualizarCliente(@PathVariable Integer id,
                                           @RequestBody @Valid ClienteEntity clienteEntity){
        return clientesRepository.findById(id).map(clienteEntityExistente -> {
            clienteEntity.setId(clienteEntityExistente.getId());
            clientesRepository.save(clienteEntity);
            return ResponseEntity.noContent().build();
        }).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente nao encontrado"));
    }

    @GetMapping("/")
    public List<ClienteEntity> buscarCliente(ClienteEntity filtro) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example exemplo = Example.of(filtro, matcher);
        return clientesRepository.findAll(exemplo);
    }


}

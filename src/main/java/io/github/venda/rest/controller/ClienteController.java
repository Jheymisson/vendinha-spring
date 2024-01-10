package io.github.venda.rest.controller;

import io.github.venda.domain.entity.Cliente;
import io.github.venda.domain.repository.Clientes;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/api/clientes")
public class ClienteController {

    private Clientes clientes;

    public ClienteController(Clientes clientes){
        this.clientes = clientes;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity buscarClienteById( @PathVariable Integer id ){
       Optional<Cliente> cliente = clientes.findById(id);
       if(cliente.isPresent()){
           return ResponseEntity.ok(cliente.get());
       }
       return ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    @ResponseBody
    public ResponseEntity salvarCliente( @RequestBody Cliente cliente){
        Cliente clienteSalvo = clientes.save(cliente);
        return ResponseEntity.ok(clienteSalvo);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity deletarCliente( @PathVariable Integer id ){
        Optional<Cliente> cliente = clientes.findById(id);
        if(cliente.isPresent()){
            clientes.delete( cliente.get() );
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}

package io.github.venda.rest.controller;

import io.github.venda.domain.entity.ProdutoEntity;
import io.github.venda.domain.repository.ProdutosRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private ProdutosRepository produtosRepository;

    public ProdutoController(ProdutosRepository produtosRepository){
        this.produtosRepository = produtosRepository;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ProdutoEntity buscarProdutoPorId(@PathVariable Integer id){
        return produtosRepository.findById(id).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }

    @GetMapping("/")
    public List<ProdutoEntity> buscarProduto(ProdutoEntity filtro) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example exemplo = Example.of(filtro, matcher);
        return produtosRepository.findAll(exemplo);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoEntity salvarProduto(@RequestBody @Valid ProdutoEntity produtoEntity){
        return produtosRepository.save(produtoEntity);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarProduto(@PathVariable Integer id, @Valid @RequestBody ProdutoEntity produtoEntity){
        produtosRepository.findById(id).map(p -> {
            produtoEntity.setId(p.getId());
            return produtosRepository.save(produtoEntity);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarProduto(@PathVariable Integer id){
        produtosRepository.findById(id).map(p -> {
            produtosRepository.delete(p);
            return Void.TYPE;
        }).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }

}

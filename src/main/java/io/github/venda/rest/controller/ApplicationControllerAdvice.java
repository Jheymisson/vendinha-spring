package io.github.venda.rest.controller;

import io.github.venda.exception.PedidoNaoEncontradoException;
import io.github.venda.exception.RegrasNegocioException;
import io.github.venda.rest.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RegrasNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleRegraNegocioException(RegrasNegocioException regrasNegocioException){
        String mensagemErro = regrasNegocioException.getMessage();
        return new ApiErrors(mensagemErro);
    }

    @ExceptionHandler(PedidoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handleRegraNegocioException(PedidoNaoEncontradoException pedidoNaoEncontradoException){
        return new ApiErrors(pedidoNaoEncontradoException.getMessage());
    }
}

package io.github.venda.rest.controller;

import io.github.venda.domain.entity.PedidoEntity;
import io.github.venda.domain.service.PedidoService;
import io.github.venda.rest.dto.PedidoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService){
        this.pedidoService = pedidoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer salvarPedido(@RequestBody PedidoDTO pedidoDTO){
        PedidoEntity pedidoEntity = pedidoService.salvar(pedidoDTO);
        return pedidoEntity.getId();
    }


}

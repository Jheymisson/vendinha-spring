package io.github.venda.rest.controller;

import io.github.venda.domain.entity.ItemPedidoEntity;
import io.github.venda.domain.entity.PedidoEntity;
import io.github.venda.domain.enums.StatusPedido;
import io.github.venda.domain.service.PedidoService;
import io.github.venda.rest.dto.AtualizacaoStatusPedidoDTO;
import io.github.venda.rest.dto.InformacoesItemPedidoDTO;
import io.github.venda.rest.dto.InformacoesPedidoDTO;
import io.github.venda.rest.dto.PedidoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("{id}")
    public InformacoesPedidoDTO buscarPedido(@PathVariable Integer id){
        return pedidoService
                .obterPedidoCompleto(id)
                .map( p -> converter1(p))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarStatus( @PathVariable Integer id,
                                 @RequestBody AtualizacaoStatusPedidoDTO atualizacaoStatusPedidoDTO){
        String novoStatus = atualizacaoStatusPedidoDTO.getNovoStatus();
        pedidoService.atualizaStatus(id, StatusPedido.valueOf(novoStatus));
    }

    private InformacoesPedidoDTO converter1(PedidoEntity pedidoEntity){
        return InformacoesPedidoDTO.builder()
                .codigo(pedidoEntity.getId())
                .dataPedido(pedidoEntity.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(pedidoEntity.getClienteEntity().getCpf())
                .nomeCliente(pedidoEntity.getClienteEntity().getNome())
                .total(pedidoEntity.getTotal())
                .status(pedidoEntity.getStatusPedido().name())
                .Itens(converter2(pedidoEntity.getItens()))
                .build();
    }


    private List<InformacoesItemPedidoDTO> converter2(List<ItemPedidoEntity> itens){
        if(CollectionUtils.isEmpty(itens)){
            return Collections.emptyList();
        }
        return itens.stream()
                .map( item ->
                        InformacoesItemPedidoDTO.builder().descricaoProduto(item.getProdutoEntity().getDescricao())
                                .precoUnitario(item.getProdutoEntity().getPreco())
                                .quantidade(item.getQuantidade()).build()).collect(Collectors.toList());
    }

}

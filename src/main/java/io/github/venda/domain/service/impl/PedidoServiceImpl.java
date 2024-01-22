package io.github.venda.domain.service.impl;

import io.github.venda.domain.entity.ClienteEntity;
import io.github.venda.domain.entity.ItemPedidoEntity;
import io.github.venda.domain.entity.PedidoEntity;
import io.github.venda.domain.entity.ProdutoEntity;
import io.github.venda.domain.enums.StatusPedido;
import io.github.venda.domain.repository.ClientesRepository;
import io.github.venda.domain.repository.PedidosRepository;
import io.github.venda.domain.repository.ProdutosRepository;
import io.github.venda.domain.service.PedidoService;
import io.github.venda.exception.RegrasNegocioException;
import io.github.venda.rest.dto.ItensPedidoDTO;
import io.github.venda.rest.dto.PedidoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidosRepository pedidosRepository;
    private final ClientesRepository clientesRepository;
    private final ProdutosRepository produtosRepository;

    @Override
    @Transactional
    public PedidoEntity salvar(PedidoDTO pedidoDTO) {
        Integer idCliente = pedidoDTO.getCliente();
        ClienteEntity clienteEntity = clientesRepository.findById(idCliente)
                .orElseThrow(() -> new RegrasNegocioException("Código de cliente inválido"));

        PedidoEntity pedidoEntity = new PedidoEntity();
        pedidoEntity.setTotal(pedidoDTO.getTotal());
        pedidoEntity.setDataPedido(LocalDate.now());
        pedidoEntity.setClienteEntity(clienteEntity);
        pedidoEntity.setStatusPedido(StatusPedido.REALIZADO);

        List<ItemPedidoEntity> itensPedido = converterItems(pedidoEntity, pedidoDTO.getItems());

        System.out.println("Itens do Pedido:");
        itensPedido.forEach(item -> System.out.println("ProdutoId: " + item.getProdutoEntity().getId() + ", Quantidade: " + item.getQuantidade()));

        pedidoEntity.setItens(itensPedido);
        PedidoEntity pedidoSalvo = pedidosRepository.save(pedidoEntity);

        System.out.println("Pedido salvo com sucesso: PedidoId=" + pedidoSalvo.getId());
        return pedidoSalvo;
    }

    @Override
    public Optional<PedidoEntity> obterPedidoCompleto(Integer id) {
        Optional<PedidoEntity> pedido = pedidosRepository.findByIdFetchItens(id);
        pedido.ifPresent(p -> System.out.println("Itens do pedido: " + p.getItens()));
        return pedido;
    }


    private List<ItemPedidoEntity> converterItems(PedidoEntity pedidoEntity, List<ItensPedidoDTO> itensPedidoDTOs) {
        if (itensPedidoDTOs.isEmpty()) {
            throw new RegrasNegocioException("Não é possível realizar um pedido sem itens.");
        }

        return itensPedidoDTOs.stream().map(dto -> {
            Integer idProduto = dto.getProduto();
            ProdutoEntity produtoEntity = produtosRepository.findById(idProduto)
                    .orElseThrow(() -> new RegrasNegocioException("Código de produto inválido"));

            ItemPedidoEntity itemPedido = new ItemPedidoEntity();
            itemPedido.setQuantidade(dto.getQuantidade());
            itemPedido.setPedidoEntity(pedidoEntity);
            itemPedido.setProdutoEntity(produtoEntity);
            return itemPedido;
        }).collect(Collectors.toList());
    }


}

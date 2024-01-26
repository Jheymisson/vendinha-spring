package io.github.venda.domain.service;

import io.github.venda.domain.entity.PedidoEntity;
import io.github.venda.domain.enums.StatusPedido;
import io.github.venda.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {

    PedidoEntity salvar(PedidoDTO pedidoDTO);

    Optional<PedidoEntity> obterPedidoCompleto(Integer id);

    void atualizaStatus(Integer id, StatusPedido statusPedido);

}

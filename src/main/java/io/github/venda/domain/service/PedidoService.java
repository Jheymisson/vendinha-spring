package io.github.venda.domain.service;

import io.github.venda.domain.entity.PedidoEntity;
import io.github.venda.rest.dto.PedidoDTO;

public interface PedidoService {

    PedidoEntity salvar(PedidoDTO pedidoDTO);

}

package io.github.venda.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "item_pedido")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private PedidoEntity pedidoEntity;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private ProdutoEntity produtoEntity;

    private Integer quantidade;


}

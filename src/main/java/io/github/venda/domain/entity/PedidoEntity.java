package io.github.venda.domain.entity;

import io.github.venda.domain.enums.StatusPedido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pedido")
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteEntity clienteEntity;

    private LocalDate dataPedido;

    @Column(name = "total", precision = 7, scale = 2)
    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusPedido statusPedido;

    @OneToMany(mappedBy = "pedidoEntity", cascade = CascadeType.ALL)
    private List<ItemPedidoEntity> itens;

    @Override
    public String toString() {
        return "PedidoEntity{" +
                "id=" + id +
                ", clienteEntity=" + (clienteEntity != null ? clienteEntity.getId() : null) +
                ", dataPedido=" + dataPedido +
                ", statusPedido=" + statusPedido +
                ", total=" + total +
                '}';
    }
}

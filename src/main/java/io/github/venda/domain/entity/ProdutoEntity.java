package io.github.venda.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "produto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Size(max = 200, message = "A descrição não pode ter mais de 200 caracteres.")
    @Column(name = "descricao", length = 200)
    @NotEmpty(message = "{campo.descricao.obrigatorio}")
    private String descricao;

    @DecimalMin(value = "0.01", message = "O preço deve ser maior que zero.")
    @Column(name = "preco", nullable = false)
    @NotNull(message = "{campo.preco.obrigatorio}")
    private BigDecimal preco;


}

package io.github.venda.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItensPedidoDTO {

    private Integer produto;
    private Integer quantidade;

}

package com.gelateria.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemVendaDTO {

    private Long produtoId;
    private String nomeProduto;
    private Double precoUnitario;
    private Integer quantidade;
}

package com.gelateria.demo.dtos;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProdutoDTO {
    private String nomeProduto;
    private Double preco;
    private Integer quantidade;
}

package com.gelateria.demo.returnDtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ReturnProdutoDTO {
    private Long produtoId;
    private String nomeProduto;
    private Double preco;
    private Integer quantidade;

}
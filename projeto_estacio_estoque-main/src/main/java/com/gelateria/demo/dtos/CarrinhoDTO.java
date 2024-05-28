package com.gelateria.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarrinhoDTO {



    private Map<Long, Integer> produtosQuantidade;

    public Map<Long, Integer> getProdutosQuantidade() {
        return produtosQuantidade;
    }

    public void setProdutosQuantidade(Map<Long, Integer> produtosQuantidade) {
        this.produtosQuantidade = produtosQuantidade;
    }
}

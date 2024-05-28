package com.gelateria.demo.returnDtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReturnCarrinhoDTO {

    private Long id;
    private List<ReturnProdutoDTO> produtos;
    private Double total;

}

package com.gelateria.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="tb_produto")
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "produtoId")
    private Long produtoId;
    @Column(name="nomeProduto", nullable = false)
    private String nomeProduto;
    @Column(name="precoUnitario", nullable = false)
    private Double preco;
    @Column(name="quantidade", nullable = false)
    private Integer quantidade;

}

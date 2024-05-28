package com.gelateria.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="tb_fornecedor")
public class Fornecedor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fornecedorId;
    @Column(name="nomeEmpresa")
    private String nome;
    @Column(name="cnpj", nullable = false)
    private String Cnpj;
    @Column(name="telefone")
    private String telefone;
    @Column(name="Endereco")
    private String endereco;

}

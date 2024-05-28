package com.gelateria.demo.dtos;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class FornecedorDTO {

    private String nome;
    private String Cnpj;
    private String telefone;
    private String endereco;
}

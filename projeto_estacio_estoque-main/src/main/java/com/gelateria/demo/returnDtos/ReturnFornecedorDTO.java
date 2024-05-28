package com.gelateria.demo.returnDtos;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ReturnFornecedorDTO {

    private Long fornecedorId;
    private String nome;
    private String Cnpj;
    private String telefone;
    private String endereco;
}

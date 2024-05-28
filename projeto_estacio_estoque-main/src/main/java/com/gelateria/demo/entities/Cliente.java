package com.gelateria.demo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="tb_cliente")
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="clienteId")
    private Long clienteId;
    @Column(name="nome", nullable = false)
    private String nome;
    @Column(name="telefone", nullable = false)
    private String telefone;
    @Column(name="Endereco", nullable = false)
    private String endereco;

}

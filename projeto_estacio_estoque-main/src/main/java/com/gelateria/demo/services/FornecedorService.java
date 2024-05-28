package com.gelateria.demo.services;

import com.gelateria.demo.entities.Fornecedor;
import com.gelateria.demo.repositories.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Transactional
    public Fornecedor criarFornecedor(Fornecedor fornecedor){
        if(fornecedor.getNome() == null || fornecedor.getNome().isEmpty()){
            throw  new RuntimeException("Fornecedor sem nome");
        }
        if(fornecedor.getCnpj().isEmpty()) {
            throw new RuntimeException("CNPJ é necessário para cadastro");
        }
        if(fornecedor.getEndereco().isEmpty()){
            throw new RuntimeException("Cadastro sem endereço");
        }
        if(fornecedor.getTelefone().isEmpty()){
            throw new RuntimeException("Telefone vazio");
        }

        return fornecedorRepository.save(fornecedor);
    }

    @Transactional(readOnly = true)
    public Fornecedor localizarFornecedorId(Long id){
        Fornecedor fornecedor = fornecedorRepository.getById(id);
        if(fornecedor == null){
            throw  new RuntimeException("Fornecedor não encontrado");
        }
        return fornecedor;
    }

    @Transactional
    public String excluirFornecedor(Long id){
        Fornecedor fornecedor = fornecedorRepository.getReferenceById(id);
        if(fornecedor != null)
            return "fornecedor não localizado";

        fornecedorRepository.delete(fornecedor);
        return "Fornecedor excluído";
    }

    @Transactional
    public Fornecedor alterarTelefone(Long id, String telefone){
        Fornecedor fornecedor = localizarFornecedorId(id);
        if(fornecedor == null){
            throw new RuntimeException("Fornecedor não encontrado");
        }
        fornecedor.setTelefone(telefone);
        return fornecedorRepository.save(fornecedor);
    }

    @Transactional
    public Fornecedor alterarEndereco(Long id, String endereco){
        Fornecedor fornecedor = localizarFornecedorId(id);
        if(fornecedor == null){
            throw new RuntimeException("Fornecedor não encontrado");
        }

        fornecedor.setEndereco(endereco);
        return fornecedorRepository.save(fornecedor);

    }
}

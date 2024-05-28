package com.gelateria.demo.services;

import com.gelateria.demo.entities.Cliente;
import com.gelateria.demo.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public Cliente criarCliente(Cliente cliente){
        if(cliente.getNome().isEmpty()){
            throw new RuntimeException("Cliente sem nome");
        }
        if(cliente.getEndereco().isEmpty()){
            throw  new RuntimeException("Cliente sem endereço");
        }
        if(cliente.getTelefone().isEmpty()){
            throw new RuntimeException("Cliente sem telefone");
        }
        return clienteRepository.save(cliente);
    }

    @Transactional(readOnly = true)
    public Cliente localizarClienteId(Long id){
        Cliente cliente = clienteRepository.getReferenceById(id);
        if(cliente == null){
            throw new RuntimeException("Cliente não localizado");
        }
        return cliente;
    }

    @Transactional
    public Cliente alterarTelefone(Long id, String telefone){
        Cliente cliente = clienteRepository.getReferenceById(id);
        if(cliente == null) {
            throw new RuntimeException("Cliente não localizado");
        }
        cliente.setTelefone(telefone);
        return clienteRepository.save(cliente);
    }

    @Transactional
    public Cliente alterarEndereco(Long id, String endereco){
        Cliente cliente = clienteRepository.getReferenceById(id);
        if(cliente == null) {
            throw new RuntimeException("Cliente não localizado");
        }
        cliente.setTelefone(endereco);
        return clienteRepository.save(cliente);
    }

    @Transactional
    public String deletarCliente(Long id){
        Cliente cliente = clienteRepository.getReferenceById(id);
        if(cliente == null){
            throw new RuntimeException("Cliente não encontrado");
        }
        clienteRepository.delete(cliente);
        return "Cliente deletado";
    }

}

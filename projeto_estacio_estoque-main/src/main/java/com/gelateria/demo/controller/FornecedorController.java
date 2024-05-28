package com.gelateria.demo.controller;

import com.gelateria.demo.dtos.EnderecoDTO;
import com.gelateria.demo.dtos.FornecedorDTO;
import com.gelateria.demo.dtos.TelefoneDTO;
import com.gelateria.demo.entities.Fornecedor;
import com.gelateria.demo.entities.Produto;
import com.gelateria.demo.returnDtos.ReturnFornecedorDTO;
import com.gelateria.demo.returnDtos.ReturnProdutoDTO;
import com.gelateria.demo.services.FornecedorService;
import com.gelateria.demo.services.ProdutoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/fornecedor")
public class FornecedorController {

    @Autowired
    private FornecedorService fornecedorService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/criar")
    public ResponseEntity<ReturnFornecedorDTO> criarFornecedor(@RequestBody FornecedorDTO fornecedorDTO){
        Fornecedor fornecedor = modelMapper.map(fornecedorDTO, Fornecedor.class);
        fornecedorService.criarFornecedor(fornecedor);
        ReturnFornecedorDTO returnFornecedorDTO = modelMapper.map(fornecedor, ReturnFornecedorDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnFornecedorDTO);
    }

    @GetMapping("getFornecedor/{id}")
    public ResponseEntity<ReturnFornecedorDTO> recuperarFornecedorId(@PathVariable long id){
        Fornecedor fornecedor = fornecedorService.localizarFornecedorId(id);
        ReturnFornecedorDTO returnFornecedorDTO = modelMapper.map(fornecedor, ReturnFornecedorDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(returnFornecedorDTO);

    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<String> excluirFornecedor(@PathVariable Long id){
        try {
            String mensagem = fornecedorService.excluirFornecedor(id);
            return ResponseEntity.ok(mensagem);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/alterarTelefone/{id}")
    public ResponseEntity<ReturnFornecedorDTO> alterarTelefone(@PathVariable Long id, @RequestBody TelefoneDTO telefoneDTO){
        Fornecedor fornecedor = fornecedorService.alterarTelefone(id, telefoneDTO.getTelefone());
        ReturnFornecedorDTO returnFornecedorDTO = modelMapper.map(fornecedor, ReturnFornecedorDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(returnFornecedorDTO);
    }

    @PutMapping("/alterarEndereco/{id}")
    public ResponseEntity<ReturnFornecedorDTO> alterarEndereco(@PathVariable Long id, @RequestBody EnderecoDTO enderecoDTO){
        Fornecedor fornecedor = fornecedorService.alterarEndereco(id, enderecoDTO.getEndereco());
        ReturnFornecedorDTO returnFornecedorDTO = modelMapper.map(fornecedor, ReturnFornecedorDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(returnFornecedorDTO);
    }


}

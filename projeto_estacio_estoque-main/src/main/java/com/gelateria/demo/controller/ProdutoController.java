package com.gelateria.demo.controller;
import com.gelateria.demo.dtos.CarrinhoDTO;
import com.gelateria.demo.dtos.PrecoDTO;
import com.gelateria.demo.dtos.ProdutoDTO;
import com.gelateria.demo.entities.Carrinho;
import com.gelateria.demo.entities.ItemCarrinho;
import com.gelateria.demo.entities.Produto;
import com.gelateria.demo.repositories.ProdutoRepository;
import com.gelateria.demo.returnDtos.ReturnProdutoDTO;
import com.gelateria.demo.returnDtos.ReturnVendaDTO;
import com.gelateria.demo.services.ProdutoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired private ProdutoRepository produtoRepository;

    @PostMapping(value = "/criar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReturnProdutoDTO> criarProduto(@RequestBody ProdutoDTO produtoDTO) {
        Produto produto = modelMapper.map(produtoDTO, Produto.class);
        produtoService.criarProduto(produto);
        ReturnProdutoDTO returnProdutoDTO = modelMapper.map(produto, ReturnProdutoDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnProdutoDTO);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReturnProdutoDTO> alterarPreco(@PathVariable Long id, @RequestBody PrecoDTO precoDTO) {
        Produto produtoAtualizado = produtoService.alterarPreco(id, precoDTO.getPreco());
        ReturnProdutoDTO returnProdutoDTO = modelMapper.map(produtoAtualizado, ReturnProdutoDTO.class);
        return ResponseEntity.ok(returnProdutoDTO);
    }

    @GetMapping
    public ResponseEntity<List<ReturnProdutoDTO>> buscarTodosProdutos() {
        List<Produto> produtos = produtoService.localizarTodosProdutos();
        List<ReturnProdutoDTO> returnProdutoDTOs = produtos.stream()
                .map(produto -> modelMapper.map(produto, ReturnProdutoDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(returnProdutoDTOs);
    }

    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<ReturnProdutoDTO> buscarProdutoId(@PathVariable Long id) {
        Produto produto = produtoService.localizarProdutoId(id);
        ReturnProdutoDTO returnProdutoDTO = modelMapper.map(produto, ReturnProdutoDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(returnProdutoDTO);
    }

    @PostMapping("/venda")
    public ResponseEntity<ReturnVendaDTO> vendaProduto(@RequestBody CarrinhoDTO carrinhoDTO) {
        ReturnVendaDTO returnVendaDTO = produtoService.realizarVenda(carrinhoDTO);
        return ResponseEntity.ok(returnVendaDTO);
    }

}

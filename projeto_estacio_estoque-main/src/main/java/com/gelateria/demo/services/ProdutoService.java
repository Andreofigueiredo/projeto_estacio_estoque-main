package com.gelateria.demo.services;

import com.gelateria.demo.dtos.CarrinhoDTO;
import com.gelateria.demo.dtos.ItemVendaDTO;
import com.gelateria.demo.entities.Carrinho;
import com.gelateria.demo.entities.ItemCarrinho;
import com.gelateria.demo.entities.Produto;
import com.gelateria.demo.repositories.CarrinhoRepository;
import com.gelateria.demo.repositories.ItemCarrinhoRepository;
import com.gelateria.demo.repositories.ProdutoRepository;
import com.gelateria.demo.returnDtos.ReturnVendaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Autowired
    private ItemCarrinhoRepository itemCarrinhoRepository;


    @Transactional
    public Produto criarProduto(Produto produto){

        if(produto.getNomeProduto().isEmpty()){
            throw new RuntimeException("nome não foi enviado");
        }
        if(produto.getPreco() == null || produto.getPreco() <=0){
            throw new RuntimeException("Produto sem preço");
        }
        return produtoRepository.save(produto);
    }

    @Transactional(readOnly = true)
    public Produto localizarProdutoId(Long id){
        Produto produto = produtoRepository.getById(id);
        if(produto == null || produto.getPreco().isNaN()){
            throw new RuntimeException("Produto não localizado");
        }
        return produto;
    }

    @Transactional(readOnly = true)
    public List<Produto> localizarTodosProdutos(){

        List<Produto> produtos = produtoRepository.findAll();
        return produtos;

    }

    @Transactional
    public Produto alterarPreco(Long id, Double preco) {
        Produto produto = localizarProdutoId(id);

        if (produto != null) {
            produto.setPreco(preco);
            produtoRepository.save(produto);
        } else {
            throw new RuntimeException("Produto não encontrado com o ID: " + id);
        }

        return produto;
    }

    @Transactional
    public ReturnVendaDTO realizarVenda(CarrinhoDTO carrinhoDTO) {
        Carrinho carrinho = new Carrinho();
        carrinho.setItensCarrinho(new ArrayList<>());

        double total = 0.0; // Inicializa o total como 0.0
        List<ItemVendaDTO> itensVenda = new ArrayList<>();

        for (Map.Entry<Long, Integer> entry : carrinhoDTO.getProdutosQuantidade().entrySet()) {
            Long idProduto = entry.getKey();
            Integer quantidade = entry.getValue();

            Produto produto = produtoRepository.findById(idProduto)
                    .orElseThrow(() -> new RuntimeException("Produto com ID " + idProduto + " não encontrado"));

            ItemCarrinho item = new ItemCarrinho();
            item.setProduto(produto);
            item.setQuantidade(quantidade);

            // Associe o item ao carrinho
            item.setCarrinho(carrinho);

            carrinho.getItensCarrinho().add(item);

            // Adicione o preço do produto multiplicado pela quantidade ao total
            total += produto.getPreco() * quantidade;

            // Adicione o item à lista de itens de venda
            itensVenda.add(new ItemVendaDTO(produto.getProdutoId(), produto.getNomeProduto(), produto.getPreco(), quantidade));
        }

        // Defina o total do carrinho após adicionar os itens
        carrinho.setTotal(total);

        // Salvar o carrinho
        Carrinho carrinhoSalvo = carrinhoRepository.save(carrinho);

        // Criar e retornar o DTO de retorno da venda com o total da compra
        ReturnVendaDTO returnVendaDTO = new ReturnVendaDTO();
        returnVendaDTO.setId(carrinhoSalvo.getCarrinhoId());
        returnVendaDTO.setTotal(carrinhoSalvo.getTotal());
        returnVendaDTO.setItensVenda(itensVenda);

        return returnVendaDTO;
    }
}

package com.gelateria.demo.config;
import com.gelateria.demo.dtos.FornecedorDTO;
import com.gelateria.demo.dtos.ProdutoDTO;
import com.gelateria.demo.entities.Fornecedor;
import com.gelateria.demo.entities.Produto;
import com.gelateria.demo.returnDtos.ReturnFornecedorDTO;
import com.gelateria.demo.returnDtos.ReturnProdutoDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true);

        // Mapeamento de Produto para ProdutoDTO
        modelMapper.createTypeMap(Produto.class, ProdutoDTO.class);
        // Mapeamento de ProdutoDTO para Produto
        modelMapper.createTypeMap(ProdutoDTO.class, Produto.class);
        // Mapeamento de Produto para ReturnProdutoDTO
        modelMapper.createTypeMap(Produto.class, ReturnProdutoDTO.class);

        // Fornecedor

        modelMapper.createTypeMap(Fornecedor.class, ReturnFornecedorDTO.class);
        modelMapper.createTypeMap(Fornecedor.class, Fornecedor.class);
        modelMapper.createTypeMap(FornecedorDTO.class, Fornecedor.class);


        return modelMapper;
    }
}

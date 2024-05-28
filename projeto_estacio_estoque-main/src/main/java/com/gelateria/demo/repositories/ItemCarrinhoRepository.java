package com.gelateria.demo.repositories;

import com.gelateria.demo.entities.ItemCarrinho;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemCarrinhoRepository extends JpaRepository<ItemCarrinho,Long> {
}

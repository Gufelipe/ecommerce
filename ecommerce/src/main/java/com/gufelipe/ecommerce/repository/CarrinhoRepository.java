package com.gufelipe.ecommerce.repository;

import com.gufelipe.ecommerce.entity.Carrinho;
import com.gufelipe.ecommerce.entity.Produto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class CarrinhoRepository {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final Set<Carrinho> carrinhos = new HashSet<>();

    public void removerDoCarrinho(Long idCliente, Produto produto) {
        Carrinho carrinho = consultarCarrinho(idCliente);
    }

    public void adicionarCarrinho(Carrinho carrinho) {
        carrinhos.add(carrinho);
    }

    public Carrinho consultarCarrinho(Long idCliente) {
        for (Carrinho carrinho : carrinhos) {
            if (carrinho.pertenceAoCliente(idCliente)) {
                return carrinho;
            }
        }
        return null;
    }

    public Set<Carrinho> listarTodosCarrinhos() {
        return carrinhos;
    }
}

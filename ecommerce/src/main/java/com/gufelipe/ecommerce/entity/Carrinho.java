package com.gufelipe.ecommerce.entity;

import com.gufelipe.ecommerce.exception.ApplicationException;
import org.springframework.http.HttpStatus;

import java.util.HashSet;
import java.util.Set;

public class Carrinho {

    private Cliente cliente;
    private Set<Produto> produtos = new HashSet<>();

    public Carrinho(Cliente cliente) {
        this.cliente = cliente;
    }

    public void adicionarProduto(Produto produto) {
        Produto produtoJaAdicionado = consultaProduto(produto.getId());
        if (produtoJaAdicionado == null) {
            produtos.add(produto);
        }
        throw new ApplicationException(
                "Produto já adicionado no carrinho",
                "PRODUTO_JA_ADICIONADO",
                HttpStatus.CONFLICT);
    }

    private Produto consultaProduto(Long idProduto) {
        for (Produto produto : produtos) {
            if (produto.getId().equals(idProduto)) {
                return produto;
            }
        }
        return null;
    }

    public Boolean pertenceAoCliente(Long idCliente) {
        return cliente.getId().equals(idCliente);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Set<Produto> getProdutos() {
        return produtos;
    }

    @Override
    public String toString() {
        return "Carrinho{" +
                "cliente=" + cliente +
                ", produtos=" + produtos +
                '}';
    }
}

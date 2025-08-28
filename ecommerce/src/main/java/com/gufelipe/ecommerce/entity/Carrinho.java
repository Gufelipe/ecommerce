package com.gufelipe.ecommerce.entity;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Carrinho {

    private final Long id = new Random().nextLong();
    private Cliente cliente;
    private Set<Produto> produtos = new HashSet<>();

    public Carrinho(Cliente cliente) {
        this.cliente = cliente;
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    public void removerProduto(Produto produto){
        produtos.remove(produto);
    }

    public Boolean pertenceAoCliente(Long idCliente) {
        return cliente.getId().equals(idCliente);
    }

    public void limparCarrinho() {
        this.produtos = new HashSet<>();
    }

    public Long getId() {
        return id;
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

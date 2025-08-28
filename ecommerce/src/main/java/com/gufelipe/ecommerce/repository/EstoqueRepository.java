package com.gufelipe.ecommerce.repository;

import com.gufelipe.ecommerce.entity.Produto;
import com.gufelipe.ecommerce.exception.ProdutoNaoEncontradoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public class EstoqueRepository {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final List<Produto> estoqueRepository = new ArrayList<>();

    public Produto consultarEstoque(String titulo) {
        for (Produto produto : estoqueRepository) {
            if (produto.getTitulo().equals(titulo)) {
                return produto;
            }
        }
        return null;
    }

    public Produto consultarEstoque(Long idProduto) {
        for (Produto produto : estoqueRepository) {
            if (produto.getId().equals(idProduto)) {
                return produto;
            }
        }
        return null;
    }

    public void inserirItem(Produto produto) {
        estoqueRepository.add(produto);
    }

    public Integer contarItens(String titulo) {
        Produto produtoEncontrado = consultarEstoque(titulo);

        if (produtoEncontrado == null) {
            return 0;
        }

        return produtoEncontrado.getQtdEstoque();
    }

    public Integer totalItens() {
        return estoqueRepository.size();
    }

    public void removerUmExemplar(Long idProduto) {
        estoqueRepository.removeIf(produto -> produto.getId().equals(idProduto));
    }

    public void removerItem(Long idProduto) {
        Produto produtoEncontrado = consultarEstoque(idProduto);
        estoqueRepository.remove(produtoEncontrado);
    }

    public List<Produto> listarProdutos() {
        return estoqueRepository;
    }

    public void atualizarProduto(Produto produto) {
        Produto produtoEncontrado = consultarEstoque(produto.getId());

        if (produtoEncontrado == null) {
            var errMsg = "Erro ao atualizar o produto= " + produto;
            throw new ProdutoNaoEncontradoException(errMsg, "PRODUTO_NAO_ENCONTRADO");
        }
        produtoEncontrado.setTitulo(produto.getTitulo());
        produtoEncontrado.setDescricao(produto.getDescricao());
        produtoEncontrado.setPreco(produto.getPreco());
        produtoEncontrado.setQtdEstoque(produto.getQtdEstoque());
    }

    public void darBaixaNosProdutos(Set<Produto> produtosParaDarBaixa) {
        for (Produto produto : produtosParaDarBaixa) {
            Produto produtoEncontrado = consultarEstoque(produto.getId());

            if (produtoEncontrado == null) {
                throw new RuntimeException("Produto não disponível para venda idProduto=" + produto.getId());
            }

            removerUmExemplar(produto.getId());
        }
    }
}
package com.gufelipe.ecommerce;

import com.gufelipe.ecommerce.modelo.Produto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

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
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto com ID " + idProduto + " não encontrado");
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

    public void removerUmExemplar(Produto produto) {
        Produto produtoEncontrado = consultarEstoque(produto.getTitulo());

        if (produtoEncontrado == null)
            return;

        produtoEncontrado.removerItens(1);
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

        if (produtoEncontrado == null){
            return;
        }

        int index = estoqueRepository.indexOf(produtoEncontrado);
        estoqueRepository.set( index , produto );
    }
}
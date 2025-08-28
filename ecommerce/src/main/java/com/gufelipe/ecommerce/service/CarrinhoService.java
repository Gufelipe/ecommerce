package com.gufelipe.ecommerce.service;

import com.gufelipe.ecommerce.entity.Carrinho;
import com.gufelipe.ecommerce.entity.Cliente;
import com.gufelipe.ecommerce.entity.Produto;
import com.gufelipe.ecommerce.exception.ApplicationException;
import com.gufelipe.ecommerce.exception.ClienteNaoEncontradoException;
import com.gufelipe.ecommerce.repository.CarrinhoRepository;
import com.gufelipe.ecommerce.repository.ClienteRepository;
import com.gufelipe.ecommerce.repository.EstoqueRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;

@Service
public class CarrinhoService {

    private final ClienteRepository clienteRepository;
    private final CarrinhoRepository carrinhoRepository;
    private final EstoqueRepository estoqueRepository;

    public CarrinhoService(
            ClienteRepository clienteRepository,
            CarrinhoRepository carrinhoRepository,
            EstoqueRepository estoqueRepository
    ) {
        this.clienteRepository = clienteRepository;
        this.carrinhoRepository = carrinhoRepository;
        this.estoqueRepository = estoqueRepository;
    }


    private Carrinho criarCarrinhoParaCliente(Long idCliente) {
        Cliente cliente = clienteRepository.consultarCliente(idCliente);
        if (cliente == null) {
            throw new ClienteNaoEncontradoException(
                    "Cliente não encontrado para o id= " + idCliente,
                    "CLIENTE_NAO_ENCONTRADO");
        }
        return new Carrinho(cliente);
    }


    public void adicionarNoCarrinho(Long idCliente, Produto produto) {
        validarExistenciaDoProduto(produto);

        Carrinho carrinhoDoCliente = carrinhoRepository.consultarCarrinhoDeCliente(idCliente);
        if (carrinhoDoCliente == null) {
            carrinhoDoCliente = criarCarrinhoParaCliente(idCliente);
        }

        carrinhoDoCliente.adicionarProduto(produto);
        carrinhoRepository.adicionarCarrinho(carrinhoDoCliente);
    }

    private void validarExistenciaDoProduto(Produto produto) {
        Produto produtoEncontrado = estoqueRepository.consultarEstoque(produto.getId());

        if (produtoEncontrado == null) {
            throw new ApplicationException(
                    "Produto encontrado com o id=" + produto.getId(),
                    "PRODUTO_NAO_ENCONTRADO_PARA_ADD_CARRINHO",
                    HttpStatus.BAD_REQUEST);
        }
    }

    public Carrinho consultarCarrinho(Long idCliente) {
        return carrinhoRepository.consultarCarrinhoDeCliente(idCliente);
    }

    public void removerDoCarrinho(Long idCliente, Produto produto) {
        Carrinho carrinhoDoCliente = carrinhoRepository.consultarCarrinhoDeCliente(idCliente);

        if (carrinhoDoCliente != null) {
            carrinhoDoCliente.removerProduto(produto);
        }
    }

    public Set<Carrinho> listarTodosCarrinhos() {
        return carrinhoRepository.listarTodosCarrinhos();
    }

    public BigDecimal valorTotalDoCarrinho(Long idCliente) {
        Carrinho carrinhoDoCLiente = carrinhoRepository.consultarCarrinhoDeCliente(idCliente);

        if (carrinhoDoCLiente == null) {
            throw new ApplicationException(
                    "Carrinho não encontrado para o ID= " + idCliente,
                    "CARRINHO_NAO_ENCONTRADO",
                    HttpStatus.NOT_FOUND
            );
        }

        BigDecimal valorTotalDoCarrinho = BigDecimal.ZERO;
        for (Produto produto : carrinhoDoCLiente.getProdutos()) {
            valorTotalDoCarrinho = valorTotalDoCarrinho.add(produto.getPreco());
        }
        return valorTotalDoCarrinho;
    }
}

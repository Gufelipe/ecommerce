package com.gufelipe.ecommerce.service;


import com.gufelipe.ecommerce.entity.Carrinho;
import com.gufelipe.ecommerce.entity.Cliente;
import com.gufelipe.ecommerce.entity.Produto;
import com.gufelipe.ecommerce.exception.ClienteNaoEncontradoException;
import com.gufelipe.ecommerce.repository.CarrinhoRepository;
import com.gufelipe.ecommerce.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CarrinhoService {

    ClienteRepository clienteRepository;
    CarrinhoRepository carrinhoRepository;

    public CarrinhoService(ClienteRepository clienteRepository, CarrinhoRepository carrinhoRepository) {
        this.clienteRepository = clienteRepository;
        this.carrinhoRepository = carrinhoRepository;
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
        Carrinho carrinhoDoCliente = carrinhoRepository.consultarCarrinho(idCliente);

        if (carrinhoDoCliente == null) {
            carrinhoDoCliente = criarCarrinhoParaCliente(idCliente);
        }

        carrinhoRepository.adicionarCarrinho(carrinhoDoCliente);
        carrinhoDoCliente.adicionarProduto(produto);
    }

    public Set<Carrinho> listarTodosCarrinhos() {
        return carrinhoRepository.listarTodosCarrinhos();
    }

}

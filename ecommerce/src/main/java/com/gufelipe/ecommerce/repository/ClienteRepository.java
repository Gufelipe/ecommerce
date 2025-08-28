package com.gufelipe.ecommerce.repository;

import com.gufelipe.ecommerce.entity.Cliente;
import com.gufelipe.ecommerce.exception.ClienteJaExistenteException;
import com.gufelipe.ecommerce.exception.ClienteNaoEncontradoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Repository
public class ClienteRepository {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final Set<Cliente> clientes = new HashSet<>();

    public Cliente consultarCliente(Long id) {
        for (Cliente cliente : clientes) {
            if (cliente.getId().equals(id)) {
                return cliente;
            }
        }
        var errMsg = "Cliente não encontrado para o ID = " + id;
        throw new ClienteNaoEncontradoException(errMsg, "CLIENTE_NAO_ENCONTRADO");
    }

    private boolean consultarCliente(String email) {
        var jaExiste = false;
        for (Cliente cliente : clientes) {
            if (cliente.getEmail().equals(email)) {
                return jaExiste = true;
            }
        }
        return jaExiste;
    }

    public void inserir(Cliente cliente) {
        logger.info("Inserindo cliente");
        logger.debug("Inserindo cliente={}", cliente);

        if (consultarCliente(cliente.getEmail())) {
            var errMsg = "Email ja cadastrado";
            throw new ClienteJaExistenteException(errMsg, "EMAIL_JA_CADASTRADO");
        }

        cliente.setId(new Random().nextLong());
        clientes.add(cliente);
    }

    public Set<Cliente> listarTodosClientes() {
        return clientes;
    }
}
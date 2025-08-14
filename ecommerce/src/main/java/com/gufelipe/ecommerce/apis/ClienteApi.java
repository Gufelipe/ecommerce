package com.gufelipe.ecommerce.apis;

import com.gufelipe.ecommerce.entity.Cliente;
import com.gufelipe.ecommerce.repository.ClienteRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/clientes")
public class ClienteApi {

    private final ClienteRepository clienteRepository;

    public ClienteApi(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @GetMapping
    public Set<Cliente> listarTodosClientes(){
        return clienteRepository.listarTodosClientes();
    }

    @PostMapping
    public void inserirCliente(@RequestBody Cliente cliente){
        clienteRepository.inserir(cliente);
    }
}
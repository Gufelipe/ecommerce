package com.gufelipe.ecommerce.apis;


import com.gufelipe.ecommerce.entity.Carrinho;
import com.gufelipe.ecommerce.entity.Produto;
import com.gufelipe.ecommerce.service.CarrinhoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/carrinhos")
public class CarrinhoApi {

    private final CarrinhoService carrinhoService;

    public CarrinhoApi(CarrinhoService carrinhoService) {
        this.carrinhoService = carrinhoService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Set<Carrinho> listarTodosCarrinhos() {
        return carrinhoService.listarTodosCarrinhos();
    }

    @GetMapping("/{idCliente}")
    @ResponseStatus(HttpStatus.OK)
    public Carrinho consultarCarrinho(@PathVariable Long idCliente) {
        return carrinhoService.consultarCarrinho(idCliente);
    }

    @PostMapping("/{idCliente}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void adicionarNoCarrinho(
            @PathVariable Long idCliente,
            @RequestBody Produto produto
    ) {
        carrinhoService.adicionarNoCarrinho(idCliente, produto);
    }

    @DeleteMapping("/{idCliente}")
    public void removerDoCarrinho(
            @PathVariable Long idCliente,
            @RequestBody Produto produto
    ) {
        carrinhoService.removerDoCarrinho(idCliente, produto);
    }
}

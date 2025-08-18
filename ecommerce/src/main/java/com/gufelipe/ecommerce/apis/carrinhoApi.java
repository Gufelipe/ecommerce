package com.gufelipe.ecommerce.apis;


import com.gufelipe.ecommerce.entity.Carrinho;
import com.gufelipe.ecommerce.entity.Produto;
import com.gufelipe.ecommerce.service.CarrinhoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/carrinhos")
public class carrinhoApi {

    private final CarrinhoService carrinhoService;

    public carrinhoApi(CarrinhoService carrinhoService) {
        this.carrinhoService = carrinhoService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Set<Carrinho> listarTodosCarrinhos() {
        return carrinhoService.listarTodosCarrinhos();
    }

    @PostMapping("/{idCliente}")
    public void adicionarNoCarrinho(
            @PathVariable Long idCliente,
            @RequestBody Produto produto
    ) {
        carrinhoService.adicionarNoCarrinho(idCliente, produto);
    }
}

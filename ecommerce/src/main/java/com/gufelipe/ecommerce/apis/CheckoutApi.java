package com.gufelipe.ecommerce.apis;

import com.gufelipe.ecommerce.service.CheckoutService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/checkout")
public class CheckoutApi {

    private final CheckoutService checkoutService;

    public CheckoutApi(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping("/cliente/{idCliente}")
    public void fecharCompra(@PathVariable Long idCliente) {
        this.checkoutService.fecharCompra(idCliente);
    }
}
package com.gufelipe.ecommerce.notificacao;

import com.gufelipe.ecommerce.Cliente;

public class NotificarViaEmail implements Notificacao {

    @Override
    public void notificarCompra(Cliente cliente) {
        System.out.println("Notificando compra via Email");
    }
}

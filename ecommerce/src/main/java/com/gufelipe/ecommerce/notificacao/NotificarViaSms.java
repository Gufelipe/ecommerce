package com.gufelipe.ecommerce.notificacao;

import com.gufelipe.ecommerce.Cliente;

public class NotificarViaSms implements Notificacao {

    @Override
    public void notificarCompra(Cliente cliente) {
        System.out.println("Notificando via SMS");
    }

}

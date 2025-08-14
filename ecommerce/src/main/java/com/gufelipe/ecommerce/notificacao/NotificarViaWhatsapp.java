package com.gufelipe.ecommerce.notificacao;

import com.gufelipe.ecommerce.entity.Cliente;

public class NotificarViaWhatsapp implements Notificacao {

    @Override
    public void notificarCompra(Cliente cliente) {
        System.out.println("Notificando via WhatsApp");
    }

}

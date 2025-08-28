package com.gufelipe.ecommerce.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class PagamentoService {

    public String solicitarPagamento(BigDecimal valorACobrar, Long idCliente) {
        String idDoPagamento = UUID.randomUUID().toString();
        return idDoPagamento;
    }

    public Boolean verificarPagamento(String idDoPagamento) {
        return Boolean.TRUE;
    }
}
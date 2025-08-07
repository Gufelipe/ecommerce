package com.gufelipe.ecommerce;

import com.gufelipe.ecommerce.modelo.Livro;
import com.gufelipe.ecommerce.notificacao.TipoNotificacao;

import java.util.ArrayList;
import java.util.List;

public class Cliente {

    private Integer idade;
    private String numeroDocumento;
    private String nome;
    private Boolean ativo;
    private TipoNotificacao tipoNotificacao;
    private List<Livro> carrinho;

    public Cliente(Integer idade, String nome, String numeroDocumento, TipoNotificacao tipoNotificacao) {
        this.idade = idade;
        this.nome = nome;
        this.numeroDocumento = numeroDocumento;
        this.tipoNotificacao = tipoNotificacao;
        this.ativo = Boolean.TRUE;
        this.carrinho = new ArrayList<>();
    }

    void desativarCliente() {
        this.ativo = Boolean.FALSE;
        System.out.printf("Cliente %s foi desativado\n", this.nome);
    }

    public void adicionarLivroNoCarrinho(Livro livro) {
        this.carrinho.add(livro);
        System.out.printf("O livro %s foi adicionado no carrinho do %s\n", livro.getTitulo(), this.nome);
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public TipoNotificacao getTipoNotificacao() {
        return tipoNotificacao;
    }

    public void setTipoNotificacao(TipoNotificacao tipoNotificacao) {
        this.tipoNotificacao = tipoNotificacao;
    }

    public List<Livro> getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(List<Livro> carrinho) {
        this.carrinho = carrinho;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "idade=" + idade +
                ", numeroDocumento='" + numeroDocumento + '\'' +
                ", nome='" + nome + '\'' +
                ", ativo=" + ativo +
                ", tipoNotificacaoPreferida=" + tipoNotificacao +
                ", carrinho=" + carrinho +
                '}';
    }
}

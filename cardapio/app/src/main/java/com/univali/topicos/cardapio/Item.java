package com.univali.topicos.cardapio;

import java.io.Serializable;

public class Item implements Serializable
{
    private String nome;
    private String descricao;
    private String calorias;
    private String preco;
    private String quantidade;

    public Item(){}

    public Item(String nome, String descricao, String calorias, String preco)
    {
        this.nome = nome;
        this.descricao = descricao;
        this.calorias = calorias;
        this.preco = preco;
    }

    public Item(String nome, String descricao, String calorias, String preco, String quantidade)
    {
        this.nome = nome;
        this.descricao = descricao;
        this.calorias = calorias;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCalorias() {
        return calorias;
    }

    public void setCalorias(String calorias) {
        this.calorias = calorias;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }
}

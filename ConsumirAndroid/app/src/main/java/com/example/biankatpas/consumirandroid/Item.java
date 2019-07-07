package com.example.biankatpas.consumirandroid;

public class Item
{
    private Long id;
    private double valor;
    private String descricao;
    private String nome;
    private String url;

    public Item() {}

    public Item(double valor, String descricao, String nome, String url)
    {
        this.valor = valor;
        this.descricao = descricao;
        this.nome = nome;
        this.url = url;
    }

    public Item(Long id, double valor, String descricao, String nome, String url)
    {
        this.id = id;
        this.valor = valor;
        this.descricao = descricao;
        this.nome = nome;
        this.url = url;
    }

    public double getValor()
    {
        return valor;
    }

    public void setValor(double valor)
    {
        this.valor = valor;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }
}


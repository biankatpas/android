package com.univali.topicos.lista_itens;

public class Cidade
{

    private String id;
    private String nome;
    private String fundacao;
    private String populacao;
    private String area;
    private String densidade;

    public Cidade() {}

    public Cidade(String id, String nome, String fundacao, String populacao, String area, String densidade)
    {
        this.id = id;
        this.nome = nome;
        this.fundacao = fundacao;
        this.populacao = populacao;
        this.area = area;
        this.densidade = densidade;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getPopulacao()
    {
        return populacao;
    }

    public void setPopulacao(String populacao)
    {
        this.populacao = populacao;
    }

    public String getArea()
    {
        return area;
    }

    public void setArea(String area)
    {
        this.area = area;
    }

    public String getDensidade()
    {
        return densidade;
    }

    public void setDensidade(String densidade)
    {
        this.densidade = densidade;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFundacao() {
        return fundacao;
    }

    public void setFundacao(String fundacao) {
        this.fundacao = fundacao;
    }
}

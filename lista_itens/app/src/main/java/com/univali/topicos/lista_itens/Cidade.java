package com.univali.topicos.lista_itens;

public class Cidade
{

    private String nome;
    private String populacao;
    private String area;
    private String densidade;

    public Cidade() {}

    public Cidade(String nome, String populacao, String area, String densidade)
    {
        this.nome = nome;
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
}

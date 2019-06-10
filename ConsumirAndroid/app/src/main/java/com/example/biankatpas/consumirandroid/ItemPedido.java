package com.example.biankatpas.consumirandroid;

public class ItemPedido

{
    private Long id;
    private Item item;
    private int quantidade;
    private double valor;

    public ItemPedido() {}

    public ItemPedido(Item item, int quantidade, double valor)
    {
        this.item = item;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public ItemPedido(Long id, Item item, int quantidade, double valor) {
        this.id = id;
        this.item = item;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public Item getItem()
    {
        return item;
    }

    public void setItem(Item item)
    {
        this.item = item;
    }

    public int getQuantidade()
    {
        return quantidade;
    }

    public void setQuantidade(int quantidade)
    {
        this.quantidade = quantidade;
    }

    public double getValor()
    {
        return valor;
    }

    public void setValor(double valor)
    {
        this.valor = valor;
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


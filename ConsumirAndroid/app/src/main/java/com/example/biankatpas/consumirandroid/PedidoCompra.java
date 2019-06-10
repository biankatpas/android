package com.example.biankatpas.consumirandroid;

import java.util.ArrayList;

public class PedidoCompra

{
    private Long id;
    private String data;
    private String hora;
    private ArrayList<ItemPedido> itens;
    private Usuario usuario;

    public PedidoCompra()
    {
        itens = new ArrayList<>();
    }

    public PedidoCompra(String data, String hora, ArrayList<ItemPedido> itens, Usuario usuario)
    {
        this.data = data;
        this.hora = hora;
        this.itens = itens;
        this.usuario = usuario;
    }

    public String getData()
    {
        return data;
    }

    public void setData(String data)
    {
        this.data = data;
    }

    public ArrayList<ItemPedido> getItens()
    {
        return itens;
    }

    public void setItens(ArrayList<ItemPedido> itens)
    {
        this.itens = itens;
    }

    public Usuario getUsuario()
    {
        return usuario;
    }

    public void setUsuario(Usuario usuario)
    {
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}


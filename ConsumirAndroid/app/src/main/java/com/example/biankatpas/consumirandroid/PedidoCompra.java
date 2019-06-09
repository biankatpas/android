package com.example.biankatpas.consumirandroid;

import java.util.ArrayList;
import java.util.Date;

public class PedidoCompra

{
    private Long id;
    private Date data;
    private ArrayList<ItemPedido> itens;
    private Usuario usuario;

    public PedidoCompra()
    {
        itens = new ArrayList<>();
    }

    public PedidoCompra(Date data, ArrayList<ItemPedido> itens, Usuario usuario)
    {
        this.data = data;
        this.itens = itens;
        this.usuario = usuario;
    }

    public Date getData()
    {
        return data;
    }

    public void setData(Date data)
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
}


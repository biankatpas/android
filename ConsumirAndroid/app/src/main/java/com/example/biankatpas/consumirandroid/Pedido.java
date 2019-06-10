package com.example.biankatpas.consumirandroid;

import java.io.Serializable;
import java.util.ArrayList;

public class Pedido implements Serializable {
    private static Pedido uniqueInstance;
    private String data;
    private String hora;
    private Usuario usuario;
    private boolean isLogged;
    private ArrayList<ItemPedido> itemsPedido;

    private Pedido() {
        isLogged = false;
        itemsPedido = new ArrayList<>();
    }

    public static synchronized Pedido getInstance() {
        if (uniqueInstance == null)
            uniqueInstance = new Pedido();
        return uniqueInstance;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ArrayList<ItemPedido> getItems() {
        return itemsPedido;
    }

    public void setItems(ArrayList<ItemPedido> itemsPedido) {
        this.itemsPedido = itemsPedido;
    }

    public void addItem(ItemPedido item) {
        this.itemsPedido.add(item);
    }

    public boolean isLogged() {
        return isLogged;
    }

    public void setLogged(boolean logged) {
        isLogged = logged;
    }
}


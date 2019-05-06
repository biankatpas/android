package com.univali.topicos.cardapio;

import java.io.Serializable;
import java.util.ArrayList;

public class Pedido implements Serializable {
    private static Pedido uniqueInstance;
    private String data;
    private String nome;
    private String endereco;
    private String telefone;
    private ArrayList<Item> items;

    private Pedido() {
        items = new ArrayList<>();
    }

    public static synchronized Pedido getInstance() {
        if (uniqueInstance == null)
            uniqueInstance = new Pedido();
        return uniqueInstance;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

}

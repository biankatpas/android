package com.univali.topicos.cardapio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemAdapter extends ArrayAdapter<Item>
{

    private final Context context;
    private final ArrayList<Item> elementos;

    public ItemAdapter(Context context, ArrayList<Item> elementos)
    {
        super(context, R.layout.linha, elementos);
        this.context = context;
        this.elementos = elementos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.linha, parent, false);

        TextView nomeCidade = (TextView) rowView.findViewById(R.id.nome);
        nomeCidade.setText(elementos.get(position).getNome());

        return rowView;
    }

}
package com.example.biankatpas.consumirandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PedidoCompraAdapter extends ArrayAdapter<PedidoCompra> {

    private final Context context;
    private final ArrayList<PedidoCompra> elementos;

    public PedidoCompraAdapter(Context context, ArrayList<PedidoCompra> elementos)
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

        TextView nomeItem = (TextView) rowView.findViewById(R.id.nome);
        nomeItem.setText(""+elementos.get(position).getId());

        return rowView;
    }
}

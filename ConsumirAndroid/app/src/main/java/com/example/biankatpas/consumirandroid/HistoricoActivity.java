package com.example.biankatpas.consumirandroid;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class HistoricoActivity extends AppCompatActivity {

    ListView lista;
    Pedido pedido = Pedido.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);
        Log.i("HISTORICO", "http://192.168.0.105:8080/Produto-WS/webresources/generic/pedidocompra/buscar/"+pedido.getUsuario().getId());
        new RetrieveFeedTask().execute("http://192.168.0.105:8080/Produto-WS/webresources/generic/pedidocompra/buscar/"+pedido.getUsuario().getId());
    }

    private void setItems(ArrayList<PedidoCompra> pedidos) {
        ArrayAdapter<PedidoCompra> adapter = new PedidoCompraAdapter(this, pedidos);

        lista = (ListView) findViewById(R.id.lvPedido);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PedidoCompra i = (PedidoCompra) lista.getItemAtPosition(position);
                Toast.makeText(getBaseContext(), "Item: "+ i.getId(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(HistoricoActivity.this, ExcluirPedidoActivity.class);
                intent.putExtra("id", ""+i.getId());
                intent.putExtra("data", i.getData());
                intent.putExtra("hora", i.getHora());
                startActivity(intent);
            }
        });

    }

    class RetrieveFeedTask extends AsyncTask<String, Void, ArrayList<PedidoCompra>> {

        @Override
        protected ArrayList<PedidoCompra> doInBackground(String... strings) {
            ArrayList<PedidoCompra> pedidos = new ArrayList<>();
            AcessoRest ar = new AcessoRest();

            try {
                Gson g = new Gson();

                String resultado = ar.sendGet(strings[0]);
                Log.i("HISTORICO", resultado);

                Type itemType = new TypeToken<ArrayList<PedidoCompra>>() {}.getType();
                pedidos = g.fromJson(resultado, itemType);

            } catch (Exception ex) {}

            return pedidos;
        }

        @Override
        protected void onPostExecute(ArrayList<PedidoCompra> result) {
            super.onPostExecute(result);
            setItems(result);
        }

    }
}

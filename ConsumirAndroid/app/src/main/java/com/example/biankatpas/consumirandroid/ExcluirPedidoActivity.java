package com.example.biankatpas.consumirandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class ExcluirPedidoActivity extends AppCompatActivity {

    String id;
    String data;
    String hora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excluir_pedido);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        data = intent.getStringExtra("data");
        hora = intent.getStringExtra("hora");

        TextView tvId = findViewById(R.id.tvId);
        tvId.setText("Id: " + id);
        TextView tvData = findViewById(R.id.tvData);
        tvData.setText("Data: " + data);
        TextView tvHora = findViewById(R.id.tvHora);
        tvHora.setText("Hora: " + hora);
    }

    public void cancelarPedido(View v){
        PedidoCompra p = new PedidoCompra();
        p.setId(Long.parseLong(id));
        Log.i("excluir", p.getId().toString());

        Gson g = new Gson();
        Type itemType = new TypeToken<PedidoCompra>() {}.getType();
        String content = g.toJson(p, itemType);

        AcessoRest ar = new AcessoRest();
        ar.sendDelete("http://192.168.0.110:8080/Produto-WS/webresources/generic/pedidocompra/excluir", content);
        Intent intent = new Intent(ExcluirPedidoActivity.this, HistoricoActivity.class);
        startActivity(intent);
    }
}

package com.example.biankatpas.consumirandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class DadosCobrancaActivity extends AppCompatActivity {

    Pedido pedido = Pedido.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_cobranca);

        EditText tvNumeroCartao = findViewById(R.id.edNumeroCartao);
        tvNumeroCartao.setText(pedido.getUsuario().getNumeroCartao());
        EditText tvCodigoSeguranca = findViewById(R.id.edCodigoSeguranca);
        tvCodigoSeguranca.setText("" + pedido.getUsuario().getCodigoSeguranca());
        EditText tvDataValidade = findViewById(R.id.edDataValidade);
        tvDataValidade.setText(pedido.getUsuario().getDataValidade());
    }

    public void alterarDados(View v){
        AcessoRest ar = new AcessoRest();
        Gson g = new Gson();

        EditText tvNumeroCartao = findViewById(R.id.edNumeroCartao);
        EditText tvCodigoSeguranca = findViewById(R.id.edCodigoSeguranca);
        EditText tvDataValidade = findViewById(R.id.edDataValidade);

        String n = tvNumeroCartao.getText().toString();
        String c = tvCodigoSeguranca.getText().toString();
        String d = tvDataValidade.getText().toString();

        pedido.getUsuario().setNumeroCartao(n);
        pedido.getUsuario().setCodigoSeguranca(Integer.parseInt(c));
        pedido.getUsuario().setDataValidade(d);

        Type itemType = new TypeToken<Usuario>() {}.getType();
        String content = g.toJson(pedido.getUsuario(), itemType);

        ar.sendPut("http://192.168.0.105:8080/Produto-WS/webresources/generic/usuario/alterar", content);

        Intent intent = new Intent(DadosCobrancaActivity.this, PedidoActivity.class);
        startActivity(intent);

    }
}

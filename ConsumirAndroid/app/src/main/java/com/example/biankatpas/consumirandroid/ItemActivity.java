package com.example.biankatpas.consumirandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ItemActivity extends AppCompatActivity {

    String nome;
    String descricao;
    String valor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        Intent intent = getIntent();
        nome = intent.getStringExtra("nome");
        descricao = intent.getStringExtra("descricao");
        valor = intent.getStringExtra("valor");

        TextView tvNome = findViewById(R.id.tvNome);
        tvNome.setText("Nome: " + nome);
        TextView tvDescricao = findViewById(R.id.tvDescricao);
        tvDescricao.setText("Descrição: " + descricao);
        TextView tvValorUnitario = findViewById(R.id.tvValor);
        tvValorUnitario.setText("Valor: " + valor);
    }

    public void adicionar(View v)
    {
        TextView tvQuantidade = findViewById(R.id.tvQuantidade);
        int qtd = Integer.parseInt(tvQuantidade.getText().toString().split(": ")[1]);
        qtd++;
        tvQuantidade.setText("Quantidade: " + qtd);
    }

    public void remover(View v)
    {
        TextView tvQuantidade = findViewById(R.id.tvQuantidade);
        int qtd = Integer.parseInt(tvQuantidade.getText().toString().split(": ")[1]);
        qtd--;
        if(qtd<0)
            qtd = 0;
        tvQuantidade.setText("Quantidade: " + qtd);
    }

}

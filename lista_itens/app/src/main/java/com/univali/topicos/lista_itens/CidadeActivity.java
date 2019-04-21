package com.univali.topicos.lista_itens;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CidadeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cidade);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String nome = intent.getStringExtra("nome");
        String fundacao = intent.getStringExtra("fundacao");
        String populacao = intent.getStringExtra("populacao");
        String area = intent.getStringExtra("area");
        String densidade = intent.getStringExtra("densidade");

        TextView tvId = findViewById(R.id.tvId);
        tvId.setText(id);
        TextView tvNome = findViewById(R.id.tvNome);
        tvNome.setText(nome);
        TextView tvFundacao = findViewById(R.id.tvFundacao);
        tvFundacao.setText(fundacao);
        TextView tvPopulacao = findViewById(R.id.tvPopulacao);
        tvPopulacao.setText(populacao);
        TextView tvArea = findViewById(R.id.tvArea);
        tvArea.setText(area);
        TextView tvDensidade = findViewById(R.id.tvDensidade);
        tvDensidade.setText(densidade);
    }

    public void voltar(View v)
    {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}

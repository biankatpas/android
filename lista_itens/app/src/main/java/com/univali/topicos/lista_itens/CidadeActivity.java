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
        String nome = (String) intent.getSerializableExtra("nome");
        String area = (String) intent.getSerializableExtra("area");
        String populacao = (String) intent.getSerializableExtra("populacao");
        String densidade = (String) intent.getSerializableExtra("densidade");

        TextView tvNome = findViewById(R.id.tvNome);
        tvNome.setText(nome);
        TextView tvArea = findViewById(R.id.tvArea);
        tvArea.setText(area);
        TextView tvPopulacao = findViewById(R.id.tvPopulacao);
        tvPopulacao.setText(populacao);
        TextView tvDensidade = findViewById(R.id.tvDensidade);
        tvDensidade.setText(densidade);
    }

    public void voltar(View v)
    {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}

package com.univali.topicos.cardapio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ItemActivity extends AppCompatActivity
{
    String nome;
    String descricao;
    String calorias;
    String preco;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        Intent intent = getIntent();
        nome = intent.getStringExtra("nome");
        descricao = intent.getStringExtra("descricao");
        calorias = intent.getStringExtra("calorias");
        preco = intent.getStringExtra("preco");

        TextView tvNome = findViewById(R.id.tvNome);
        tvNome.setText("Nome: " + nome);
        TextView tvDescricao = findViewById(R.id.tvDescricao);
        tvDescricao.setText("Descrição: " + descricao);
        TextView tvValorUnitario = findViewById(R.id.tvValorUnitario);
        tvValorUnitario.setText("Preço: " + preco);
        TextView tvCalorias = findViewById(R.id.tvCalorias);
        tvCalorias.setText("Calorias: " + calorias);
    }

    public void adicionar(View v)
    {
        TextView tvQuantidade = findViewById(R.id.tvQuantidade);
        int qtd = Integer.parseInt(tvQuantidade.getText().toString().split(": ")[1]);
        qtd++;
        tvQuantidade.setText("Quantidade: " + qtd);

        calcularCaloriasTotais(qtd);
        calcularValorTotal(qtd);
    }

    public void remover(View v)
    {
        TextView tvQuantidade = findViewById(R.id.tvQuantidade);
        int qtd = Integer.parseInt(tvQuantidade.getText().toString().split(": ")[1]);
        qtd--;
        if(qtd<0)
            qtd = 0;
        tvQuantidade.setText("Quantidade: " + qtd);

        calcularCaloriasTotais(qtd);
        calcularValorTotal(qtd);
    }

    private void calcularCaloriasTotais(int qtd)
    {
        int caloriasTotais = qtd * Integer.parseInt(calorias);
        TextView tvCaloriasTotais = findViewById(R.id.tvCaloriasTotais);
        tvCaloriasTotais.setText("Calorias totais: " + caloriasTotais);
    }

    private void calcularValorTotal(int qtd)
    {
        float valorTotal = qtd * Float.parseFloat(preco.split("\\$")[1]);
        TextView tvValorTotal = findViewById(R.id.tvValorTotal);
        tvValorTotal.setText("Valor total: " + valorTotal);
    }

    public void confirmar(View v)
    {
        Intent intent = new Intent(ItemActivity.this, MainActivity.class);
        intent.putExtra("nome", nome);
        intent.putExtra("descricao", descricao);
        intent.putExtra("calorias", calorias);
        intent.putExtra("preco", preco);

        TextView tvQuantidade = findViewById(R.id.tvQuantidade);
        String qtd = tvQuantidade.getText().toString().split(": ")[1];
        intent.putExtra("quantidade", qtd);

        startActivity(intent);
    }
}

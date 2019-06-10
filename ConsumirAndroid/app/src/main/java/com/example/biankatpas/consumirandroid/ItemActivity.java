package com.example.biankatpas.consumirandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ItemActivity extends AppCompatActivity {

    String id;
    String nome;
    String descricao;
    String valor;
    String quantidade;

    Pedido pedido = Pedido.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        nome = intent.getStringExtra("nome");
        descricao = intent.getStringExtra("descricao");
        valor = intent.getStringExtra("valor");
        quantidade = intent.getStringExtra("quantidade");

        TextView tvNome = findViewById(R.id.tvNome);
        tvNome.setText("Nome: " + nome);
        TextView tvDescricao = findViewById(R.id.tvDescricao);
        tvDescricao.setText("Descrição: " + descricao);
        TextView tvValorUnitario = findViewById(R.id.tvValor);
        tvValorUnitario.setText("Valor Unitario: " + valor);
        TextView tvQuantidade = findViewById(R.id.tvQuantidade);
        tvQuantidade.setText("Quantidade: " + quantidade);
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

    public void confirmar(View v)
    {
        TextView tvQuantidade = findViewById(R.id.tvQuantidade);
        String qtd = tvQuantidade.getText().toString().split(": ")[1];
        if(pedido.isLogged()){
            pedido.addItem(new ItemPedido(new Item(Long.parseLong(id), Double.parseDouble(valor), descricao, nome), Integer.parseInt(qtd), Double.parseDouble(valor)*Integer.parseInt(qtd)));
            Intent intent = new Intent(ItemActivity.this, MainActivity.class);
            startActivity(intent);

        } else{
            Intent intent = new Intent(ItemActivity.this, LoginActivity.class);
            intent.putExtra("id", id);
            intent.putExtra("nome", nome);
            intent.putExtra("descricao", descricao);
            intent.putExtra("valor", valor);
            intent.putExtra("quantidade", qtd);
            startActivity(intent);
        }
    }

}

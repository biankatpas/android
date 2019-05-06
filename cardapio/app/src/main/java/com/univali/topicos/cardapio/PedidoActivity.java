package com.univali.topicos.cardapio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class PedidoActivity extends AppCompatActivity
{

    Pedido pedido = new Pedido();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        setDate();
        setHour();
//        setCaloriasTotais();
//        setValorTotal();

    }

    private void setHour()
    {
        SimpleDateFormat dateFormat_hora = new SimpleDateFormat("HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        Date data_atual = cal.getTime();
        TextView tvHorario = findViewById(R.id.tvHorario);
        tvHorario.setText("Horário: " + dateFormat_hora.format(data_atual));
    }

    private void setDate()
    {
        SimpleDateFormat dateFormat_data = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        Date data_atual = cal.getTime();
        TextView tvData = findViewById(R.id.tvData);
        tvData.setText("Data: " + dateFormat_data.format(data_atual));
    }

    private void setCaloriasTotais()
    {
        int caloriasTotais = 0;

        Bundle bundle = getIntent().getBundleExtra("BUNDLE");
        ArrayList<Item> items = (ArrayList<Item>) bundle.getSerializable("items");

        System.out.println("merda");
//        System.out.println(items.size());

//        for(Item i : items)
        {
//            int calorias = Integer.parseInt(i.getCalorias());
//            int qtd = Integer.parseInt(i.getQuantidade());
//            caloriasTotais+=calorias*qtd;
        }
//        TextView tvCaloriasTotais = findViewById(R.id.tvCaloriasTotais);
//        tvCaloriasTotais.setText("Calorias totais: " + caloriasTotais);
    }

    private void setValorTotal()
    {
        //todo
    }

    public void enviarPedido(View v)
    {
        //todo: escrever xml
    }
}

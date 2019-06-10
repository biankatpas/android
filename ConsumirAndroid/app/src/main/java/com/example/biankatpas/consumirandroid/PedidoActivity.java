package com.example.biankatpas.consumirandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PedidoActivity extends AppCompatActivity {

    Pedido pedido = Pedido.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        setDate();
        setHour();

        TextView tvNumeroCartao = findViewById(R.id.tvNumeroCartao);
        tvNumeroCartao.setText("Numero cartao: " + pedido.getUsuario().getNumeroCartao());

        TextView tvCodigoSeguranca = findViewById(R.id.tvCodigoSeguranca);
        tvCodigoSeguranca.setText("Codigo seguranca: " + pedido.getUsuario().getCodigoSeguranca());

        TextView tvDataValidade = findViewById(R.id.tdData);
        tvDataValidade.setText("Data validade: " + pedido.getUsuario().getDataValidade());
    }

    private void setHour()
    {
        SimpleDateFormat dateFormat_hora = new SimpleDateFormat("HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        Date data_atual = cal.getTime();
        TextView tvHorario = findViewById(R.id.tvHora);
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

    public void enviarPedido(View v)
    {
        if(pedido.isLogged()){
            TextView tvData = findViewById(R.id.tvData);
            pedido.setData(tvData.getText().toString().split(" ")[1]);

            TextView tvHour = findViewById(R.id.tvHora);
            pedido.setHora(tvHour.getText().toString().split(" ")[1]);

            sendJSON();
        }

        Intent intent = new Intent(PedidoActivity.this, HistoricoActivity.class);
        startActivity(intent);
    }

    private void sendJSON()
    {
        AcessoRest ar = new AcessoRest();

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        Gson gson = gsonBuilder.create();

        Type itemType = new TypeToken<PedidoCompra>() {}.getType();

        PedidoCompra p = new PedidoCompra();
        p.setData(pedido.getData());
        p.setHora(pedido.getHora());
        p.setUsuario(pedido.getUsuario());
        p.setItens(pedido.getItems());

        String content = gson.toJson(p, itemType);
        Log.i("PEDIDO", content);
        ar.sendPost("http://192.168.0.105:8080/Produto-WS/webresources/generic/pedidocompra/inserir", content);

    }

    public void atualizarDados(View v){
        Intent intent = new Intent(PedidoActivity.this, DadosCobrancaActivity.class);
        startActivity(intent);
    }
}

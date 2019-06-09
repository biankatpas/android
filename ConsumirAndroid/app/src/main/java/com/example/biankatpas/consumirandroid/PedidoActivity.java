package com.example.biankatpas.consumirandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

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

        TextView tvData = findViewById(R.id.tvData);
        pedido.setData(tvData.getText().toString().split(" ")[1]);

        TextView tvHour = findViewById(R.id.tvHora);
        pedido.setHora(tvHour.getText().toString().split(" ")[1]);

        sendJSON();
    }

    private void sendJSON()
    {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        Gson gson = gsonBuilder.create();
        System.out.println(gson.toJson(pedido, new TypeToken<Pedido>() {}.getType()));
    }

}

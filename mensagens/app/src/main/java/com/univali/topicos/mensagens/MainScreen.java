package com.univali.topicos.mensagens;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        String hora_atual = getHoraAtual();
        verificaHorario(hora_atual);
    }

    private String getHoraAtual()
    {
        SimpleDateFormat dateFormat_hora = new SimpleDateFormat("HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        Date data_atual = cal.getTime();
        return dateFormat_hora.format(data_atual);
    }

    private void verificaHorario(String hora_atual)
    {
        View view = this.getWindow().getDecorView();

        String[] parts = hora_atual.split(":");
        int hour = Integer.parseInt(parts[0]);

        if (hour < 13)
        {
            Toast.makeText(this, "Bom dia!", Toast.LENGTH_LONG).show();
            view.setBackgroundColor(Color.YELLOW);
        }
        else if (hour < 18)
        {
            Toast.makeText(this, "Boa tarde!", Toast.LENGTH_LONG).show();
            view.setBackgroundColor(Color.GREEN);
        }
        else
        {
            Toast.makeText(this, "Boa Noite!", Toast.LENGTH_LONG).show();
            view.setBackgroundColor(Color.BLUE);
        }
    }
}

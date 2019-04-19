package com.univali.topicos.layouts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainScreen extends AppCompatActivity
{
    ArrayList<EditText> fieldsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        LinearLayout ll = (LinearLayout)findViewById(R.id.linear_layout_bottom);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        int numberOfFieldsToAdd = countWorkedHours();
        for (int i = 0;i<numberOfFieldsToAdd;i++){
            EditText ed = new EditText(this);
            ed.setLayoutParams(p);
            ed.setHint("Hora Trabalhada "+(fieldsList.size()+1));
            ed.setTextSize(10);
            ed.setId(fieldsList.size()+1);
            ll.addView(ed);
            fieldsList.add(ed);
        }
    }

    private int countWorkedHours()
    {
        String hour = getHour();
        String[] parts = hour.split(":");
        int h = Integer.parseInt(parts[0]);

        int worked_hours = 0;
        if(h >= 9 && h <= 17)
        {
            worked_hours = h - 8;
            if(h >= 13)
                worked_hours -= 1;
        }

        return worked_hours;
    }

    private String getHour()
    {
        SimpleDateFormat dateFormat_hora = new SimpleDateFormat("HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        Date data_atual = cal.getTime();
        return dateFormat_hora.format(data_atual);
    }
}

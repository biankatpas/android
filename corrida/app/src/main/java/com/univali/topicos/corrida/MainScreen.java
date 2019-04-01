package com.univali.topicos.corrida;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MainScreen extends AppCompatActivity
{
    boolean ASC = true;
    boolean DESC = false;

    ArrayList<EditText> atletas = new ArrayList<>();
    ArrayList<EditText> tempos = new ArrayList<>();

    Button btCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        atletas.add((EditText) findViewById(R.id.edA1));
        atletas.add((EditText) findViewById(R.id.edA2));
        atletas.add((EditText) findViewById(R.id.edA3));
        atletas.add((EditText) findViewById(R.id.edA4));
        atletas.add((EditText) findViewById(R.id.edA5));
        atletas.add((EditText) findViewById(R.id.edA6));
        atletas.add((EditText) findViewById(R.id.edA7));
        atletas.add((EditText) findViewById(R.id.edA8));
        atletas.add((EditText) findViewById(R.id.edA9));
        atletas.add((EditText) findViewById(R.id.edA10));

        tempos.add((EditText) findViewById(R.id.edT1));
        tempos.add((EditText) findViewById(R.id.edT2));
        tempos.add((EditText) findViewById(R.id.edT3));
        tempos.add((EditText) findViewById(R.id.edT4));
        tempos.add((EditText) findViewById(R.id.edT5));
        tempos.add((EditText) findViewById(R.id.edT6));
        tempos.add((EditText) findViewById(R.id.edT7));
        tempos.add((EditText) findViewById(R.id.edT8));
        tempos.add((EditText) findViewById(R.id.edT9)) ;
        tempos.add((EditText) findViewById(R.id.edT10));

        btCalcular = (Button) findViewById(R.id.btCalcular);
        btCalcular.setEnabled(false);
        btCalcular.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                boolean fieldsFilled=verificaCamposPreenchidos();
                if(fieldsFilled)
                    btCalcular.setEnabled(true);
                else
                    btCalcular.setEnabled(false);
            }
        });

    }

    public boolean verificaCamposPreenchidos()
    {
        for(int i=0; i<atletas.size();i++)
            if((atletas.get(i).getText().toString().equals("")) || tempos.get(i).getText().toString().equals(""))
                return false;
        return true;
    }

    public void calcular(View v)
    {
        Map<String, Calendar> corrida = getDadosCorrida();

        String resultado = calcularMelhorTempo(corrida);
        Calendar media = calcularMedia(corrida);
        Calendar desvio = calcularDesvioPadrao(corrida);

        Intent it = new Intent(this, Resultado.class);
        it.putExtra("resultado", resultado);
        String m = media.get(Calendar.HOUR_OF_DAY) +"H "+ media.get(Calendar.MINUTE) +"m "+ media.get(Calendar.SECOND) + "s";
        it.putExtra("media", m);
        String d = desvio.get(Calendar.HOUR_OF_DAY) +"H "+ desvio.get(Calendar.MINUTE) +"m "+ desvio.get(Calendar.SECOND) + "s";
        it.putExtra("desvio", d);
        startActivity(it);
    }

    public Map<String, Calendar> getDadosCorrida()
    {
        Map<String, Calendar> corrida = new HashMap<>();
        for(int i=0; i<atletas.size();i++)
        {
            String nome = atletas.get(i).getText().toString();
            Calendar tempo = stringToCalendar(tempos.get(i).getText().toString());
            corrida.put(nome, tempo);
        }
        return corrida;
    }

    public Calendar stringToCalendar(String time)
    {
        String[] splitTime = time.split(":");
        int h = Integer.parseInt(splitTime[0]);
        int m = Integer.parseInt(splitTime[1]);
        int s = Integer.parseInt(splitTime[2]);

        Calendar c = new GregorianCalendar();
        c.set(Calendar.HOUR_OF_DAY,h);
        c.set(Calendar.MINUTE,m);
        c.set(Calendar.SECOND,s);

        return c;
    }

    public String calcularMelhorTempo(Map<String, Calendar> corrida)
    {
        Map<String, Calendar> sortedMapDesc = sortByComparator(corrida, ASC);
        return getText(sortedMapDesc);
    }

    private Map<String, Calendar> sortByComparator(Map<String, Calendar> unsortMap, final boolean order)
    {

        List<Map.Entry<String, Calendar>> list = new LinkedList<>(unsortMap.entrySet());

        // Sorting the list based on values
        Collections.sort(list, new Comparator<Map.Entry<String, Calendar>>()
        {
            public int compare(Map.Entry<String, Calendar> o1,
                               Map.Entry<String, Calendar> o2)
            {
                if (order)
                {
                    return o1.getValue().compareTo(o2.getValue());
                }
                else
                {
                    return o2.getValue().compareTo(o1.getValue());

                }
            }
        });

        // Maintaining insertion order with the help of LinkedList
        Map<String, Calendar> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Calendar> entry : list)
        {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

    public String getText(Map<String, Calendar> map)
    {
        String m = "";
        for (Map.Entry<String, Calendar> entry : map.entrySet()) {
            m += "Atleta: " + entry.getKey() + " Tempo: " + entry.getValue().get(Calendar.HOUR_OF_DAY) + "H " + entry.getValue().get(Calendar.MINUTE) + "m "+ entry.getValue().get(Calendar.SECOND) +"s ,";
        }
        return m;
    }

    public Calendar calcularMedia(Map<String, Calendar> corrida)
    {
        long total = 0;

        for (Calendar tempo : corrida.values())
        {
            total += tempo.getTimeInMillis();
        }
        long m = total / corrida.size();

        Calendar media = new GregorianCalendar();
        media.setTimeInMillis(m);
        return media;
    }

    public Calendar calcularDesvioPadrao(Map<String, Calendar> corrida)
    {
        long total = 0;

        Calendar media = calcularMedia(corrida);
        Log.i("media", media.get(Calendar.HOUR_OF_DAY)+":"+media.get(Calendar.MINUTE)+":"+media.get(Calendar.SECOND));

        for (Calendar tempo : corrida.values())
            total += Math.pow(tempo.getTimeInMillis() - media.getTimeInMillis(), 2);
        long d = (long) Math.sqrt(total / corrida.size());

        Calendar desvio = new GregorianCalendar();
        desvio.setTimeInMillis(d);
        return desvio;
    }

}

package com.univali.topicos.presidente;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MainScreen extends AppCompatActivity
{
    boolean ASC = true;
    boolean DESC = false;

    ArrayList<EditText> presidentes = new ArrayList<>();
    ArrayList<EditText> votos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        presidentes.add((EditText) findViewById(R.id.edP1));
        presidentes.add((EditText) findViewById(R.id.edP2));
        presidentes.add((EditText) findViewById(R.id.edP3));
        presidentes.add((EditText) findViewById(R.id.edP4));
        presidentes.add((EditText) findViewById(R.id.edP5));
        presidentes.add((EditText) findViewById(R.id.edP6));
        presidentes.add((EditText) findViewById(R.id.edP7));
        presidentes.add((EditText) findViewById(R.id.edP8));

        votos.add((EditText) findViewById(R.id.edV1));
        votos.add((EditText) findViewById(R.id.edV2));
        votos.add((EditText) findViewById(R.id.edV3));
        votos.add((EditText) findViewById(R.id.edV4));
        votos.add((EditText) findViewById(R.id.edV5));
        votos.add((EditText) findViewById(R.id.edV6));
        votos.add((EditText) findViewById(R.id.edV7));
        votos.add((EditText) findViewById(R.id.edV8));
    }

    public void apurarVotos(View v)
    {
        Map<String,Integer> eleicao = new HashMap<>();
        for(int i=0; i<presidentes.size();i++)
        {
            eleicao.put(presidentes.get(i).getText().toString(), Integer.parseInt(votos.get(i).getText().toString()));
        }

        Map<String, Integer> sortedMapDesc = sortByComparator(eleicao, DESC);
        String resultado = getText(sortedMapDesc);

        Intent i = new Intent(this, Resultado.class);
        i.putExtra("resultado", resultado);
        startActivity(i);
    }

    private Map<String, Integer> sortByComparator(Map<String, Integer> unsortMap, final boolean order)
    {

        List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());

        // Sorting the list based on values
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>()
        {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2)
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
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : list)
        {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

    public String getText(Map<String, Integer> map)
    {
        String m = "";
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
//            System.out.println("Key : " + entry.getKey() + " Value : "+ entry.getValue());
            m += "Candidato: " + entry.getKey() + " Nr de votos: " + entry.getValue() + ",";
        }
        return m;
    }
}

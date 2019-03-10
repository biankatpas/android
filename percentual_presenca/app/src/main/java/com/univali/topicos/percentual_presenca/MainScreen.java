package com.univali.topicos.percentual_presenca;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainScreen extends AppCompatActivity
{
    //    declara os objetos que identificam os campos de tela
    EditText edAulas;
    EditText edFaltas;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        //        associa os elementos de tela aos objetos declarados
        edAulas = (EditText) findViewById(R.id.edAulas);
        edFaltas = (EditText) findViewById(R.id.edFaltas);
    }

    public void calcularPresenca(View v)
    {
        //        declara as variaveis para efetuar o calculo da media
        int aulas = Integer.parseInt(edAulas.getText().toString());
        int faltas = Integer.parseInt(edFaltas.getText().toString());

        int aulas_assistidas = aulas - faltas;
        float presenca = (aulas_assistidas * 100) / aulas;

        //        Declara o controle Intent que sera usado para identificar a proxima tela
        Intent i = new Intent(this, Resultado.class);
//        Insere um parametro a ser utilizado na proxima tela
        i.putExtra("presenca", presenca);
//        chama a proxima tela
        startActivity(i);
    }
}

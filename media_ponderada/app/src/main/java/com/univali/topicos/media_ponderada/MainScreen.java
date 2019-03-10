package com.univali.topicos.media_ponderada;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainScreen extends AppCompatActivity
{
//    declara os objetos que identificam os campos de tela
    EditText edNota1;
    EditText edNota2;
    EditText edNota3;

    EditText edPeso1;
    EditText edPeso2;
    EditText edPeso3;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

//        associa os elementos de tela aos objetos declarados
        edNota1 = (EditText) findViewById(R.id.edNota1);
        edNota2 = (EditText) findViewById(R.id.edNota2);
        edNota3 = (EditText) findViewById(R.id.edNota3);

        edPeso1 = (EditText) findViewById(R.id.edPeso1);
        edPeso2 = (EditText) findViewById(R.id.edPeso2);
        edPeso3 = (EditText) findViewById(R.id.edPeso3);
    }

    public void calcularMediaPonderada(View v)
    {
//        declara as variaveis para efetuar o calculo da media
        float nota1 = Float.parseFloat(edNota1.getText().toString());
        float nota2 = Float.parseFloat(edNota2.getText().toString());
        float nota3 = Float.parseFloat(edNota3.getText().toString());

        int peso1 = Integer.parseInt(edPeso1.getText().toString());
        int peso2 = Integer.parseInt(edPeso2.getText().toString());
        int peso3 = Integer.parseInt(edPeso3.getText().toString());

        float media = (nota1*peso1 + nota2*peso2 + nota3*peso3) / (peso1+peso2+peso3);

//        Declara o controle Intent que sera usado para identificar a proxima tela
        Intent i = new Intent(this, Resultado.class);
//        Insere um parametro a ser utilizado na proxima tela
        i.putExtra("media", media);
//        chama a proxima tela
        startActivity(i);
    }
}

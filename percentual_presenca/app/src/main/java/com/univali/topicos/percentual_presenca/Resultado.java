package com.univali.topicos.percentual_presenca;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Resultado extends AppCompatActivity
{
    TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        //        Associa os objetos aos elementos de tela
        tvResultado = (TextView)findViewById(R.id.tvResultado);
//        Declara o objeto que recebera o gerenciador de atividades
        Intent i = getIntent();
//        le o parametro que foi passado pela tela anterior
        float presenca = i.getFloatExtra("presenca", 0f);
//        define o que sera apresentado ao usuario
        String situacao = null;
        if(presenca>=75)
            situacao = "Aprovado";
        else
            situacao = "Reprovado";
        String msg = "Você foi " + situacao + " com " + presenca + "% de presença.";
//        copia a mensagem que sera mostrada ao usuario para o campo tvResultado
        tvResultado.setText(msg);

    }

    public void voltar(View v)
    {
        Intent i = new Intent(this, MainScreen.class);
        startActivity(i);
    }
}

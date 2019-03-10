package com.univali.topicos.hipotenusa_triangulo_retangulo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainScreen extends AppCompatActivity
{

    //    declara os objetos que identificam os campos de tela
    EditText edA;
    EditText edB;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        //        associa os elementos de tela aos objetos declarados
        edA = (EditText) findViewById(R.id.edA);
        edB = (EditText) findViewById(R.id.edB);
    }

    public void calcularHipotenusa(View v)
    {
        //        declara as variaveis para efetuar o calculo da hipotenusa
        int a = Integer.parseInt(edA.getText().toString());
        int b = Integer.parseInt(edB.getText().toString());

        double hipotenusa = Math.sqrt(a*a + b*b);

        //        Declara o controle Intent que sera usado para identificar a proxima tela
        Intent i = new Intent(this, resultado.class);
//        Insere um parametro a ser utilizado na proxima tela
        i.putExtra("hipotenusa", hipotenusa);
//        chama a proxima tela
        startActivity(i);
    }
}

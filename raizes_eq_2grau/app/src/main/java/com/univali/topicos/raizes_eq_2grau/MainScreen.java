package com.univali.topicos.raizes_eq_2grau;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainScreen extends AppCompatActivity {

    //    declara os objetos que identificam os campos de tela
    EditText edAX2;
    EditText edBX;
    EditText edC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        //        associa os elementos de tela aos objetos declarados
        edAX2 = (EditText) findViewById(R.id.edAX2);
        edBX = (EditText) findViewById(R.id.edBX);
        edC = (EditText) findViewById(R.id.edC);
    }

    public void calcularRaizes(View v)
    {
        int ax2 = Integer.parseInt(edAX2.getText().toString());
        int bx = Integer.parseInt(edBX.getText().toString());
        int c = Integer.parseInt(edC.getText().toString());

        int delta = ((bx*bx)-(4*ax2*c));//Fórmula do Delta

        double x1 = 0;
        double x2 = 0;

        if (delta >= 0){ //Se o valor de delta for maior ou igual a zero podemos resolver...
            x1 = (-bx + Math.sqrt (delta)) / ( 2*ax2 );//equação do x1
            x2 = (-bx - Math.sqrt (delta)) / ( 2*ax2 ) ;//equação do x2
        }

        //        Declara o controle Intent que sera usado para identificar a proxima tela
        Intent i = new Intent(this, Resultado.class);
//        Insere um parametro a ser utilizado na proxima tela
        i.putExtra("x1", x1);
        i.putExtra("x2", x2);
//        chama a proxima tela
        startActivity(i);
    }
}

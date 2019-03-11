package com.univali.topicos.determinante;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainScreen extends AppCompatActivity
{

    //    declara os objetos que identificam os campos de tela
    EditText edL1C1;
    EditText edL1C2;
    EditText edL1C3;

    EditText edL2C1;
    EditText edL2C2;
    EditText edL2C3;

    EditText edL3C1;
    EditText edL3C2;
    EditText edL3C3;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        //        associa os elementos de tela aos objetos declarados
        edL1C1 = (EditText) findViewById(R.id.edL1C1);
        edL1C2 = (EditText) findViewById(R.id.edL1C2);
        edL1C3 = (EditText) findViewById(R.id.edL1C3);

        edL2C1 = (EditText) findViewById(R.id.edL2C1);
        edL2C2 = (EditText) findViewById(R.id.edL2C2);
        edL2C3 = (EditText) findViewById(R.id.edL2C3);

        edL3C1 = (EditText) findViewById(R.id.edL3C1);
        edL3C2 = (EditText) findViewById(R.id.edL3C2);
        edL3C3 = (EditText) findViewById(R.id.edL3C3);
    }

    public void calcularDeterminante(View v)
    {
        int l1c1 = Integer.parseInt(edL1C1.getText().toString());
        int l1c2 = Integer.parseInt(edL1C2.getText().toString());
        int l1c3 = Integer.parseInt(edL1C3.getText().toString());

        int l2c1 = Integer.parseInt(edL2C1.getText().toString());
        int l2c2 = Integer.parseInt(edL2C2.getText().toString());
        int l2c3 = Integer.parseInt(edL2C3.getText().toString());

        int l3c1 = Integer.parseInt(edL3C1.getText().toString());
        int l3c2 = Integer.parseInt(edL3C2.getText().toString());
        int l3c3 = Integer.parseInt(edL3C3.getText().toString());

        int diag_principal = l1c1 * l2c2 * l3c3;
        int principal_paralela_superior = l3c1 * l1c2 * l2c3;
        int principal_paralela_inferior = l2c1 * l3c2 * l1c3;

        int diag_secundaria = l1c3 * l2c2 * l3c1;
        int secundaria_paralela_superior = l2c1 * l1c2 * l3c3;
        int secundaria_paralela_inferior = l3c2 * l2c3 * l1c1;


        int a = diag_principal + principal_paralela_superior + principal_paralela_inferior;
        int b = diag_secundaria + secundaria_paralela_superior + secundaria_paralela_inferior;
        int determinante = a - b;

        //        Declara o controle Intent que sera usado para identificar a proxima tela
        Intent i = new Intent(this, Resultado.class);
//        Insere um parametro a ser utilizado na proxima tela
        i.putExtra("determinante", determinante);
//        chama a proxima tela
        startActivity(i);
    }
}

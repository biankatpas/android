package com.univali.topicos.imc;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainScreen extends AppCompatActivity
{
    EditText edAltura;
    EditText edPeso;
    Button btCalcular;
    TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);


        edAltura = (EditText) findViewById(R.id.edAltura);
        edPeso = (EditText) findViewById(R.id.edPeso);

        btCalcular = (Button) findViewById(R.id.btCalcular);
        btCalcular.setEnabled(false);
        btCalcular.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                boolean fieldsFilled=!edAltura.getText().toString().equals("") && !edPeso.getText().toString().equals("");
                if(fieldsFilled)
                    btCalcular.setEnabled(true);
                else
                    btCalcular.setEnabled(false);
            }
        });

        tvResultado = (TextView) findViewById(R.id.tvResultado);
    }

    public void calcularIMC(View v)
    {
        float altura = Float.parseFloat(edAltura.getText().toString());
        float peso = Float.parseFloat(edPeso.getText().toString());
        float imc = peso / (altura*altura);

        if (imc < 20)
            tvResultado.setTextColor(Color.rgb(255,0,255));
        else if (imc > 25)
            tvResultado.setTextColor(Color.rgb(255,0,0));
        else
            tvResultado.setTextColor(Color.rgb(0,0,255));

        String msg = "O valor do IMC é " + imc;
        tvResultado.setText(msg);
    }
}

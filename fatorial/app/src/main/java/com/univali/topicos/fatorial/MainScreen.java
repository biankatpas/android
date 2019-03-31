package com.univali.topicos.fatorial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import java.math.BigInteger;

public class MainScreen extends AppCompatActivity {

    EditText tiNumero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        tiNumero = (EditText) findViewById(R.id.tiNumero);
    }

    public void calcular(View v)
    {
        int n = Integer.parseInt(tiNumero.getText().toString());
        BigInteger f = factorial(n);
        Intent it = new Intent(this, Resultado.class);
        it.putExtra("fatorial", f);
        startActivity(it);
    }

    /*
     * Java method to calculate factorial of a large number
     * @return BigInteger factorial of given number
     */
    private BigInteger factorial(int number) {
        BigInteger factorial = BigInteger.ONE;

        for (int i = number; i > 0; i--) {
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }

        return factorial;
    }
}

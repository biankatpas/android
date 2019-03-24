package com.univali.topicos.corrida;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

public class Resultado extends AppCompatActivity
{

    TextView tvResultado;
    TextView tvMedia;
    TextView tvDesvio;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        tvResultado = (TextView)findViewById(R.id.tvResultado);
        tvMedia = (TextView)findViewById(R.id.tvMedia);
        tvDesvio = (TextView)findViewById(R.id.tvDesvio);

        Intent it = getIntent();
        String[] resultado = it.getStringExtra("resultado").split("\\,", -1);
        String media = it.getStringExtra("media");
        String desvio = it.getStringExtra("desvio");

        for(int i=0;i<resultado.length;i++)
            if(i==0)
                appendColoredText(tvResultado, resultado[i], Color.BLUE);
            else
                appendColoredText(tvResultado, resultado[i], Color.BLACK);

        String m = "O tempo médio foi de " + media;
        tvMedia.setText(m);
        String d = "O desvio padrão foi de " + desvio;
        tvDesvio.setText(d);

    }

    public void appendColoredText(TextView tv, String text, int color) {
        int start = tv.getText().length();
        tv.append(text);
        int end = tv.getText().length();

        Spannable spannableText = (Spannable) tv.getText();
        spannableText.setSpan(new ForegroundColorSpan(color), start, end, 0);
        tv.append("\n");
    }

    public void voltar(View v)
    {
        Intent i = new Intent(this, MainScreen.class);
        startActivity(i);
    }
}

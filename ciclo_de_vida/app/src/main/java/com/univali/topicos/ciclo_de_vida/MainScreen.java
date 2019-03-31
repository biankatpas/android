package com.univali.topicos.ciclo_de_vida;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        String m = "Loading Activity";
        System.out.println(m);
        Toast.makeText(this, m, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        String m = "Resuming Activity";
        System.out.println(m);
        Toast.makeText(this, m, Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        String m = "Restarting Activity";
        System.out.println(m);
        Toast.makeText(this, m, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        String m = "Pausing Activity";
        System.out.println(m);
        Toast.makeText(this, m, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        String m = "Destroying Activity";
        System.out.println(m);
        Toast.makeText(this, m, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        String m = "Starting Activity";
        System.out.println(m);
        Toast.makeText(this, m, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        String m = "Stop Activity";
        System.out.println(m);
        Toast.makeText(this, m, Toast.LENGTH_LONG).show();
    }
}

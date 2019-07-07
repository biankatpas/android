package com.example.biankatpas.consumirandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class LoginActivity extends AppCompatActivity {

    Pedido pedido = Pedido.getInstance();
    String id;
    String nome;
    String descricao;
    String valor;
    String quantidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        nome = intent.getStringExtra("nome");
        descricao = intent.getStringExtra("descricao");
        valor = intent.getStringExtra("valor");
        quantidade = intent.getStringExtra("quantidade");
    }

    public void login(View v){
        AcessoRest ar = new AcessoRest();
        Usuario u = new Usuario();
        Gson g = new Gson();

        EditText tvLogin = findViewById(R.id.etLogin);
        EditText tvSenha = findViewById(R.id.etSenha);

        String l = tvLogin.getText().toString();
        String s = tvSenha.getText().toString();

        Log.i("LOGIN", l);
        Log.i("SENHA", s);

        String resultado = ar.sendGet("http://192.168.0.110:8080/Produto-WS/webresources/generic/usuario/get/"+l);
        Log.i("URL", "http://192.168.0.110:8080/Produto-WS/webresources/generic/usuario/get/"+l);
        Log.i("JSON", resultado);

        Type itemType = new TypeToken<Usuario>() {}.getType();
        u = g.fromJson(resultado, itemType);

        if(u.getSenha().equals(s)){
            pedido.setLogged(true);
            pedido.setUsuario(u);
            Intent intent = new Intent(LoginActivity.this, ItemActivity.class);
            intent.putExtra("id", id);
            intent.putExtra("nome", nome);
            intent.putExtra("descricao", descricao);
            intent.putExtra("valor", valor);
            intent.putExtra("quantidade", quantidade);
            startActivity(intent);
        }else{
            Toast.makeText(getBaseContext(), "LOGIN/SENHA INCORRETOS", Toast.LENGTH_SHORT).show();
        }

    }
}

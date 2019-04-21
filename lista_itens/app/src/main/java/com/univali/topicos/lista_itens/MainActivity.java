package com.univali.topicos.lista_itens;

import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity
{

    ArrayList<Cidade> cidades;
    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = (ListView) findViewById(R.id.lvCidades);
        cidades = adicionarCidades();

        ArrayAdapter<Cidade> adapter = new CidadeAdapter(this, cidades);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cidade cidade = (Cidade) lista.getItemAtPosition(position);
                Toast.makeText(getBaseContext(), "Cidade: "+ cidade.getNome(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, CidadeActivity.class);
                intent.putExtra("id", cidade.getId());
                intent.putExtra("nome", cidade.getNome());
                intent.putExtra("fundacao", cidade.getFundacao());
                intent.putExtra("populacao", cidade.getPopulacao());
                intent.putExtra("area", cidade.getArea());
                intent.putExtra("densidade", cidade.getDensidade());
                startActivity(intent);
            }
        });
    }

    public ArrayList<Cidade> adicionarCidades()
    {
        ArrayList<Cidade> cidades = new ArrayList<>();
        AssetManager assetManager = getResources().getAssets();
        InputStream inputStream;

        try {
            inputStream = assetManager.open("cidades.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String linha;
            while((linha = bufferedReader.readLine())!=null){
                String nome="";
                String dados="";
                char[] c = linha.toCharArray();
                for ( int i = 0; i < c.length; i++ )
                    if ( Character.isLetter( c[ i ] ) )
                        nome+=c[i] ;
                    else
                        dados+=c[i];

                String[] result = dados.split(" ");
                if(result.length>6)
                {
                    Cidade cidade = new Cidade(result[0], nome, result[3], result[4], result[5], result[6]);
                    cidades.add(cidade);
                }
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cidades;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        MenuItem searchItem = menu.findItem(R.id.item_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
          @Override
          public boolean onQueryTextChange(String newText){
              ArrayList<Cidade> tempList = new ArrayList<>();
              for (Cidade temp : cidades){
                  if(temp.getNome().toLowerCase().contains(newText.toLowerCase())){
                      tempList.add(temp);
                  }
              }
              ArrayAdapter<Cidade> adapter = new CidadeAdapter(MainActivity.this, tempList);
              lista.setAdapter(adapter);

              return true;
          }

          @Override
          public boolean onQueryTextSubmit(String query){
              return false;
          }
        });
        return super.onCreateOptionsMenu(menu);
    }
}

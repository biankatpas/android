package com.univali.topicos.lista_itens;

import android.content.Intent;
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

import java.util.ArrayList;

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
                Toast.makeText(getBaseContext(), "Cidade: "+ cidades.get(position).getNome(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, CidadeActivity.class);
                intent.putExtra("nome", cidades.get(position).getNome());
                intent.putExtra("populacao", cidades.get(position).getPopulacao());
                intent.putExtra("area", cidades.get(position).getArea());
                intent.putExtra("densidade", cidades.get(position).getDensidade());
                startActivity(intent);
            }
        });
    }

    private ArrayList<Cidade> adicionarCidades()
    {
        ArrayList<Cidade> cidades = new ArrayList<>();
        Cidade c = new Cidade("Sto Amaro da Imp", "10.000", "300", "4");
        cidades.add(c);
        c = new Cidade("Fpolis", "100.000", "300", "40");
        cidades.add(c);
        c = new Cidade("São José", "100.000", "300", "40");
        cidades.add(c);
        c = new Cidade("Palhoça", "100.000", "300", "40");
        cidades.add(c);
        c = new Cidade("Biguaçu", "100.000", "300", "40");
        cidades.add(c);

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

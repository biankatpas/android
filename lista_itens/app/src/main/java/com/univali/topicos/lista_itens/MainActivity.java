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

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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

//        getDados();

        lista = (ListView) findViewById(R.id.lvCidades);
        cidades = new ArrayList<>();

        adicionarCidades();
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

    public void adicionarCidades()
    {
        Cidade c = new Cidade("Fpolis", "100.000", "300", "40");
        cidades.add(c);
        c = new Cidade("São José", "100.000", "300", "40");
        cidades.add(c);
        c = new Cidade("Palhoça", "100.000", "300", "40");
        cidades.add(c);
        c = new Cidade("Biguaçu", "100.000", "300", "40");
        cidades.add(c);
    }

    private void getDados()
    {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try  {
                    Document doc = Jsoup.connect("https://pt.wikipedia.org/wiki/Lista_de_munic%C3%ADpios_de_Santa_Catarina").get();
                    Element table_indice = doc.getElementById("toc");
                    Elements row_indice = table_indice.select("tr");
//                    for (Element r : row_indice)
//                    {
//                        Elements cell = r.select("td");
//                        for(Element c : cell)
//                        {
//                            System.out.println(c.text());
//                        }
//                    }
                    ArrayList<String> ids = new ArrayList<>();
                    ids.add("A");
//                    for(int i=2; i<ids.size();i++)
                    {
                        Element cidade = doc.getElementById(ids.get(0));
                        Elements dados_cidade = cidade.select("td");
                        for(Element dados : dados_cidade)
                        {
                            System.out.println(dados);
                        }

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
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

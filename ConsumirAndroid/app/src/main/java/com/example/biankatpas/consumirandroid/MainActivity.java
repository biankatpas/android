package com.example.biankatpas.consumirandroid;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ListView lista;
    ArrayList<Item> itens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new RetrieveFeedTask().execute("http://192.168.0.105:8080/Produto-WS/webresources/generic/item/list");
    }

    private void setItems(ArrayList<Item> items) {
        ArrayAdapter<Item> adapter = new ItemAdapter(this, items);

        lista = (ListView) findViewById(R.id.lvItem);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Item i = (Item) lista.getItemAtPosition(position);
                Toast.makeText(getBaseContext(), "Item: "+ i.getNome(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, ItemActivity.class);
                intent.putExtra("id", ""+i.getId());
                intent.putExtra("nome", i.getNome());
                intent.putExtra("descricao", i.getDescricao());
                intent.putExtra("valor", ""+i.getValor());
                startActivity(intent);
            }
        });

    }

    public void finalizarPedido(View v)
    {
        Intent intent = new Intent(MainActivity.this, PedidoActivity.class);
        startActivity(intent);
    }

    class RetrieveFeedTask extends AsyncTask<String, Void, ArrayList<Item>> {

        @Override
        protected ArrayList<Item> doInBackground(String... strings) {
            ArrayList<Item> produtos = new ArrayList<>();
            AcessoRest ar = new AcessoRest();

            try {
                Gson g = new Gson();

                String resultado = ar.exemploGet(strings[0]);
                Log.i("JSON", resultado);

                Type itemType = new TypeToken<ArrayList<Item>>() {}.getType();
                produtos = g.fromJson(resultado, itemType);

            } catch (Exception ex) {}

            return produtos;
        }

        @Override
        protected void onPostExecute(ArrayList<Item> result) {
            super.onPostExecute(result);
            setItems(result);
            itens = result;
        }

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
                ArrayList<Item> tempList = new ArrayList<>();
                for (Item temp : itens){
                    if(temp.getNome().toLowerCase().contains(newText.toLowerCase())){
                        tempList.add(temp);
                    }
                }
                ArrayAdapter<Item> adapter = new ItemAdapter(MainActivity.this, tempList);
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

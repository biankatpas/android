package com.univali.topicos.cardapio;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {

    ArrayList<Item> items;
    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new RetrieveFeedTask().execute("https://www.w3schools.com/xml/simple.xml");
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
                intent.putExtra("nome", i.getNome());
                intent.putExtra("descricao", i.getDescricao());
                intent.putExtra("calorias", i.getCalorias());
                intent.putExtra("preco", i.getPreco());
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
            ArrayList<Item> foods = new ArrayList<>();

            try {
                URL url = new URL(strings[0]);

                InputStream in = url.openStream();

                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(in);

                NodeList nodeList = doc.getElementsByTagName("food");

                for (int temp = 0; temp < nodeList.getLength(); temp++) {

                    Node nNode = nodeList.item(temp);

                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                        Element eElement = (Element) nNode;
                        String name = eElement.getElementsByTagName("name").item(0).getTextContent();
                        String price = eElement.getElementsByTagName("price").item(0).getTextContent();
                        String description = eElement.getElementsByTagName("description").item(0).getTextContent();
                        String calories = eElement.getElementsByTagName("calories").item(0).getTextContent();

                        foods.add(new Item(name, description, calories, price));
                    }
                }


            } catch (SAXException | ParserConfigurationException | IOException e) {
                e.printStackTrace();
            }

            return foods;
        }

        @Override
        protected void onPostExecute(ArrayList<Item> result) {
            super.onPostExecute(result);
            setItems(result);
        }

    }
}



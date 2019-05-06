package com.univali.topicos.cardapio;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class PedidoActivity extends AppCompatActivity
{

    Pedido pedido = Pedido.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        setDate();
        setHour();
        setCaloriasTotais();
        setValorTotal();

    }

    private void setHour()
    {
        SimpleDateFormat dateFormat_hora = new SimpleDateFormat("HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        Date data_atual = cal.getTime();
        TextView tvHorario = findViewById(R.id.tvHorario);
        tvHorario.setText("Horário: " + dateFormat_hora.format(data_atual));
    }

    private void setDate()
    {
        SimpleDateFormat dateFormat_data = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        Date data_atual = cal.getTime();
        TextView tvData = findViewById(R.id.tvData);
        tvData.setText("Data: " + dateFormat_data.format(data_atual));
    }

    private void setCaloriasTotais()
    {
        int caloriasTotais = 0;
        ArrayList<Item> items = pedido.getItems();
        for(Item i : items)
        {
            int calorias = Integer.parseInt(i.getCalorias());
            int qtd = Integer.parseInt(i.getQuantidade());
            caloriasTotais+=calorias*qtd;
        }
        TextView tvCaloriasTotais = findViewById(R.id.tvCaloriasTotais);
        tvCaloriasTotais.setText("Calorias totais: " + caloriasTotais);
    }

    private void setValorTotal()
    {
        float valorTotal = 0;
        ArrayList<Item> items = pedido.getItems();
        for(Item i : items)
        {
            float valor = Float.parseFloat(i.getPreco().split("\\$")[1]);
            int qtd = Integer.parseInt(i.getQuantidade());
            valorTotal+=valor*qtd;
        }
        TextView tvValorTotal = findViewById(R.id.tvValorTotal);
        tvValorTotal.setText("Valor total: " + valorTotal);
    }

    public void enviarPedido(View v)
    {
        try
        {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = null;
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            Element order = doc.createElement("order");
            Element created = doc.createElement("created");
            Element date = doc.createElement("date");
            Element hour = doc.createElement("hour");
            Element name = doc.createElement("name");
            Element address = doc.createElement("address");
            Element phone_number = doc.createElement("phone_number");
            Element items = doc.createElement("items");

            TextView tvData = findViewById(R.id.tvData);
            date.setAttribute("date", tvData.getText().toString().split(" ")[1]);

            TextView tvHour = findViewById(R.id.tvHorario);
            hour.setAttribute("hour", tvHour.getText().toString().split(" ")[1]);

            TextView tvNome = findViewById(R.id.hNome);
            name.setAttribute("name", tvNome.getText().toString());

            TextView tvEndereco = findViewById(R.id.hEndereco);
            address.setAttribute("address", tvEndereco.getText().toString());

            TextView tvTelefone = findViewById(R.id.hTelefone);
            phone_number.setAttribute("phone_number", tvTelefone.getText().toString());

            ArrayList<Item> itemsList = pedido.getItems();
            for(Item i : itemsList)
            {
                Element item = doc.createElement("item");
                item.setAttribute("name", i.getNome());
                item.setAttribute("quantity", i.getQuantidade());
                items.appendChild(item);
            }
            created.appendChild(date);
            created.appendChild(hour);
            order.appendChild(created);
            order.appendChild(name);
            order.appendChild(address);
            order.appendChild(phone_number);
            order.appendChild(items);

            doc.appendChild(order);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

            DOMSource domSource = new DOMSource(doc);
            StringWriter sw = new StringWriter();
            StreamResult sr = new StreamResult(sw);
            transformer.transform(domSource, sr);

            System.out.println(sw.toString());

        } catch (ParserConfigurationException | TransformerException e)
        {
            e.printStackTrace();
        }


    }
}

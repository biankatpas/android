package com.univali.topicos.cardapio;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new RetrieveFeedTask().execute("https://www.w3schools.com/xml/simple.xml");

    }

    class RetrieveFeedTask extends AsyncTask<String, Void, Void>
    {

        @Override
        protected Void doInBackground(String... strings)
        {
            try
            {
                URL url = new URL(strings[0]);

                InputStream in = url.openStream();

                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(in);

                System.out.println("Root element : "+doc.getDocumentElement().getNodeName());

                NodeList nodeList = doc.getElementsByTagName("food");

                System.out.println("----------------------------");


                for (int temp = 0; temp < nodeList.getLength(); temp++) {

                    Node nNode = nodeList.item(temp);

                    System.out.println("\nCurrent Element :" + nNode.getNodeName());

                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                        Element eElement = (Element) nNode;

                        System.out.println("Name : " + eElement.getAttribute("name"));
                        System.out.println("Price : " + eElement.getElementsByTagName("price").item(0).getTextContent());
                        System.out.println("Description : " + eElement.getElementsByTagName("description").item(0).getTextContent());
                        System.out.println("Calories : " + eElement.getElementsByTagName("calories").item(0).getTextContent());

                    }
                }

            }
            catch (SAXException | ParserConfigurationException | IOException e)
            {
                e.printStackTrace();
            }

            return null;
        }
    }
}


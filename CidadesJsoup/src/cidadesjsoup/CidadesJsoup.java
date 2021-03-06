/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cidadesjsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.PrintWriter;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.nodes.Element;

/**
 *
 * @author biankatpas
 */
public class CidadesJsoup {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FileWriter arq = null;
        try {
            arq = new FileWriter("cidades.txt");
            PrintWriter gravarArq = new PrintWriter(arq);
            Document doc = null;
           
            doc = Jsoup.connect("https://censo2010.ibge.gov.br/sinopse/index.php?uf=42&dados=0").get();
            List<Element> elements = doc.getElementById("div_tabela_dados").select("table").select("tbody").select("tr");
            for (Element e : elements)
            {
                System.out.println(e.text());
                gravarArq.printf(e.text()+"\n");
            }
            arq.close();

        } catch (IOException ex) {
            Logger.getLogger(CidadesJsoup.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                arq.close();
            } catch (IOException ex) {
                Logger.getLogger(CidadesJsoup.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    
}

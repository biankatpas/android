/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Item;

/**
 *
 * @author biankatpas
 */
public class ItemDAO 
{

    public Item buscar(Item item)
    {
        String sql = "SELECT * FROM item where nome=?";
        Item retorno = null;
        
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        try {
           
            pst.setString(1, item.getNome());
            ResultSet res = pst.executeQuery();
            
            if(res.next())
            {
                retorno = new Item();
                retorno.setId(res.getLong("id"));
                retorno.setDescricao(res.getString("descricao"));
                retorno.setValor(res.getDouble("valor"));
            }
            
        } catch (SQLException ex) 
        {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return retorno;
    
    }
    
    public List<Item> listar() 
    {
        String sql = "SELECT * FROM item";
        List<Item> retorno = new ArrayList<>();

        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        try 
        {
            ResultSet res = pst.executeQuery();
            while (res.next()) 
            {
                Item item = new Item();
                item.setId(res.getLong("id"));
                item.setNome(res.getString("nome"));
                item.setDescricao(res.getString("descricao"));
                item.setValor(res.getDouble("valor"));

                retorno.add(item);
            }

        } catch (SQLException ex) 
        {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);

        }

        return retorno;

    }
}

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
import modelo.PedidoCompra;
import modelo.Usuario;

/**
 *
 * @author biankatpas
 */
public class PedidoCompraDAO {

    public boolean inserir(PedidoCompra pedido) {
        String sql = "INSERT INTO pedidocompra(data,hora,usuario) VALUES(?,?,?)";
        Boolean retorno = false;
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        try {
            pst.setString(1, pedido.getData());
            pst.setString(2, pedido.getHora());
            pst.setLong(3, pedido.getUsuario().getId());

            if (pst.executeUpdate() > 0) {
                retorno = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(PedidoCompraDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        }

        return retorno;

    }

    public List<PedidoCompra> buscar(Usuario usuario) {
        String sql = "SELECT * FROM pedidocompra where usuario=?";
         List<PedidoCompra> retorno = new ArrayList<>();
         
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        try {
            
            pst.setLong(1, usuario.getId());
            ResultSet res = pst.executeQuery();
            while(res.next())
            {
                PedidoCompra pedido = new PedidoCompra();
                pedido.setData(res.getString("data"));
                pedido.setHora(res.getString("hora"));
                pedido.setId(res.getLong("id"));
                retorno.add(pedido);
            }
               
        } catch (SQLException ex) {
            Logger.getLogger(PedidoCompraDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return retorno;
    }
    
    public boolean excluir(PedidoCompra pedido) {
        String sql = "DELETE FROM pedidocompra where id=?";
        Boolean retorno = false;
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        try {

            pst.setLong(1, pedido.getId());
            if (pst.executeUpdate() > 0) {
                retorno = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(PedidoCompraDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        }

        return retorno;

    }

}

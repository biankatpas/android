/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.ItemPedido;
import modelo.PedidoCompra;

/**
 *
 * @author biankatpas
 */
public class ItemPedidoDAO {
    
    public boolean inserir(ItemPedido item) {
        String sql = "INSERT INTO itempedido(quantidade,pedidocompra,valor,item) VALUES(?,?,?,?)";
        Boolean retorno = false;
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        try {
            pst.setInt(1, item.getQuantidade());
            pst.setLong(2, 1);
            pst.setDouble(3, item.getValor());
            pst.setLong(4, item.getItem().getId());

            if (pst.executeUpdate() > 0) {
                retorno = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(PedidoCompraDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        }

        return retorno;

    }
    
    public boolean excluir(PedidoCompra pedido) {
        String sql = "DELETE FROM itempedido where pedidocompra=?";
        Boolean retorno = false;
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        try {

            pst.setLong(1, pedido.getId());
            if (pst.executeUpdate() > 0) {
                retorno = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ItemPedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        }

        return retorno;

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Usuario;

/**
 *
 * @author biankatpas
 */
public class UsuarioDAO {

    public Usuario buscar(Usuario usuario)
    {
        String sql = "SELECT * FROM usuario where login=?";
        Usuario retorno = null;
        
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        try {
           
            pst.setString(1, usuario.getLogin());
            ResultSet res = pst.executeQuery();
            
            if(res.next())
            {
                retorno = new Usuario();
                retorno.setId(res.getLong("id"));
                retorno.setLogin(res.getString("login"));
                retorno.setSenha(res.getString("senha"));
                retorno.setNumeroCartao(res.getString("numerocartao"));
                retorno.setCodigoSeguranca(res.getInt("codigoseguranca"));
                retorno.setDataValidade(res.getString("datavalidade"));
                
            }
            
        } catch (SQLException ex) 
        {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return retorno;
    
    }
    
     public boolean atualizar(Usuario usuario)
    {
        String sql = "UPDATE usuario set numerocartao=?,codigoseguranca=?,datavalidade=? where login=?";
        Boolean retorno = false;
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        try {
          
            pst.setString(1, usuario.getNumeroCartao());
            pst.setInt(2, usuario.getCodigoSeguranca());
            pst.setString(3, usuario.getDataValidade());
            pst.setString(4, usuario.getLogin());
            if(pst.executeUpdate()>0)
            {
                retorno = true;
            }
                
            
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        }
        
        return retorno;
    
    }

}

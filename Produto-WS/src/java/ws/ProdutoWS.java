/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import dao.UsuarioDAO;
import dao.ItemDAO;
import dao.ItemPedidoDAO;
import dao.PedidoCompraDAO;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import modelo.Item;
import modelo.PedidoCompra;
import modelo.Usuario;

/**
 * REST Web Service
 *
 * @author biankatpas
 */
@Path("generic")
public class ProdutoWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ProdutoWS
     */
    public ProdutoWS() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        return "Hello, Produto-WS!";
    }

//    -----------------------------------------------------------------------------------------------------------------------
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("usuario/get/{login}")
    public String getUsuario(@PathParam("login") String login) {
        Usuario u = new Usuario();
        u.setLogin(login);

        UsuarioDAO dao = new UsuarioDAO();
        u = dao.buscar(u);

        Gson g = new Gson();
        return g.toJson(u);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("usuario/alterar")
    public void alterar(String content) {
        Gson g = new Gson();
        Usuario u = (Usuario) g.fromJson(content, Usuario.class);
        UsuarioDAO dao = new UsuarioDAO();
        dao.atualizar(u);
    }

//    -----------------------------------------------------------------------------------------------------------------------
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("item/list")
    public String listItens() {
        List<Item> lista = new ArrayList<>();
        ItemDAO dao = new ItemDAO();
        lista = dao.listar();

        Gson g = new Gson();
        return g.toJson(lista);
    }

//    -----------------------------------------------------------------------------------------------------------------------
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("pedidocompra/inserir")
    public boolean inserir(String content) {
        Gson g = new Gson();
        PedidoCompra p = (PedidoCompra) g.fromJson(content, PedidoCompra.class);
        PedidoCompraDAO dao = new PedidoCompraDAO();
        ItemPedidoDAO dao2 = new ItemPedidoDAO();

        dao.inserir(p);
        dao2.inserir(p.getItens().get(0));

        return true;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("pedidocompra/buscar/{usuario}")
    public String buscarPedidos(@PathParam("usuario") String usuario) {
        Usuario u = new Usuario();
        u.setId(Long.parseLong(usuario));

        List<PedidoCompra> lista = new ArrayList<>();
        PedidoCompraDAO dao = new PedidoCompraDAO();
        lista = dao.buscar(u);

        Gson g = new Gson();
        return g.toJson(lista);
    }

    @DELETE
    @Path("pedidocompra/excluir/")
    public boolean excluir(String content) {
        Gson g = new Gson();
        PedidoCompra p = (PedidoCompra) g.fromJson(content, PedidoCompra.class);

        PedidoCompraDAO dao = new PedidoCompraDAO();
        ItemPedidoDAO dao2 = new ItemPedidoDAO();

        dao.excluir(p);
        dao2.excluir(p);

        return true;
    }

}

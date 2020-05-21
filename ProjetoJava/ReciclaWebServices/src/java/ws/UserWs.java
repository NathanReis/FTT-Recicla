/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import java.sql.SQLException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import recicla.business.basis.FabricaRepositorio;
import recicla.business.crud.CadastraUsuario;
import recicla.comuns.crud.basis.Entidade;
import recicla.comuns.enums.EntidadesDisponiveis;
import recicla.comuns.vos.Usuario;
import recicla.dao.acesso.UsuarioMySQLDAO;
import recicla.dao.repositorio.basis.Repositorio;

/**
 * REST Web Service
 *
 * @author vitorlupinetti
 */
@Path("user")
public class UserWs {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UserWs
     */
    public UserWs() {
    }

    /**
     * Retrieves representation of an instance of ws.UserWs
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/obtem-usuario/{user}/{senha}")
    public String getUser(@PathParam("user") String user, @PathParam("senha") String senha) throws SQLException {
        //TODO return proper representation object
        Usuario u = new Usuario();
        UsuarioMySQLDAO dao = new UsuarioMySQLDAO();
        
        u = (Usuario)dao.buscarPorCredenciais(user, senha);
        
    
        Gson g = new Gson();
        return g.toJson(u);
    }

    /**
     * PUT method for updating or creating an instance of UserWs
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/adciona-usuario")
    public String addUser(String user) throws SQLException {
        Gson g = new Gson();
        Usuario u = g.fromJson(user, Usuario.class);
        CadastraUsuario crud = new CadastraUsuario();
        //UsuarioMySQLDAO dao = new UsuarioMySQLDAO();
        crud.InsereUsuario(u);
        //dao.inserir(u);
        return g.toJson(u);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import recicla.business.validations.IValidation;
import recicla.business.validations.UsuarioValidation;
import recicla.comuns.vos.ItemLoja;
import recicla.comuns.vos.Jogo;
import recicla.comuns.vos.Usuario;
import recicla.dao.acesso.UsuarioMySQLDAO;
import recicla.dao.jogo.JogoMySQLDAO;

/**
 *
 * @author vitorlupinetti
 */
@Path("jogos")
public class JogoWs {
    @Context
    private UriInfo context;
    JogoMySQLDAO dao;
    IValidation validation;
    /**
     * Creates a new instance of UserWs
     */
    public JogoWs() {
        dao = new JogoMySQLDAO();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listar-jogos")
    public String getGames() throws SQLException {
        //TODO return proper representation object
                  
        List<Jogo> jogos = new ArrayList<>();
        jogos = dao.listar();
        Gson g = new Gson();
       
        return g.toJson(jogos);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/obtem-jogo-por-id/{gameId}")
    public String getGameById(@PathParam("gameId") int gameId) throws SQLException {
        //TODO return proper representation object
                  
        List<Jogo> jogos = new ArrayList<>();
        Jogo jogo = (Jogo) dao.consultar(gameId);
        
        Gson g = new Gson();
        
        return g.toJson(jogo);
    }

  
}

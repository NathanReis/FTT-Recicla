package ws;

import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import recicla.business.validations.IValidation;
import recicla.comuns.vos.Jogo;
import recicla.comuns.vos.JogoRodada;
import recicla.comuns.vos.Rodada;
import recicla.dao.jogo.JogoMySQLDAO;
import recicla.dao.sala.JogoRodadaMySQLDAO;

@Path("jogos")
public class JogoWs {
    @Context
    private UriInfo context;
    JogoMySQLDAO dao;
    JogoRodadaMySQLDAO jogoRodadaDao;

    IValidation validation;
    /**
     * Creates a new instance of UserWs
     */
    public JogoWs() {
        dao = new JogoMySQLDAO();
        jogoRodadaDao = new JogoRodadaMySQLDAO();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listar-jogos")
    public String getGames() throws SQLException {
        List<Jogo> jogos = new ArrayList<>();
        jogos = dao.listar();
        Gson g = new Gson();
       
        return g.toJson(jogos);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/obtem-jogo-por-id/{gameId}")
    public String getGameById(@PathParam("gameId") int gameId) throws SQLException {
        // List<Jogo> jogos = new ArrayList<>();
        Jogo jogo = (Jogo)dao.consultar("JogoId", gameId);
        
        Gson g = new Gson();
        
        return g.toJson(jogo);
    }

     @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/obtem-jogos-por-rodadaId/{rodadaId}")
    public String getGameByRoundId(@PathParam("rodadaId") int rodadaId) throws SQLException {
        List<JogoRodada> jogos = new ArrayList<>();
        jogos = jogoRodadaDao.listarPorRodadaId(rodadaId);
        
        Gson g = new Gson();
        
        return g.toJson(jogos);
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/adciona-jogo-rodada")
    public String addGameRound(String roundJson) throws SQLException {
        Gson g = new Gson();
        JogoRodada r = g.fromJson(roundJson, JogoRodada.class);
        jogoRodadaDao.inserir(r);
       return g.toJson(r);              
    }
  
}

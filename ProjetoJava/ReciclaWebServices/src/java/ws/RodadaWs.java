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
import recicla.business.crud.CadastraRodadaXAluno;
import recicla.business.validations.IValidation;
import recicla.comuns.vos.ItemLojaXUsuario;
import recicla.comuns.vos.Jogo;
import recicla.comuns.vos.JogoRodada;
import recicla.comuns.vos.Rodada;
import recicla.comuns.vos.RodadaXAluno;
import recicla.dao.sala.JogoRodadaMySQLDAO;
import recicla.dao.sala.RodadaMySQLDAO;
import recicla.dao.sala.RodadaXAlunoMySQLDAO;

@Path("rodada")
public class RodadaWs {
    @Context
    private UriInfo context;
    RodadaMySQLDAO dao;
    RodadaXAlunoMySQLDAO rodadaAlunoDao;

    IValidation validation;
    /**
     * Creates a new instance of UserWs
     */
    public RodadaWs() {
        dao = new RodadaMySQLDAO();
        //validation = new SalaValidation();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listar-por-salaId/{salaId}")
    public String getRoundsByRoomId(@PathParam("salaId") int salaId) throws SQLException {
        List<Rodada> rodadas = new ArrayList<>();
        rodadas = dao.listarPorSalaId(salaId);
        Gson g = new Gson();
       
        return g.toJson(rodadas);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/obtem-rodada-por-salaId/{salaId}")
    public String getActualRoundByRoomId(@PathParam("salaId") int salaId) throws SQLException {
        List<Rodada> rodadas = new ArrayList<>();
        rodadas = dao.listarPorSalaId(salaId);
        Rodada rodadaAtual = rodadas.get(rodadas.size() - 1);
        Gson g = new Gson();
       
        return g.toJson(rodadaAtual);
    }

    /**
     * PUT method for updating or creating an instance of UserWs
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/inicia-rodada")
    public String beginRound(String rodada) throws SQLException {
        Gson g = new Gson();
        Rodada rodadaRetorno = g.fromJson(rodada,Rodada.class);
        
        dao.atualiza_rodada(rodadaRetorno);
        //dao.inserir(itemRetorno);
        
        return g.toJson(rodadaRetorno);
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/adciona-rodada")
    public String addRound(String roundJson) throws SQLException {
        Gson g = new Gson();
        Rodada r = g.fromJson(roundJson, Rodada.class);
        
        dao.inserir(r);
        
       return g.toJson(dao.getIdInserido());              
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/adciona-jogo-rodada/")
    public String addGameOnRound(String gameRoundJson) throws SQLException {
        Gson g = new Gson();
        JogoRodadaMySQLDAO jogoRodadaDAO = new JogoRodadaMySQLDAO();
        JogoRodada jogoRodada = g.fromJson(gameRoundJson, JogoRodada.class);
        
        jogoRodadaDAO.inserir(jogoRodada);
        
       return g.toJson(jogoRodadaDAO.getIdInserido());              
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/adciona-rodada-aluno")
    public String addStudentRound(String roundJson) throws SQLException {
        Gson g = new Gson();
        RodadaXAluno r = g.fromJson(roundJson, RodadaXAluno.class);
        CadastraRodadaXAluno crud = new CadastraRodadaXAluno();
        boolean isValid = crud.InsereRodadaXAluno(r);
        
        if(isValid)
            return g.toJson(r);     
        else
            return null;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listar-rodadaAluno-por-userId/{userId}")
    public String getStudentRoundByUserId(@PathParam("userId") int userId) throws SQLException {
        List<RodadaXAluno> rodadas = new ArrayList<>();
        rodadas = rodadaAlunoDao.listarPorUsuarioId(userId);
        Gson g = new Gson();
       
        return g.toJson(rodadas);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listar-rodadaAluno-por-rodadaId/{rodadaId}")
    public String getStudentRoundByRoundId(@PathParam("rodadaId") int rodadaId) throws SQLException {
        List<RodadaXAluno> rodadas = new ArrayList<>();
        rodadas = rodadaAlunoDao.listarPorRodadaId(rodadaId);
        Gson g = new Gson();
       
        return g.toJson(rodadas);
    }
}

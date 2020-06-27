/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import java.sql.SQLException;
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
import recicla.comuns.vos.Recorde;
import recicla.comuns.vos.RodadaXAluno;
import recicla.dao.recorde.RecordeMySQLDAO;
import recicla.dao.sala.RodadaXAlunoMySQLDAO;

/**
 *
 * @author vitorlupinetti
 */
@Path("recorde")
public class RecordeWs {
     @Context
    private UriInfo context;
    RecordeMySQLDAO dao;
    IValidation validation;
    /**
     * Creates a new instance of UserWs
     */
    public RecordeWs() {
        dao = new RecordeMySQLDAO();
        //validation = new SalaValidation();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/obtem-recorde-por-idUsuario/{usuarioId}")
    public String getRecordByUser(@PathParam("usuarioId") int usuarioId) throws SQLException {
        Recorde r = (Recorde)dao.consultar("UsuarioId", usuarioId);
        Gson g = new Gson();
       
        return g.toJson(r);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/obtem-recorde-por-jogoId/{jogoId}")

    public String getRecordByGame(@PathParam("jogoId") int jogoId) throws SQLException { 
        Recorde r = (Recorde)dao.consultar("JogoId", jogoId);
        Gson g = new Gson();
       
        return g.toJson(r);
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
    @Path("/adciona-recorde")
    public String addRecord(String recordeJson) throws SQLException {
        Gson g = new Gson();
        Recorde r = g.fromJson(recordeJson, Recorde.class);
        dao.inserir(r);
        
       return g.toJson(r);
               
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/adciona-pontos")
    public String addPoints(String rodadaAlunoJson) throws SQLException {
        Gson g = new Gson();
        RodadaXAluno r = g.fromJson(rodadaAlunoJson, RodadaXAluno.class);
        RodadaXAlunoMySQLDAO rodadaAlunoDao = new RodadaXAlunoMySQLDAO();
        rodadaAlunoDao.adicionarPontos(r.getUsuarioId(), r.getPontos());
        
        
       return g.toJson(r);
               
    }
}

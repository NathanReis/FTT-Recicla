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
import recicla.business.validations.IValidation;
import recicla.business.validations.PerguntaQuizValidation;
import recicla.comuns.vos.PerguntaQuiz;
import recicla.dao.jogo.PerguntaQuizMySQLDAO;

/**
 * REST Web Service
 *
 * @author vitorlupinetti
 */
@Path("pergunta-quiz")
public class PerguntaQuizWs {

    @Context
    private UriInfo context;
    PerguntaQuizMySQLDAO dao;
    IValidation validation;
    /**
     * Creates a new instance of UserWs
     */
    public PerguntaQuizWs() {
        dao = new PerguntaQuizMySQLDAO();
        validation = new PerguntaQuizValidation();
    }

    /**
     * Retrieves representation of an instance of ws.UserWs
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listar-perguntas")
    public String getQuestions() throws SQLException {
        //TODO return proper representation object
                
        List<PerguntaQuiz> perguntas = new ArrayList<>();
        perguntas = dao.listar();
        
        Gson g = new Gson();
       
        return g.toJson(perguntas);
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
    @Path("/adcionar-pergunta")
    public String addQuestion(String question) throws SQLException {
        Gson g = new Gson();
        PerguntaQuiz pergunta = g.fromJson(question, PerguntaQuiz.class);
        boolean isValid = validation.validate(pergunta);
        
        if(isValid){
            dao.inserir(pergunta);
            return g.toJson(pergunta);
        }
        else
            return null;               
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/obtem-pergunta-por-id/{questionId}")
    public String getQuestionById(@PathParam("questionId") int questionId) throws SQLException {
        //TODO return proper representation object
                  
       
        PerguntaQuiz pergunta = (PerguntaQuiz) dao.consultar(questionId);
        System.out.print(pergunta);
        Gson g = new Gson();
        
        return g.toJson(pergunta);
    }
}

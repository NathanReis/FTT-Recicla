package ws;

import com.google.gson.Gson;
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
import recicla.business.validations.IValidation;
import recicla.business.validations.SalaValidation;
import recicla.comuns.vos.Sala;
import recicla.dao.sala.SalaMySQLDAO;
import recicla.business.crud.CadastraSala;

/**
 * REST Web Service
 *
 * @author vitorlupinetti
 */
@Path("sala")
public class SalaWs {

    @Context
    private UriInfo context;
    SalaMySQLDAO dao;
    IValidation validation;
    /**
     * Creates a new instance of UserWs
     */
    public SalaWs() {
        dao = new SalaMySQLDAO();
        validation = new SalaValidation();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/obtem-sala-por-id/{salaId}")
    public String getRoomById(@PathParam("salaId") int salaId) throws SQLException {
        Sala sala = (Sala)dao.consultar("SalaId", salaId);
        Gson g = new Gson();
       
        return g.toJson(sala);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/obtem-sala-por-chave/{chave}")
    public String getRoomByKey(@PathParam("chave") String chave) throws SQLException { 
        Sala sala = (Sala)dao.consultar("ChaveAcesso", chave);
        Gson g = new Gson();
       
        return g.toJson(sala);
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
    @Path("/adciona-sala")
    public String addRoom(String salaJson) throws SQLException, Exception {
        Gson g = new Gson();
        Sala sala = g.fromJson(salaJson, Sala.class);
        System.out.print(g);
        CadastraSala crud = new CadastraSala();
        boolean isValid = crud.InsereSala(sala);
        if(isValid){
            return g.toJson(sala);
        }
        else
            return null;              
    }
}

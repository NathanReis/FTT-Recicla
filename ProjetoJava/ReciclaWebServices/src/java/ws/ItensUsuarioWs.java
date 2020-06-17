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
import recicla.comuns.vos.ItemLojaXUsuario;
import recicla.dao.loja.ItemLojaXUsuarioMySQLDAO;

/**
 * REST Web Service
 *
 * @author vitorlupinetti
 */
@Path("itens")
public class ItensUsuarioWs {

    @Context
    private UriInfo context;
    ItemLojaXUsuarioMySQLDAO dao;
    /**
     * Creates a new instance of UserWs
     */
    public ItensUsuarioWs() {
        dao = new ItemLojaXUsuarioMySQLDAO();
    }

    /**
     * Retrieves representation of an instance of ws.UserWs
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/obtem-itens-usuario/{userId}")
    public String getUserItensByUserId(@PathParam("userId") int userId) throws SQLException {
        List<ItemLojaXUsuario> itens = new ArrayList<>();
        itens = dao.listarPorUsuarioId(userId);
    
        Gson g = new Gson();
        return g.toJson(itens);
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
    @Path("/adciona-item-usuario")
    public String addUserItem(String item) throws SQLException {
        Gson g = new Gson();
        ItemLojaXUsuario itemRetorno = g.fromJson(item,ItemLojaXUsuario.class);

        dao.inserir(itemRetorno);
        
        return g.toJson(itemRetorno);
    }
}

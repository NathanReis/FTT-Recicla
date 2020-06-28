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
import recicla.business.validations.UsuarioValidation;
import recicla.comuns.vos.ItemLojaXUsuario;
import recicla.comuns.vos.Usuario;
import recicla.dao.acesso.UsuarioMySQLDAO;

/**
 * REST Web Service
 *
 * @author vitorlupinetti
 */
@Path("user")
public class UserWs {

    @Context
    private UriInfo context;
    UsuarioMySQLDAO dao;
    IValidation validation;
    /**
     * Creates a new instance of UserWs
     */
    public UserWs() {
        dao = new UsuarioMySQLDAO();
        validation = new UsuarioValidation();
    }

    /**
     * Retrieves representation of an instance of ws.UserWs
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/obtem-usuario/{user}/{senha}")
    public String getUser(@PathParam("user") String user, @PathParam("senha") String senha) throws SQLException {
        Usuario u = (Usuario)dao.buscarPorCredenciais(user, senha);   
        System.out.print(u);
        Gson g = new Gson();
       
        return g.toJson(u);
    }
    
     @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/obtem-usuario-por-id/{userId}")
    public String getUserById(@PathParam("userId") int userId) throws SQLException {
        Usuario u = (Usuario)dao.consultar("UsuarioId", userId);
        System.out.print(u);
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
        boolean isValid = validation.validate(u);
        
        if(isValid){
            dao.inserir(u);
            return g.toJson(u);
        }
        else
            return null;
               
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/atualiza-usuario")
    public String updateUser(String userJson) throws SQLException {
        Gson g = new Gson();
        Usuario user = g.fromJson(userJson,Usuario.class);
        
        dao.atualiza_usuario(user);
        //dao.inserir(itemRetorno);
        
        return g.toJson(user);
    }
}

package ws;

import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import recicla.comuns.vos.ItemLoja;
import recicla.comuns.vos.ItemLojaXUsuario;
import recicla.dao.loja.ItemLojaMySQLDAO;

@Path("itens")
public class ItensWs {
    private ItemLojaMySQLDAO dao;
    public ItensWs(){
        dao = new ItemLojaMySQLDAO();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listar-itens")
    public String getAllItens() throws SQLException {
        
        List<ItemLoja> itens = new ArrayList<>();
        itens = dao.listar();
    
        Gson g = new Gson();
        return g.toJson(itens);
    }
    
     @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/obtem-item/{itemId}")
    public String getItemById(@PathParam("itemId") String itemId) throws SQLException {
        ItemLoja item = (ItemLoja)dao.consultar(itemId);
    
        Gson g = new Gson();
        return g.toJson(item);
    }
}

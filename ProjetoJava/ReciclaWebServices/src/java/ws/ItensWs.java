package ws;

import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import recicla.comuns.vos.ItemLoja;
import recicla.comuns.vos.ItemLojaXUsuario;
import recicla.dao.loja.ItemLojaMySQLDAO;

@Path("itens")
public class ItensWs {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listar-itens")
    public String getAllItens() throws SQLException {
        ItemLojaMySQLDAO dao = new ItemLojaMySQLDAO();
        List<ItemLoja> itens = new ArrayList<>();
        itens = dao.listar();
    
        Gson g = new Gson();
        return g.toJson(itens);
    }
}

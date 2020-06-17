/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recicla.business.crud;

import com.google.gson.Gson;
import java.sql.SQLException;
import recicla.business.httpRequests.httpRequest;
import recicla.comuns.vos.ItemLojaXUsuario;
import recicla.comuns.vos.Usuario;
import recicla.dao.acesso.UsuarioMySQLDAO;

/**
 *
 * @author vitorlupinetti
 */
public class CrudItens {
    
    public boolean insereItemUsuario(ItemLojaXUsuario item) throws Exception{
        boolean retorno = true;
        Gson g = new Gson();

        try {
            String userItemJson = g.toJson(item);
            System.out.print(userItemJson);
            httpRequest.sendPut(userItemJson, "itens/adciona-item-usuario");
            
        } catch (SQLException ex) {
            retorno = false;
        }
        
        return retorno;
    }
}

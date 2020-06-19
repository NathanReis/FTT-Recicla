/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recicla.business.crud;

import com.google.gson.Gson;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import recicla.business.httpRequests.httpRequest;
import recicla.business.validations.SalaValidation;
import recicla.comuns.vos.Sala;
import recicla.dao.acesso.UsuarioMySQLDAO;
import recicla.dao.sala.SalaMySQLDAO;

/**
 *
 * @author italo
 */
public class CadastraSala {
    String url = "sala/adciona-sala";
    SalaMySQLDAO dao = new SalaMySQLDAO();

    public boolean InsereSala(Sala sala) throws Exception{
        boolean retorno = true;
        
         try {
            
            sala.setChaveAcesso(codigo_de_acesso());
            SalaValidation valida_sala = new SalaValidation();
            retorno = valida_sala.validate(sala);
            if(retorno == false){
                return false;
            }

            dao.inserir(sala);

            

        } catch (SQLException ex) {
            retorno = false;
        }
         return retorno;
    }
    
    public String codigo_de_acesso() {
        String codigo = "";

        Random rand = new Random();
        for (int i = 0; i < 4; i++) {
            codigo +=  String.valueOf(rand.nextInt(6));
        }
        return codigo;
    } 
  

}

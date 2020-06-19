/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recicla.business.crud;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import recicla.business.validations.SalaValidation;
import recicla.comuns.vos.Sala;
import recicla.dao.acesso.UsuarioMySQLDAO;
import recicla.dao.sala.SalaMySQLDAO;

/**
 *
 * @author italo
 */
public class CadastraSala {
    
    public boolean InsereSala(Sala sala){
        boolean retorno = true;
        
         try {
            SalaValidation valida_sala = new SalaValidation();
            boolean isValid = valida_sala.validate(sala);
            
            if(isValid == false){
                return false;
            }
            sala.setChaveAcesso(codigo_de_acesso(sala.getDescricao()));
            SalaMySQLDAO dao = new SalaMySQLDAO();
            dao.inserir(sala);
            
        } catch (SQLException ex) {
            retorno = false;
        }
         return retorno;
    }
    
    public String codigo_de_acesso(String nome) {

        DateTimeFormatter yearformatter = DateTimeFormatter.ofPattern("yyyy");
        DateTimeFormatter monthrformatter = DateTimeFormatter.ofPattern("MM");
        LocalDateTime now = LocalDateTime.now();

        return nome + "_" + monthrformatter.format(now) + "_" + yearformatter.format(now);

    }
    
}

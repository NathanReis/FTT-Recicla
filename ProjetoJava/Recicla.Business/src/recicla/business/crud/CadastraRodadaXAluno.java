/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recicla.business.crud;

import java.sql.SQLException;
import recicla.business.validations.RodadaXAlunoValidation;
import recicla.comuns.vos.RodadaXAluno;
import recicla.dao.sala.RodadaXAlunoMySQLDAO;

/**
 *
 * @author italo
 */
public class CadastraRodadaXAluno {
    
    public boolean InsereRodadaXAluno(RodadaXAluno rodadaxAluno){
        boolean retorno = true;
        
         try {
            RodadaXAlunoValidation valida_rodada_x_aluno = new RodadaXAlunoValidation();
            boolean isValid = valida_rodada_x_aluno.validate(rodadaxAluno);
            
            if(isValid == false){
                return false;
            }
            
            RodadaXAlunoMySQLDAO dao = new RodadaXAlunoMySQLDAO();
            dao.inserir(rodadaxAluno);
            
        } catch (SQLException ex) {
            retorno = false;
        }
         return retorno;
    }
    
}

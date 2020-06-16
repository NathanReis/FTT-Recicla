/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recicla.business.validations;

import recicla.comuns.crud.basis.Entidade;
import recicla.comuns.vos.RodadaXAluno;

/**
 *
 * @author italo
 */
public class RodadaXAlunoValidation implements IValidation {

    @Override
    public boolean validate(Entidade e) {
        boolean isValid = true;
        RodadaXAluno rodadaXaluno = (RodadaXAluno) e;

        if (rodadaXaluno.getRodadaId() == 0) {
            isValid = false;
        }
        if (rodadaXaluno.getUsuarioId() == 0) {
            isValid = false;
        }

        return isValid;
    }
    
}

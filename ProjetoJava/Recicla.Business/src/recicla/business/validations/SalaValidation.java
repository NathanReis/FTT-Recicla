/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recicla.business.validations;

import recicla.comuns.crud.basis.Entidade;
import recicla.comuns.vos.Sala;

/**
 *
 * @author vitorlupinetti
 */
public class SalaValidation implements IValidation {

    @Override
    public boolean validate(Entidade e) {
        boolean isValid = true;
        Sala sala = (Sala)e;
        
        if(sala.getDescricao().trim().length() == 0)
            isValid = false;
        if(sala.getChaveAcesso().trim().length() == 0)
           isValid = false;
                
        return isValid;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recicla.business.validations;

import recicla.comuns.crud.basis.Entidade;
import recicla.comuns.vos.Usuario;

/**
 *
 * @author vitorlupinetti
 */
public class UsuarioValidation implements IValidation {

    @Override
    public boolean validate(Entidade e) {
        boolean isValid = true;
        Usuario user = (Usuario) e;
        if(user.getUsuario().trim().length() == 0)
            isValid = false;
        if(user.getNome().trim().length() == 0)
            isValid = false;
        if(user.getSenha().trim().length() == 0)
            isValid = false;
        
        return isValid;
    }
    
}

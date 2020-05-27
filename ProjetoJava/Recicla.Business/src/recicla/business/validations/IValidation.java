/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recicla.business.validations;

import recicla.comuns.crud.basis.Entidade;

/**
 *
 * @author vitorlupinetti
 */
public interface IValidation {
    boolean validate(Entidade e);
}

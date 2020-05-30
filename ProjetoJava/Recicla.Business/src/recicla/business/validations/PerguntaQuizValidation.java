/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recicla.business.validations;

import recicla.comuns.crud.basis.Entidade;
import recicla.comuns.vos.PerguntaQuiz;

/**
 *
 * @author vitorlupinetti
 */
public class PerguntaQuizValidation implements IValidation {

    @Override
    public boolean validate(Entidade e) {
        boolean isValid = true;
        PerguntaQuiz pergunta = (PerguntaQuiz)e;
        
        if(pergunta.getPergunta().trim().length() == 0)
            isValid = false;
        if(pergunta.getAlternativa1().trim().length() == 0)
            isValid = false;
        if(pergunta.getAlternativa2().trim().length() == 0)
            isValid = false;
        if(pergunta.getRespostaCorreta().trim().length() == 0)
            isValid = false;
        
        return isValid;
    }
    
}

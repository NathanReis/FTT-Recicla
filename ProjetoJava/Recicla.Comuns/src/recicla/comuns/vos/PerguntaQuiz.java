/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recicla.comuns.vos;

import recicla.comuns.crud.basis.Entidade;

/**
 *
 * @author italo
 */
public class PerguntaQuiz extends Entidade {
    private String Pergunta;
    private String RespostaCorreta;
    private String Alternativa1;
    private String Alternativa2;
    private int JogoId;

    /**
     * @return the Pergunta
     */
    public String getPergunta() {
        return Pergunta;
    }

    /**
     * @param Pergunta the Pergunta to set
     */
    public void setPergunta(String Pergunta) {
        this.Pergunta = Pergunta;
    }

    /**
     * @return the RespostaCorreta
     */
    public String getRespostaCorreta() {
        return RespostaCorreta;
    }

    /**
     * @param RespostaCorreta the RespostaCorreta to set
     */
    public void setRespostaCorreta(String RespostaCorreta) {
        this.RespostaCorreta = RespostaCorreta;
    }

    /**
     * @return the Alternativa1
     */
    public String getAlternativa1() {
        return Alternativa1;
    }

    /**
     * @param Alternativa1 the Alternativa1 to set
     */
    public void setAlternativa1(String Alternativa1) {
        this.Alternativa1 = Alternativa1;
    }

    /**
     * @return the Alternativa2
     */
    public String getAlternativa2() {
        return Alternativa2;
    }

    /**
     * @param Alternativa2 the Alternativa2 to set
     */
    public void setAlternativa2(String Alternativa2) {
        this.Alternativa2 = Alternativa2;
    }

    /**
     * @return the JogoId
     */
    public int getJogoId() {
        return JogoId;
    }

    /**
     * @param JogoId the JogoId to set
     */
    public void setJogoId(int JogoId) {
        this.JogoId = JogoId;
    }
}

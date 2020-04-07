package recicla.comuns.vos;

import annotation.CampoNoBanco;
import recicla.comuns.crud.basis.Entidade;

public class PerguntaQuiz extends Entidade {
    @CampoNoBanco(nome = "PerguntaQuizId", chave = true)
    private int PerguntaQuizId;
    @CampoNoBanco(nome = "Pergunta", chave = false)
    private String Pergunta;
    @CampoNoBanco(nome = "RespostaCorreta", chave = false)
    private String RespostaCorreta;
    @CampoNoBanco(nome = "Alternativa1", chave = false)
    private String Alternativa1;
    @CampoNoBanco(nome = "Alternativa2", chave = false)
    private String Alternativa2;
    @CampoNoBanco(nome = "JogoId", chave = false)
    private int JogoId;
    
    /**
     * @return the PerguntaQuizId
     */
    public int getPerguntaQuizId() {
        return PerguntaQuizId;
    }

    /**
     * @param PerguntaQuizId the PerguntaQuizId to set
     */
    public void setPerguntaQuizId(int PerguntaQuizId) {
        this.PerguntaQuizId = PerguntaQuizId;
    }

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
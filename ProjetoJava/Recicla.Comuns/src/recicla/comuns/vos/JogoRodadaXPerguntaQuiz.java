package recicla.comuns.vos;

import annotation.CampoNoBanco;
import recicla.comuns.crud.basis.Entidade;

public class JogoRodadaXPerguntaQuiz extends Entidade {
    @CampoNoBanco(nome = "JogoRodadaId", chave = true)
    private int JogoRodadaId;
    
    @CampoNoBanco(nome = "PerguntaQuizId", chave = true)
    private int PerguntaQuizId;

    /**
     * @return the JogoRodadaId
     */
    public int getJogoRodadaId() {
        return JogoRodadaId;
    }

    /**
     * @param JogoRodadaId the JogoRodadaId to set
     */
    public void setJogoRodadaId(int JogoRodadaId) {
        this.JogoRodadaId = JogoRodadaId;
    }

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
}

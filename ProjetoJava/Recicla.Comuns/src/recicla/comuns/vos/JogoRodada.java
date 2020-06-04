package recicla.comuns.vos;

import annotation.CampoNoBanco;
import recicla.comuns.crud.basis.Entidade;

public class JogoRodada extends Entidade {
    @CampoNoBanco(nome = "JogoRodadaId", chave = true)
    private int JogoRodadaId;
    
    @CampoNoBanco(nome = "JogoId", chave = false)
    private int JogoId;
    
    @CampoNoBanco(nome = "RodadaId", chave = false)
    private int RodadaId;

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

    /**
     * @return the RodadaId
     */
    public int getRodadaId() {
        return RodadaId;
    }

    /**
     * @param RodadaId the RodadaId to set
     */
    public void setRodadaId(int RodadaId) {
        this.RodadaId = RodadaId;
    }
}

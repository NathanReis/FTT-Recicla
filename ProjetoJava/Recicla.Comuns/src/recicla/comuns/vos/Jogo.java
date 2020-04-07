package recicla.comuns.vos;

import annotation.CampoNoBanco;
import recicla.comuns.crud.basis.Entidade;

public class Jogo extends Entidade {
    @CampoNoBanco(nome = "JogoId", chave = true)
    private int JogoId;
    @CampoNoBanco(nome = "Descricao", chave = false)
    private String Descricao;
    
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
     * @return the Descricao
     */
    public String getDescricao() {
        return Descricao;
    }

    /**
     * @param Descricao the Descricao to set
     */
    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }
}
package recicla.comuns.vos;

import annotation.CampoNoBanco;
import recicla.comuns.crud.basis.Entidade;

public class Recorde extends Entidade {
    @CampoNoBanco(nome = "RecordeId", chave = true)
    private int RecordeId;
    @CampoNoBanco(nome = "JogoId", chave = false)
    private int JogoId;
    @CampoNoBanco(nome = "UsuarioId", chave = false)
    private int UsuarioId;
    @CampoNoBanco(nome = "QtdPartidade", chave = false)
    private int QtdPartidade;
    @CampoNoBanco(nome = "QtdVitorias", chave = false)
    private int QtdVitorias;
    @CampoNoBanco(nome = "MelhorTempo", chave = false)
    private float MelhorTempo;
    @CampoNoBanco(nome = "Pontos", chave = false)
    private int Pontos;
    
    /**
     * @return the RecordeId
     */
    public int getRecordeId() {
        return RecordeId;
    }

    /**
     * @param RecordeId the RecordeId to set
     */
    public void setRecordeId(int RecordeId) {
        this.RecordeId = RecordeId;
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
     * @return the UsuarioId
     */
    public int getUsuarioId() {
        return UsuarioId;
    }

    /**
     * @param UsuarioId the UsuarioId to set
     */
    public void setUsuarioId(int UsuarioId) {
        this.UsuarioId = UsuarioId;
    }

    /**
     * @return the QtdPartidade
     */
    public int getQtdPartidade() {
        return QtdPartidade;
    }

    /**
     * @param QtdPartidade the QtdPartidade to set
     */
    public void setQtdPartidade(int QtdPartidade) {
        this.QtdPartidade = QtdPartidade;
    }

    /**
     * @return the QtdVitorias
     */
    public int getQtdVitorias() {
        return QtdVitorias;
    }

    /**
     * @param QtdVitorias the QtdVitorias to set
     */
    public void setQtdVitorias(int QtdVitorias) {
        this.QtdVitorias = QtdVitorias;
    }

    /**
     * @return the MelhorTempo
     */
    public float getMelhorTempo() {
        return MelhorTempo;
    }

    /**
     * @param MelhorTempo the MelhorTempo to set
     */
    public void setMelhorTempo(float MelhorTempo) {
        this.MelhorTempo = MelhorTempo;
    }

    /**
     * @return the Pontos
     */
    public int getPontos() {
        return Pontos;
    }

    /**
     * @param Pontos the Pontos to set
     */
    public void setPontos(int Pontos) {
        this.Pontos = Pontos;
    }
}
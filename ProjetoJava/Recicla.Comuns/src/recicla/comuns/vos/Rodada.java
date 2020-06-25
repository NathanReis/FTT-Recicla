package recicla.comuns.vos;

import annotation.CampoNoBanco;
import recicla.comuns.crud.basis.Entidade;

public class Rodada extends Entidade {
    @CampoNoBanco(nome = "RodadaId", chave = true)
    private int RodadaId;
    @CampoNoBanco(nome = "SalaId", chave = false)
    private int SalaId;
    
    private int StatusRodada;
    
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

    /**
     * @return the SalaId
     */
    public int getSalaId() {
        return SalaId;
    }

    /**
     * @param SalaId the SalaId to set
     */
    public void setSalaId(int SalaId) {
        this.SalaId = SalaId;
    }

    /**
     * @return the StatusRodada
     */
    public int getStatusRodada() {
        return StatusRodada;
    }

    /**
     * @param StatusRodada the StatusRodada to set
     */
    public void setStatusRodada(int StatusRodada) {
        this.StatusRodada = StatusRodada;
    }
}
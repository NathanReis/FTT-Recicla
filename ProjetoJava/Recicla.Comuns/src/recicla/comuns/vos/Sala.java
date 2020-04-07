package recicla.comuns.vos;

import annotation.CampoNoBanco;
import recicla.comuns.crud.basis.Entidade;

public class Sala extends Entidade {
    @CampoNoBanco(nome = "SalaId", chave = true)
    private int SalaId;
    @CampoNoBanco(nome = "Descricao", chave = false)
    private String Descricao;
    @CampoNoBanco(nome = "ChaveAcesso", chave = false)
    private String ChaveAcesso;
    @CampoNoBanco(nome = "HorarioInicio", chave = false)
    private float HorarioInicio;
    
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

    /**
     * @return the ChaveAcesso
     */
    public String getChaveAcesso() {
        return ChaveAcesso;
    }

    /**
     * @param ChaveAcesso the ChaveAcesso to set
     */
    public void setChaveAcesso(String ChaveAcesso) {
        this.ChaveAcesso = ChaveAcesso;
    }

    /**
     * @return the HorarioInicio
     */
    public float getHorarioInicio() {
        return HorarioInicio;
    }

    /**
     * @param HorarioInicio the HorarioInicio to set
     */
    public void setHorarioInicio(float HorarioInicio) {
        this.HorarioInicio = HorarioInicio;
    }
}
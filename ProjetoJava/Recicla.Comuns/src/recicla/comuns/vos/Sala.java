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
public class Sala extends Entidade {
    private String Descricao;
    private String ChaveAcesso;
    private float HorarioInicio;

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

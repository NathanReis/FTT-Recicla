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
public class Recorde extends Entidade {
    private int JogoId;
    private int UsuarioId;
    private int QtdPartidade;
    private int QtdVitorias;
    private float MelhorTempo;
    private int Pontos;

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

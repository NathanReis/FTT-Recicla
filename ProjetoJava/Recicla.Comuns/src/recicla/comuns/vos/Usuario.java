/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recicla.comuns.vos;

import recicla.comuns.crud.basis.Entidade;

/**
 *
 * @author vitorlupinetti
 */
public class Usuario extends Entidade {
    
    private String Nome;
    private String Usuario;
    private String Senha;
    private char TipoUsuario;
    private int SalaId;
    private double Dinheiro;
    
    /**
     * @return the Nome
     */
    public String getNome() {
        return Nome;
    }

    /**
     * @param Nome the Nome to set
     */
    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    /**
     * @return the Usuario
     */
    public String getUsuario() {
        return Usuario;
    }

    /**
     * @param Usuario the Usuario to set
     */
    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    /**
     * @return the Senha
     */
    public String getSenha() {
        return Senha;
    }

    /**
     * @param Senha the Senha to set
     */
    public void setSenha(String Senha) {
        this.Senha = Senha;
    }

    /**
     * @return the TipoUsuario
     */
    public char getTipoUsuario() {
        return TipoUsuario;
    }

    /**
     * @param TipoUsuario the TipoUsuario to set
     */
    public void setTipoUsuario(char TipoUsuario) {
        this.TipoUsuario = TipoUsuario;
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
     * @return the Dinheiro
     */
    public double getDinheiro() {
        return Dinheiro;
    }

    /**
     * @param Dinheiro the Dinheiro to set
     */
    public void setDinheiro(double Dinheiro) {
        this.Dinheiro = Dinheiro;
    }
  
}

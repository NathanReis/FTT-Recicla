package recicla.business.config;

import recicla.comuns.enums.TipoRepositorio;
import recicla.comuns.vos.Usuario;

public class Config {
    private static Config uniqueInstance;
 
    private Config() {
    }
 
    public static synchronized Config getInstance() {
        if (uniqueInstance == null)
            uniqueInstance = new Config();
 
        return uniqueInstance;
    }
    
    private TipoRepositorio tipoRepositorio; 
    private Usuario loggedUser;
    private int salaAtualEditando;
    private int rodadaAtualEditando;
    private int pontuacaoRodada;

    public int getPontuacaoRodada() {
        return pontuacaoRodada;
    }

    public void setPontuacaoRodada(int pontuacaoRodada) {
        this.pontuacaoRodada = pontuacaoRodada;
    }

    public TipoRepositorio getTipoRepositorio() {
        return tipoRepositorio;
    }
    
    public void setTipoRepositorio(TipoRepositorio tipoRepositorio) {
        this.tipoRepositorio = tipoRepositorio;
    }
    
    public void setLoggedUser (Usuario usuario){
        this.loggedUser = usuario;
    }
    
    public Usuario getLoggedUser (){
        return this.loggedUser;
    }

    /**
     * @return the salaAtualEditando
     */
    public int getSalaAtualEditando() {
        return salaAtualEditando;
    }

    /**
     * @param salaAtualEditando the salaAtualEditando to set
     */
    public void setSalaAtualEditando(int salaAtualEditando) {
        this.salaAtualEditando = salaAtualEditando;
    }

    /**
     * @return the rodadaAtualEditando
     */
    public int getRodadaAtualEditando() {
        return rodadaAtualEditando;
    }

    /**
     * @param rodadaAtualEditando the rodadaAtualEditando to set
     */
    public void setRodadaAtualEditando(int rodadaAtualEditando) {
        this.rodadaAtualEditando = rodadaAtualEditando;
    }
}
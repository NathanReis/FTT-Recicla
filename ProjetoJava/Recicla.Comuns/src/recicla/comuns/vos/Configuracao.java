package recicla.comuns.vos;

import annotation.CampoNoBanco;
import recicla.comuns.crud.basis.Entidade;

public class Configuracao extends Entidade {
    @CampoNoBanco(nome = "ConfiguracaoId", chave = true)
    private int ConfiguracaoId;
    
    @CampoNoBanco(nome = "Musica", chave = false)
    private String Musica;
    
    @CampoNoBanco(nome = "Som", chave = false)
    private String Som;

    /**
     * @return the ConfiguracaoId
     */
    public int getConfiguracaoId() {
        return ConfiguracaoId;
    }

    /**
     * @param ConfiguracaoId the ConfiguracaoId to set
     */
    public void setConfiguracaoId(int ConfiguracaoId) {
        this.ConfiguracaoId = ConfiguracaoId;
    }

    /**
     * @return the Musica
     */
    public String getMusica() {
        return Musica;
    }

    /**
     * @param Musica the Musica to set
     */
    public void setMusica(String Musica) {
        this.Musica = Musica;
    }
    
    /**
     * @return the Som
     */
    public String getSom() {
        return Som;
    }

    /**
     * @param Som the Som to set
     */
    public void setSom(String Som) {
        this.Som = Som;
    }
}

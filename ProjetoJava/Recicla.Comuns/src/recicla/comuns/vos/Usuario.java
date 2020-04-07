package recicla.comuns.vos;

import annotation.CampoNoBanco;
import recicla.comuns.crud.basis.Entidade;

public class Usuario extends Entidade {
    @CampoNoBanco(nome = "UsuarioId", chave = true)
    private int UsuarioId;
    @CampoNoBanco(nome = "Nome", chave = false)
    private String Nome;
    @CampoNoBanco(nome = "Usuario", chave = false)
    private String Usuario;
    @CampoNoBanco(nome = "Senha", chave = false)
    private String Senha;
    @CampoNoBanco(nome = "TipoUsuario", chave = false)
    private String TipoUsuario;
    @CampoNoBanco(nome = "SalaId", chave = false)
    private int SalaId;
    @CampoNoBanco(nome = "Dinheiro", chave = false)
    private double Dinheiro;
    
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
    public String getTipoUsuario() {
        return TipoUsuario;
    }

    /**
     * @param TipoUsuario the TipoUsuario to set
     */
    public void setTipoUsuario(String TipoUsuario) {
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
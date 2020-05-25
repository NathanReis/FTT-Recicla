package recicla.comuns.vos;

import annotation.CampoNoBanco;
import recicla.comuns.crud.basis.Entidade;

public class ItemLojaXUsuario extends Entidade {
    @CampoNoBanco(nome = "ItemLojaId", chave = true)
    private int ItemLojaId;
    
    @CampoNoBanco(nome = "UsuarioId", chave = true)
    private int UsuarioId;
    
    @CampoNoBanco(nome = "Quantidade", chave = false)
    private int Quantidade;

    /**
     * @return the ItemLojaId
     */
    public int getItemLojaId() {
        return ItemLojaId;
    }

    /**
     * @param ItemLojaId the ItemLojaId to set
     */
    public void setItemLojaId(int ItemLojaId) {
        this.ItemLojaId = ItemLojaId;
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
     * @return the Quantidade
     */
    public int getQuantidade() {
        return Quantidade;
    }

    /**
     * @param Quantidade the Quantidade to set
     */
    public void setQuantidade(int Quantidade) {
        this.Quantidade = Quantidade;
    }
}

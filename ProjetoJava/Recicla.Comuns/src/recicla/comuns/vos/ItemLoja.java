package recicla.comuns.vos;

import annotation.CampoNoBanco;
import recicla.comuns.crud.basis.Entidade;

public class ItemLoja extends Entidade {
    @CampoNoBanco(nome = "ItemLojaId", chave = true)
    private int ItemLojaId;
    @CampoNoBanco(nome = "Nome", chave = false)
    private String Nome;
    @CampoNoBanco(nome = "Descricao", chave = false)
    private String Descricao;
    @CampoNoBanco(nome = "Preco", chave = false)
    private double Preco;
    
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
     * @return the Preco
     */
    public double getPreco() {
        return Preco;
    }

    /**
     * @param Preco the Preco to set
     */
    public void setPreco(double Preco) {
        this.Preco = Preco;
    }
}
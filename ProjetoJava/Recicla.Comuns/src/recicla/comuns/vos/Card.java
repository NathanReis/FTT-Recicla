/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recicla.comuns.vos;

import java.awt.Image;
import recicla.comuns.crud.basis.Entidade;

/**
 *
 * @author italo
 */
public class Card extends Entidade {
    
    private int id_carta;
    private boolean status;
    private Image imagem;

    /**
     * @return the id_carta
     */
    public int getId_carta() {
        return id_carta;
    }

    /**
     * @param id_carta the id_carta to set
     */
    public void setId_carta(int id_carta) {
        this.id_carta = id_carta;
    }

    /**
     * @return the status
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * @return the imagem
     */
    public Image getImagem() {
        return imagem;
    }

    /**
     * @param imagem the imagem to set
     */
    public void setImagem(Image imagem) {
        this.imagem = imagem;
    }
    
}

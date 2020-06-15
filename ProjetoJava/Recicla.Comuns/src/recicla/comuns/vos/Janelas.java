/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recicla.comuns.vos;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 *
 * @author cliente
 */
public class Janelas {
    
    private int id;
    private boolean estado;
    private Image imagem;
    
    public void setId(int id){
        this.id = id;
    }
    
    public int getId(){
        return this.id;
    }
     
     public void setEstado(boolean estado){
        this.estado = estado;
    }
     
     public boolean getEstado(){
        return this.estado;
    }
     
    public Image getImagem(){
        return this.imagem;
    }
    
    public void setImagem(Image imagem){
        this.imagem = imagem;
    }
}

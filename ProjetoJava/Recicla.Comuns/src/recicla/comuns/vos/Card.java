package recicla.comuns.vos;

import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import recicla.comuns.crud.basis.Entidade;

public class Card extends Entidade {
    private int id_carta;
    private boolean status;
    private Image imagem;
    private int id_carta_par;
    
    public Card(int id){
        this.id_carta = id;     
    }
    
    /**
     * @return the id_carta_par
     */    
    public int getId_carta_par(){
        return id_carta_par;
    }
    
    /**
     * @param id_carta_par the status to set
     */    
    public void setId_carta_par(int id_carta_par){
        this.id_carta_par = id_carta_par;
    }

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
    
    public List<Card> Lista_cards_randomicos(){
        List<Card> lista_cards = new ArrayList<Card>();
        Random rand = new Random();
        boolean status = false;
        int n=0;
        
        while (!status) {
            Card carta = new Card(rand.nextInt(11));
            if( carta.id_carta == 0)
                continue;
            if (!lista_cards.stream().anyMatch(x -> x.id_carta == carta.id_carta)) {
                lista_cards.add(carta);
                n++;
            }
            if (n == 10) 
                status = true;
        }

        return lista_cards;
    }
    
    public static boolean Verifica_Par_cartas(int numero, List<Card> cartas) {
        if (cartas.stream().anyMatch(x -> x.id_carta_par == numero)) {
            return true;
        } else {
            return false;
        }
    }    
}

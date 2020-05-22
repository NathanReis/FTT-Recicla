/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recicla.presentation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import recicla.comuns.vos.Card;


/**
 * FXML Controller class
 *
 * @author italo
 */
public class TelaJogoMemoriaController implements Initializable {

    @FXML
    private Label txtDinheiro;
    @FXML
    private Label txtTempo;
    @FXML
    private Label txtTempo1;
    @FXML
    private ImageView img_card_1;
    @FXML
    private ImageView img_card_2;
    @FXML
    private ImageView img_card_6;
    @FXML
    private ImageView img_card_7;
    @FXML
    private ImageView img_card_8;
    @FXML
    private ImageView img_card_9;
    @FXML
    private ImageView img_card_10;
    @FXML
    private ImageView img_card_3;
    @FXML
    private ImageView img_card_4;
    @FXML
    private ImageView img_card_5;
    @FXML
    private Label txtPontuacao;

    /**
     * Initializes the controller class.
     */
    //lista global das cartas
    List<Card> cartas;
    Image card_background; 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Card carta = new Card(0);
        cartas = carta.Lista_cards_randomicos();
        try {
            Monta_Jogo_da_Memoria(cartas); 
            card_background = new Image(new FileInputStream("card_background.png"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TelaJogoMemoriaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //Este método monta o tabuleiro de forma randomica
    public void Monta_Jogo_da_Memoria(List<Card> cartas) throws FileNotFoundException {
        int n = 1;
        Random rand = new Random();
        int card_aleatorio;
        boolean flagto0 = true;
        boolean status = true;
        int i = 9;
        while (status) {
            //carta aleatória de 0 a 4
            card_aleatorio = rand.nextInt(5);

            if (!Card.Verifica_Par_cartas(card_aleatorio, cartas) || (card_aleatorio == 0 && flagto0)) {

                //defino as imagens
                cartas.get(i).setImagem(Instancia_Imagens(n));
                cartas.get(card_aleatorio).setImagem(Instancia_Imagens(n));

                //defino os pares
                cartas.get(i).setId_carta_par(card_aleatorio);
                cartas.get(card_aleatorio).setId_carta_par(i);

                //acréscimo do n
                n++;

                //decréscimo do i
                i--;

                if (card_aleatorio == 0) {
                    flagto0 = false;
                }
            }

            if (i < 5) {
                status = false;
            }

        }

    }

    public Image Instancia_Imagens(int numero_imagem) throws FileNotFoundException{
         
        switch (numero_imagem) {
            case 1: {
                Image image = new Image(new FileInputStream("card_1.png"));
                return image;
            }
            case 2: {
                Image image = new Image(new FileInputStream("card_2.png"));
                return image;
            }
            case 3: {
                Image image = new Image(new FileInputStream("card_3.png"));
                return image;
            }
            case 4: {
                Image image = new Image(new FileInputStream("card_4.png"));
                return image;
            }
            case 5: {
                Image image = new Image(new FileInputStream("card_5.png"));
                return image;
            }

        }

        return null;
    }

    //Evento do Card 1
    @FXML
    private void Card_Event(MouseEvent event) {
        
        Card carta = cartas.stream().filter(x -> x.getId_carta() == 1).findFirst().get();

        if (carta != null) {
            if (!carta.isStatus()) {
                img_card_1.setImage(carta.getImagem());
                cartas.stream().filter(x -> x.getId_carta() == 1).findFirst().get().setStatus(true);
            } else {
                img_card_1.setImage(card_background);
                cartas.stream().filter(x -> x.getId_carta() == 1).findFirst().get().setStatus(false);
            }

        } else {
            System.out.println("Erro na busca da carta");
        }
        
    }
   //Evento do Card 2
    @FXML
    private void Card_Event_2(MouseEvent event) {
        Card carta = cartas.stream().filter(x -> x.getId_carta() == 2).findFirst().get();

        if (carta != null) {
            if (!carta.isStatus()) {
                img_card_2.setImage(carta.getImagem());
                cartas.stream().filter(x -> x.getId_carta() == 2).findFirst().get().setStatus(true);
            } else {
                img_card_2.setImage(card_background);
                cartas.stream().filter(x -> x.getId_carta() == 2).findFirst().get().setStatus(false);
            }

        } else {
            System.out.println("Erro na busca da carta");
        }
    }
    //Evento do Card 6
    @FXML
    private void Card_Event_6(MouseEvent event) {
        Card carta = cartas.stream().filter(x -> x.getId_carta() == 6).findFirst().get();

        if (carta != null) {
            if (!carta.isStatus()) {
                img_card_6.setImage(carta.getImagem());
                cartas.stream().filter(x -> x.getId_carta() == 6).findFirst().get().setStatus(true);
            } else {
                img_card_6.setImage(card_background);
                cartas.stream().filter(x -> x.getId_carta() == 6).findFirst().get().setStatus(false);
            }

        } else {
            System.out.println("Erro na busca da carta");
        }
    }

    @FXML
    private void Card_Event_7(MouseEvent event) {
        Card carta = cartas.stream().filter(x -> x.getId_carta() == 7).findFirst().get();

        if (carta != null) {
            if (!carta.isStatus()) {
                img_card_7.setImage(carta.getImagem());
                cartas.stream().filter(x -> x.getId_carta() == 7).findFirst().get().setStatus(true);
            } else {
                img_card_7.setImage(card_background);
                cartas.stream().filter(x -> x.getId_carta() == 7).findFirst().get().setStatus(false);
            }

        } else {
            System.out.println("Erro na busca da carta");
        }
    }

    @FXML
    private void Card_Event_8(MouseEvent event) {
        Card carta = cartas.stream().filter(x -> x.getId_carta() == 8).findFirst().get();

        if (carta != null) {
            if (!carta.isStatus()) {
                img_card_8.setImage(carta.getImagem());
                cartas.stream().filter(x -> x.getId_carta() == 8).findFirst().get().setStatus(true);
            } else {
                img_card_8.setImage(card_background);
                cartas.stream().filter(x -> x.getId_carta() == 8).findFirst().get().setStatus(false);
            }

        } else {
            System.out.println("Erro na busca da carta");
        }
    }

    @FXML
    private void Card_Event_9(MouseEvent event) {
        Card carta = cartas.stream().filter(x -> x.getId_carta() == 9).findFirst().get();

        if (carta != null) {
            if (!carta.isStatus()) {
                img_card_9.setImage(carta.getImagem());
                cartas.stream().filter(x -> x.getId_carta() == 9).findFirst().get().setStatus(true);
            } else {
                img_card_9.setImage(card_background);
                cartas.stream().filter(x -> x.getId_carta() == 9).findFirst().get().setStatus(false);
            }

        } else {
            System.out.println("Erro na busca da carta");
        }
    }

    @FXML
    private void Card_Event_10(MouseEvent event) {
         Card carta = cartas.stream().filter(x -> x.getId_carta() == 10).findFirst().get();

        if (carta != null) {
            if (!carta.isStatus()) {
                img_card_10.setImage(carta.getImagem());
                cartas.stream().filter(x -> x.getId_carta() == 10).findFirst().get().setStatus(true);
            } else {
                img_card_10.setImage(card_background);
                cartas.stream().filter(x -> x.getId_carta() == 10).findFirst().get().setStatus(false);
            }

        } else {
            System.out.println("Erro na busca da carta");
        }
    }

    @FXML
    private void Card_Event_3(MouseEvent event) {
        Card carta = cartas.stream().filter(x -> x.getId_carta() == 3).findFirst().get();

        if (carta != null) {
            if (!carta.isStatus()) {
                img_card_3.setImage(carta.getImagem());
                cartas.stream().filter(x -> x.getId_carta() == 3).findFirst().get().setStatus(true);
            } else {
                img_card_3.setImage(card_background);
                cartas.stream().filter(x -> x.getId_carta() == 3).findFirst().get().setStatus(false);
            }

        } else {
            System.out.println("Erro na busca da carta");
        }
    }

    @FXML
    private void Card_Event_4(MouseEvent event) {
        Card carta = cartas.stream().filter(x -> x.getId_carta() == 4).findFirst().get();

        if (carta != null) {
            if (!carta.isStatus()) {
                img_card_4.setImage(carta.getImagem());
                cartas.stream().filter(x -> x.getId_carta() == 4).findFirst().get().setStatus(true);
            } else {
                img_card_4.setImage(card_background);
                cartas.stream().filter(x -> x.getId_carta() == 4).findFirst().get().setStatus(false);
            }

        } else {
            System.out.println("Erro na busca da carta");
        }
    }

    @FXML
    private void Card_Event_5(MouseEvent event) {
        Card carta = cartas.stream().filter(x -> x.getId_carta() == 5).findFirst().get();

        if (carta != null) {
            if (!carta.isStatus()) {
                img_card_5.setImage(carta.getImagem());
                cartas.stream().filter(x -> x.getId_carta() == 5).findFirst().get().setStatus(true);
            } else {
                img_card_5.setImage(card_background);
                cartas.stream().filter(x -> x.getId_carta() == 5).findFirst().get().setStatus(false);
            }

        } else {
            System.out.println("Erro na busca da carta");
        }
    }
    
}

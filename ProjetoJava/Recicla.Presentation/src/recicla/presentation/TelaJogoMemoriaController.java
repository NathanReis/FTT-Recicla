/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recicla.presentation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
    private int pontos = 0;
    /**
     * Initializes the controller class.
     */
    //lista global das cartas
    List<Card> cartas;
    Image card_background; 
    @FXML
    private Button btn_compara_cartas;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Card carta = new Card(0);
        cartas = carta.Lista_cards_randomicos();
        try {
            Monta_Jogo_da_Memoria(cartas); 
            card_background = new Image(new FileInputStream("card_background.png"));        
            Time();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TelaJogoMemoriaController.class.getName()).log(Level.SEVERE, null, ex);
        } 

    }
    //Retorna a posição da carta com o Id_especificado
    public int Posicao_Lista_por_id (int Id){
     Card carta;
        try {
            carta = cartas.stream().filter(x -> x.getId_carta() == Id).findFirst().get();
            if (carta != null) {
                return cartas.indexOf(carta);
            } else {
                return -1;
            }
        } catch (Exception ex) {
            //Quando não encontra uma carta na busca ele lança o NoSuchElementException
            return -1;
        }
    
    
    }

    //Este método monta o tabuleiro de forma randomica
    public void Monta_Jogo_da_Memoria(List<Card> cartas) throws FileNotFoundException {
        int n = 1;
        Random rand = new Random();
        int card_aleatorio;      
        boolean status = true;
        int i = 10;
  
        while (status) {
            //carta aleatória de 1 a 5
            card_aleatorio = rand.nextInt(6);
            if(card_aleatorio == 0)
                continue;

            if (!Card.Verifica_Par_cartas(card_aleatorio, cartas)) {

                //defino as imagens
                cartas.get(Posicao_Lista_por_id(i)).setImagem(Instancia_Imagens(n));
                cartas.get(Posicao_Lista_por_id(card_aleatorio)).setImagem(Instancia_Imagens(n));

                //defino os pares
                cartas.get(Posicao_Lista_por_id(i)).setId_carta_par(card_aleatorio);
                cartas.get(Posicao_Lista_por_id(card_aleatorio)).setId_carta_par(i);

                //acréscimo do n
                n++;

                //decréscimo do i
                i--;

//                if (card_aleatorio == 0) {
//                    flagto0 = false;
//                }
            }

            if (i < 6) {
                status = false;
            }

        }

    }
    
    //Instancia as imagens
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
    
    //Compara as Cartas abertas
    public void Compara_Cartas(Card carta_aberta) {

        Card carta;
        try {
            carta = cartas.stream().filter(x -> x.isStatus() == true && x.getId_carta() != carta_aberta.getId_carta()).findFirst().get();
            if (carta != null) {
                if (carta_aberta.getId_carta() == carta.getId_carta_par()) {
                    
                    cartas.stream().filter(x -> x.getId_carta() == carta_aberta.getId_carta()).findFirst().get().setStatus(false);
                    cartas.stream().filter(x -> x.getId_carta() == carta.getId_carta()).findFirst().get().setStatus(false);
                    
                    calculaPontos(true);
                    
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Validação");
                    alert.setContentText("Você acertou");
                    alert.showAndWait();
                    
                    Acao_Conparacao_Carta(carta_aberta.getId_carta(), carta.getId_carta(), true);

                } else {
                    cartas.stream().filter(x -> x.getId_carta() == carta_aberta.getId_carta()).findFirst().get().setStatus(false);
                    cartas.stream().filter(x -> x.getId_carta() == carta.getId_carta()).findFirst().get().setStatus(false);
                    
                    calculaPontos(false);
                    
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Validação");
                    alert.setContentText("Você errou");
                    alert.showAndWait();
                    
                    Acao_Conparacao_Carta(carta_aberta.getId_carta(), carta.getId_carta(), false);
                }
            }
        } catch (Exception ex) {
            //Quando não encontra uma carta na busca ele lança o NoSuchElementException
            System.out.println(ex.getMessage());
        }
        System.out.println("Acabou de comparar as cartas");

    }
    
    //Verifica se possui outra carta aberta
    public boolean Verifica_Cartas_Abertas(Card carta_aberta) {
        Card carta;
        try {
            carta = cartas.stream().filter(x -> x.isStatus() == true && x.getId_carta() != carta_aberta.getId_carta()).findFirst().get();
            if (carta != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            //Quando não encontra uma carta na busca ele lança o NoSuchElementException
            return false;
        }

    }    
    
    //Esconde ou mostra as cartas
    public void Acao_Conparacao_Carta(int Id_1, int Id_2, boolean resultado){ 

        if (resultado) {

            if (Id_1 == 1 || Id_2 == 1) {                
                img_card_1.setImage(null);
            }
            if (Id_1 == 2 || Id_2 == 2) {               
                img_card_2.setImage(null);
            }
            if (Id_1 == 3 || Id_2 == 3) {
                img_card_3.setImage(null);
            }
            if (Id_1 == 4 || Id_2 == 4) {
                img_card_4.setImage(null);
            }
            if (Id_1 == 5 || Id_2 == 5) {
                img_card_5.setImage(null);
            }
            if (Id_1 == 6 || Id_2 == 6) {
                img_card_6.setImage(null);
            }
            if (Id_1 == 7 || Id_2 == 7) {
                img_card_7.setImage(null);
            }
            if (Id_1 == 8 || Id_2 == 8) {
                img_card_8.setImage(null);
            }
            if (Id_1 == 9 || Id_2 == 9) {
                img_card_9.setImage(null);
            }
            if (Id_1 == 10 || Id_2 == 10) {
                img_card_10.setImage(null);
            }

        } else {
            if (Id_1 == 1 || Id_2 == 1) {       
                img_card_1.setImage(card_background);
            }
            if (Id_1 == 2 || Id_2 == 2) {             
                img_card_2.setImage(card_background);
            }
            if (Id_1 == 3 || Id_2 == 3) {
                img_card_3.setImage(card_background);
            }
            if (Id_1 == 4 || Id_2 == 4) {
                img_card_4.setImage(card_background);
            }
            if (Id_1 == 5 || Id_2 == 5) {
                img_card_5.setImage(card_background);
            }
            if (Id_1 == 6 || Id_2 == 6) {
                img_card_6.setImage(card_background);
            }
            if (Id_1 == 7 || Id_2 == 7) {
                img_card_7.setImage(card_background);
            }
            if (Id_1 == 8 || Id_2 == 8) {
                img_card_8.setImage(card_background);
            }
            if (Id_1 == 9 || Id_2 == 9) {
                img_card_9.setImage(card_background);
            }
            if (Id_1 == 10 || Id_2 == 10) {
                img_card_10.setImage(card_background);
            }

        }

    }
    
    //calculo de pontos
    private void calculaPontos(boolean acertou){
        if(acertou){
            pontos+= 10;
        }
        else{
            pontos-= 5;
            if(pontos < 0)
                pontos = 0;
        }
        txtPontuacao.setText(Integer.toString(pontos));

    }
    
    //Time
    private void Time(){

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int interval = 30;

            public void run() {
                if (interval > 0) {
                    Platform.runLater(() -> txtTempo.setText(String.valueOf(interval)));   
                    interval--;
                } else {
                    timer.cancel();
                }
            }
        }, 1000, 1000);
    
    }
    
   
        
        
        
        

    //Evento do Card 1
    @FXML
    private void Card_Event(MouseEvent event) {
        
        Card carta = cartas.stream().filter(x -> x.getId_carta() == 1).findFirst().get();
        
        if (carta != null) {
            if (!carta.isStatus()) {
                img_card_1.setImage(carta.getImagem());
                cartas.stream().filter(x -> x.getId_carta() == 1).findFirst().get().setStatus(true);
                if (Verifica_Cartas_Abertas(carta)) {
                     Compara_Cartas(carta);
                    System.out.println("Tem carta aberta");                    
                } else {
                    System.out.println("Não tem carta aberta");
                }
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
                 if (Verifica_Cartas_Abertas(carta)) {
                      Compara_Cartas(carta);
                    System.out.println("Tem carta aberta");
                } else {
                    System.out.println("Não tem carta aberta");
                }
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
                 if (Verifica_Cartas_Abertas(carta)) {
                     Compara_Cartas(carta);                   
                    System.out.println("Tem carta aberta");
                } else {
                    System.out.println("Não tem carta aberta");
                }
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
                 if (Verifica_Cartas_Abertas(carta)) {
                     Compara_Cartas(carta);                   
                    System.out.println("Tem carta aberta");
                } else {
                    System.out.println("Não tem carta aberta");
                }
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
                 if (Verifica_Cartas_Abertas(carta)) {
                     Compara_Cartas(carta);                   
                    System.out.println("Tem carta aberta");
                } else {
                    System.out.println("Não tem carta aberta");
                }
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
                 if (Verifica_Cartas_Abertas(carta)) {
                     Compara_Cartas(carta);                   
                    System.out.println("Tem carta aberta");
                } else {
                    System.out.println("Não tem carta aberta");
                }
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
                 if (Verifica_Cartas_Abertas(carta)) {
                     Compara_Cartas(carta);                   
                    System.out.println("Tem carta aberta");
                } else {
                    System.out.println("Não tem carta aberta");
                }
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
                 if (Verifica_Cartas_Abertas(carta)) {
                    Compara_Cartas(carta);                   
                    System.out.println("Tem carta aberta");
                } else {
                    System.out.println("Não tem carta aberta");
                }
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
                 if (Verifica_Cartas_Abertas(carta)) {
                    Compara_Cartas(carta);                   
                    System.out.println("Tem carta aberta");
                } else {
                    System.out.println("Não tem carta aberta");
                }
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
                 if (Verifica_Cartas_Abertas(carta)) {
                    Compara_Cartas(carta); 
                    System.out.println("Tem carta aberta");
                } else {
                    System.out.println("Não tem carta aberta");
                }
            } else {
                img_card_5.setImage(card_background);
                cartas.stream().filter(x -> x.getId_carta() == 5).findFirst().get().setStatus(false);
            }

        } else {
            System.out.println("Erro na busca da carta");
        }
    }

    @FXML
    private void Comparar_Cartas(MouseEvent event) {
        Card carta;
        try {
            carta = cartas.stream().filter(x -> x.isStatus() == true).findFirst().get();
            Compara_Cartas(carta);
        } catch (Exception ex) {
            //Quando não encontra uma carta na busca ele lança o NoSuchElementException

        }
    }
    
}

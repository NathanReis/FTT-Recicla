/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recicla.presentation;

import com.google.gson.Gson;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import recicla.business.config.Config;
import recicla.business.httpRequests.httpRequest;
import recicla.business.serversocket.RoundMannager;
import recicla.comuns.helperController.HelperController;
import recicla.comuns.vos.ItemLojaXUsuario;
import recicla.comuns.vos.Janelas;
import recicla.comuns.vos.JogoRodada;

/**
 * FXML Controller class
 *
 * @author cliente
 */
public class TelaAcerteAlvoController implements Initializable {

    @FXML
    private Label txtDinheiro;
    @FXML
    private Label txtTempo;
    @FXML
    private ImageView janela0;
    @FXML
    private ImageView janela1;
    @FXML
    private ImageView janela4;
    @FXML
    private ImageView janela7;
    @FXML
    private ImageView janela3;
    @FXML
    private ImageView janela6;
    @FXML
    private ImageView janela5;
    @FXML
    private ImageView janela2;
    @FXML
    private ImageView janela9;
    @FXML
    private ImageView janela8;
    @FXML
    private Label txtPontuacao;
    
    private String idJanela = "";
    private Image img;
    private int totalJanela = 10;
    private int pontos;
    private int n;
    Timer timer;
    private int tempoTimer;
    @FXML
    private ImageView itemQuiz;
    @FXML
    private ImageView itemTempo;
    @FXML
    private Label item2x;
    private int multiplicador = 1;
    
    List<Janelas> janelas;
    List<Integer> contem = new ArrayList<Integer>();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         List<Janelas> janelas1 = new ArrayList<Janelas>();
         verificaDinheiroUsuario();
         verificaItensUsuario();
         Time(30);
        //Adiciona todos objetos janela em uma lista
        
        try {
            for (int i = 0; i < totalJanela; i++) {

                Janelas j = new Janelas();
                Image imagem = new Image((new FileInputStream("janela" + Integer.toString(i) + ".png")));
                j.setImagem(imagem);
                j.setId(i);
                janelas1.add(j);
            }
            
       }catch(FileNotFoundException ex){
           Logger.getLogger(TelaAcerteAlvoController.class.getName()).log(Level.SEVERE, null, ex);
       }
       janelas = janelas1;
        for(Janelas j: janelas1){
            
            ApagaJanela(j.getId());
        }
        GeraIdRandomico();
    }    
    
    @FXML
     private void itemTempoClicked() throws Exception {
        cancelTimer();
        consomeItem(1);
        verificaItensUsuario();
    }
    
    @FXML
    private void item2xClicked() throws Exception{
        consomeItem(3);
        multiplicador = multiplicador * 2;
        verificaItensUsuario();
    }
    
    public void GeraIdRandomico(){
  
        n = 0;
        int r;
        boolean status = false;
        Random random = new Random();
        while(!status){
            
            r = random.nextInt(totalJanela);
            
            for(Janelas j: janelas){
                  
                if(j.getId() == r){
                    
                    if(j.getEstado() == false){
                        
                      contem.add(j.getId());
                      AcendeJanela(j.getId());
                      n++;
                    }
                }
            }
            if(n == totalJanela){
                status = true;
            }
        }
        n = 0;
        for(int c : contem){
            System.out.println(c);
        }
    }
    
    public void CalculaPontos(int id){
        
        if(janelas.get(id).getEstado()){
            n++;
            pontos += 5 * multiplicador;
            if(n == totalJanela){
                txtPontuacao.setText(Integer.toString(pontos));
            }
        }
    }
    
    public void ApagaJanela(int id){
        switch(id){
            case 0:
               janelas.get(id).setEstado(false);
               img = janelas.get(id).getImagem();
               janela0.setOpacity(0.0);
               janela0.setImage(img);
               
               break;
            case 1:
               janelas.get(id).setEstado(false);
               img = janelas.get(id).getImagem();
               janela1.setOpacity(0.0);
               janela1.setImage(img);
               break;
            case 2:
               janelas.get(id).setEstado(false);
               img = janelas.get(id).getImagem();
               janela2.setOpacity(0.0);
               janela2.setImage(img);
               break;
            case 3:
               janelas.get(id).setEstado(false);
               img = janelas.get(id).getImagem();
               janela3.setOpacity(0.0);
               janela3.setImage(img);
               break; 
            case 4:
               janelas.get(id).setEstado(false);
               img = janelas.get(id).getImagem();
               janela4.setOpacity(0.0);
               janela4.setImage(img);
               break;
            case 5:
               janelas.get(id).setEstado(false);
               img = janelas.get(id).getImagem();
               janela5.setOpacity(0.0);
               janela5.setImage(img);
               break;
            case 6:
               janelas.get(id).setEstado(false);
               img = janelas.get(id).getImagem();
               janela6.setOpacity(0.0);
               janela6.setImage(img);
               break;
            case 7:
               janelas.get(id).setEstado(false);
               img = janelas.get(id).getImagem();
               janela7.setOpacity(0.0);
               janela7.setImage(img);
               break;
            case 8:
               janelas.get(id).setEstado(false);
               img = janelas.get(id).getImagem();
               janela8.setOpacity(0.0);
               janela8.setImage(img);
               break;
            case 9:
               janelas.get(id).setEstado(false);
               img = janelas.get(id).getImagem();
               janela9.setOpacity(0.0);
               janela9.setImage(img);
               break;
           
        }
    }
    
    public void AcendeJanela(int id){
        switch(id){
            case 0:
               img = janelas.get(id).getImagem();
               janelas.get(id).setEstado(true);
               janela0.setOpacity(1.0);
               janela0.setImage(img);
               
               break;
            case 1:
               img = janelas.get(id).getImagem();
               janelas.get(id).setEstado(true);
               janela1.setOpacity(1.0);
               janela1.setImage(img);
               break;
            case 2:
               img = janelas.get(id).getImagem();
               janelas.get(id).setEstado(true);
               janela2.setOpacity(1.0);
               janela2.setImage(img);
               break;
            case 3:
               img = janelas.get(id).getImagem();
               janelas.get(id).setEstado(true);
               janela3.setOpacity(1.0);
               janela3.setImage(img);
               break; 
            case 4:
               img = janelas.get(id).getImagem();
               janelas.get(id).setEstado(true);
               janela4.setOpacity(1.0);
               janela4.setImage(img);
               break;
            case 5:
               img = janelas.get(id).getImagem();
               janelas.get(id).setEstado(true);
               janela5.setOpacity(1.0);
               janela5.setImage(img);
               break;
            case 6:
               img = janelas.get(id).getImagem();
               janelas.get(id).setEstado(true);
               janela6.setOpacity(1.0);
               janela6.setImage(img);
               break;
            case 7:
               img = janelas.get(id).getImagem();
               janelas.get(id).setEstado(true);
               janela7.setOpacity(1.0);
               janela7.setImage(img);
               break;
            case 8:
               img = janelas.get(id).getImagem();
               janelas.get(id).setEstado(true);
               janela8.setOpacity(1.0);
               janela8.setImage(img);
               break;
            case 9:
               img = janelas.get(id).getImagem();
               janelas.get(id).setEstado(true);
               janela9.setOpacity(1.0);
               janela9.setImage(img);
               break;
            
        }
    }
    
    @FXML
    private void JanelaAtivada0(MouseEvent event) throws Exception {
        
       CalculaPontos(0);
       ApagaJanela(0);
       verifica_fim_jogo();
    }

    @FXML
    private void JanelaAtivada1(MouseEvent event) throws Exception {
        
        CalculaPontos(1);
        ApagaJanela(1);
        verifica_fim_jogo();
    }

    @FXML
    private void JanelaAtivada4(MouseEvent event) throws Exception {
        
        CalculaPontos(4);
        ApagaJanela(4);
        verifica_fim_jogo();
    }

    @FXML
    private void JanelaAtivada7(MouseEvent event) throws Exception {
        
        CalculaPontos(7);
        ApagaJanela(7);
        verifica_fim_jogo();
    }

    @FXML
    private void JanelaAtivada3(MouseEvent event) throws Exception {
        
        CalculaPontos(3);
        ApagaJanela(3);
        verifica_fim_jogo();
    }

    @FXML
    private void JanelaAtivada6(MouseEvent event) throws Exception {
        
        CalculaPontos(6);
        ApagaJanela(6);
        verifica_fim_jogo();
    }

    @FXML
    private void JanelaAtivada5(MouseEvent event) throws Exception {
        
        CalculaPontos(5);
        ApagaJanela(5);
        verifica_fim_jogo();
    }

    @FXML
    private void JanelaAtivada2(MouseEvent event) throws Exception {
        
        CalculaPontos(2);
        ApagaJanela(2);
        verifica_fim_jogo();
    }

    @FXML
    private void JanelaAtivada9(MouseEvent event) throws Exception {
        
        CalculaPontos(9);
        ApagaJanela(9);
        verifica_fim_jogo();
    }

    @FXML
    private void JanelaAtivada8(MouseEvent event) throws Exception {
        
        CalculaPontos(8);
        ApagaJanela(8);
        verifica_fim_jogo();
    }
    
    
    
    //Time
    private void Time(int tempo) {

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int interval = tempo;

            public void run() {
                if (interval > 0) {
                    Platform.runLater(() -> txtTempo.setText(String.valueOf(interval)));
                    tempoTimer = interval;
                    interval--;
                } else {
                   Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("Fim do jogo Acerte ao Alvo");
                            JogoRodada game = RoundMannager.getInstance().remove_game();
                            if (game != null) {
                                try {
                                    String tela = HelperController.dicover_game(game);
                                    Parent root = FXMLLoader.load(getClass().getResource(tela));
                                    HelperController.exibirTela(root);
                                    
                                } catch (Exception ex) {
                                    System.out.println(ex.getMessage());
                                }

                            } else {
                                try {
                                    Parent root = FXMLLoader.load(getClass().getResource("TelaRanking.fxml"));
                                    HelperController.exibirTela(root);
                                  
                                } catch (Exception ex) {
                                    System.out.println(ex.getMessage());
                                }
                            }
                        }
// ...
                    });
                    timer.cancel();
                }
            }
        }, 1000, 1000);
        
    }
    
     private void cancelTimer(){
        
        timer.cancel();
        Time(tempoTimer + 30);
    }
    
   private void verificaItensUsuario() {
        itemQuiz.setVisible(false);
        itemTempo.setVisible(false);
        item2x.setVisible(false);
        
        ItemLojaXUsuario[] itens = Config.getInstance().getLoggedUser().getItens();
        for (ItemLojaXUsuario item : itens) {
            if (item.getItemLojaId() == 1 && item.getQuantidade() >= 1) {
                itemTempo.setVisible(true);
            }
            //apenas no quiz
            /*if (item.getItemLojaId() == 2 && item.getQuantidade() >= 1) {
                itemQuiz.setVisible(true);
            }*/
            if (item.getItemLojaId() == 3 && item.getQuantidade() >= 1) {
                item2x.setVisible(true);
            }

        }
    }
        
        
    private void consomeItem(int idItem) throws Exception {
        ItemLojaXUsuario[] itens = Config.getInstance().getLoggedUser().getItens();
        Gson g = new Gson();
        for (ItemLojaXUsuario item : itens) {
            if (item.getItemLojaId() == idItem) {
                int qtd = item.getQuantidade();
                item.setQuantidade(qtd - 1);
                String chamadaWs = "itens/consome-item-usuario";
                httpRequest.sendPut(g.toJson(item), chamadaWs);
                break;
            }
        }
        Config.getInstance().getLoggedUser().setItens(itens);
    }
    
    private void verifica_fim_jogo() throws IOException, Exception{
    
        if(janela0.getOpacity() == 0.0 &&
           janela1.getOpacity() == 0.0 &&
           janela2.getOpacity() == 0.0 &&
           janela3.getOpacity() == 0.0 &&
           janela4.getOpacity() == 0.0 &&
           janela5.getOpacity() == 0.0 &&
           janela6.getOpacity() == 0.0 &&
           janela7.getOpacity() == 0.0 &&
           janela8.getOpacity() == 0.0 &&
           janela9.getOpacity() == 0.0   ){
            salvaDinheiro(calculaDinheiroGanho());
            System.out.println("Fim jogo Acerte ao alvo");
            JogoRodada game = RoundMannager.getInstance().remove_game();
            if (game != null) {
                String tela = HelperController.dicover_game(game);
                Parent root = FXMLLoader.load(getClass().getResource(tela));
                HelperController.exibirTela(root);
                timer.cancel();

            } else {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("TelaRanking.fxml"));
                    HelperController.exibirTela(root);
                    timer.cancel();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        
        }
    
    }
        
     private void salvaDinheiro(double dinheiro) throws Exception {
        double saldoAtual = Config.getInstance().getLoggedUser().getDinheiro();
        double novoSaldo = saldoAtual + dinheiro;
        Config.getInstance().getLoggedUser().setDinheiro(novoSaldo);
        System.out.print("novo saldo: " + dinheiro);
        Gson g = new Gson();
        String chamadaWs = "user/atualiza-usuario";
        httpRequest.sendPut(g.toJson(Config.getInstance().getLoggedUser()), chamadaWs);
    }
    
    private double calculaDinheiroGanho(){
        int tempo = tempoTimer;
        double dinheiroGanho = tempo * pontos;
        
        return dinheiroGanho;
    }
    private void verificaDinheiroUsuario() {
        double dinheiro = Config.getInstance().getLoggedUser().getDinheiro();
        txtDinheiro.setText(Double.toString(dinheiro));
    }
}

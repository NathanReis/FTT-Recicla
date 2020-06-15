/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recicla.presentation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import recicla.comuns.vos.Janelas;

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
    private Label txtTempo1;
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
    
    List<Janelas> janelas;
    List<Integer> contem = new ArrayList<Integer>();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         List<Janelas> janelas1 = new ArrayList<Janelas>();
       
        //Adiciona todos objetos janela em uma lista
        
       try{ 
            for(int i = 0; i < totalJanela;i++){

                     Janelas j = new Janelas();
                     Image imagem = new Image((new FileInputStream("janela"+Integer.toString(i)+".png")));
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
            pontos += 5;
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
    private void JanelaAtivada0(MouseEvent event) {
        
       CalculaPontos(0);
       ApagaJanela(0);
    }

    @FXML
    private void JanelaAtivada1(MouseEvent event) {
        
        CalculaPontos(1);
        ApagaJanela(1);
    }

    @FXML
    private void JanelaAtivada4(MouseEvent event) {
        
        CalculaPontos(4);
        ApagaJanela(4);
    }

    @FXML
    private void JanelaAtivada7(MouseEvent event) {
        
        CalculaPontos(7);
        ApagaJanela(7);
    }

    @FXML
    private void JanelaAtivada3(MouseEvent event) {
        
        CalculaPontos(3);
        ApagaJanela(3);
    }

    @FXML
    private void JanelaAtivada6(MouseEvent event) {
        
        CalculaPontos(6);
        ApagaJanela(6);
    }

    @FXML
    private void JanelaAtivada5(MouseEvent event) {
        
        CalculaPontos(5);
        ApagaJanela(5);
    }

    @FXML
    private void JanelaAtivada2(MouseEvent event) {
        
        CalculaPontos(2);
        ApagaJanela(2);
    }

    @FXML
    private void JanelaAtivada9(MouseEvent event) {
        
        CalculaPontos(9);
        ApagaJanela(9);
    }

    @FXML
    private void JanelaAtivada8(MouseEvent event) {
        
        CalculaPontos(8);
        ApagaJanela(8);
    }
    
}

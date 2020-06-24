/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recicla.business.serversocket;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import recicla.comuns.vos.JogoRodada;

/**
 *
 * @author italo
 */
public class RoundMannager {
    
    private static RoundMannager instance;    
    ConcurrentLinkedQueue<JogoRodada> LinesGame = new ConcurrentLinkedQueue<JogoRodada>();
    private  boolean IsRoomAvaliable = false;
    private boolean memory_game = false;
    private boolean quiz_game = false;
    private boolean target_game = false;
    
    

    
    public static RoundMannager getInstance() {
        if (instance == null) 
             instance = new RoundMannager();
        
        return instance;
    }
    
    public ConcurrentLinkedQueue<JogoRodada> getGames(){
        return this.LinesGame;
    }    

    public void add_game(JogoRodada game) {
        this.LinesGame.add(game);
    }

    public JogoRodada remove_game() {
         return this.LinesGame.poll();
    }

    /**
     * @return the IsRoomAvaliable
     */
    public boolean isIsRoomAvaliable() {
        return IsRoomAvaliable;
    }

    /**
     * @param IsRoomAvaliable the IsRoomAvaliable to set
     */
    public void setIsRoomAvaliable(boolean IsRoomAvaliable) {
        this.IsRoomAvaliable = IsRoomAvaliable;
    }

    /**
     * @return the memory_game
     */
    public boolean isMemory_game() {
        return memory_game;
    }

    /**
     * @param memory_game the memory_game to set
     */
    public void setMemory_game(boolean memory_game) {
        this.memory_game = memory_game;
    }

    /**
     * @return the quiz_game
     */
    public boolean isQuiz_game() {
        return quiz_game;
    }

    /**
     * @param quiz_game the quiz_game to set
     */
    public void setQuiz_game(boolean quiz_game) {
        this.quiz_game = quiz_game;
    }

    /**
     * @return the target_game
     */
    public boolean isTarget_game() {
        return target_game;
    }

    /**
     * @param target_game the target_game to set
     */
    public void setTarget_game(boolean target_game) {
        this.target_game = target_game;
    }

}



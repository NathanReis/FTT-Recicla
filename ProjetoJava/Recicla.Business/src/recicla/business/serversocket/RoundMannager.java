/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recicla.business.serversocket;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *
 * @author italo
 */
public class RoundMannager {
    
    private static RoundMannager instance;    
    ConcurrentLinkedQueue<String> LinesGame = new ConcurrentLinkedQueue<String>();;
    private  boolean IsRoomAvaliable = false;
    
    public static RoundMannager getInstance() {
        if (instance == null) 
             instance = new RoundMannager();
        
        return instance;
    }
    

//    public void GamesManager() {
//        if (this.LinesGame == null) {
//            LinesGame = new ConcurrentLinkedQueue<String>();
//        }
//    }
    
    public ConcurrentLinkedQueue<String> getGames(){
        return this.LinesGame;
    }    

    public void add_game(String game) {
        this.LinesGame.add(game);
    }

    public void remove_game() {
        this.LinesGame.poll();
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

}



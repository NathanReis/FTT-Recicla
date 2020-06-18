/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recicla.business.serversocket;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *
 * @author italo
 */
public class RoundMannager {

    ConcurrentLinkedQueue<String> LinesGame;

    public void GamesManager() {
        if (this.LinesGame == null) {
            LinesGame = new ConcurrentLinkedQueue<String>();
        }
    }

    public void add_game(String game) {
        this.LinesGame.add(game);
    }

    public void remove_game() {
        this.LinesGame.poll();
    }

}



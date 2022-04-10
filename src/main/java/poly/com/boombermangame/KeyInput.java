/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.com.boombermangame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author Admin
 */
public class KeyInput extends KeyAdapter{
    GamePanel gamePanel;
    
    KeyInput(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
     gamePanel.keyReleased(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        gamePanel.keyPressed(e);
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.com.boombermangame;

import java.awt.HeadlessException;
import javax.swing.JFrame;

/**
 *
 * @author Admin
 */
public class GameFrame extends JFrame{
    GamePanel gamePanel;

    public GameFrame() {
        gamePanel = new GamePanel();
        add(gamePanel);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        gamePanel.start();
        addKeyListener(new KeyInput(gamePanel));
        setVisible(true);
    }
    
    public static void main(String[] args) {
        GameFrame gameFrame = new GameFrame();
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.com.boombermangame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.time.Clock;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 *
 * @author Admin
 */
public class GamePanel extends JPanel implements Runnable {

    public static int gameWidth = 320;
    public static int gameHeight = 224;
    public static int numberX = 20;
    public static int numberY = 14;
    public static int squareSize = 16;
    public int arrGame[][];
    private Thread thread;
    public boolean running = false;
    private boolean  gameOver = false;

    private Image ground;
    private Image stone;
    private Image candy;
    
    
    Bomber bomber;
    Boom boom;
    public Explosion explosion;
    private Controller controller;
    
    // 2 là đồ phá được, 1 là cỏ, 0 là đá bị chặn
    public static int[][] array
            = {
                {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
                {3, 1, 1, 1, 1, 2, 1, 1, 0, 1, 2, 2, 2, 1, 0, 0, 1, 1, 1, 3},
                {3, 1, 0, 1, 0, 1, 0, 1, 0, 2, 0, 1, 0, 1, 0, 1, 0, 1, 1, 3},
                {3, 1, 2, 2, 2, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1, 2, 1, 2, 1, 3},
                {3, 2, 0, 1, 0, 2, 0, 2, 0, 1, 0, 1, 0, 1, 0, 2, 2, 2, 1, 3},
                {3, 1, 2, 1, 2, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 2, 2, 1, 1, 3},
                {3, 1, 0, 1, 0, 1, 1, 0, 2, 0, 2, 0, 1, 0, 1, 0, 1, 0, 1, 3},
                {3, 0, 1, 2, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 2, 2, 0, 1, 1, 3},
                {3, 1, 0, 1, 0, 1, 0, 1, 0, 2, 2, 0, 1, 1, 1, 0, 2, 2, 1, 3},
                {3, 1, 2, 1, 1, 1, 2, 0, 1, 2, 1, 0, 1, 2, 2, 0, 1, 1, 1, 3},
                {3, 1, 2, 2, 2, 0, 1, 1, 2, 0, 2, 0, 1, 1, 0, 1, 1, 1, 1, 3},
                {3, 1, 2, 1, 1, 0, 1, 0, 2, 2, 1, 1, 0, 2, 0, 1, 1, 2, 1, 3},
                {3, 1, 1, 2, 2, 2, 1, 1, 1, 0, 1, 2, 0, 1, 0, 2, 1, 1, 1, 3},
                {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3},};

    public GamePanel() {
        init();
    }

    private void init() {
        setPreferredSize(new Dimension(gameWidth, gameHeight));
        thread = new Thread(this);
        SpriteSheet.loadImages();
        ground = SpriteSheet.grabImage(1, 1, squareSize, squareSize);
        stone = SpriteSheet.grabImage(2, 1, squareSize, squareSize);
        candy = SpriteSheet.grabImage(3, 1, squareSize, squareSize);
        bomber = new Bomber(1,1,this);
        boom = new Boom();
        explosion = new Explosion(this);
        controller = new Controller();
        controller.addGhost(new Ghost(18*16, 12 *16,this));
        controller.addGhost(new Ghost(18*16,1*16,this));
    }

    @Override
    public void paint(Graphics g) {
        PaintBackGround(g);
        bomber.draw(g);
        boom.draw(g);
        explosion.draw(g);
        controller.draw(g);
        if (gameOver){
            DrawGameOver(g);
        }
        if (Controller.score == 2){
            DrawGameWin(g);
        }
        if (bomber.countRightMove>=150){
            DrawGameWin(g);
        }
       
    }
    public void DrawGameWin(Graphics g){
         Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.red);
        g2d.setStroke(new BasicStroke());
        g2d.setFont(new Font("", Font.BOLD, 30));
        g2d.drawString("YOU WIN", gameWidth/2 - 80, gameHeight/2);
    }
    public void DrawGameOver(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.red);
        g2d.setStroke(new BasicStroke());
        g2d.setFont(new Font("", Font.BOLD, 30));
        g2d.drawString("GAME OVER", gameWidth/2 - 100, gameHeight/2);
    }

    public void PaintBackGround(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.setColor(Color.GREEN);
        for (int i=0; i<14; i++){
            for (int j=0; j<20; j++){
                if (array[i][j]==3){
                    g2d.drawImage(ground, j*squareSize, i*squareSize, null);
                }
                if (array[i][j]==2){
                    g2d.drawImage(candy,j*squareSize, i*squareSize, null);
                }
                if (array[i][j]==1){
                    g2d.setColor(Color.green);
                    g2d.fillRect(j*squareSize,i*squareSize, squareSize,squareSize);
                }
                if (array[i][j]==0){
                  g2d.drawImage(stone,j*squareSize, i*squareSize, null);
                }
            }
        }
    }

    public void start() {
        if (running) {
            return;
        }
        running = true;
        thread.start();
    }

    public void stop() {
        if (!running) {
            return;
        }
        running = false;
        try {
            thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.exit(1);
        
    }

    @Override
    public void run() {
        long lasttime = System.nanoTime();
        final double amountsOfTick = 60.0; // tick like update
        double ns = 1000000000 / amountsOfTick;
        double delta = 0;
        long ticks = 0;
        long timer = System.currentTimeMillis();
        while (running) {
            long now = System.nanoTime();
            delta += (now - lasttime) / ns;
            lasttime = now;
            if (delta >= 1) { 
                update();
                delta--;
                ticks++;
            }

            repaint();
            if (Controller.score == 2){
                repaint();
                stop();
            }
            if (bomber.countRightMove>=152){
               running = false;
            }
            if (!bomber.isVisible()) {
                gameOver = true;
                running = false;
            }
        }
        
        stop();
    }
    
     public void update() {
        bomber.update();
        boom.update();
        explosion.update();
        controller.update();
    }

    long timer1 = 0;
    
    public void keyReleased(KeyEvent e) {
         int key = e.getKeyCode();
         if (key == KeyEvent.VK_RIGHT) bomber.setVector(Bomber.V_RIGHT);
         if (key == KeyEvent.VK_LEFT) bomber.setVector(Bomber.V_LEFT);
         if (key == KeyEvent.VK_UP) bomber.setVector(Bomber.V_UP);
         if (key == KeyEvent.VK_DOWN) bomber.setVector(Bomber.V_DOWN);
  
        
//          
//         
    }
    
    public void keyPressed(KeyEvent e) {
         int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_RIGHT) { 
            bomber.countRightMove++;
            bomber.setX(bomber.getX()+1);
            if (array[bomber.getY()][bomber.getX()]!=1) bomber.setX(bomber.getX()-1);
            if (bomber.getX()==boom.getX() && bomber.getY() == boom.getY() && boom.isVisible()) bomber.setX(bomber.getX()-1);
            bomber.setVector(Bomber.V_RIGHT);
            bomber.RightAnimation.update();
        }
        
        if (key == KeyEvent.VK_LEFT){
            bomber.setX(bomber.getX()-1);
            if (array[bomber.getY()][bomber.getX()]!=1) bomber.setX(bomber.getX()+1);
            if (bomber.getX()==boom.getX() && bomber.getY() == boom.getY() && boom.isVisible()) bomber.setX(bomber.getX()+1);
            bomber.setVector(Bomber.V_LEFT);
            bomber.LeftAnimation.update();
        }
        if (key == KeyEvent.VK_UP) {
            bomber.setY(bomber.getY()-1);
            if (array[bomber.getY()][bomber.getX()]!=1) bomber.setY(bomber.getY()+1);
            if (bomber.getX()==boom.getX() && bomber.getY() == boom.getY() && boom.isVisible()) bomber.setY(bomber.getY()+1);
            bomber.setVector(Bomber.V_UP);
            bomber.UpAnimation.update();
        }
        if (key == KeyEvent.VK_DOWN){
            bomber.setY(bomber.getY()+1);
            if (array[bomber.getY()][bomber.getX()]!=1) bomber.setY(bomber.getY()-1);
            if (bomber.getX()==boom.getX() && bomber.getY() == boom.getY() && boom.isVisible()) bomber.setY(bomber.getY()-1);
            bomber.setVector(Bomber.V_DOWN);
            bomber.DownAnimation.update();
        }
       
        if (key == KeyEvent.VK_SPACE) { 
            if (!boom.isVisible()){
//            timer1 = System.currentTimeMillis();
            boom.setVisible(true);
            boom.setBoomDone(false);
            boom.timer = System.currentTimeMillis();
            boom.setX(bomber.getX());
            boom.setY(bomber.getY());
            explosion.visible = false;
            
        }
        }
    }

   
}

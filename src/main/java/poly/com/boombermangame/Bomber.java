/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.com.boombermangame;

import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author Admin
 */
public class Bomber {

    private int x;
    private int y;
    
   
    private Image DownImage;
    private Image DownImage_1;
    private Image DownImage_2;

    private Image UpImage;
    private Image UpImage_1;
    private Image UpImage_2;

    private Image LeftImage;
    private Image LeftImage_1;
    private Image LeftImage_2;

    private Image RightImage;
    private Image RightImage_1;
    private Image RightImage_2;

    private int vector;
    private boolean visible = true;
    private GamePanel gamePanel;

    public static int V_RIGHT = 1;
    public static int V_LEFT = 2;
    public static int V_UP = 3;
    public static int V_DOWN = 4;
    
    int countRightMove = 0 ; // variable for hack Game =))
    
    public Animation DownAnimation;
    public Animation UpAnimation;
    public Animation LeftAnimation;
    public Animation RightAnimation;

    public Bomber(int x, int y, GamePanel gamePanel) {
        this.x = x;
        this.y = y;
        this.gamePanel = gamePanel;
        loadAllimage();
        loadAllAnimation();

        vector = V_DOWN;
    }

    public void loadAllimage() {

        DownImage = SpriteSheet.grabImage(1, 3, GamePanel.squareSize, GamePanel.squareSize);
        DownImage_1 = SpriteSheet.grabImage(2, 3, GamePanel.squareSize, GamePanel.squareSize);
        DownImage_2 = SpriteSheet.grabImage(3, 3, GamePanel.squareSize, GamePanel.squareSize);

        LeftImage = SpriteSheet.grabImage(4, 3, GamePanel.squareSize, GamePanel.squareSize);
        LeftImage_1 = SpriteSheet.grabImage(5, 3, GamePanel.squareSize, GamePanel.squareSize);
        LeftImage_2 = SpriteSheet.grabImage(6, 3, GamePanel.squareSize, GamePanel.squareSize);

        RightImage = SpriteSheet.grabImage(7, 3, GamePanel.squareSize, GamePanel.squareSize);
        RightImage_1 = SpriteSheet.grabImage(8, 3, GamePanel.squareSize, GamePanel.squareSize);
        RightImage_2 = SpriteSheet.grabImage(1, 4, GamePanel.squareSize, GamePanel.squareSize);

        UpImage = SpriteSheet.grabImage(2, 4, GamePanel.squareSize, GamePanel.squareSize);
        UpImage_1 = SpriteSheet.grabImage(3, 4, GamePanel.squareSize, GamePanel.squareSize);
        UpImage_2 = SpriteSheet.grabImage(4, 4, GamePanel.squareSize, GamePanel.squareSize);

    }

    public void loadAllAnimation() {

        DownAnimation = new Animation();
        DownAnimation.addImage(DownImage);
        DownAnimation.addImage(DownImage_1);
        DownAnimation.addImage(DownImage_2);

        LeftAnimation = new Animation();
        LeftAnimation.addImage(LeftImage);
        LeftAnimation.addImage(LeftImage_1);
        LeftAnimation.addImage(LeftImage_2);

        UpAnimation = new Animation();
        UpAnimation.addImage(UpImage);
        UpAnimation.addImage(UpImage_1);
        UpAnimation.addImage(UpImage_2);

        RightAnimation = new Animation();
        RightAnimation.addImage(RightImage);
        RightAnimation.addImage(RightImage_1);
        RightAnimation.addImage(RightImage_2);

    }

    public void update() {
           if (gamePanel.explosion.visible){
        int u = gamePanel.boom.getX();
        int v = gamePanel.boom.getY();
        if (x == u && y == v) {
            setVisible(false);
        }
        if (x == u + 1 && y == v) {
            setVisible(false);
        }
        if (x == u - 1 && y == v) {
            setVisible(false);
        }
        if (x == u && y == v - 1) {
            setVisible(false);
        }
        if (x == u && y == v + 1) {
            setVisible(false);
        }
           }

    }

    public void draw(Graphics g) {
        if (visible) {
            if (vector == V_RIGHT) {
                g.drawImage(RightAnimation.getCurrentImage(), x * GamePanel.squareSize, y * GamePanel.squareSize, null);
            }
            if (vector == V_LEFT) {
                g.drawImage(LeftAnimation.getCurrentImage(), x * GamePanel.squareSize, y * GamePanel.squareSize, null);
            }
            if (vector == V_UP) {
                g.drawImage(UpAnimation.getCurrentImage(), x * GamePanel.squareSize, y * GamePanel.squareSize, null);

            }
            if (vector == V_DOWN) {
                g.drawImage(DownAnimation.getCurrentImage(), x * GamePanel.squareSize, y * GamePanel.squareSize, null);

            }

        }

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getVector() {
        return vector;
    }

    public void setVector(int vector) {
        this.vector = vector;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.com.boombermangame;

import java.awt.Image;

/**
 *
 * @author Admin
 */
public class Ghost {

    public Image ghostImage;
    public int x;
    public int y;
    public GamePanel gamePanel;

    private int vector;
    private boolean visible = true;

    public static int V_RIGHT = 1;
    public static int V_LEFT = 2;
    public static int V_UP = 3;
    public static int V_DOWN = 4;

    public Ghost(int x, int y, GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.x = x;
        this.y = y;
        vector = 3;
        ghostImage = SpriteSheet.grabImage(1, 7, GamePanel.squareSize, GamePanel.squareSize);
    }
    long timer = 0;

    public void update() {
        if (System.currentTimeMillis() - timer > 50) {
            if (visible){
            if (gamePanel.explosion.visible) {
                int u = gamePanel.boom.getX();
                int v = gamePanel.boom.getY();
                if (x / 16 == u && y / 16 == v) {
                    setVisible(false);
                    Controller.score++;
                }
                if (x / 16 == u + 1 && y / 16 == v) {
                    setVisible(false);
                    Controller.score++;
                }
                if (x / 16 == u - 1 && y / 16 == v) {
                    setVisible(false);
                    Controller.score++;
                }
                if (x / 16 == u && y / 16 == v - 1) {
                    setVisible(false);
                    Controller.score++;
                }
                if (x / 16 == u && y / 16 == v + 1) {
                    setVisible(false);
                    Controller.score++;
                }

            }
                if (vector == V_RIGHT) {
                    x++;
                    if (gamePanel.boom.isVisible() && gamePanel.boom.getX() == (x + 16) / 16 && gamePanel.boom.getY() == y / 16 || GamePanel.array[y / 16][(x + 16) / 16] != 1) {
                        vector = V_UP;
                    }
                }
                if (vector == V_LEFT) {
                    x--;
                    if (gamePanel.boom.isVisible() &&gamePanel.boom.getX() == x / 16 && gamePanel.boom.getY() == y / 16 || GamePanel.array[y / 16][x / 16] != 1) {
                        vector = V_RIGHT;
                    }
                }
                if (vector == V_UP) {
                    y--;
                    if (gamePanel.boom.isVisible() &&gamePanel.boom.getX() == (x) / 16 && gamePanel.boom.getY() == y / 16 || GamePanel.array[y / 16][x / 16] != 1) {
                        vector = V_DOWN;
                    }
                }
                if (vector == V_DOWN) {
                    y++;
                    if (gamePanel.boom.isVisible() &&gamePanel.boom.getX() == x / 16 && gamePanel.boom.getY() == (y + 16) / 16 || GamePanel.array[(y + 16) / 16][x / 16] != 1) {
                        vector = V_LEFT;
                    }
                }
            }
            timer = System.currentTimeMillis();
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

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

}

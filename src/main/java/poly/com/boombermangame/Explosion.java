/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.com.boombermangame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author Admin
 */
public class Explosion {

    GamePanel gamePanel;

    Animation centreAnimation;
    Animation leftAnimation;
    Animation rightAnimation;
    Animation topAnimation;
    Animation downAnimation;

    public Explosion(GamePanel gamePanel) {

        this.gamePanel = gamePanel;
        loadImages();
        loadAnimation();

    }

    private Image centre;
    private Image centre_1;
    private Image left;
    private Image left_1;
    private Image right;
    private Image right_1;
    private Image top;
    private Image top_1;
    private Image down;
    private Image down_1;

    public void loadImages() {
        
        left = SpriteSheet.grabImage(1, 6, GamePanel.squareSize, GamePanel.squareSize);
        centre = SpriteSheet.grabImage(2, 6, GamePanel.squareSize, GamePanel.squareSize);
        right = SpriteSheet.grabImage(3, 6, GamePanel.squareSize, GamePanel.squareSize);
        top = SpriteSheet.grabImage(2, 5, GamePanel.squareSize, GamePanel.squareSize);
        down = SpriteSheet.grabImage(2, 7, GamePanel.squareSize, GamePanel.squareSize);

        left_1 = SpriteSheet.grabImage(5, 6, GamePanel.squareSize, GamePanel.squareSize);
        centre_1 = SpriteSheet.grabImage(6, 6, GamePanel.squareSize, GamePanel.squareSize);
        right_1 = SpriteSheet.grabImage(7, 6, GamePanel.squareSize, GamePanel.squareSize);
        top_1 = SpriteSheet.grabImage(6, 5, GamePanel.squareSize, GamePanel.squareSize);
        down_1 = SpriteSheet.grabImage(6, 7, GamePanel.squareSize, GamePanel.squareSize);

    }

    public void loadAnimation() {

        centreAnimation = new Animation();
        centreAnimation.addImage(centre);
        centreAnimation.addImage(centre_1);

        leftAnimation = new Animation();
        leftAnimation.addImage(left);
        leftAnimation.addImage(left_1);

        rightAnimation = new Animation();
        rightAnimation.addImage(right);
        rightAnimation.addImage(right_1);

        topAnimation = new Animation();
        topAnimation.addImage(top);
        topAnimation.addImage(top_1);

        downAnimation = new Animation();
        downAnimation.addImage(down);
        downAnimation.addImage(down_1);
    }

    public boolean visible = false;

    public void update() {
        visible = false;
        centreAnimation.UpdateforExplosion();
        leftAnimation.UpdateforExplosion();
        rightAnimation.UpdateforExplosion();
        topAnimation.UpdateforExplosion();
        downAnimation.UpdateforExplosion();
        if (System.currentTimeMillis() - gamePanel.boom.nowExplosion > 0 && System.currentTimeMillis() - gamePanel.boom.nowExplosion < 300) {
            visible = true;
        }

    }

    public void draw(Graphics g) {
        if (visible) {
            if (GamePanel.array[gamePanel.boom.getY()][gamePanel.boom.getX() + 1] == 1 || GamePanel.array[gamePanel.boom.getY()][gamePanel.boom.getX() + 1] == 2) {
                g.drawImage(rightAnimation.getCurrentImage(), (gamePanel.boom.getX() + 1) * 16, 16 * gamePanel.boom.getY(), null);
            }

            if (GamePanel.array[gamePanel.boom.getY()][gamePanel.boom.getX() - 1] == 1 || GamePanel.array[gamePanel.boom.getY()][gamePanel.boom.getX() - 1] == 2) {
                g.drawImage(leftAnimation.getCurrentImage(), 16 * (gamePanel.boom.getX() - 1), 16 * gamePanel.boom.getY(), null);
            }

            if (GamePanel.array[gamePanel.boom.getY() + 1][gamePanel.boom.getX()] == 1 || GamePanel.array[gamePanel.boom.getY() + 1][gamePanel.boom.getX()] == 2) {
                g.drawImage(downAnimation.getCurrentImage(), 16 * gamePanel.boom.getX(), 16 * (gamePanel.boom.getY() + 1), null);
            }

            if (GamePanel.array[gamePanel.boom.getY() - 1][gamePanel.boom.getX()] == 1 || GamePanel.array[gamePanel.boom.getY() - 1][gamePanel.boom.getX()] == 2) {
                g.drawImage(topAnimation.getCurrentImage(), 16 * gamePanel.boom.getX(), 16 * (gamePanel.boom.getY() - 1), null);
            }

            if (GamePanel.array[gamePanel.boom.getY()][gamePanel.boom.getX()] == 1 || GamePanel.array[gamePanel.boom.getY()][gamePanel.boom.getX()] == 2) {
                g.drawImage(centreAnimation.getCurrentImage(), 16 * gamePanel.boom.getX(), 16 * (gamePanel.boom.getY()), null);
            }

        }
    }

}

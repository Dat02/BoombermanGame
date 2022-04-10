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
public class Boom {
    
    private int x;
    private int y;
    private Image boomImage; 
    
    private boolean visible = false;
    private boolean boomDone; //biến boom vừa nổ xong
    
    
    public Boom(){
    boomImage = SpriteSheet.grabImage(1, 2, GamePanel.squareSize, GamePanel.squareSize);
    boomDone = false;
    }
    
    public void draw(Graphics g){
        if (visible) g.drawImage(boomImage, x*GamePanel.squareSize, y*GamePanel.squareSize, null);
        
    }

   long timer; 
   long nowExplosion;
    public void update(){
        
        if (visible){
           if (System.currentTimeMillis() - timer > 2000){
               
               visible = false;
               
               if (GamePanel.array[y][x+1]==2) GamePanel.array[y][x+1]=1;
               if (GamePanel.array[y][x-1]==2) GamePanel.array[y][x-1]=1;
               if (GamePanel.array[y-1][x]==2) GamePanel.array[y-1][x]=1;
               if (GamePanel.array[y+1][x]==2) GamePanel.array[y+1][x]=1;
      
               timer = System.currentTimeMillis();
               nowExplosion = System.currentTimeMillis();
               
               boomDone = true;
           }
        }
        
    }
    
    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
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

    public boolean isBoomDone() {
        return boomDone;
    }

    public void setBoomDone(boolean boomDone) {
        this.boomDone = boomDone;
    }
   
    
}
    


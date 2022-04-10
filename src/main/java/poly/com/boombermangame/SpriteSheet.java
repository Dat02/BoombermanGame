/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.com.boombermangame;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Admin
 */
public class SpriteSheet {
    
    public static BufferedImage sprite;
    
//    public static Image ground;
    
    public SpriteSheet(){
        
    }
    
    public static void loadImages(){
        try {
            sprite = ImageIO.read(new File("D:/CodePassion/BoomberManGame/src/main/java/res/spriteBomberMan.png"));
        } catch (IOException ex) {
            Logger.getLogger(SpriteSheet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Image grabImage(int row, int column, int width, int height){
        Image img = sprite.getSubimage(row*16-16 ,column*16-16,width, height);
        return img;
    }
    
}

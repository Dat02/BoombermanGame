/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.com.boombermangame;

import java.awt.Graphics;
import java.util.LinkedList;

/**
 *
 * @author Admin
 */
public class Controller {
    LinkedList<Ghost> ghosts ;
    public static int score = 0;
   
    public Controller() {
    ghosts = new LinkedList<>();
    }
    
    public void addGhost(Ghost gosh){
        ghosts.add(gosh);
    }
    
    public void update(){
        for (int i=0; i<ghosts.size(); i++) {
            ghosts.get(i).update();
        }
    }
    
    public void draw(Graphics g){
        for (int i=0; i<ghosts.size(); i++){
            if (ghosts.get(i).isVisible()) g.drawImage(ghosts.get(i).ghostImage, ghosts.get(i).x , ghosts.get(i).y,null);
        }
    }
    
}

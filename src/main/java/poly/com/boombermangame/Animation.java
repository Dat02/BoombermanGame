/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.com.boombermangame;

import java.awt.Image;
import java.util.LinkedList;

/**
 *
 * @author Admin
 */
public class Animation {

    public LinkedList<Image> images;
    public int currentImage;

    public Animation() {
        currentImage = 0;
        images = new LinkedList<>();
    }

    public void addImage(Image img) {
        images.add(img);
    }

    public void update() {
        currentImage++;
        if (currentImage >= images.size()) {
            currentImage = 0;
        }
    }
    
    public void UpdateforExplosion(){
        currentImage++;
        if (currentImage >= images.size()){
            currentImage = 0;
        }
    }
    public Image getCurrentImage() {
        return images.get(currentImage);
        
    }

}

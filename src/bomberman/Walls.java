/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 *
 * @author yasinguzel
 */
public class Walls {
    
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected Image image;
    
    public Walls(int x,int y){
        this.x = x;
        this.y = y;
    }
    
    protected void loadImage(String imageName){
        ImageIcon ii = new ImageIcon(imageName);
        image = ii.getImage();
    }
    
    protected void getImageDimensions(){
        width = image.getWidth(null);
        height = image.getHeight(null);
    }
    
    public Image getImage(){
        return image;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
        
    public Rectangle getBounds(){
        return new Rectangle(x, y, width, height);
    }
    
}

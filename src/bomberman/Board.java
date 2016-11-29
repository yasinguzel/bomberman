/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author yasinguzel
 */
public class Board extends JPanel{
    //the games takes place in this class.
    
    private Image nonFragileWall;
    private int location_x = 0,location_y = 42;
    
    public Board(){
        initBoard();
    }

    private void initBoard() {
        loadImage();
        
    }

    private void loadImage() {
        ImageIcon ii = new ImageIcon("images/walls/nonFragileWall.png");
        nonFragileWall = ii.getImage();
    }
    
    public void paintComponent(Graphics g){
        drawFrame(g);       
    }

    private void drawFrame(Graphics g) {
        for (int i = 0; i < 17*2; i++) {
            if(i == 17){
                location_x = 0;
                location_y = 474;
            }
            g.drawImage(nonFragileWall, location_x, location_y, null);
            location_x+=32;
        }
    }
}

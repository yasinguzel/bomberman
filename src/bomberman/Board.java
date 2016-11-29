/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author yasinguzel
 */
public class Board extends JPanel{
    //the games takes place in this class.
    
    private Image nonFragileWall;
    private Image FragileWall;
    
    public Board(){
        initBoard();
    }

    private void initBoard() {
        loadImage();  
        loadImagef();
    }

    private void loadImage() {
        ImageIcon ii = new ImageIcon("images/walls/nonFragileWall.png");
        nonFragileWall = ii.getImage();
    }
    
    private void loadImagef() {
        ImageIcon ii = new ImageIcon("images/walls/FragileWall.png");
        FragileWall = ii.getImage();
    }
    
    public void paintComponent(Graphics g){
        drawFrame(g);       
    }

    private void drawFrame(Graphics g) {
        //draw game map
        int line=0,column=0;
        for (int i = 48; i < 528; i+=32) {
            for (int j = 0; j < 544; j+=32) {
                if((line==0)||(line==14))
                    g.drawImage(nonFragileWall, j, i, null);
                else if((column%2==0)&&(line!=0)&&(line!=14)&&(line%2==0))
                    g.drawImage(nonFragileWall, j, i, null);
                else if((column==0)||(column==16)&&(line!=0)&&(line!=14)&&(line%2!=0))
                    g.drawImage(nonFragileWall, j, i, null);   
                else if((line==1)&&(column==1)||(line==1)&&(column==2)||(line==1)&&(column==3)||(line==2)&&(column==1)||(line==3)&&(column==1)){}
                else if((line==13)&&(column==15)||(line==13)&&(column==14)||(line==13)&&(column==13)||(line==12)&&(column==15)||(line==11)&&(column==15)){}
                else
                    g.drawImage(FragileWall, j, i, null);                   
                column++;
            }
            column=0;
            line++;
        }
    }
}

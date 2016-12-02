/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author yasinguzel
 */
public class Board extends JPanel{
    //the games takes place in this class.
    
    private ArrayList fws = new ArrayList(); //FragileWalls Array
    private ArrayList nfws = new ArrayList(); //nonFragileWalls Array
    
    public Board(){
        initBoard();
    }

    private void initBoard() {
          
    }
    
    public void paintComponent(Graphics g){
        drawFrame(g);       
    }

    private void drawFrame(Graphics g) {
        //draw game map
        //collision deject
        FragileWall fw;
        NonFragileWall nfw;
        int line=0,column=0;
        for (int i = 48; i < 528; i+=32) {
            for (int j = 0; j < 544; j+=32) {
                if((line==0)||(line==14)){
                    nfw = new NonFragileWall(j, i);
                    nfws.add(nfw);
                    g.drawImage(nfw.getImage(), j, i, null);
                }
                    
                else if((column%2==0)&&(line!=0)&&(line!=14)&&(line%2==0)){
                    nfw = new NonFragileWall(j, i);
                    nfws.add(nfw);
                    g.drawImage(nfw.getImage(), j, i, null);
                }
                    
                else if((column==0)||(column==16)&&(line!=0)&&(line!=14)&&(line%2!=0)){
                    nfw = new NonFragileWall(j, i);
                    nfws.add(nfw);
                    g.drawImage(nfw.getImage(), j, i, null);
                }
                
                else if((line==1)&&(column==1)||(line==1)&&(column==2)||(line==1)&&(column==3)||(line==2)&&(column==1)||(line==3)&&(column==1)){}
                else if((line==13)&&(column==15)||(line==13)&&(column==14)||(line==13)&&(column==13)||(line==12)&&(column==15)||(line==11)&&(column==15)){}
//                else{
//                    fw = new FragileWall(j, i);
//                    fws.add(fw);
//                    g.drawImage(fw.getImage(), j, i, null);
//                }
                                      
                column++;
            }
            column=0;
            line++;
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author yasinguzel
 */
public class Board extends JPanel implements ActionListener
{
    //the games takes place in this class.

    Walls wall[][] = new Walls[15][17];//Wall table
    private Timer timer;
    private Player player;
    private final int DELAY = 15;
    private boolean NotCollision = true;

    public Board()
    {
        initBoard();
        timer = new Timer(DELAY,this);
        timer.start();
    }

    private void initBoard()
    {
        addKeyListener(new TAdapter());
        setFocusable(true);
        setDoubleBuffered(true);
        player = new Player(32, 32);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        drawObjects(g);
    }
    
    private void drawObjects(Graphics g) {
        //draw game map
        //collision deject
        g.drawImage(player.getImage(), player.getX(), player.getY(), this);
        //draw game map
        //collision deject
        FragileWall fw;
        NonFragileWall nfw;
        int multiplier = 32;

        for (int line = 0; line < 15; line++)
        {
            for (int column = 0; column < 17; column++)
            {
                if ((line == 0) || (line == 14))
                {
                    nfw = new NonFragileWall(column * multiplier, line * multiplier);
                    wall[line][column] = nfw;
                    g.drawImage(nfw.getImage(), column * multiplier, line * multiplier, null);
                } 
                else if ((column % 2 == 0) && (line != 0) && (line != 14) && (line % 2 == 0))
                {
                    nfw = new NonFragileWall(column * multiplier, line * multiplier);
                    wall[line][column] = nfw;
                    g.drawImage(nfw.getImage(), column * multiplier, line * multiplier, null);
                } 
                else if ((column == 0) || (column == 16) && (line != 0) && (line != 14) && (line % 2 != 0))
                {
                    nfw = new NonFragileWall(column * multiplier, line * multiplier);
                    wall[line][column] = nfw;
                    g.drawImage(nfw.getImage(), column * multiplier, line * multiplier, null);
                } 
                else if ((line == 1) && (column == 1) || (line == 1) && (column == 2) || (line == 1) && (column == 3) || (line == 2) && (column == 1) || (line == 3) && (column == 1))
                {
                } 
                else if ((line == 13) && (column == 15) || (line == 13) && (column == 14) || (line == 13) && (column == 13) || (line == 12) && (column == 15) || (line == 11) && (column == 15))
                {
                } 
//                else
//                {
//                    fw = new FragileWall(column * multiplier, line * multiplier);
//                    wall[line][column] = fw;
//                    g.drawImage(fw.getImage(), column * multiplier, line * multiplier, null);
//                }
            }
        }

        Toolkit.getDefaultToolkit().sync();
    }
    
    public void actionPerformed(ActionEvent e){
        checkCollisions();
        updatePlayer();
        repaint();
    }
    
    private void checkCollisions() {
        Rectangle rp = player.getBounds();
        System.out.println("x: "+player.getX()+"y: "+player.getY());
         for (int i=0;i<15;i++) {
             for (int j = 0; j < 17; j++) {
                 if (wall[i][j]!=null) {
                    Rectangle rnfw = wall[i][j].getBounds();
                    if (rp.intersects(rnfw)) {
                        NotCollision = false;
                    }
                 }
             }  
        }
        System.out.println(NotCollision);
    }

    private void updatePlayer() {
        if (NotCollision) {
            player.move();
        }
    }
    
    private class TAdapter extends KeyAdapter {
        public void keyReleased(KeyEvent e){
            player.keyReleased(e);
        }
        public void keyPressed(KeyEvent e){
            player.keyPressed(e);
        }
        
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author yasinguzel
 */
public class Board extends JPanel
{
    //the games takes place in this class.

    Walls wall[][] = new Walls[15][17];//Wall table
    private Player[] Players = new Player[2]; //Players Array

    public Board()
    {
        initBoard();
    }

    private void initBoard()
    {

    }

    public void paintComponent(Graphics g)
    {
        drawFrame(g);
    }

    private void drawFrame(Graphics g)
    {
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
                else
                {
                    fw = new FragileWall(column * multiplier, line * multiplier);
                    wall[line][column] = fw;
                    g.drawImage(fw.getImage(), column * multiplier, line * multiplier, null);
                }
                if ((line == 1) && (column == 1))
                {
                    Players[0] = new Player(column * multiplier, line * multiplier);
                    g.drawImage(Players[0].getImage(), column * multiplier, line * multiplier, null);
                }
                if ((line == 13) && (column == 15))
                {
                    Players[1] = new Player(column * multiplier, line * multiplier);
                    g.drawImage(Players[1].getImage(), column * multiplier, line * multiplier, null);
                }
            }
        }

    }
}

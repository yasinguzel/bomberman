/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;

/**
 *
 * @author yasinguzel
 */
public class Board extends JPanel
{
    //the games takes place in this class.

    Walls wall[][] = new Walls[15][17];//Wall table
    int map[][] = new int[15][17]; //[satır][sütun]
    private Player player1; //Players Array
    int playerX=1,playerY=1;


    public Board()
    {
        initBoard();
    }

    private void initBoard()
    {
        addKeyListener(new TAdapter());
        setFocusable(true);
        setDoubleBuffered(true);
        player1 = new Player(32, 80);
        drawMap();
    }
    
    private void drawMap() {
            for (int line = 0; line < 15; line++)
            {
                for (int column = 0; column < 17; column++)
                {
                    if ((line == 0) || (line == 14))
                    {
                         map[line][column] = 2;
                    } 
                    else if ((column % 2 == 0) && (line != 0) && (line != 14) && (line % 2 == 0))
                    {
                         map[line][column] = 2;
                    } 
                    else if ((column == 0) || (column == 16) && (line != 0) && (line != 14) && (line % 2 != 0))
                    {
                         map[line][column] = 2;
                    } 
                    else if ((line == 1) && (column == 1) || (line == 1) && (column == 2) || (line == 1) && (column == 3) || (line == 2) && (column == 1) || (line == 3) && (column == 1)){
                        map[line][column] = 0;
                    } 
                    else if ((line == 13) && (column == 15) || (line == 13) && (column == 14) || (line == 13) && (column == 13) || (line == 12) && (column == 15) || (line == 11) && (column == 15)){
                        map[line][column] = 0;
                    } 
                }
            }
            map[playerY][playerX] = 1;
        }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        drawObjects(g);
    }
    
    private void drawObjects(Graphics g) {
        //draw game map
        showMap();
        g.drawImage(player1.getImage(), player1.getX(), player1.getY(), this);
        
        FragileWall fw;
        NonFragileWall nfw;
        int multiplier = 32;
        int scorBoardSpace = 48;
        int x;
        int y;

        for (int line = 0; line < 15; line++)
        {
            for (int column = 0; column < 17; column++)
            {
                x = column * multiplier;
                y = line * multiplier + scorBoardSpace;
                if ((line == 0) || (line == 14))
                {
                     nfw = new NonFragileWall(x, y);
                     wall[line][column] = nfw;
                     g.drawImage(nfw.getImage(), x, y, null);
                } 
                else if ((column % 2 == 0) && (line != 0) && (line != 14) && (line % 2 == 0))
                {
                     nfw = new NonFragileWall(x, y);
                     wall[line][column] = nfw;
                     g.drawImage(nfw.getImage(), x, y, null);
                } 
                else if ((column == 0) || (column == 16) && (line != 0) && (line != 14) && (line % 2 != 0))
                {
                     nfw = new NonFragileWall(x, y);
                     wall[line][column] = nfw;
                     g.drawImage(nfw.getImage(), x, y, null);
                } 
                else if ((line == 1) && (column == 1) || (line == 1) && (column == 2) || (line == 1) && (column == 3) || (line == 2) && (column == 1) || (line == 3) && (column == 1)){                } 
                else if ((line == 13) && (column == 15) || (line == 13) && (column == 14) || (line == 13) && (column == 13) || (line == 12) && (column == 15) || (line == 11) && (column == 15)){                } 
//                else
//                {
//                     fw = new FragileWall(x, y);
//                     wall[line][column] = fw;
//                     g.drawImage(fw.getImage(), x, y, null);
//                }
//                if ((line == 1) && (column == 1))
//                {
//                     Players[0] = new Player(x, y);
//                     g.drawImage(Players[0].getImage(), x, y, null);
//                }
//                if ((line == 13) && (column == 15))
//                {
//                     Players[1] = new Player(x, y);
//                     g.drawImage(Players[1].getImage(), x, y, null);
//                }
            }
        }

        Toolkit.getDefaultToolkit().sync();
    }

    

    private void showMap() {
        System.out.println("new map");
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 17; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }
    
    
    

    private class TAdapter extends KeyAdapter {
        
        public void keyPressed(KeyEvent e) {
        //map[satır][sutun],map[y][x]        
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_LEFT){
                playerX--;
                if (map[playerY][playerX] == 2) {
                    playerX++;
                    return;
                }
                map[playerY][playerX] = 1;
                playerX++;
                map[playerY][playerX] = 0;
                playerX--;
                player1.setDx(-32);
                player1.move();
            }
            if(key == KeyEvent.VK_RIGHT){   
                playerX++;
                if (map[playerY][playerX] == 2) {
                    playerX--;
                    return;
                    
                }
                map[playerY][playerX] = 1;
                playerX--;
                map[playerY][playerX] = 0;
                playerX++;
                player1.setDx(32);
                player1.move();
            }
            if(key == KeyEvent.VK_UP){
                playerY--;
                if (map[playerY][playerX] == 2) {
                    playerY++;
                    return;
                    
                }
                map[playerY][playerX] = 1;
                playerY++;
                map[playerY][playerX] = 0;
                playerY--;
                player1.setDy(-32);
                player1.move();
                
            }
            if(key == KeyEvent.VK_DOWN){
                playerY++;
                if (map[playerY][playerX] == 2) {
                    playerY--;
                    return;
                }
                map[playerY][playerX] = 1;
                playerY--;
                map[playerY][playerX] = 0;
                playerY++;
                player1.setDy(32);
                player1.move();
            }            
            repaint();
        }
    }
        

}

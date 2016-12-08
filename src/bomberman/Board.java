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
    private Player[] players = new Player[2];//Players Array
    int player1X = 1, player1Y = 1, player2X = 15, player2Y = 13;

    public Board()
    {
        initBoard();
    }

    private void initBoard()
    {
        addKeyListener(new TAdapter());
        setFocusable(true);
        setDoubleBuffered(true);
        players[0] = new Player(32, 80);
        players[1] = new Player(480, 464);
        drawMap();
    }

    private void drawMap()
    {
        for (int line = 0; line < 15; line++)
        {
            for (int column = 0; column < 17; column++)
            {
                if ((line == 0) || (line == 14))
                {
                    map[line][column] = 2;
                } else if ((column % 2 == 0) && (line != 0) && (line != 14) && (line % 2 == 0))
                {
                    map[line][column] = 2;
                } else if ((column == 0) || (column == 16) && (line != 0) && (line != 14) && (line % 2 != 0))
                {
                    map[line][column] = 2;
                } else if ((line == 1) && (column == 1) || (line == 1) && (column == 2) || (line == 1) && (column == 3) || (line == 2) && (column == 1) || (line == 3) && (column == 1))
                {
                    map[line][column] = 0;
                } else if ((line == 13) && (column == 15) || (line == 13) && (column == 14) || (line == 13) && (column == 13) || (line == 12) && (column == 15) || (line == 11) && (column == 15))
                {
                    map[line][column] = 0;
                }
            }
        }
        map[player1Y][player1X] = 1;
        map[player2Y][player2X] = 1;
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        drawObjects(g);
    }

    private void drawObjects(Graphics g)
    {
        //draw game map
        showMap();
        g.drawImage(players[0].getImage(), players[0].getX(), players[0].getY(), this);
        g.drawImage(players[1].getImage(), players[1].getX(), players[1].getY(), this);

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
                } else if ((column % 2 == 0) && (line != 0) && (line != 14) && (line % 2 == 0))
                {
                    nfw = new NonFragileWall(x, y);
                    wall[line][column] = nfw;
                    g.drawImage(nfw.getImage(), x, y, null);
                } else if ((column == 0) || (column == 16) && (line != 0) && (line != 14) && (line % 2 != 0))
                {
                    nfw = new NonFragileWall(x, y);
                    wall[line][column] = nfw;
                    g.drawImage(nfw.getImage(), x, y, null);
                } else if ((line == 1) && (column == 1) || (line == 1) && (column == 2) || (line == 1) && (column == 3) || (line == 2) && (column == 1) || (line == 3) && (column == 1))
                {
                } else if ((line == 13) && (column == 15) || (line == 13) && (column == 14) || (line == 13) && (column == 13) || (line == 12) && (column == 15) || (line == 11) && (column == 15))
                {
                }
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

    private void showMap()
    {
        System.out.println("new map");
        for (int i = 0; i < 15; i++)
        {
            for (int j = 0; j < 17; j++)
            {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    private class TAdapter extends KeyAdapter
    {

        public void keyPressed(KeyEvent e)
        {
            //map[satır][sutun],map[y][x]        
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT)
            {
                player1X--;
                if (map[player1Y][player1X] == 2)
                {
                    player1X++;
                    return;
                }
                map[player1Y][player1X] = 1;
                player1X++;
                map[player1Y][player1X] = 0;
                player1X--;
                players[0].setDx(-32);
                players[0].move();
            }
            if (key == KeyEvent.VK_RIGHT)
            {
                player1X++;
                if (map[player1Y][player1X] == 2)
                {
                    player1X--;
                    return;

                }
                map[player1Y][player1X] = 1;
                player1X--;
                map[player1Y][player1X] = 0;
                player1X++;
                players[0].setDx(32);
                players[0].move();
            }
            if (key == KeyEvent.VK_UP)
            {
                player1Y--;
                if (map[player1Y][player1X] == 2)
                {
                    player1Y++;
                    return;

                }
                map[player1Y][player1X] = 1;
                player1Y++;
                map[player1Y][player1X] = 0;
                player1Y--;
                players[0].setDy(-32);
                players[0].move();

            }
            if (key == KeyEvent.VK_DOWN)
            {
                player1Y++;
                if (map[player1Y][player1X] == 2)
                {
                    player1Y--;
                    return;
                }
                map[player1Y][player1X] = 1;
                player1Y--;
                map[player1Y][player1X] = 0;
                player1Y++;
                players[0].setDy(32);
                players[0].move();
            }
            if (key == KeyEvent.VK_A)
            {
                player2X--;
                if (map[player2Y][player2X] == 2)
                {
                    player2X++;
                    return;
                }
                map[player2Y][player2X] = 1;
                player2X++;
                map[player2Y][player2X] = 0;
                player2X--;
                players[1].setDx(-32);
                players[1].move();
            }
            if (key == KeyEvent.VK_D)
            {
                player2X++;
                if (map[player2Y][player2X] == 2)
                {
                    player2X--;
                    return;

                }
                map[player2Y][player2X] = 1;
                player2X--;
                map[player2Y][player2X] = 0;
                player2X++;
                players[1].setDx(32);
                players[1].move();
            }
            if (key == KeyEvent.VK_W)
            {
                player2Y--;
                if (map[player2Y][player2X] == 2)
                {
                    player2Y++;
                    return;

                }
                map[player2Y][player2X] = 1;
                player2Y++;
                map[player2Y][player2X] = 0;
                player2Y--;
                players[1].setDy(-32);
                players[1].move();

            }
            if (key == KeyEvent.VK_S)
            {
                player2Y++;
                if (map[player2Y][player2X] == 2)
                {
                    player2Y--;
                    return;
                }
                map[player2Y][player2X] = 1;
                player2Y--;
                map[player2Y][player2X] = 0;
                player2Y++;
                players[1].setDy(32);
                players[1].move();
            }
            repaint();
        }
    }

}

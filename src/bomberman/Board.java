/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import net.beadsproject.beads.core.AudioContext;
import net.beadsproject.beads.data.SampleManager;
import net.beadsproject.beads.ugens.Gain;
import net.beadsproject.beads.ugens.SamplePlayer;

/**
 *
 * @author yasinguzel
 */
public class Board extends JPanel implements ActionListener
{

    //the games takes place in this class.
    Timer timer;
    public Entity[][] entities = new Entity[15][17];//Wall table
    int map[][] = new int[15][17]; //[satır][sütun]
    private final Player[] players = new Player[2];//Players Array
    int player1X = 15, player1Y = 13, player2X = 1, player2Y = 1;
    //List <Bomb> bombs = new ArrayList<Bomb>();
    static AudioContext ac = new AudioContext();

    public Board()
    {
        initBoard();
    }

    private void initBoard()
    {
        addKeyListener(new TAdapter());
        setFocusable(true);
        setDoubleBuffered(true);
        players[0] = new Player(480, 464);
        players[1] = new Player(32, 80);
        drawMap();
        timer = new Timer(100, (ActionListener) this);
        timer.start();
        playBackgroundSound();
    }

    int x;
    int y;
    int multiplier = 32;
    int scorBoardSpace = 48;

    public static void playBackgroundSound()
    {
        try
        {
            String audioFile = "audio/bombermanMusic.wav";
            SamplePlayer player = new SamplePlayer(ac, SampleManager.sample(audioFile));
            Gain g = new Gain(ac, 2, 1f);
            g.addInput(player);
            ac.out.addInput(g);
            ac.start();
        } catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, "Error with playing sound.");
        }
    }

    private void drawMap()
    {

        for (int line = 0; line < 15; line++)
        {
            for (int column = 0; column < 17; column++)
            {
                x = column * multiplier;
                y = line * multiplier + scorBoardSpace;
                if ((line == 0) || (line == 14))
                {
                    entities[line][column] = new NonFragileWall(x, y);
                } else if ((column % 2 == 0) && (line != 0) && (line != 14) && (line % 2 == 0))
                {
                    entities[line][column] = new NonFragileWall(x, y);
                } else if ((column == 0) || (column == 16) && (line != 0) && (line != 14) && (line % 2 != 0))
                {
                    entities[line][column] = new NonFragileWall(x, y);
                } else if ((line == 1) && (column == 1) || (line == 1) && (column == 2) || (line == 1) && (column == 3) || (line == 2) && (column == 1) || (line == 3) && (column == 1))
                {
                } else if ((line == 13) && (column == 15) || (line == 13) && (column == 14) || (line == 13) && (column == 13) || (line == 12) && (column == 15) || (line == 11) && (column == 15))
                {
                } else
                {
                    entities[line][column] = new FragileWall(x, y);
                }
            }
        }

        for (int line = 0; line < 15; line++)
        {
            for (int column = 0; column < 17; column++)
            {
                if ((line == 0) || (line == 14))
                {
                    map[line][column] = 2;//NonFragileWall
                } else if ((column % 2 == 0) && (line != 0) && (line != 14) && (line % 2 == 0))
                {
                    map[line][column] = 2;//NonFragileWall
                } else if ((column == 0) || (column == 16) && (line != 0) && (line != 14) && (line % 2 != 0))
                {
                    map[line][column] = 2;//NonFragileWall
                } else if ((line == 1) && (column == 1) || (line == 1) && (column == 2) || (line == 1) && (column == 3) || (line == 2) && (column == 1) || (line == 3) && (column == 1))
                {
                    map[line][column] = 0;//Space
                } else if ((line == 13) && (column == 15) || (line == 13) && (column == 14) || (line == 13) && (column == 13) || (line == 12) && (column == 15) || (line == 11) && (column == 15))
                {
                    map[line][column] = 0;//Space
                } else
                {
                    map[line][column] = 3;//FragileWall
                }//4 = bomb
                //5 = flame
            }
        }
        map[player1Y][player1X] = 1;
        map[player2Y][player2X] = 1;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        drawObjects(g);
    }

    private void drawObjects(Graphics g)
    {
        //draw game map
        showMap();
        g.drawImage(players[0].getImage(), players[0].getX(), players[0].getY(), 32, 32, this);
        g.drawImage(players[1].getImage(), players[1].getX(), players[1].getY(), 32, 32, this);

        for (int line = 0; line < 15; line++)
        {
            for (int column = 0; column < 17; column++)
            {
                x = column * multiplier;
                y = line * multiplier + scorBoardSpace;
                if (entities[line][column] != null)
                {
                    if ((line == 0) || (line == 14))
                    {
                        g.drawImage(entities[line][column].getImage(), x, y, 32, 32, null);
                    } else if ((column % 2 == 0) && (line != 0) && (line != 14) && (line % 2 == 0))
                    {
                        g.drawImage(entities[line][column].getImage(), x, y, 32, 32, null);
                    } else if ((column == 0) || (column == 16) && (line != 0) && (line != 14) && (line % 2 != 0))
                    {
                        g.drawImage(entities[line][column].getImage(), x, y, 32, 32, null);
                    } //                    else if ((line == 1) && (column == 1) || (line == 1) && (column == 2) || (line == 1) && (column == 3) || (line == 2) && (column == 1) || (line == 3) && (column == 1))
                    //                    {
                    //     broken         } else if ((line == 13) && (column == 15) || (line == 13) && (column == 14) || (line == 13) && (column == 13) || (line == 12) && (column == 15) || (line == 11) && (column == 15))
                    //                    {
                    //                    } 
                    else if (entities[line][column].isVisible())
                    {
                        g.drawImage(entities[line][column].getImage(), x, y, 32, 32, null);
                    }
                    if (map[line][column] == 4)
                    {
                        if (!entities[line][column].isVisible())
                        {
                            bombSound();
                            for (int i = 1; i <= 2; i++)
                            {
                                if (map[line + i][column] == 2)
                                {
                                    break;
                                }
                                if (map[line + i][column] == 0 || map[line + i][column] == 1 || map[line + i][column] == 3)
                                {
                                    map[line + i][column] = 0;
                                    entities[line + i][column] = new Flame(x, y, FlameDirection.Down);
                                }
                            }
                            for (int i = 0; i <= 2; i++)
                            {
                                if (map[line][column + i] == 2)
                                {
                                    break;
                                }
                                if (map[line][column + i] == 0 || map[line][column + i] == 1 || map[line][column + i] == 3)
                                {
                                    map[line][column + i] = 0;
                                    entities[line][column + i] = new Flame(x, y, FlameDirection.Right);
                                }
                            }
                            for (int i = 0; i <= 2; i++)
                            {
                                if (map[line - i][column] == 2)
                                {
                                    break;
                                }
                                if (map[line - i][column] == 0 || map[line - i][column] == 1 || map[line - i][column] == 3)
                                {
                                    map[line - i][column] = 0;
                                    entities[line - i][column] = new Flame(x, y, FlameDirection.Up);
                                }
                            }
                            for (int i = 0; i <= 2; i++)
                            {

                                if (map[line][column - i] == 2)
                                {
                                    break;
                                }
                                if (map[line][column - i] == 0 || map[line][column - i] == 1 || map[line][column - i] == 3)
                                {
                                    map[line][column - i] = 0;
                                    entities[line][column - i] = new Flame(x, y, FlameDirection.Left);
                                }
                            }
                            entities[line][column] = null;
                            map[line][column] = 0;
                        } else
                        {
                            g.drawImage(entities[line][column].getImage(), x, y, 32, 32, null);
                        }
                    }
                }
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

    @Override
    public void actionPerformed(ActionEvent e)
    {
        repaint();
    }

    private void bombSound()
    {
        try
        {
            String audioFile = "audio/explosion.wav";
            SamplePlayer player = new SamplePlayer(ac, SampleManager.sample(audioFile));
            Gain g = new Gain(ac, 2, 1f);
            g.addInput(player);
            ac.out.addInput(g);
            ac.start();
        } catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, "Error with playing sound.");
        }
    }

    private class TAdapter extends KeyAdapter implements ActionListener
    {

        Timer timer;

        public TAdapter()
        {
            timer = new Timer(100, this);
            timer.start();
        }

        MultiKeyPressListener listener = new MultiKeyPressListener();

        @Override
        public void keyPressed(KeyEvent e)
        {
            listener.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e)
        {
            listener.keyReleased(e);
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            for (int key : listener.GetKeys())
            {
                if (key == KeyEvent.VK_LEFT)
                {
                    player1X--;
                    if (map[player1Y][player1X] != 1 && map[player1Y][player1X] != 0)
                    {
                        player1X++;

                    } else
                    {
                        map[player1Y][player1X] = 1;
                        player1X++;
                        if (map[player1Y][player1X] != 4)
                        {
                            map[player1Y][player1X] = 0;
                        }
                        player1X--;
                        players[0].setDx(-32);
                        players[0].move();
                    }
                }
                if (key == KeyEvent.VK_RIGHT)
                {
                    player1X++;
                    if (map[player1Y][player1X] != 1 && map[player1Y][player1X] != 0)
                    {
                        player1X--;
                    } else
                    {

                        map[player1Y][player1X] = 1;
                        player1X--;
                        if (map[player1Y][player1X] != 4)
                        {
                            map[player1Y][player1X] = 0;
                        }
                        player1X++;
                        players[0].setDx(32);
                        players[0].move();
                    }
                }
                if (key == KeyEvent.VK_UP)
                {
                    player1Y--;
                    if (map[player1Y][player1X] != 1 && map[player1Y][player1X] != 0)
                    {
                        player1Y++;

                    } else
                    {
                        map[player1Y][player1X] = 1;
                        player1Y++;
                        if (map[player1Y][player1X] != 4)
                        {
                            map[player1Y][player1X] = 0;
                        }
                        player1Y--;
                        players[0].setDy(-32);
                        players[0].move();

                    }

                }
                if (key == KeyEvent.VK_DOWN)
                {
                    player1Y++;
                    if (map[player1Y][player1X] != 1 && map[player1Y][player1X] != 0)
                    {
                        player1Y--;
                    } else
                    {

                        map[player1Y][player1X] = 1;
                        player1Y--;
                        if (map[player1Y][player1X] != 4)
                        {
                            map[player1Y][player1X] = 0;
                        }
                        player1Y++;
                        players[0].setDy(32);
                        players[0].move();
                    }
                }
                if (key == KeyEvent.VK_A)
                {
                    player2X--;
                    if (map[player2Y][player2X] != 1 && map[player2Y][player2X] != 0)
                    {
                        player2X++;
                    } else
                    {

                        map[player2Y][player2X] = 1;
                        player2X++;
                        if (map[player2Y][player2X] != 4)
                        {
                            map[player2Y][player2X] = 0;
                        }
                        player2X--;
                        players[1].setDx(-32);
                        players[1].move();
                    }
                }
                if (key == KeyEvent.VK_D)
                {
                    player2X++;
                    if (map[player2Y][player2X] != 1 && map[player2Y][player2X] != 0)
                    {
                        player2X--;

                    } else
                    {

                        map[player2Y][player2X] = 1;
                        player2X--;
                        if (map[player2Y][player2X] != 4)
                        {
                            map[player2Y][player2X] = 0;
                        }
                        player2X++;
                        players[1].setDx(32);
                        players[1].move();
                    }
                }
                if (key == KeyEvent.VK_W)
                {
                    player2Y--;
                    if (map[player2Y][player2X] != 1 && map[player2Y][player2X] != 0)
                    {
                        player2Y++;

                    } else
                    {

                        map[player2Y][player2X] = 1;
                        player2Y++;
                        if (map[player2Y][player2X] != 4)
                        {
                            map[player2Y][player2X] = 0;
                        }
                        player2Y--;
                        players[1].setDy(-32);
                        players[1].move();
                    }

                }
                if (key == KeyEvent.VK_S)
                {
                    player2Y++;
                    if (map[player2Y][player2X] != 1 && map[player2Y][player2X] != 0)
                    {
                        player2Y--;
                    } else
                    {

                        map[player2Y][player2X] = 1;
                        player2Y--;
                        if (map[player2Y][player2X] != 4)
                        {
                            map[player2Y][player2X] = 0;
                        }
                        player2Y++;
                        players[1].setDy(32);
                        players[1].move();
                    }
                }

                if (key == KeyEvent.VK_CONTROL)//player2
                {
                    x = player2X * multiplier;
                    y = player2Y * multiplier + scorBoardSpace;

                    map[player2Y][player2X] = 4;
                    entities[player2Y][player2X] = new Bomb(x, y);
                    bombPutSound();
                }
                if (key == KeyEvent.VK_SPACE)//player1
                {
                    x = player1X * multiplier;
                    y = player1Y * multiplier + scorBoardSpace;

                    map[player1Y][player1X] = 4;
                    entities[player1Y][player1X] = new Bomb(x, y);
                    bombPutSound();
                }
                repaint();
            }

        }

        private void bombPutSound()
        {
            Clip clip;
            try
            {
                String audioFile = "audio/bombPutted.wav";
                SamplePlayer player = new SamplePlayer(ac, SampleManager.sample(audioFile));
                Gain g = new Gain(ac, 2, 1f);
                g.addInput(player);
                ac.out.addInput(g);
                ac.start();
            } catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, "Error with playing sound.");
            }
        }
    }

}

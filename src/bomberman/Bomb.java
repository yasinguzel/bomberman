/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author blacklake
 */
public class Bomb extends Entity implements ActionListener
{

    Timer timer;

    Bomb(int x, int y)
    {
        super(x, y);
        InitBomb();
    }

    private void InitBomb()
    {
        vis = true;
        loadImage("images/walls/Bomb1.png");
        timer = new Timer(1000, (ActionListener) this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        vis = false;
    }

}

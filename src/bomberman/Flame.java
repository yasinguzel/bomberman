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
public class Flame extends Entity implements ActionListener
{
    Timer timer;
    FlameDirection direction;
    public Flame(int x, int y, FlameDirection dir)
    {
        super(x, y);
        direction=dir;
        InitFlame();
    }

    private void InitFlame()
    {
        vis=true;
        if (direction==FlameDirection.Up)
        {
             loadImage("images/walls/FlameUp.png");
        }
        if (direction==FlameDirection.Right)
        {
             loadImage("images/walls/FlameRight.png");
        }
        if (direction==FlameDirection.Down)
        {
             loadImage("images/walls/FlameDown.png");
        }
        if (direction==FlameDirection.Left)
        {
             loadImage("images/walls/FlameLeft.png");
        }
        getImageDimensions();
        timer = new Timer(500, (ActionListener) this);
        timer.start();
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        vis=false;
    }

    
}

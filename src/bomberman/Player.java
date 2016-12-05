/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import java.awt.Rectangle;

/**
 *
 * @author blacklake
 */
public class Player extends Entity
{

    Rectangle r;

    public Player(int x, int y)
    {
        super(x, y);
        InitPlayer();
    }

    private void InitPlayer()
    {
        r = getBounds();
        loadImage("/home/blacklake/NetBeansProjects/Bomberman/images/Player/player.png");
        getImageDimensions();
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;

/**
 *
 * @author blacklake
 */
public class Player extends Entity
{
    private int dx;
    private int dy;
    
    public Player(int x, int y)
    {
        super(x, y);
        InitPlayer();
    }

    private void InitPlayer()
    {
        loadImage("images/Player/player.png");
        getImageDimensions();
    }
    
    public void move(){
        x+=dx;
        y+=dy;
    }
    
    public Rectangle getBounds(){
        return new Rectangle(x, y, 22, 22);
    }
    
    public void keyReleased(KeyEvent e) {
        
        int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }
        
        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
        
        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }
        
        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }
    
    

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_LEFT) {
            dx = -1;
        }
        
        if (key == KeyEvent.VK_RIGHT) {
            dx = 1;
        }
        
        if (key == KeyEvent.VK_UP) {
            dy = -1;
        }
        
        if (key == KeyEvent.VK_DOWN) {
            dy = 1;
        }
    }

}

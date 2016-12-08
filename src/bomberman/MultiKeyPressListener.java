/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author blacklake
 */
public class MultiKeyPressListener implements KeyListener
{

    private final Set<Integer> pressed = new HashSet<>();
    
    

    @Override
    public synchronized void keyPressed(KeyEvent e)
    {
        pressed.add(e.getKeyCode());
        if (pressed.size() > 1)
        {
            // More than one key is currently pressed.
            // Iterate over pressed to get the keys.
        }
    }

    @Override
    public synchronized void keyReleased(KeyEvent e)
    {
        pressed.remove(e.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent e)
    {/* Not used */ }

    public Set<Integer> GetKeys()
    {
        return pressed;
    }


    

}

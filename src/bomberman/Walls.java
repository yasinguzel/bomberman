/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import java.awt.Rectangle;


/**
 *
 * @author yasinguzel
 */
public class Walls extends Entity{
    
    public Walls(int x, int y) {
        super(x, y);
        initWalls();
    }

    private void initWalls() {
        
    }
    
    public Rectangle getBounds(){
        return new Rectangle(x, y, 32, 32);
    }
    
    
    
}

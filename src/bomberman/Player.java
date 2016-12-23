/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;


/**
 *
 * @author blacklake
 */
public class Player extends Entity
{
    private int dx;
    private int dy;
    int bombCount=3;
    
    public Player(int x, int y)
    {
        super(x, y);
    }
    
    public void move(){
            x+=dx;
            y+=dy;
            //System.out.println("PlayerX: "+x+" PlayerY: "+y+"PlayerDx: "+dx+"PlayerDY: "+dy);
            setZero();
    }
    
    public void setDx(int dx){
        this.dx = dx;
    }
    
    public void setDy(int dy){
        this.dy = dy;
    }
    
    public void setZero(){
        dx = 0;
        dy = 0;
    }
    
    

}

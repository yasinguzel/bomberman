/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

/**
 *
 * @author yasinguzel
 */
public class FragileWall extends Walls{
    
    protected boolean vis=true;
    
    public FragileWall(int x, int y) {
        super(x, y);
        initFragileWall();
    }

    private void initFragileWall() {
        loadImage("images/walls/fragilewall.png");
    }
    
    public boolean isVisible(){
        return vis;
    }
    
    public void setVisible(Boolean visible){
        vis = visible;
    }
    
}

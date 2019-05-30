/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import view.GamePanel;

public class FlyingSaucerStrategy implements Strategy
{
    private final int WIDTH = 50;
    private final int HEIGHT = 20;
    private final int UNIT_TRAVEL = 5; // per frame
    private int direction = 1; // +1: to the right; -1 to the left
    
    
    public FlyingSaucerStrategy()
    {
        
    }
    
    @Override
    public void move(float x, float y, GameFigure context)
    {
       // if(state == GameFigureState.STATE_ACTIVE)
      //  {
        if (direction > 0) {
            // moving to the right
            x += UNIT_TRAVEL;
            if (x + WIDTH > GamePanel.width) {
                direction = -1;
            }
        } 
        else {
            // moving to the left
            x -= UNIT_TRAVEL;
            if (x <= 0) {
                direction = 1;
            }
        }
        
//        else if(state == GameFigureState.STATE_DYING)
//        {
//            if(y - 5.0F > 2.0F)
//            {
//                y += 5.0F;
//            }
//            else
//                state = GameFigureState.STATE_DONE;
//        }

        context.setPosition(x, y);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import view.GamePanel;

public class FlyingSaucerDieingMove implements Strategy
{
    private final int WIDTH = 50;
    private final int HEIGHT = 20;
    private final int UNIT_TRAVEL = 5; // per frame
    private int direction = 1; // +1: to the right; -1 to the left
    
    
    public FlyingSaucerDieingMove()
    {
        
    }
    
    @Override
    public void move(float x, float y, GameFigure context)
    {      

            if(y - 5.0F > 2.0F)
            {
                y += 5.0F;
                context.setPosition(x, y);
            }
            else
                context.goNextState();
        


    }
}

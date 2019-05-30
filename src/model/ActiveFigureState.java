/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

public class ActiveFigureState implements GameFigureState
{
    public ActiveFigureState()
    {
        
    }
    
    @Override
    public void goNext(GameFigure context)
    {
        context.setState(new DieingFigureState());
    }
}

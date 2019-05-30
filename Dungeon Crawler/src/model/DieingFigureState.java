/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

public class DieingFigureState implements GameFigureState
{
    public DieingFigureState()
    {

    }
    
    @Override
    public void goNext(GameFigure context)
    {
        context.setState(new DoneFigureState());
    }
}

package model;

import java.awt.Graphics2D;

public abstract class GameFigure implements CollisionBox {

    // public for a faster access during animation
    public float x;
    public float y;
    
    //public int state;
    
    
    //For use when state and strategy implemented
    public GameFigureState state;
    public Strategy movement;



    public GameFigure(float x, float y) {
        this.x = x;
        this.y = y;
    }
    
    public float getX(){
        return x;
    }
    
    public float getY(){
           return y;
    }
    // how to render on the canvas
    public abstract void render(Graphics2D g);

    // changes per frame
    public abstract void update();
    
    
    //for use when state and strategy implemented
    public abstract void setState(GameFigureState state);
    
    public abstract void goNextState();
    
    //not sure if this one will be needed, if health pickup then most likely
    //public abstract void goPrevState();
    
    //used by concrete strategy to set position of GameFigure
    public abstract void setPosition(float x, float y);
    

}

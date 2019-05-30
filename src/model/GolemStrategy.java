/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.Main;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import view.GamePanel;

public class GolemStrategy implements Strategy
{
    private int dx = 1; // normal 5, 1 for testing
    private int dy = 1; // normal 5, 1 for testing
    private int counter, rangeCounter;
    private int time, ranged;
    private int top;
    Random rand;
    private final int SIZE = 80;
    private float sx, sy;
    private boolean zoom;
    
    
    GolemStrategy()
    {
        counter = -1;
        rangeCounter = -1;
        rand = new Random();
        time = rand.nextInt(250) + 30;
        ranged = rand.nextInt(70) + 30;
        sx = 0;
        sy = 0;
        zoom = false;
    }
    
    @Override
    public void move(float x, float y, GameFigure context)
    {
        sx = x;
        sy = y;
        Rectangle2D hero = model.GameData.shooter.getCollisionBox();
        float heroX = (float) hero.getCenterX();
        float heroY = (float) hero.getCenterY();
        Point2D.Float golemCenter = new Point2D.Float(sx + 20, sy + 20);

        double distance = golemCenter.distance(heroX, heroY);
        GolemBoss golem = (GolemBoss) context;
        
        if(rangeCounter > 40 && distance <= 150)
        {            
            Smash s = new Smash(golemCenter.x, golemCenter.y, heroX, heroY, distance);
            Main.gameData.enemyFigures.add(s);
            rangeCounter = -1;
            ranged = rand.nextInt(70) + 30;
        }
        else if(rangeCounter == ranged)
        {
            Rock r = new Rock(sx, sy, heroX, heroY);
            Main.gameData.enemyFigures.add(r);
            rangeCounter = -1;
            ranged = rand.nextInt(70) + 30;
        }
        
        if(!zoom)
        {
            if(dx > 0)
                golem.setAnimation(golem.animation, golem.moveRight);
            else
                golem.setAnimation(golem.animation, golem.moveLeft);
        }
        
        if(counter < time)
        {
            sx += dx;
            if(sx + SIZE > GamePanel.width)
            {
                dx = -dx;
                sx = GamePanel.width - SIZE;
                golem.setAnimation(golem.animation, golem.moveLeft);
            }
            else if(sx < 0)
            {
                dx = -dx;
                sx = 0;
                golem.setAnimation(golem.animation, golem.moveRight);
            }
        }
        
        else
        {
            sx += dx;
            sy += dy;
            zoom = true;
            
            if(dy > 0)
                golem.setAnimation(golem.animation, golem.moveDown); 
            else
                golem.setAnimation(golem.animation, golem.moveUp);
    
            if(sy + SIZE > GamePanel.height)
            {
                sy = GamePanel.height - SIZE;
                dy = -dy;
                top = rand.nextInt(GamePanel.height - SIZE);
                golem.setAnimation(golem.animation, golem.moveUp);
                //if(top > GamePanel.height - SIZE)
            }
            else if(sx + SIZE > GamePanel.width)
            {
                dx = -dx;
                sx = GamePanel.width - SIZE;
            }
            else if(sx < 0)
            {
                dx = -dx;
                sx = 0;
            }
            else if(sy <= top)
            {
                sy = top;
                dy = -dy;
                counter = -1;
                time = rand.nextInt(250) + 30;
                zoom = false;
            }
        }
        

        context.setPosition(sx, sy);
        golem.animation.update();
        counter++;
        rangeCounter++;
        
    }
}

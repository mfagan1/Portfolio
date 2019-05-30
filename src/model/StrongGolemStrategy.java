/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.Main;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import view.GamePanel;

public class StrongGolemStrategy implements Strategy
{
    private int dx = 0; // set to 3 normal, 0 for testing
    private int dy = 0; // set to 3 normal, 0 for testing
    private int counter, rangeCounter;
    private int time, ranged;
    private int top;
    Random rand;
    private final int SIZE = 80;
    private float sx, sy;
    private boolean zoom;
    
    
    StrongGolemStrategy()
    {
        counter = -1;
        rangeCounter = -1;
        rand = new Random();
        time = rand.nextInt(200) +1;
        ranged = rand.nextInt(120) + 50;
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
            ranged = rand.nextInt(120) + 50;
        }
        else if(rangeCounter == ranged)
        {
            Rock r = new Rock(sx, sy, heroX, heroY);
            Main.gameData.enemyFigures.add(r);
            rangeCounter = -1;
            ranged = rand.nextInt(120) + 50;
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
                time = rand.nextInt(200) + 1;
                zoom = false;
            }
        }
        
        //set the new position of the GameFigure
      context.setPosition(sx, sy);
      golem.animation.update();
      counter++;
      rangeCounter++;
        
    }
}
//public class StrongGolemStrategy implements Strategy
//{
//    //private int dx = 5;
//    //private int dy = 5;
//    private int counter;
//    private int time;
//    private int top;
//    Random rand;
//    private final int SIZE = 50;
//    private float bx, by, tx, ty, dx, dy;
//    private double angle;
//    private final int UNIT_TRAVEL = 5;
//    
//    
//    StrongGolemStrategy()
//    {
//        counter = -1;
//        rand = new Random();
//        time = rand.nextInt(200) +1;
//        bx = 0;
//        by = 0;
//        
//    }
//    
//    @Override
//    public void move(float x, float y, GameFigure context)
//    {
//        //set x & y location of GolemBoss
//        bx = x;
//        by = y;
////        
////        if(counter < time)
////        {
////            sx += dx;
////            if(sx + SIZE > GamePanel.width)
////            {
////                dx = -dx;
////                sx = GamePanel.width - SIZE;
////            }
////            else if(sx < 0)
////            {
////                dx = -dx;
////                sx = 0;
////            }
////        }
////        
////        else
////        {
////            sx += dx;
////            sy += dy;
////            if(sy + SIZE > GamePanel.height)
////            {
////                sy = GamePanel.height - SIZE;
////                dy = -dy;
////                top = rand.nextInt(GamePanel.height - SIZE);
////                //if(top > GamePanel.height - SIZE)
////            }
////            else if(sx + SIZE > GamePanel.width)
////            {
////                dx = -dx;
////                sx = GamePanel.width - SIZE;
////            }
////            else if(sx < 0)
////            {
////                dx = -dx;
////                sx = 0;
////            }
////            else if(sy <= top)
////            {
////                sy = top;
////                dy = -dy;
////                counter = -1;
////                time = rand.nextInt(200) + 1;
////            }
////        }
//        
////        bx = super.x;
////        by = super.y;
//        
//
//            if(counter < time)
//            {
//                dx = UNIT_TRAVEL;
//                bx += dx;
//                if(bx + SIZE > GamePanel.width)
//                {
//                    dx = -dx;
//                    bx = GamePanel.width - SIZE;
//                }
//                else if(bx < 0)
//                {
//                    dx = -dx;
//                    bx = 0;
//                }
//            }
//            
//            else if(counter == time)
//            {
//                Rectangle2D target = model.GameData.shooter.getCollisionBox();
//                Rectangle2D golemStart = context.getCollisionBox();
//                //Get center of shooter and GolemBoss for attack angle
//                tx = (float) target.getCenterX();
//                ty = (float) target.getCenterY();
//                bx = (float) golemStart.getCenterX();
//                by = (float) golemStart.getCenterY();
//                
//                angle = Math.atan2(ty - by, tx - bx);
//                dx = (float) (UNIT_TRAVEL * Math.cos(angle));
//                dy = (float) (UNIT_TRAVEL * Math.sin(angle));
////                if(ty - by < 0)
////                {
////                    dy = -dy;
////                }
////                if(tx - dx < 0)
////                {
////                    dx = -dx;
////                }
//            }
//            
//            else if(counter > time)
//            {
//                //bx += (float) (dx * Math.cos(angle));
//                //by += (float) (dy * Math.sin(angle));
//                bx += dx;
//                by += dy;
//                
//                if(by + SIZE > GamePanel.height)
//                {
//                    by = GamePanel.height - SIZE;
//                    dy = -dy;
//                    top = rand.nextInt(GamePanel.height - SIZE);
//                    //time = rand.nextInt(200) + 1;
//                }
//                else if(by < top)
//                {
//                    by = top;
//                    dy = -dy;
//                    time = rand.nextInt(200) + 1;
//                }
//                else if(bx + SIZE > GamePanel.width)
//                {
//                    dx = -dx;
//                    bx = GamePanel.width - SIZE;
//                    //attack = false;
//                    counter = -1;
//                    time = rand.nextInt(200) + 1;
//                    top = rand.nextInt(GamePanel.height - SIZE);
//                }
//                else if(bx < 0)
//                {
//                    dx = -dx;
//                    bx = 0;
//                    //attack = false;
//                    counter = -1;
//                    time = rand.nextInt(200) + 1;
//                    top = rand.nextInt(GamePanel.height - SIZE);
//                }
//                else if(by <= top)
//                {
//                    by = top;
//                    dy = -dy;
//                    counter = -1;
//                    //attack = false;
//                    time = rand.nextInt(200) + 1;
//                    top = rand.nextInt(GamePanel.height - SIZE);
//                }
//        }
//        counter++;
//        context.setPosition(bx, by);
//               
//    }
//}

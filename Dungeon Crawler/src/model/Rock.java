/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Rock extends GameFigure implements Weapon
{
    public Point2D.Float hero;
    private float dx, dy;
    private static final int MOVE_DISTANCE = 7;
    private int rockSize = 15;
    private static final int MAX_SIZE = 70;
    private boolean done;
    private DamageStrategy damageStrategy;

    private final String imagePath = "..//resources//images//rockAttack//";
    public SpriteAnimation animation, moving, exploding, idle;

    public Rock(float x, float y, float tx, float ty) {
        super(x, y);
        super.state = new ActiveFigureState();
        damageStrategy = new DamageStrategyOncePerTarget(2);
        double angle = Math.atan2(ty - y, tx - x);
        dx = (float) (MOVE_DISTANCE * Math.cos(angle));
        dy = (float) (MOVE_DISTANCE * Math.sin(angle));
        hero = new Point2D.Float(tx, ty);
        done = false;

        BufferedImage[] moving = {Sprite.getSprite(imagePath, 0), Sprite.getSprite(imagePath, 4), Sprite.getSprite(imagePath, 9), Sprite.getSprite(imagePath, 17),
             Sprite.getSprite(imagePath, 23)};
        BufferedImage[] exploding = {Sprite.getSprite(imagePath, 00), Sprite.getSprite(imagePath, 1), Sprite.getSprite(imagePath, 2), Sprite.getSprite(imagePath, 3), 
            Sprite.getSprite(imagePath, 44), Sprite.getSprite(imagePath, 5), Sprite.getSprite(imagePath, 6), Sprite.getSprite(imagePath, 7), Sprite.getSprite(imagePath, 8), 
            Sprite.getSprite(imagePath, 99), Sprite.getSprite(imagePath, 10), Sprite.getSprite(imagePath, 11), Sprite.getSprite(imagePath, 13), Sprite.getSprite(imagePath, 14), 
            Sprite.getSprite(imagePath, 14)};

        this.moving = new SpriteAnimation(moving, 5);
        this.exploding = new SpriteAnimation(exploding, 2);
        this.animation = this.moving;
        animation.start();


    }
    
    public void updateLocation() {
        super.x += dx;
        super.y += dy;
        
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(animation.getSprite(), (int) super.x, (int) super.y, rockSize, rockSize, null);
    }
    
    public void setAnimation(SpriteAnimation animation, SpriteAnimation newAnimation)
    {
        if(animation.equals(newAnimation)) return;
        this.animation.stop();
        this.animation.reset();
        this.animation = newAnimation;
        this.animation.restart();
    }

    @Override
    public void update() {
        updateState();
        if(state instanceof ActiveFigureState)
        {
            updateLocation();
        }
        else if (state instanceof DieingFigureState)
        {
            updateSize();
            super.x -= 1;
            super.y -= 1;
        } 
        animation.update();
    }
    
    public void updateSize()
    {
        rockSize += 2;
    }
    
    public void updateState()
    {
        if(state instanceof ActiveFigureState)
        {
            double distance = hero.distance(super.x, super.y);
            if(distance <= 5) {
                goNextState();
                //setAnimation(this.animation, exploding);
               // System.out.println("Target reached, go Next state");
            }
        } else if(state instanceof DieingFigureState) {
            if(rockSize >= MAX_SIZE) {
                done = true;
                goNextState();
               // System.out.println("Max rockSize reached, go Next state");
            }
        }
    }

    @Override
    public void setState(GameFigureState state) {
        this.state = state;
    }

    @Override
    public void goNextState() {
        if(state instanceof ActiveFigureState)
        {
            state.goNext(this);
            setAnimation(this.animation, exploding);
        }
        else if(state instanceof DieingFigureState && done == true)
            state.goNext(this);
    }

    @Override
    public void setPosition(float x, float y) {
        super.x = x;
        super.y = y;
    }

    @Override
    public Rectangle2D getCollisionBox() {
       // if(state instanceof DieingFigureState)
       //     return new Rectangle2D.Float(-50, -50, 0, 0);
       // else
            return new Rectangle2D.Float(super.x, super.y, rockSize, rockSize); 
    }
    
    @Override
    public void doDamageTo(GameFigureWithHealth target)
    {
        damageStrategy.doDamageTo(target);
    }
    
    @Override
    public void setDamage(int newDamage)
    {
        damageStrategy.setDamage(newDamage);
    }
    
}

package model;

import controller.Main;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class MonsterFireball extends GameFigure
{
    
    //private final int HEIGHT = 20;
    //private final int WIDTH = 20;
    private int height, width;
    private final String PATH = "..//resources//images//fireball//";
    private Strategy strategy;
    public String direction;
    public float dx;
    public float dy;
    public SpriteAnimation animation, downShot, leftShot, rightShot, upShot;
    
    public MonsterFireball(float x, float y, int height, int width, String direction)
    {
        super(x, y);
        super.state = new ActiveFigureState();
        this.height = height;
        this.width = width;
        this.direction = direction;
        BufferedImage[] shootingDown = {Sprite.getSprite(PATH, 3), Sprite.getSprite(PATH, 4), Sprite.getSprite(PATH, 5)};
        BufferedImage[] shootingLeft = {Sprite.getSprite(PATH, 6), Sprite.getSprite(PATH, 7), Sprite.getSprite(PATH, 8)};
        BufferedImage[] shootingRight = {Sprite.getSprite(PATH, 0), Sprite.getSprite(PATH, 1), Sprite.getSprite(PATH, 2)};
        BufferedImage[] shootingUp = {Sprite.getSprite(PATH, 9), Sprite.getSprite(PATH, 10), Sprite.getSprite(PATH, 11)};
        this.downShot = new SpriteAnimation(shootingDown, 3);
        this.leftShot = new SpriteAnimation(shootingLeft, 3);
        this.rightShot = new SpriteAnimation(shootingRight, 3);
        this.upShot = new SpriteAnimation(shootingUp, 3);
        this.strategy = new FireballAttackStrategy();
        
    }

    @Override
    public void render(Graphics2D g)
    {
        g.drawImage(animation.getSprite(), (int)super.x, (int)super.y, width, height, null);
    }

    @Override
    public void update()
    {
        strategy.move(super.x, super.y, this);
        if(notInGameArea()){
            this.state = new DoneFigureState();
        }
        //this.animation.update();
    }
    
    public void setAnimation(SpriteAnimation animation, SpriteAnimation newAnimation)
    {
//        if (animation != null)
//        {
//            this.animation.stop();
//            this.animation.reset();
//            this.animation = newAnimation;
//            this.animation.restart();
//        }
        this.animation = newAnimation;
        this.animation.start();
    }

    @Override
    public void setState(GameFigureState state)
    {
        this.state = state;
    }

    @Override
    public void goNextState()
    {
        state.goNext(this);
    }

    @Override
    public void setPosition(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    @Override
    public Rectangle2D getCollisionBox()
    {
        return new Rectangle2D.Float(0, 0, 0, 0);
    }
    
    private boolean notInGameArea(){
        return this.x > Main.WIN_WIDTH + width 
                || this.y > Main.WIN_HEIGHT + height
                || this.x < 0 || this.y < 0;
    }
    
}

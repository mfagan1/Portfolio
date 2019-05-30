package model;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class GoblinEnemy extends GameFigure
{
    private final int HEIGHT = 100;
    private final int WIDTH = 75;
    private final String PATH = "..//resources//images//goblin//";
    public int health;
    private Strategy strategy;
    public SpriteAnimation animation, idle, down, right, up, left, stabDown, stabRight,
            stabUp, stabLeft, death;
    public Direction direction;
    public int damageThreshold;
    
    public GoblinEnemy(float x, float y)
    {
        super(x, y);
        health = 3;
        damageThreshold = 0;
        strategy = new GoblinStrategy();
        BufferedImage[] idling = {Sprite.getSprite(PATH, 0), Sprite.getSprite(PATH, 1)};
        BufferedImage[] walkDown = {Sprite.getSprite(PATH, 0), Sprite.getSprite(PATH, 1), Sprite.getSprite(PATH, 2),
            Sprite.getSprite(PATH, 3), Sprite.getSprite(PATH, 4), Sprite.getSprite(PATH, 5), Sprite.getSprite(PATH, 6)};
        BufferedImage[] walkRight = {Sprite.getSprite(PATH, 11), Sprite.getSprite(PATH, 12), Sprite.getSprite(PATH, 13),
            Sprite.getSprite(PATH, 14), Sprite.getSprite(PATH, 15), Sprite.getSprite(PATH, 16)};
        BufferedImage[] walkUp = {Sprite.getSprite(PATH, 21), Sprite.getSprite(PATH, 22), Sprite.getSprite(PATH, 23),
            Sprite.getSprite(PATH, 24), Sprite.getSprite(PATH, 25), Sprite.getSprite(PATH, 26), Sprite.getSprite(PATH, 27)};
        BufferedImage[] walkLeft = {Sprite.getSprite(PATH, 31), Sprite.getSprite(PATH, 32), Sprite.getSprite(PATH, 33),
            Sprite.getSprite(PATH, 34), Sprite.getSprite(PATH, 35), Sprite.getSprite(PATH, 36)};
        BufferedImage[] attackD = {Sprite.getSprite(PATH, 7), Sprite.getSprite(PATH, 8), Sprite.getSprite(PATH, 9),
            Sprite.getSprite(PATH, 10)};
        BufferedImage[] attackR = {Sprite.getSprite(PATH, 17), Sprite.getSprite(PATH, 18), Sprite.getSprite(PATH, 19),
            Sprite.getSprite(PATH, 20)};
        BufferedImage[] attackU = {Sprite.getSprite(PATH, 28), Sprite.getSprite(PATH, 29), Sprite.getSprite(PATH, 30)};
        BufferedImage[] attackL = {Sprite.getSprite(PATH, 37), Sprite.getSprite(PATH, 38), Sprite.getSprite(PATH, 39),
            Sprite.getSprite(PATH, 40)};
        BufferedImage[] dying = {Sprite.getSprite(PATH, 41), Sprite.getSprite(PATH, 42), Sprite.getSprite(PATH, 43),
            Sprite.getSprite(PATH, 44)};
        idle = new SpriteAnimation(idling, 5);
        down = new SpriteAnimation(walkDown, 5);
        right = new SpriteAnimation(walkRight, 5);
        up = new SpriteAnimation(walkUp, 5);
        left = new SpriteAnimation(walkLeft, 5);
        stabDown = new SpriteAnimation(attackD, 5);
        stabRight = new SpriteAnimation(attackR, 5);
        stabUp = new SpriteAnimation(attackU, 5);
        stabLeft = new SpriteAnimation(attackL, 5);
        death = new SpriteAnimation(dying, 5);
        animation = idle;
        animation.start();
    }

    @Override
    public void render(Graphics2D g)
    {
        if (this.direction != null)
        {
            g.drawImage(animation.getSprite(), (int)super.x, (int)super.y, WIDTH, HEIGHT, null);
        }
    }

    @Override
    public void update() 
    {
        strategy.move(super.x, super.y, this);
        animation.update();
    }
    
    public void setAnimation(SpriteAnimation animation, SpriteAnimation newAnimation)
    {
        if (animation.equals(newAnimation)) return;
        this.animation.stop();
        this.animation.reset();
        this.animation = newAnimation;
        this.animation.restart();
    }

    @Override
    public void setState(GameFigureState state) 
    {
    }

    @Override
    public void goNextState() 
    {
    }

    @Override
    public void setPosition(float x, float y) 
    {
        this.x = x;
        this.y = y;
    }
    
    public Direction getDirection()
    {
        return this.direction;
    }
    
    public void setDirection(Direction direction)
    {
        this.direction = direction;
    }

    @Override
    public Rectangle2D getCollisionBox() 
    {
        return new Rectangle2D.Float(0, 0, 0, 0);
    }
    
    public void decreaseHealth()
    {
        this.health -= 1;
    }
    
    public int getHealth()
    {
        return this.health;
    }
}

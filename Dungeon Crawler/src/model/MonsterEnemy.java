package model;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class MonsterEnemy extends GameFigure
{
    private final int HEIGHT = 100;
    private final int WIDTH = 75;
    private final String PATH = "..//resources//images//monster//";
    public int health;
    private Strategy walkingStrategy;
    private Strategy attackStrategy;
    private Strategy explodeStrategy;
    public float dx;
    public float dy;
    public SpriteAnimation animation, moveDown, moveLeft, moveRight, moveUp, idle,
            attackDown, attackLeft, attackRight, attackUp, explode;
    public MonsterFireball fireball;
    public int damageThreshold;
    
    public MonsterEnemy(float x, float y)
    {
        super(x, y);
        super.state = new MonsterWalkingState();
        health = 2;
        damageThreshold = 0;
        walkingStrategy = new MonsterWalkingStrategy();
        attackStrategy = new MonsterAttackStrategy();
        explodeStrategy = new MonsterExplosionStrategy();
        BufferedImage[] movingDown = {Sprite.getSprite(PATH, 0), Sprite.getSprite(PATH, 1), Sprite.getSprite(PATH, 2)};
        BufferedImage[] movingLeft = {Sprite.getSprite(PATH, 3), Sprite.getSprite(PATH, 4), Sprite.getSprite(PATH, 5)};
        BufferedImage[] movingRight = {Sprite.getSprite(PATH, 6), Sprite.getSprite(PATH, 7), Sprite.getSprite(PATH, 8)};
        BufferedImage[] movingUp = {Sprite.getSprite(PATH, 9), Sprite.getSprite(PATH, 10), Sprite.getSprite(PATH, 11)};
        BufferedImage[] idling = {Sprite.getSprite(PATH, 0), Sprite.getSprite(PATH, 1)};
        BufferedImage[] attackingDown = {Sprite.getSprite(PATH, 12), Sprite.getSprite(PATH, 13), Sprite.getSprite(PATH, 14)};
        BufferedImage[] attackingLeft = {Sprite.getSprite(PATH, 15), Sprite.getSprite(PATH, 5), Sprite.getSprite(PATH, 16),
                                        Sprite.getSprite(PATH, 4), Sprite.getSprite(PATH, 17)};
        BufferedImage[] attackingRight = {Sprite.getSprite(PATH, 18), Sprite.getSprite(PATH, 8), Sprite.getSprite(PATH, 19),
                                         Sprite.getSprite(PATH, 7), Sprite.getSprite(PATH, 20)};
        BufferedImage[] attackingUp = {Sprite.getSprite(PATH, 21), Sprite.getSprite(PATH, 22), Sprite.getSprite(PATH, 23)};
        BufferedImage[] exploding = {Sprite.getSprite(PATH, 24), Sprite.getSprite(PATH, 25), Sprite.getSprite(PATH, 26),
                                    Sprite.getSprite(PATH, 27), Sprite.getSprite(PATH, 28), Sprite.getSprite(PATH, 29),
                                    Sprite.getSprite(PATH, 30), Sprite.getSprite(PATH, 31)};
        this.moveDown = new SpriteAnimation(movingDown, 5);
        this.moveLeft = new SpriteAnimation(movingLeft, 5);
        this.moveRight = new SpriteAnimation(movingRight, 5);
        this.moveUp = new SpriteAnimation(movingUp, 5);
        this.idle = new SpriteAnimation(idling, 5);
        this.attackDown = new SpriteAnimation(attackingDown, 5);
        this.attackLeft = new SpriteAnimation(attackingLeft, 5);
        this.attackRight = new SpriteAnimation(attackingRight, 5);
        this.attackUp = new SpriteAnimation(attackingUp, 5);
        this.explode = new SpriteAnimation(exploding, 5);
        this.animation = idle;
        animation.start();
    }

    @Override
    public void render(Graphics2D g)
    {
        g.drawImage(animation.getSprite(), (int)super.x, (int)super.y, WIDTH, HEIGHT, null);
    }

    @Override
    public void update()
    {
        if (this.state instanceof MonsterWalkingState)
        {
            walkingStrategy.move(super.x, super.y, this);
        }
        if (this.state instanceof MonsterAttackingState)
        {
            attackStrategy.move(super.x, super.y, this);
        }
        if (this.state instanceof MonsterExplodingState)
        {
            explodeStrategy.move(super.x, super.y, this);
            if (this.animation.currentFrame == this.animation.totalFrames - 1)
            {
                this.decreaseHealth();
            }
            goNextState();
        }
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
    public Rectangle2D getCollisionBox()
    {
        //return new Rectangle2D.Float(x, y, WIDTH, HEIGHT);
        if (this.state instanceof MonsterWalkingState && this.damageThreshold >= 20) 
        {
            return new Rectangle2D.Float(x + 37, y, 1, HEIGHT);
        }
        else if (this.state instanceof MonsterAttackingState && this.damageThreshold >= 40) 
        {
            return new Rectangle2D.Float(x + 37, y, 1, HEIGHT);
        } else {
            this.damageThreshold++;
            return new Rectangle2D.Float(0, 0, 0, 0);
        }
        //return new Rectangle2D.Float(0, 0, 0, 0);
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
    
    public void decreaseHealth()
    {
        this.health -= 1;
    }
    
    public int getHealth()
    {
        return this.health;
    }
    
}

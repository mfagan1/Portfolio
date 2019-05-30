package model;

import controller.Main;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

enum Dir {
    LEFT, RIGHT, UP, DOWN
};

public class Shooter extends GameFigureWithHealth {

    private final int HEIGHT = 75;
    private final int WIDTH = 50;
    private final String PATH = "..//resources//images//hero//";
    private final String PATH2 = "..//resources//images//chrono//";
    public int health;
    public float dx;
    public float dy;
    public SpriteAnimation animation, moveDown, moveLeft, moveRight, moveUp, idle, strikeRight, strikeLeft, dying;
    private boolean isStrike = false;
    private int frameCounter = 0;
    private Dir dir;
    public static int mana;
    private final int maxMana = 50;
    private int counter;


    public Shooter(float x, float y)
    {
        super(x, y);
        super.state = new ActiveFigureState();
        movement = new CannotPassBorderStrategy();
    }

    public void setSprites(boolean isHeroSelected)
    {
        if (isHeroSelected)
        {
            BufferedImage[] movingUp = {Sprite.getSprite(PATH, 0), Sprite.getSprite(PATH, 1), Sprite.getSprite(PATH, 2),
                Sprite.getSprite(PATH, 3), Sprite.getSprite(PATH, 4)};
            BufferedImage[] movingDown = {Sprite.getSprite(PATH, 5), Sprite.getSprite(PATH, 6), Sprite.getSprite(PATH, 7),
                Sprite.getSprite(PATH, 8), Sprite.getSprite(PATH, 9)};
            BufferedImage[] movingRight = {Sprite.getSprite(PATH, 10), Sprite.getSprite(PATH, 11), Sprite.getSprite(PATH, 12),
                Sprite.getSprite(PATH, 13), Sprite.getSprite(PATH, 14), Sprite.getSprite(PATH, 15),
                Sprite.getSprite(PATH, 16), Sprite.getSprite(PATH, 17)};
            BufferedImage[] movingLeft = {Sprite.getSprite(PATH, 18), Sprite.getSprite(PATH, 19), Sprite.getSprite(PATH, 20),
                Sprite.getSprite(PATH, 21), Sprite.getSprite(PATH, 22), Sprite.getSprite(PATH, 23),
                Sprite.getSprite(PATH, 24), Sprite.getSprite(PATH, 25)};

            BufferedImage[] strikingRight = {Sprite.getSprite(PATH, 26), Sprite.getSprite(PATH, 27), Sprite.getSprite(PATH, 28),
                Sprite.getSprite(PATH, 29), Sprite.getSprite(PATH, 30), Sprite.getSprite(PATH, 31),
                Sprite.getSprite(PATH, 32), Sprite.getSprite(PATH, 33), Sprite.getSprite(PATH, 34)};
            BufferedImage[] strikingLeft = {Sprite.getSprite(PATH, 37), Sprite.getSprite(PATH, 38), Sprite.getSprite(PATH, 39),
                                            Sprite.getSprite(PATH, 40), Sprite.getSprite(PATH, 41), Sprite.getSprite(PATH, 42)};

            BufferedImage[] dying = {Sprite.getSprite(PATH, 35)};
            BufferedImage[] idling = {Sprite.getSprite(PATH, 0)};

            this.moveDown = new SpriteAnimation(movingDown, 5);
            this.moveLeft = new SpriteAnimation(movingLeft, 5);
            this.moveRight = new SpriteAnimation(movingRight, 5);
            this.moveUp = new SpriteAnimation(movingUp, 5);
            this.idle = new SpriteAnimation(idling, 5);
            this.strikeRight = new SpriteAnimation(strikingRight, 2);
            this.strikeLeft = new SpriteAnimation(strikingLeft, 2);
            this.dying = new SpriteAnimation(dying, 4);
            this.animation = idle;
            this.dir = Dir.LEFT;
            animation.start();
            currentHealth = 100;
            mana = 50;
            counter = 0;
        }
        else
        {
            BufferedImage[] movingUp = {Sprite.getSprite(PATH2, 12), Sprite.getSprite(PATH2, 13), Sprite.getSprite(PATH2, 14),
                    Sprite.getSprite(PATH2, 15), Sprite.getSprite(PATH2, 16), Sprite.getSprite(PATH2, 17)};

            BufferedImage[] movingDown = {Sprite.getSprite(PATH2, 18), Sprite.getSprite(PATH2, 19), Sprite.getSprite(PATH2, 20),
                    Sprite.getSprite(PATH2, 21), Sprite.getSprite(PATH2, 22), Sprite.getSprite(PATH2, 23)};

            BufferedImage[] movingRight = {Sprite.getSprite(PATH2, 0), Sprite.getSprite(PATH2, 1), Sprite.getSprite(PATH2, 2),
                    Sprite.getSprite(PATH2, 3), Sprite.getSprite(PATH2, 4), Sprite.getSprite(PATH2, 5)};

            BufferedImage[] movingLeft = {Sprite.getSprite(PATH2, 6), Sprite.getSprite(PATH2, 7), Sprite.getSprite(PATH2, 8),
                    Sprite.getSprite(PATH2, 9), Sprite.getSprite(PATH2, 10), Sprite.getSprite(PATH2, 11)};

            BufferedImage[] strikingRight = {Sprite.getSprite(PATH2, 24), Sprite.getSprite(PATH2, 25), Sprite.getSprite(PATH2, 26),
                    Sprite.getSprite(PATH2, 27)};

            BufferedImage[] strikingLeft = {Sprite.getSprite(PATH2, 24), Sprite.getSprite(PATH2, 25), Sprite.getSprite(PATH2, 26),
                    Sprite.getSprite(PATH2, 27)};

            BufferedImage[] dying = {Sprite.getSprite(PATH2, 30)};
            BufferedImage[] idling = {Sprite.getSprite(PATH2, 28)};

            this.moveDown = new SpriteAnimation(movingDown, 1);
            this.moveLeft = new SpriteAnimation(movingLeft, 1);
            this.moveRight = new SpriteAnimation(movingRight, 1);
            this.moveUp = new SpriteAnimation(movingUp, 1);
            this.idle = new SpriteAnimation(idling, 5);
            this.strikeRight = new SpriteAnimation(strikingRight, 2);
            this.strikeLeft = new SpriteAnimation(strikingLeft, 2);
            this.dying = new SpriteAnimation(dying, 4);
            this.animation = idle;
            this.dir = Dir.LEFT;
            animation.start();
            currentHealth = 100;
            mana = 50;
            counter = 0;
        }
    }

    public void translate(int x, int y) {
        movement.move(x, y, this);
    }

    public void shoot(int targetX, int targetY) {
        if(mana >= 5)
        {
            Gunshot m = new Gunshot(this.x + WIDTH / 2, this.y + HEIGHT / 2, targetX, targetY, Color.RED);
            Main.gameData.friendFigures.add(m);
            mana -= 5;
            Main.gameData.mana.setMana(mana);
        }
    }

    public void strike() {
        isStrike = true;
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(animation.getSprite(), (int) super.x, (int) super.y, WIDTH, HEIGHT, null);
    }

    @Override
    public void update() {
        //this.animation.update();
        if (isStrike) {
            //save current character position as main position
            if (this.dir == Dir.LEFT || this.dir == Dir.UP) {
                this.setAnimation(animation, strikeLeft);
            } else {
                this.setAnimation(animation, strikeRight);
            }
            this.animation.update();

            frameCounter++;
            if (frameCounter >= 20) {
                frameCounter = 0;
                isStrike = false;
            }
        }
        if(state instanceof DieingFigureState)
        {
            goNextState();
            Main.gameData.setGameState(new GameOverState());

        }
//        if (state instanceof DoneFigureState)
//        {
//            Main.gameData.setGameState(new GameOverState());
//        }
        if(counter == 20)
        {
            mana++;
            mana = (mana > maxMana) ? (mana = maxMana) : mana;
            counter = 0;
        }
        Main.gameData.mana.setMana(mana);
        counter++;
    }

    public void setAnimation(SpriteAnimation animation, SpriteAnimation newAnimation) {
        if (animation.equals(newAnimation)) {
            return;
        }
        this.animation.stop();
        this.animation.reset();
        this.animation = newAnimation;
        this.animation.restart();
    }

    @Override
    public Rectangle2D.Float getCollisionBox() {
        return new Rectangle2D.Float(x, y, WIDTH, HEIGHT);
        //return new Rectangle2D.Float(0, 0, 1, 1);
    }

    @Override
    public void setState(GameFigureState state) {
        this.state = state;
    }

    @Override
    public void goNextState() {
        state.goNext(this);
    }

    @Override
    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void takeDamage(int damage)
    {
        currentHealth = (currentHealth > 0) ? (currentHealth -= damage) : 0;
        Main.gameData.health.setHealth(currentHealth);

        if(currentHealth <= 0)
        {
            goNextState();
            dying();
        }


    }

    public void heal(int health) {
        currentHealth = (currentHealth + health) % maxHealth;
        Main.gameData.health.setHealth(currentHealth);
    }

    private void dying() {

        this.setAnimation(animation, dying);

    }

    public void setDirection(String dir) {
        switch(dir){
            case "UP": this.dir = Dir.UP;
            break;
            case "DOWN": this.dir = Dir.DOWN;
            break;
            case "LEFT": this.dir = Dir.LEFT;
            break;
            case "RIGHT": this.dir = Dir.RIGHT;
            break;
            default: {}
        }
    }

}

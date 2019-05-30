package model;

public class FireballAttackStrategy implements Strategy
{
    
    private final int UNIT_TRAVEL_DISTANCE = 7;

    @Override
    public void move(float x, float y, GameFigure context)
    {
        MonsterFireball fireball = (MonsterFireball) context;
        
        switch (fireball.direction)
        {
            case "down":
                fireball.setPosition(x, y + UNIT_TRAVEL_DISTANCE);
                fireball.animation.update();
                break;
            case "up":
                fireball.setPosition(x, y - UNIT_TRAVEL_DISTANCE);
                fireball.animation.update();
                break;
            case "left":
                fireball.setPosition(x - UNIT_TRAVEL_DISTANCE, y);
                fireball.animation.update();
                break;
            case "right":
                fireball.setPosition(x + UNIT_TRAVEL_DISTANCE, y);
                fireball.animation.update();
                break;
            default:
                break;
        }
    }
    
}

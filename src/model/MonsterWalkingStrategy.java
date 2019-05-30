package model;

import controller.Main;

public class MonsterWalkingStrategy implements Strategy
{
    private static final int UNIT_TRAVEL_DISTANCE = 2;
    private MonsterFireball fireball;

    @Override
    public void move(float x, float y, GameFigure context)
    {
        MonsterEnemy monster = (MonsterEnemy) context;
        double angle = Math.atan2(GameData.shooter.y - monster.y, GameData.shooter.x - monster.x);
        monster.dx = (float) (UNIT_TRAVEL_DISTANCE * Math.cos(angle));
        monster.dy = (float) (UNIT_TRAVEL_DISTANCE * Math.sin(angle));
        monster.x += monster.dx;
        monster.y += monster.dy;
        updateAnimationOnPositionChange(monster, monster.x, monster.y);
        if (checkIfFireballsAreCleared())
        {
            generateFireballAttack(monster.x, monster.y);
        }
        
    }
    
    private void updateAnimationOnPositionChange(MonsterEnemy monster, float x, float y)
    {
        float shx = GameData.shooter.x;
        float shy = GameData.shooter.y;
        if ((shx > x && shy > y && Math.abs(shy - y) > Math.abs(shx - x))
           || (shx < x && shy > y && Math.abs(shy - y) > Math.abs(shx - x)))
        {
            monster.setAnimation(monster.animation, monster.moveDown);
            monster.animation.update();
        }
        if ((shx > x && shy > y && Math.abs(shy - y) < Math.abs(shx - x))
           || (shx > x && shy < y && Math.abs(shy - y) < Math.abs(shx - x)))
        {
            monster.setAnimation(monster.animation, monster.moveRight);
            monster.animation.update();
        }
        if ((shx < x && shy < y && Math.abs(shy - y) > Math.abs(shx - x))
           || (shx > x && shy < y && Math.abs(shy - y) > Math.abs(shx - x)))
        {
            monster.setAnimation(monster.animation, monster.moveUp);
            monster.animation.update();
        }
        if ((shx < x && shy > y && Math.abs(shy - y) < Math.abs(shx - x))
           || (shx < x && shy < y && Math.abs(shy - y) < Math.abs(shx - x)))
        {
            monster.setAnimation(monster.animation, monster.moveLeft);
            monster.animation.update();
        }
    }
    
    private boolean checkIfFireballsAreCleared()
    {
        for (GameFigure e : Main.gameData.enemyFigures)
        {
            if (e instanceof MonsterFireball)
            {
                if (e.x < 0 || e.x > 1200 || e.y < 0 || e.y > 800) {
                    Main.gameData.enemyFigures.remove(e);
                    return true;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
    
    private void generateFireballAttack(float x, float y)
    {       
        if (Math.abs(GameData.shooter.x - x) <= 60 && GameData.shooter.y > y && (Math.abs(GameData.shooter.y - y) > 200))
        {
            fireball = new MonsterFireball(x + 25, y + 40, 40, 25, "down");
            fireball.setAnimation(fireball.animation, fireball.downShot);
            Main.gameData.enemyFigures.add(fireball);
        }
        else if (Math.abs(GameData.shooter.x - x) <= 60 && GameData.shooter.y < y && (Math.abs(GameData.shooter.y - y) > 200))
        {
            fireball = new MonsterFireball(x + 25, y - 5, 40, 25, "up");
            fireball.setAnimation(fireball.animation, fireball.upShot);
            Main.gameData.enemyFigures.add(fireball);
        }
        else if (Math.abs(GameData.shooter.y - y) <= 60 && GameData.shooter.x < x && (Math.abs(GameData.shooter.x - x) > 200))
        {
            fireball = new MonsterFireball(x - 5, y + 15, 25, 40, "left");
            fireball.setAnimation(fireball.animation, fireball.leftShot);
            Main.gameData.enemyFigures.add(fireball);
        }
        else if (Math.abs(GameData.shooter.y - y) <= 60 && GameData.shooter.x > x && (Math.abs(GameData.shooter.x - x) > 200))
        {
            fireball = new MonsterFireball(x + 60, y + 15, 25, 40, "right");
            fireball.setAnimation(fireball.animation, fireball.rightShot);
            Main.gameData.enemyFigures.add(fireball);
        }
    }
    
}

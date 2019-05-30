package model;

import controller.Main;

public class MonsterAttackStrategy implements Strategy
{
    private static final double UNIT_TRAVEL_DISTANCE = 3.5;

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
    }
    
    private void updateAnimationOnPositionChange(MonsterEnemy monster, float x, float y)
    {
        float shx = GameData.shooter.x;
        float shy = GameData.shooter.y;
        if ((shx > x && shy > y && Math.abs(shy - y) > Math.abs(shx - x))
           || (shx < x && shy > y && Math.abs(shy - y) > Math.abs(shx - x)))
        {
            monster.setAnimation(monster.animation, monster.attackDown);
            monster.animation.update();
        }
        if ((shx > x && shy > y && Math.abs(shy - y) < Math.abs(shx - x))
           || (shx > x && shy < y && Math.abs(shy - y) < Math.abs(shx - x)))
        {
            monster.setAnimation(monster.animation, monster.attackRight);
            monster.animation.update();
        }
        if ((shx < x && shy < y && Math.abs(shy - y) > Math.abs(shx - x))
           || (shx > x && shy < y && Math.abs(shy - y) > Math.abs(shx - x)))
        {
            monster.setAnimation(monster.animation, monster.attackUp);
            monster.animation.update();
        }
        if ((shx < x && shy > y && Math.abs(shy - y) < Math.abs(shx - x))
           || (shx < x && shy < y && Math.abs(shy - y) < Math.abs(shx - x)))
        {
            monster.setAnimation(monster.animation, monster.attackLeft);
            monster.animation.update();
        }
    }
}

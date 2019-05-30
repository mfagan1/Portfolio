package model;

public class MonsterExplosionStrategy implements Strategy
{

    @Override
    public void move(float x, float y, GameFigure context)
    {
        MonsterEnemy monster = (MonsterEnemy) context;
        
        if (!monster.animation.equals(monster.explode))
        {
            monster.setPosition(x, y);
            monster.setAnimation(monster.animation, monster.explode);
        }
        
        monster.animation.update();
    }
       
}

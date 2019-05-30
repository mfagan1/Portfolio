package model;

public class MonsterExplodingState implements GameFigureState
{
    public MonsterExplodingState()
    {
        
    }   

    @Override
    public void goNext(GameFigure context)
    {
        MonsterEnemy monster = (MonsterEnemy) context;
        
        if (monster.getHealth() == 0)
        {
            monster.damageThreshold = 0;
            monster.setState(new DoneFigureState());
        }
    }
}

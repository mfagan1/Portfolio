package model;

public class MonsterWalkingState implements GameFigureState
{
    public MonsterWalkingState()
    {
        
    }

    @Override
    public void goNext(GameFigure context)
    {
        MonsterEnemy monster = (MonsterEnemy) context;
        monster.decreaseHealth();
        monster.setState(new MonsterAttackingState());
    }
}

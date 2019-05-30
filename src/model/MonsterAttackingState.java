package model;

public class MonsterAttackingState implements GameFigureState
{
    public MonsterAttackingState()
    {
        
    }

    @Override
    public void goNext(GameFigure context)
    {
        context.setState(new MonsterExplodingState());
    }
}

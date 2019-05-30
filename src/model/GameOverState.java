package model;

public class GameOverState implements IGameState
{
    @Override
    public State notifyModel()
    {
        return State.OVER;
    }
}

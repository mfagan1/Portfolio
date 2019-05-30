package model;

public class GameActiveState implements IGameState
{
    @Override
    public State notifyModel()
    {
        return State.ACTIVE;
    }
}

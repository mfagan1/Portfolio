package model;

//Code used to implement State design pattern
// create concrete states for desired GameFigure
// call goNext(GameFigure context)from GameFigure to go to next state 

public interface GameFigureState
{
    void goNext(GameFigure context);
}

// Will need to remove when State pattern is implemented
//public class GameFigureState {
//
///* common to all game figures */

//public static final int STATE_DONE = 0;
//public static final int STATE_ACTIVE = 1;
//public static final int STATE_DYING = 2;
//
///* missile states */
//    public static final int MISSILE_STATE_LAUNCHED = 1;
//    public static final int MISSILE_STATE_EXPLODED = 2;
//
///* ufo states */
//    public static final int UFO_STATE_APPEARED = 1;
//    public static final int UFO_STATE_DAMAGED = 2;
//
///* bomb states */
//    public static final int BOMB_STATE_ADDED = 1;
//    public static final int BOMB_EXPLODED = 2;
//
///* shooter states */
//
//public static final int SHOOTER_STATE_HEALTH_LEVEL_5 = 30;
//public static final int SHOOTER_STATE_HEALTH_LEVEL_4 = 31; // not implemented yet
//public static final int SHOOTER_STATE_HEALTH_LEVEL_3 = 32; // not implemented yet
//public static final int SHOOTER_STATE_HEALTH_LEVEL_2 = 33; // not implemented yet
//public static final int SHOOTER_STATE_HEALTH_LEVEL_1 = 34; // not implemented yet

//}

package controller;

public class GameStaticState
{
    private static boolean isSplash;

    private static boolean isFading = false;
    
    private static boolean inv = false;

    public static boolean PAUSED = true;

    public static void setPaused()
    {
        GameStaticState.PAUSED = !PAUSED;
    }

    public static void setInventory()
    {
        GameStaticState.inv = !inv;
    }
    
    public static boolean isInventory()
    {
        return inv;
    }
    
    public static boolean isPaused()
    {
        return PAUSED;
    }

    public static boolean isFading() {
        return isFading;
    }

    public static void setIsFading(boolean isFading) {
        GameStaticState.isFading = isFading;
    }
}

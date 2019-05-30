package controller.actions.gameactions;

import controller.AudioPlayer;
import controller.GameStaticState;
import controller.Main;

import java.awt.event.ActionEvent;
import javax.swing.*;

public class PauseEnterAction extends AbstractAction
{
    private AudioPlayer player;

    @Override
    public void actionPerformed(ActionEvent e)
    {
        // If the game is paused the enter action will
        // carry out an action based off of the selection
        // property from the pauseScreen object.
        if(GameStaticState.isPaused())
        {
            // resume is selected.
            if (Main.gameData.pauseScreen.getSelection())
            {
                player = new AudioPlayer("src/view/resources/Audio/pausesound.wav", 3);
                player.play();
                GameStaticState.setPaused();
            }
            // Quit is selected.
            else
            {
                System.exit(-1);
            }
        }
    }

}
package controller.actions.gameactions;

import controller.AudioPlayer;
import controller.GameStaticState;
import controller.Main;
import model.Shooter;
import javax.swing.*;
import java.awt.event.ActionEvent;

public class UpArrowAction extends AbstractAction
{
    private AudioPlayer player;
    @Override
    public void actionPerformed(ActionEvent e) {

        if (!GameStaticState.isPaused())
        {
            Shooter sh = Main.gameData.shooter;
            sh.translate(0, -10);
            sh.setDirection("UP");
            sh.setAnimation(sh.animation, sh.moveUp);
            sh.animation.update();
        }
        else
        {
            player = new AudioPlayer("src/view/resources/Audio/menuclick.wav", 3);
            player.play();
            Main.gameData.pauseScreen.toggleSelection();
        }
    }
}

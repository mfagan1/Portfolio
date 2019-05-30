package controller.actions.splashactions;

import controller.GameStaticState;
import controller.Main;
import model.Shooter;
import view.SplashPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class EnterAction extends AbstractAction
{
    private SplashPanel panel;

    public EnterAction(SplashPanel panel)
    {
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Main.gameData.shooter.setSprites(panel.getIsHeroSelected());
        GameStaticState.setPaused();
    }
}

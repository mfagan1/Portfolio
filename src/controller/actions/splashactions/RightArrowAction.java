package controller.actions.splashactions;

import controller.GameStaticState;
import controller.Main;
import view.SplashPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class RightArrowAction extends AbstractAction
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        Main.splashPanel.setIsHeroSelected(!Main.splashPanel.getIsHeroSelected());
    }
}

package controller.actions.splashactions;

import controller.GameStaticState;
import view.SplashPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class EnterSelectAction extends AbstractAction
{
    private SplashPanel panel;

    public EnterSelectAction(SplashPanel panel)
    {
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        panel.setPositions();
    }
}

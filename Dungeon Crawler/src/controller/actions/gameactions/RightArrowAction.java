package controller.actions.gameactions;

import controller.Main;
import model.Shooter;
import javax.swing.*;
import java.awt.event.ActionEvent;

public class RightArrowAction extends AbstractAction
{
    @Override
    public void actionPerformed(ActionEvent e) {
        Shooter sh = Main.gameData.shooter;
        sh.translate(10, 0);
        sh.setDirection("RIGHT");
        sh.setAnimation(sh.animation, sh.moveRight);
        sh.animation.update();
    }
}

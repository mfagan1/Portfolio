package controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import model.Shooter;

public class KeyController extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent e) {
        Shooter shooter = (Shooter) Main.gameData.friendFigures.get(0);

        // horizontal move only
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                shooter.translate(-10, 0);
                break;
            case KeyEvent.VK_RIGHT:
                shooter.translate(10, 0);
                break;
            case KeyEvent.VK_UP:
                shooter.translate(0, -10);
                break;
            case KeyEvent.VK_DOWN:
                shooter.translate(0, 10);
                break;
        }
    }

}

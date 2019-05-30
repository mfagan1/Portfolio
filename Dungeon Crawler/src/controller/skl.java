package controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class skl extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent e) {

        // horizontal move only
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                GameStaticState.setPaused();
                break;
        }
    }

}

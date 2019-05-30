package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.MainWindow;

public class ButtonListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == MainWindow.addButton) {
            Main.gameData.add(10);
        } 
        else if(ae.getSource() == MainWindow.ufoButton)
            {
                Main.gameData.addUFO();
            }
        else if (ae.getSource() == MainWindow.quitButton) {
            if (Main.animator.running) {
                Main.animator.running = false;
            }
            
            else {
                System.exit(0);
            }
        }
    }

}

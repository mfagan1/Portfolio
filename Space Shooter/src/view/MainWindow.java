package view;

import controller.ButtonListener;
import controller.KeyController;
import controller.Main;
import controller.MouseController;
import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainWindow extends JFrame {

    public static JButton addButton;
    public static JButton ufoButton;
    public static JButton quitButton;
    public static MouseController mouseController;

    public MainWindow() {

        Container c = getContentPane();

        c.add(Main.gamePanel, "Center");

        JPanel southPanel = new JPanel();
        addButton = new JButton("Add 10");
        southPanel.add(addButton);
        ufoButton = new JButton("Add UFO");
        southPanel.add(ufoButton);
        quitButton = new JButton("Quit");
        southPanel.add(quitButton);
        c.add(southPanel, "South");

        ButtonListener buttonListener = new ButtonListener();
        addButton.addActionListener(buttonListener);
        quitButton.addActionListener(buttonListener);
        ufoButton.addActionListener(buttonListener);
        
        mouseController = new MouseController();
        Main.gamePanel.addMouseListener(mouseController);
        Main.gamePanel.addMouseMotionListener(mouseController);

        KeyController keyListener = new KeyController();
        Main.gamePanel.addKeyListener(keyListener);
        Main.gamePanel.setFocusable(true);
        // just have one Component "true", the rest must be "false"
        addButton.setFocusable(false);
        quitButton.setFocusable(false);
        ufoButton.setFocusable(false);
    }

}

package view;

import controller.ButtonListener;
import controller.KeyController;
import controller.Main;
import controller.MouseController;
import javafx.embed.swing.JFXPanel;

import java.awt.*;
import java.util.concurrent.CountDownLatch;
import javax.swing.*;

public class MainWindow extends JFrame {

    public static JButton addButton;
    public static JButton quitButton;
    public static JButton ufoButton;
    public static JPanel cards;
    public static MouseController mouseController;
    public static Container container;

    public MainWindow() {

        initializeFX();
        container = getContentPane();

        // Cards is a layout which allows for easier
        // access to different panels. This helps eliminate
        // focus issues.
        cards = new JPanel(new CardLayout());
        cards.add(Main.splashPanel, "SplashPanel");
        cards.add(Main.gamePanel, "GamePanel");

        container.add(cards, "Center");

        mouseController = new MouseController();
        Main.gamePanel.addMouseListener(mouseController);
        Main.gamePanel.addMouseMotionListener(mouseController);
    }

    // This function gets the SplashPanel
    // from the card layout and sets focus
    // to that panel.
    public void splashLayout()
    {
        CardLayout cardLayout = (CardLayout)(cards.getLayout());
        cardLayout.show(cards, "SplashPanel");
        //Main.splashPanel.revalidate();
    }

    // This function gets the GamePanel
    // from the card layout and sets focus
    // to that panel.
    public void gameLayout()
    {
        CardLayout cardLayout = (CardLayout)(cards.getLayout());
        cardLayout.show(cards, "GamePanel");
    }

    // Initializes FX for audio. Without this method
    // sound cannot be played.
    private void initializeFX()
    {
        try
        {
            final CountDownLatch latch = new CountDownLatch(1);
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    new JFXPanel(); // initializes JavaFX environment
                    latch.countDown();
                }
            });
            latch.await();
        }
        catch (InterruptedException e)
        {
            System.out.println("Something broke :(");
            System.exit(1);
        }
    }
}

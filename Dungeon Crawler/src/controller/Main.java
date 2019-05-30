package controller;

import javax.swing.*;

import com.sun.javafx.applet.Splash;
import javafx.embed.swing.JFXPanel;
import model.GameData;
import view.GamePanel;
import view.MainWindow;
import view.SplashPanel;

import java.awt.*;
import java.util.concurrent.CountDownLatch;

public class Main {

    public static GamePanel gamePanel;
    public static SplashPanel splashPanel;
    public static GameData gameData;
    public static Animator animator;
    public static MainWindow game;
    public static SplashAnimator sa;

    public static int WIN_WIDTH = 1200;
    public static int WIN_HEIGHT = 800;

    public static void main(String[] args) {

        animator = new Animator();
        gameData = new GameData();
        gamePanel = new GamePanel();
        splashPanel = new SplashPanel();
        sa = new SplashAnimator();

        game = new MainWindow();
        game.setTitle("Dungeon Plungin'");
        game.setSize(WIN_WIDTH, WIN_HEIGHT);
        game.setLocation(100, 0);
        game.setResizable(false); // window size cannot change
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setVisible(true);

        splashScreen();

        Main.game.gameLayout();

        // start animation
        new Thread(animator).start();

    }

    /**
     * Method splashScreen starts a new thread for
     * the splash screen animation to live on.
     * A join is called in order to prevent the main
     * game thread from being started before the splash
     * screen thread has finished.
     */
    public static void splashScreen()
    {
        try
        {
            game.splashLayout();
            Thread thread = new Thread(sa);
            thread.start();
            thread.join();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}


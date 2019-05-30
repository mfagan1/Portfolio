package view;

import Inventory.Inventory;
import controller.AudioPlayer;
import controller.Main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

import controller.actions.gameactions.*;
import model.*;
import controller.actions.gameactions.DownArrowAction;
import controller.actions.gameactions.LeftArrowAction;
import controller.actions.gameactions.RightArrowAction;
import controller.actions.gameactions.UpArrowAction;
import controller.actions.gameactions.BButtonAction;
import controller.actions.gameactions.inventoryAction;
import controller.actions.gameactions.KButtonAction;
import controller.actions.gameactions.MButtonAction;

import model.Border;
import model.GolemBoss;

public class GamePanel extends JPanel {

    // size of the canvas - determined at runtime once rendered
    public static int width;
    public static int height;

    // off screen rendering
    private Graphics2D g2;
    private Image dbImage = null; // double buffer image
    private InputMap inputMap;
    private ActionMap actionMap;
    private Image background;

    private Timer gameOverTimer;
    private ActionListener gameOverAction;
    private float alphaLevel;
    private Composite comp;

    private AudioPlayer gameOverSong;

    public GamePanel()
    {
        // Key bindings for Game Panel.
        // All Actions contained in controller.actions.gameactions
        this.inputMap = getInputMap(WHEN_IN_FOCUSED_WINDOW);
        this.actionMap = getActionMap();

        this.gameOverSong = new AudioPlayer("src/view/resources/Audio/uded.wav", 1.0);

        try
        {
            background = ImageIO.read(getClass().getResource("resources/splashscreen/gameoverimg.png"));
        }
        catch (Exception e)
        {
            System.exit(0);
        }

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false), "LEFT");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false), "RIGHT");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false), "UP");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false), "DOWN");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_B, 0, false), "Boss");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_K, 0, false), "SpawnSpikeyEnemy");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_L, 0, false), "SpikeyEnemyDemo");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_I, 0, false), "Inventory");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false), "PAUSE");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "ENTER");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_M, 0, false), "MonsterEnemy");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_G, 0, false), "GoblinEnemy");


        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "SPACE");
        
        
        actionMap.put("LEFT", new LeftArrowAction());
        actionMap.put("RIGHT", new RightArrowAction());
        actionMap.put("UP", new UpArrowAction());
        actionMap.put("DOWN", new DownArrowAction());
        actionMap.put("Inventory", new inventoryAction());
        //BButton for dev of GolemBoss.  Hotkey shortcut
        actionMap.put("Boss", new BButtonAction());
        actionMap.put("SpawnSpikeyEnemy", new KButtonAction());
        actionMap.put("SpikeyEnemyDemo", new LButtonAction());
        actionMap.put("PAUSE", new PauseAction());
        actionMap.put("ENTER", new PauseEnterAction());
        actionMap.put("MonsterEnemy", new MButtonAction());

        this.gameOverTimer = null;
        this.alphaLevel = 1.0f;

        this.gameOverAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };

        actionMap.put("GoblinEnemy", new GButtonAction());
    }


    public void gameRender() {
        width = getSize().width;
        height = getSize().height;
        if (dbImage == null) {
            // Creates an off-screen drawable image to be used for double buffering
            dbImage = createImage(width, height);
            if (dbImage == null) {
                System.out.println("Critical Error: dbImage is null");
                System.exit(1);
            } else {
                g2 = (Graphics2D) dbImage.getGraphics();
            }
        }

        g2.clearRect(0, 0, width, height);
        g2.setBackground(Color.BLACK);

        if (Main.animator.running) {

            Main.gameData.shooter.render(g2);
            Main.gameData.health.render(g2);
            Main.gameData.mana.render(g2);

            for (GameFigure f : Main.gameData.enemyFigures) {
                f.render(g2);
            }

            for (GameFigure f : Main.gameData.friendFigures) {
                f.render(g2);
            }
            
            for (GameFigure f: Main.gameData.enemyFiguresWithHealth){
                f.render(g2);
                if(f instanceof GolemBoss)
                {
                    Main.gameData.bossHealth.render(g2);
                }
            }
            
            for(GameFigure f: Main.gameData.invulnerableEnemies){
                f.render(g2);
            }
            
            for (GameFigure f: Main.gameData.itemFigures){
                f.render(g2);
            }
            
            for (GameFigure f: Main.gameData.weaponAttackFigures){
                f.render(g2);
            }

            for(Border b : Main.gameData.borders){
                b.render(g2);
            }
            
            if(Main.gameData.stairs != null){
                Main.gameData.stairs.render(g2);
            }

            for (Inventory f : Main.gameData.inventory){
                f.render(g2);
            }
            
            if (Main.gameData.getGameState() == State.OVER && gameOverTimer == null)
            {
                this.gameOverSong.play();
                this.gameOverTimer = new Timer(5000, this.gameOverAction);
                this.gameOverTimer.setRepeats(false);
                this.gameOverTimer.start();
                this.actionMap.clear();
                this.inputMap.clear();
            }

            if (Main.gameData.getGameState() == State.OVER)
            {
                runGameOver(this.g2);
            }
        }
        
        // TESTING ONLY
        //renderBordersDebug();
    }

    private void runGameOver(Graphics2D g2)
    {
        this.comp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float)this.alphaLevel);
        g2.setComposite(comp);
        g2.drawImage(background, (height/2) + 65, (width/2) - 400, 300, 300, null);


        if (this.alphaLevel > 0.02)
        {
            this.alphaLevel -= 0.006f;
        }
    }

    public void renderPauseScreen()
    {
        Main.gameData.pauseScreen.render(g2);
    }

    // use active rendering to put the buffered image on-screen
    public void printScreen() {
        Graphics g;
        try {
            g = this.getGraphics();
            if ((g != null) && (dbImage != null)) {
                g.drawImage(dbImage, 0, 0, this);
            }
            Toolkit.getDefaultToolkit().sync();  // sync the display on some systems
            if (g != null) {
                g.dispose();
            }
        } catch (Exception e) {
            System.out.println("Graphics error: " + e);
        }
    }
    // TESTING PURPOSES ONLY
    public void renderBordersDebug(){
        for(Border b: Main.gameData.borders){
            b.render(g2);
        }
    }
}
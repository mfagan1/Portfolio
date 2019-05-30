package view;

import controller.GameStaticState;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

import controller.Main;
import controller.actions.splashactions.EnterAction;
import controller.actions.splashactions.EnterSelectAction;
import controller.actions.splashactions.LeftArrowAction;
import controller.actions.splashactions.RightArrowAction;
import model.Sprite;

/**
 * Class SplashPanel contains the logic for the splash screen panel.
 * Animation is run in SplashAnimator.java.
 */

public class SplashPanel extends JPanel {

    private Image background;
    private Image mover;
    private Image mover2;
    private Image mover3;
    private Image choose;
    private Image chronoStationary;
    private Image heroStationary;
    private Image chronoImage;
    private Image heroImage;
    private int x;
    private int y;
    private static int width;
    private static int height;
    private Image dbImage = null;
    private Graphics2D g2;
    private Composite comp;
    private float alphaLevel;
    private InputMap inputMap;
    private ActionMap actionMap;
    private int yLocation;
    private int xLocation;
    private int imageWidth;
    private int imageHeight;
    private boolean isPositionSet;
    private final String PATH = "..//resources//images//hero//";
    private final String PATH2 = "..//resources//images//chrono//";
    private BufferedImage[] chrono;
    private BufferedImage[] hero;
    private Timer animationTimer;
    private ActionListener animationAction;
    private boolean isHeroSelected;
    private int animationCounter;

    public SplashPanel() {

        this.imageHeight = 200;
        this.imageWidth = 400;
        this.x = -300;
        this.y = -300;
        this.isPositionSet = false;

        this.animationCounter = 0;

        BufferedImage[] chronoWalking = {Sprite.getSprite(PATH2, 18), Sprite.getSprite(PATH2, 19), Sprite.getSprite(PATH2, 20),
                Sprite.getSprite(PATH2, 21), Sprite.getSprite(PATH2, 22), Sprite.getSprite(PATH2, 23)};

        BufferedImage[] heroWalking = {Sprite.getSprite(PATH, 7),
            Sprite.getSprite(PATH, 8)};

        this.chrono = chronoWalking;
        this.hero = heroWalking;

        this.heroStationary = Sprite.getSprite(PATH, 5);
        this.chronoStationary = Sprite.getSprite(PATH2, 29);

        this.chronoImage = chronoStationary;
        this.heroImage = heroStationary;

        try {
            background = ImageIO.read(getClass().getResource("resources/splashscreen/splashbg.png"));
            mover = ImageIO.read(getClass().getResource("resources/splashscreen/dungeonimg.png"));
            mover2 = ImageIO.read(getClass().getResource("resources/splashscreen/plunginimg.png"));
            mover3 = ImageIO.read(getClass().getResource("resources/splashscreen/start.png"));
            choose = ImageIO.read(getClass().getResource("resources/splashscreen/choosecharacter.jpg"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open shooter.png");
            System.exit(-1);
        }

        // Create a key binding for this JPanel.
        this.inputMap = getInputMap(WHEN_IN_FOCUSED_WINDOW);
        this.actionMap = getActionMap();

        // Enter key binding is created and action is mapped to
        // the PauseEnterAction defined in controller.actions.splashactions.
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "ENTER");
        actionMap.put("ENTER", new EnterSelectAction(this));
        this.alphaLevel = 1.0f;

        isHeroSelected = true;

        this.animationAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (isHeroSelected)
                {
                    if (animationCounter > hero.length - 1) animationCounter = 0;

                    heroImage = hero[animationCounter];
                    chronoImage = chronoStationary;
                    animationCounter++;
                }
                else
                {
                    if (animationCounter > chrono.length - 1) animationCounter = 0;

                    chronoImage = chrono[animationCounter];
                    heroImage = heroStationary;
                    animationCounter++;
                }
            }
        };

    }

    // Animating logic for the splash screen.
    public void gameRender()
    {

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
        g2.drawImage(background, 0,0, width, height, null);
        g2.drawImage(mover, (width/2) - (imageWidth/2), y, imageWidth, imageHeight, null);

        // Sets "Dungeon" image in place.
        if (y < (height/2) - (imageHeight/2) - 120)
        {
            y += 5;
        }
        else
        {
            // After "dungeon" image is in place start animating "Plungin'" image.
            g2.drawImage(mover2, x, (height/2) - (imageHeight/2) + 28, imageWidth, imageHeight, null);

            if (x < (width/2) - (imageWidth/2) + 85)
            {
                x += 5;
            }
            else
            {
                // After "Plungin'" image is in place display "Press Start Image".
                g2.drawImage(mover3, (width / 2) - (100), (height / 2) - (imageHeight / 2) + 220,
                        200, 100, null);
            }
        }

        if (GameStaticState.isFading())
        {
            comp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float)alphaLevel);
            g2.setComposite(comp);

            if (alphaLevel > 0.02)
            {
                alphaLevel -= 0.02;
            }
        }

        if (isPositionSet)
        {
            int w = 500;
            int h = 250;
            int boxX = Main.WIN_WIDTH / 2 - w / 2;
            int boxY = Main.WIN_HEIGHT / 2 - h / 2 + 220;
            int heroX = boxX + 90;
            int heroY = boxY + 75;
            int chronoX = boxX + 300;
            int chronoY = heroY;

            g2.setStroke(new BasicStroke(7));
            g2.setColor(Color.BLACK);
            g2.fillRect(boxX, boxY, w, h);
            g2.setColor(Color.WHITE);
            g2.drawRect(boxX, boxY, w, h);

            g2.setColor(Color.WHITE);

            if (isHeroSelected)
            {
                g2.drawRect(heroX - 5, heroY - 5, 125, 140);
            }
            else
            {
                g2.drawRect(chronoX - 5, chronoY - 5, 125, 140);
            }

            g2.drawImage(choose, boxX + 100, boxY + 25, 295, 35, null);

            g2.drawImage(heroImage, heroX, heroY, 120, 130, null);
            g2.drawImage(chronoImage, chronoX, chronoY, 120, 130, null);

        }
    }

    public void setIsHeroSelected(boolean isHeroSelected)
    {
        this.isHeroSelected = isHeroSelected;
    }

    public boolean getIsHeroSelected()
    {
        return isHeroSelected;
    }

    public void setPositions()
    {
        this.y = (height / 2) - (imageHeight / 2) - 120;
        this.x = (width/2) - (imageWidth/2) + 85;

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true), "LEFT");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true), "RIGHT");

        this.actionMap.put("ENTER", new EnterAction(this));
        this.actionMap.put("LEFT", new LeftArrowAction());
        this.actionMap.put("RIGHT", new RightArrowAction());

        this.isPositionSet = true;

        this.animationTimer = new Timer(130, animationAction);
        this.animationTimer.setRepeats(true);
        this.animationTimer.start();
    }

    public void printScreen() {
        Graphics g;
        try {
            g = this.getGraphics();
            if ((g != null) && (dbImage != null)) {
                g.drawImage(dbImage, 0, 0, null);
            }
            Toolkit.getDefaultToolkit().sync();  // sync the display on some systems
            if (g != null) {
                g.dispose();
            }
        } catch (Exception e) {
            System.out.println("Graphics error: " + e);
        }
    }
}

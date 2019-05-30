package model;

import controller.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Pause
{
    private int x;
    private int y;
    private int buttonX;
    private int buttonY;
    private int button2X;
    private int button2Y;
    private int width;
    private int height;
    private int buttonWidth;
    private int buttonHeight;
    private int button2Width;
    private int button2Height;
    private Image title1;
    private Image quit;
    private Image resume;
    private boolean isResumeSelected;

    /**
     * Constructor for Pause class. Sets values
     * for attributes and loads resources.
     */
    public Pause()
    {
        this.width = 500;
        this.height = 400;
        this.x = Main.WIN_WIDTH / 2 - this.width / 2;
        this.y = Main.WIN_HEIGHT / 2 - this.height / 2;

        this.buttonX = x + 50;
        this.buttonY = y + 100;
        this.buttonWidth = width - 100;
        this.buttonHeight = 100;

        this.button2X = x + 50;
        this.button2Y = y + 225;
        this.button2Width = width - 100;
        this.button2Height = 100;

        this.isResumeSelected = true;

        try {
            title1 = ImageIO.read(getClass().getResource("..//view//resources//Pause//pause.png"));
            quit = ImageIO.read(getClass().getResource("..//view//resources//Pause//quits.png"));
            resume = ImageIO.read(getClass().getResource("..//view//resources//Pause//resumes.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open shooter.png");
            System.exit(-1);
        }

    }

    public void render(Graphics2D g)
    {
        int imageX = x + 200;

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(x, y, width, height);
        g.setColor(Color.DARK_GRAY);
        g.drawRect(x, y, width, height);
        g.fillRect(buttonX, buttonY, buttonWidth, buttonHeight);
        g.fillRect(button2X, button2Y, button2Width, button2Height);
        g.drawImage(title1, imageX, y + 5, 100, 50, null);
        g.drawImage(resume, buttonX + 150, buttonY + (buttonHeight / 4), 100, 50, null);
        g.drawImage(quit, button2X + 150, button2Y + (button2Height / 4), 100, 50, null);

        g.setColor(Color.BLUE);

        if (this.isResumeSelected)
        {
            g.drawRect(buttonX, buttonY, buttonWidth, buttonHeight);
        }
        else
        {
            g.drawRect(button2X, button2Y, button2Width, button2Height);
        }
    }

    /**
     * Used for toggling the selection on the pause screen.
     */
    public void toggleSelection()
    {
        this.isResumeSelected = !this.isResumeSelected;
    }

    /**
     * Gets the pause screen selection.
     * @return the selection.
     */
    public boolean getSelection()
    {
        return this.isResumeSelected;
    }
}

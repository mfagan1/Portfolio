package model;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class BossHealthBar
{
    public static double health;
    private int healthBorderWidth;
    private int x;
    private int y;
    private int h;
    private int borderWidth;
    private int arcw;
    private int arch;
    //create color for BossEnemy health bar, semi transparent
    private Color boss = new Color(0, 150, 255, 150);
        
    private RoundRectangle2D roundedRectangle;


    public BossHealthBar()
    {
        this.x = 940;
        this.y = 25;
        this.healthBorderWidth = 240;
        this.arcw = 10;
        this.arch = 10;
        this.borderWidth = 4;
        this.health = 30;
        this.h = 20;
        this.roundedRectangle = new RoundRectangle2D.Float(x - 1, y, healthBorderWidth + 1, h, arcw, arch);


    }

    public void render(Graphics2D g)
    {
        int healthLevel = (int)(240 * (health / 30));
        g.setColor(boss);
        g.fillRect(x, y, healthLevel, 20);
        g.setColor(Color.WHITE);
        g.setStroke(new BasicStroke(borderWidth));
        g.draw(roundedRectangle);
        
        // width: this.x + healthBorderWidth/2 - (some guessed number because the string has width) = 20+120-5
        // height: this.y + borderWidth + this.h/2 = 25+4+10
        g.drawString(Double.toString(health), 1050, 39);
    }

    public double getHealth()
    {
        return this.health;
    }

    public void setHealth(int health)
    {
        this.health = health;
    }
}

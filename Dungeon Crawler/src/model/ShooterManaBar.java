
package model;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class ShooterManaBar {
    
    public static double mana;
    private int manaBorderWidth;
    private int x, y, h, borderWidth, arcw, arch;
    private Color power = new Color(0, 255, 0, 150);
    private RoundRectangle2D roundedRectangle;
    
    
    public ShooterManaBar()
    {
        this.x = 20;
        this.y = 50;
        this.manaBorderWidth = 240;
        this.arcw = 10;
        this.arch = 10;
        this.borderWidth = 4;
        this.mana = 50;
        this.h = 10;
        this.roundedRectangle = new RoundRectangle2D.Float(x -1, y, manaBorderWidth + 1, h, arcw, arch);
        
    }
    
    public void render(Graphics2D g)
    {
        int manaLevel = (int)(240 * (mana / 50));
        g.setColor(power);
        g.fillRect(x, y, manaLevel, 10);
        g.setColor(Color.WHITE);
        g.setStroke(new BasicStroke(borderWidth));
        g.draw(roundedRectangle);
    }
    
    public double getMana()
    {
        return this.mana;
    }
    
    public void setMana(int mana)
    {
        this.mana = mana;
    }
}

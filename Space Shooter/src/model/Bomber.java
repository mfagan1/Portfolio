package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import view.GamePanel;

public class Bomber extends GameFigure {
    private final int WIDTH = 50;
    private final int HEIGHT = 20;
    private final int UNIT_TRAVEL = 10; // per frame
    private Image image;

    private int direction = 1; // +1: to the right; -1 to the left

    public Bomber(float x, float y) {
        super(x, y); // origin: upper-left corner
        super.state = GameFigureState.UFO_STATE_APPEARED;

        image = null;

        try {
            image = ImageIO.read(getClass().getResource("enemy1.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open enemy1.png");
            System.exit(-1);
        }
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(image, (int) super.x, (int) super.y,
                WIDTH, HEIGHT, null);
    }

    @Override
    public void update() {
        Random r = new Random();
        int i1 = r.nextInt(50 - 1) + 1;
        if(state == 10)
        {
        if (direction > 0) {
            // moving to the right
            super.x += UNIT_TRAVEL;
            if (super.x + WIDTH > GamePanel.width) {
                direction = -1;
            }
        } else {
            // moving to the left
            super.x -= UNIT_TRAVEL;
            if (super.x <= 0) {
                direction = 1;
            }
        }
    }
        else if(state == 11)
        {
            if (y - 5 > 2) {
            y += UNIT_TRAVEL;
      } else {
        state = 0;
      }
        }
        
    
    }
    //@Override
    public Rectangle2D getCollisionBox() {
        return new Rectangle2D.Float(x - 20, y - 5, 40, 10);
    }

}

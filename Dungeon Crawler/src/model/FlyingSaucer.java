package model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import view.GamePanel;

public class FlyingSaucer extends GameFigure {

    private final int WIDTH = 50;
    private final int HEIGHT = 20;
    private final int UNIT_TRAVEL = 5; // per frame
    private Image image;

    private int direction = 1; // +1: to the right; -1 to the left

    public FlyingSaucer(float x, float y) {
        super(x, y); // origin: upper-left corner
        super.state = new ActiveFigureState();
        //super.state = GameFigureState.STATE_ACTIVE;
        movement = new FlyingSaucerStrategy();

        image = null;

        try {
            image = ImageIO.read(getClass().getResource("u.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open shooter.png");
            System.exit(-1);
        }
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(image, (int) super.x, (int) super.y,
                WIDTH, HEIGHT, null);
    }
    
    @Override
    public void setState(GameFigureState state)
    {
        this.state = state;
        if(this.state instanceof DieingFigureState)
            super.movement = new FlyingSaucerDieingMove();
    }
    
    @Override
    public void goNextState()
    {
        state.goNext(this);
    }

    @Override
    public void update() 
    {
        //call movement strategy function move
        //"this" is the instance of the gameFigure 
        movement.move(super.x, super.y, this);
        
//        if(state == GameFigureState.STATE_ACTIVE)
//        {
//        if (direction > 0) {
//            // moving to the right
//            super.x += UNIT_TRAVEL;
//            if (super.x + WIDTH > GamePanel.width) {
//                direction = -1;
//            }
//        } else {
//            // moving to the left
//            super.x -= UNIT_TRAVEL;
//            if (super.x <= 0) {
//                direction = 1;
//            }
//        }
//        }
//        else if(state == GameFigureState.STATE_DYING)
//        {
//            if(y - 5.0F > 2.0F)
//            {
//                y += 5.0F;
//            }
//            else
//                state = GameFigureState.STATE_DONE;
//        }
    }
    
    @Override
    public Rectangle2D.Float getCollisionBox()
    {
        return new Rectangle2D.Float(x , y , WIDTH, HEIGHT);
    }
    
    @Override
    public void setPosition(float x, float y)
    {
        super.x = x;
        super.y = y;
    }

}

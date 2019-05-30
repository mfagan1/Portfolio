/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inventory;

import view.GamePanel;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import model.ActiveFigureState;
import model.DoneFigureState;
import model.GameFigure;
import model.GameFigureState;
/**
 *
 * @author matthew
 */
public class ItemPotion extends GameFigure{
    private Image image;

    public ItemPotion(float x, float y) {
        super(x, y); // origin: upper-left corner
        super.state = new ActiveFigureState();

        image = null;

        try {
            image = ImageIO.read(getClass().getResource("potion.png"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error: Cannot open potion.png");
            System.exit(-1);
        }
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(image, (int) super.x, (int) super.y,
                15, 15, null);
    }

    @Override
    public void update() {
        if(state instanceof ActiveFigureState)
        {
            
        }
        else{
            //state = GameFigureState.STATE_DONE;
        }
    }
    
        @Override
    public Rectangle2D.Float getCollisionBox()
    {
        return new Rectangle2D.Float(x , y , 15, 15);
    }

    @Override
    public void setState(GameFigureState state) {
        this.state = state;
    }

    @Override
    public void goNextState() {
        // MODIFIED BY BRANDY
        // NOTE:
        // Setting state to dead b/c items don't have dying animations.
        // If I didn't do this, the count would be incremented twice in
        // the collision manager
        
        // goNextState() should call state.goNext(this);
        // In this case just call state.goNext(this) twice 
        // The new state creation is handled in the concrete State
        setState(new DoneFigureState());
    }

    @Override
    public void setPosition(float x, float y) {
        super.x = x;
        super.y = y;
    }
    
    public String name()
    {
        return "healthPotion";
    }
    
}

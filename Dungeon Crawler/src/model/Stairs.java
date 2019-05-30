/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Stairs extends GameFigure{
    
    private final int HEIGHT = 50;
    private final int WIDTH = 50;   
    
    private final String stairsImagePath = "..//resources//images//stairs.png";
    private Image stairsImage;
    private boolean isVisible = false;
    
    public Stairs(float x, float y){
        super(x,y);
        super.state = new ActiveFigureState();
        
        try{
            stairsImage = ImageIO.read(getClass().getResource(stairsImagePath));
        }catch(IOException ex){
            JOptionPane.showMessageDialog(null, "Error: Cannot open stairs.png");
        }
    }
    
    @Override
    public void render(Graphics2D g) {
        if(isVisible){
            g.drawImage(stairsImage, (int)super.x, (int)super.y, WIDTH, HEIGHT, null);
        }
    }

    @Override
    public void update() {
        // does not get updated; static image
    }

    @Override
    public void setState(GameFigureState state) {
        // does not change state
    }

    @Override
    public void goNextState() {
        // does not change state
    }

    @Override
    public void setPosition(float x, float y) {
        super.x = x;
        super.y = y;
    }

    @Override
    public Rectangle2D getCollisionBox() {
        if(!isVisible){
            return new Rectangle2D.Float(-1000, -1000, 5, 5);
        }
        return new Rectangle2D.Float(x, y, WIDTH, HEIGHT);
    }
    
    public void setVisibility(boolean shouldBeVisible){
        isVisible = shouldBeVisible;
    }
}

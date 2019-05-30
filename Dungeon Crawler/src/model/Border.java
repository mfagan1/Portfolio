/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Border implements CollisionBox{

    private Rectangle2D.Float border;
    private Color currentColor = Color.GRAY;
    public Border(float startX, float startY, float width, float height){
        border = new Rectangle2D.Float(startX, startY, width, height);
    }
    
    @Override
    public Rectangle2D getCollisionBox() {
        return border;
    }
    
    public void render(Graphics2D g) {
        g.setColor(currentColor);
        g.setStroke(new BasicStroke(2)); // thickness of the line
        g.fillRect((int)border.x, (int)border.y, (int)border.width, (int)border.height);
    }
    
    public void setColor(Color newColor){
        currentColor = newColor;
    }
    
}

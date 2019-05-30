package model;

import java.awt.BasicStroke;
import view.GamePanel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Bomb extends GameFigure {
    
    //super.state = GameFigureState.UFO_STATE_APPEARED;
    private int radius;
    private final Color color;
    private int dx = 3;
    private int dy = 3;
    private boolean implode = false;
    private final int explodeRadius;
    private Rectangle2D.Float collisionBox;

    public Bomb(float x, float y, int radius, Color color) {
        super(x, y);
        super.state = GameFigureState.BOMB_STATE_ADDED;
        //super.state = GameFigureState.BOMB_STATE_ADDED;
        this.radius = radius;
        this.color = color;
        this.explodeRadius = (radius * 3);
        this.collisionBox = new Rectangle2D.Float(x - radius, y - radius, radius * 2, radius * 2);
        
    }

    @Override
    public void render(Graphics2D g) {
         if (this.state == 20)
    {
      g.fillOval((int)(this.x - this.radius), (int)(this.y - this.radius), this.radius * 2, this.radius * 2);
    }
    else if (this.state == 21)
    {
      g.fillOval((int)(this.x - this.radius - 7.0F), (int)(this.y - this.radius - 5.0F), this.radius * 2, this.radius * 2);
      
      g.fillOval((int)(this.x - this.radius + 5.0F), (int)(this.y - this.radius + 7.0F), this.radius * 2, this.radius * 2);
      
      g.fillOval((int)(this.x - this.radius + 7.0F), (int)(this.y - this.radius - 10.0F), this.radius * 2, this.radius * 2);
    }
    }

    @Override
    public void update() {
       if (this.state == 20)
    {
      this.x += this.dx;
      this.y += this.dy;
    }
    else if (state == 21)
    {
      if ((radius < explodeRadius) && (!implode))
      {
        radius += 2;
      }
      else
      {
        implode = true;
        if (radius > 0) {
          radius -= 1;
        } else {
          state = 0;
        }
      }
    }
       
    if (x + radius > GamePanel.width)
    {
      dx = (-dx);
      x = (GamePanel.width - radius);
    }
    else if (x - radius < 0.0F)
    {
      dx = (-dx);
      x = radius;
    }
    if (y + radius > GamePanel.height)
    {
      dy = (-dy);
      y = (GamePanel.height - radius);
    }
    else if (y - radius < 0.0F)
    {
      dy = (-dy);
      y = radius;
    }
    collisionBox.x = x;
    collisionBox.y = y;
    }
   
    //@Override
    public Rectangle2D getCollisionBox() {
        return collisionBox;
    }
    
}

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Square extends MyShape
{
  public Square(int x, int y, Color color, int size)
  {
    super(new Point2D.Float(x, y), color, size);
  }
  
  public void draw(Graphics2D g2)
  {
    g2.setColor(getColor());
    g2.fill(new Rectangle2D.Float(getLocation().x - getSize() / 2, getLocation().y - getSize() / 2, getSize(), getSize()));
  }
}



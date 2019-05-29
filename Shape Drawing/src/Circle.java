import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class Circle extends MyShape
{
    
  public Circle(int x, int y, Color color, int size)
  {
    super(new Point2D.Float(x, y) {}, color, size);
  }
  
  public void draw(Graphics2D g2)
  {
    g2.setColor(getColor());
    g2.fill(new Ellipse2D.Float(getLocation().x - getSize() / 2, getLocation().y - getSize() / 2, getSize(), getSize()) {});
  }
}

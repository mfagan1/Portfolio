import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class Triangle extends MyShape
{
  private final Point2D.Float p1;
  private final Point2D.Float p2;
  public Triangle(int x, int y, Color color, int size)
  {
    super(new Point2D.Float(x, y), color, size);
    p1 = new Point2D.Float(getLocation().x, getLocation().y - size);
    p2 = new Point2D.Float(getLocation().x + size, getLocation().y);
  }
  
  public void draw(Graphics2D g2)
  {
    g2.setColor(getColor());
    g2.draw(new Line2D.Float(p1, p2));
    g2.draw(new Line2D.Float(getLocation(), p1));
    g2.draw(new Line2D.Float(getLocation(), p2));
  }
}
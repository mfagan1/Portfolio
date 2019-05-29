import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

public abstract class MyShape
{
  private final Point2D.Float location;
  private final Color color;
  private final int size;
  
  public MyShape(Point2D.Float l, Color c, int s)
  {
    location = l;
    color = c;
    size = s;
  }
  
  public Point2D.Float getLocation()
  {
    return location;
  }
  
  public Color getColor()
  {
    return color;
  }
  
  public int getSize()
  {
    return size;
  }
  
  public abstract void draw(Graphics2D paramGraphics2D);
}

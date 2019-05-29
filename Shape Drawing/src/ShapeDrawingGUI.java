import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class ShapeDrawingGUI extends JFrame
{
  private final JPanel canvas;
  private final JButton buttonClear = new JButton("Clear");
  private final ArrayList<MyShape> shapes = new ArrayList();
  private final JRadioButton buttonSmall = new JRadioButton("Small");
  private final JRadioButton buttonMedium = new JRadioButton("Medium");
  private final JRadioButton buttonLarge = new JRadioButton("Large");
  private final JRadioButton buttonCircle = new JRadioButton("Circle");
  private final JRadioButton buttonSquare = new JRadioButton("Square");
  private final JRadioButton buttonTriangle = new JRadioButton("Triangle");
  private final JRadioButton buttonRed = new JRadioButton("Red");
  private final JRadioButton buttonBlue = new JRadioButton("Blue");
  private final JRadioButton buttonGreen = new JRadioButton("Green");
  private final JRadioButton buttonYellow = new JRadioButton("Yellow");
  private final int small = 25;
  private final int medium = 35;
  private final int large = 45;
  private final int circle = 0;
  private final int square = 1;
  private final int triangle = 2;
  private int size = 20;
  private int shape = 0;
  private Color color = Color.RED;
  
  public ShapeDrawingGUI()
  {
    Container cp = getContentPane();
    
    canvas = new Canvas();
    canvas.setBackground(Color.GRAY);
    cp.add(canvas, "Center");
    canvas.addMouseListener(new MousePressListener());
    
    JPanel panelSize = new JPanel();
    panelSize.setBorder(new TitledBorder(new EtchedBorder(), "Size"));
    panelSize.add(buttonSmall);
    panelSize.add(buttonMedium);
    panelSize.add(buttonLarge);
    ButtonGroup sizeGroup = new ButtonGroup();
    sizeGroup.add(buttonSmall);
    sizeGroup.add(buttonMedium);
    sizeGroup.add(buttonLarge);
    buttonSmall.setSelected(true);
    
    JPanel panelShape = new JPanel();
    panelShape.setBorder(new TitledBorder(new EtchedBorder(), "Shape"));
    panelShape.add(buttonCircle);
    panelShape.add(buttonSquare);
    panelShape.add(buttonTriangle);
    ButtonGroup shapeGroup = new ButtonGroup();
    shapeGroup.add(buttonCircle);
    shapeGroup.add(buttonSquare);
    shapeGroup.add(buttonTriangle);
    buttonCircle.setSelected(true);
    
    JPanel panelColor = new JPanel();
    panelColor.setBorder(new TitledBorder(new EtchedBorder(), "Color"));
    panelColor.add(buttonRed);
    panelColor.add(buttonBlue);
    panelColor.add(buttonGreen);
    panelColor.add(buttonYellow);
    ButtonGroup colorGroup = new ButtonGroup();
    colorGroup.add(buttonRed);
    colorGroup.add(buttonBlue);
    colorGroup.add(buttonGreen);
    colorGroup.add(buttonYellow);
    buttonRed.setSelected(true);
    
    JPanel northPanel = new JPanel();
    northPanel.setLayout(new GridLayout(3, 1));
    northPanel.add(panelSize);
    northPanel.add(panelShape);
    northPanel.add(panelColor);
    cp.add(northPanel, "North");
    
    JPanel southPanel = new JPanel();
    southPanel.add(buttonClear);
    cp.add(southPanel, "South");
    
    ButtonListener listener = new ButtonListener();
    buttonSmall.addActionListener(listener);
    buttonMedium.addActionListener(listener);
    buttonLarge.addActionListener(listener);
    buttonCircle.addActionListener(listener);
    buttonSquare.addActionListener(listener);
    buttonTriangle.addActionListener(listener);
    buttonRed.addActionListener(listener);
    buttonBlue.addActionListener(listener);
    buttonGreen.addActionListener(listener);
    buttonYellow.addActionListener(listener);
    buttonClear.addActionListener(listener);
  }
  
  private class Canvas extends JPanel
  {
    private Canvas() {}
    
    public void paintComponent(Graphics g)
    {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D)g;
      for (MyShape s : shapes) {
        s.draw(g2);
      }
    }
  }
  
  private class MousePressListener implements MouseListener
  {
    private MousePressListener() {}
    
    public void mousePressed(MouseEvent e)
    {
      MyShape object;
      //MyShape object;
      if (shape == 0)
      {
        object = new Circle(e.getX(), e.getY(), color, size);
      }
      else
      {
        //MyShape object;
        if (shape == 1) {
          object = new Square(e.getX(), e.getY(), color, size);
        } else {
          object = new Triangle(e.getX(), e.getY(), color, size);
        }
      }
      shapes.add(object);
      canvas.repaint();
    }
    
    public void mouseClicked(MouseEvent e) {}
    
    public void mouseReleased(MouseEvent e) {}
    
    public void mouseEntered(MouseEvent e) {}
    
    public void mouseExited(MouseEvent e) {}
  }
  
  private class ButtonListener implements ActionListener
  {
    private ButtonListener() {}
    
    public void actionPerformed(ActionEvent e)
    {
      Object source = e.getSource();
      if (source == buttonClear)
      {
        shapes.clear();
        canvas.repaint();
      }
      else if (source == buttonSmall)
      {
        size = 25;
      }
      else if (source == buttonMedium)
      {
        size = 35;
      }
      else if (source == buttonLarge)
      {
        size = 45;
      }
      else if (source == buttonCircle)
      {
        shape = 0;
      }
      else if (source == buttonSquare)
      {
        shape = 1;
      }
      else if (source == buttonTriangle)
      {
        shape = 2;
      }
      else if (source == buttonRed)
      {
        color = Color.RED;
      }
      else if (source == buttonBlue)
      {
        color = Color.BLUE;
      }
      else if (source == buttonGreen)
      {
        color = Color.GREEN;
      }
      else if (source == buttonYellow)
      {
        color = Color.YELLOW;
      }
    }
  }
}

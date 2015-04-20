package event2;

import java.awt.*;
import java.awt.event.*;
import java.applet.*;

public class Applet1 extends Applet
implements MouseMotionListener, MouseListener {
  boolean isStandalone = false;
  BorderLayout borderLayout1 = new BorderLayout();
  Label label1 = new Label();
  Panel panel1 = new Panel();
  Panel panel2 = new Panel();
  Choice choice1 = new Choice();
  Button button1 = new Button();
  Checkbox checkbox1 = new Checkbox();
  Checkbox checkbox2 = new Checkbox();
  CheckboxGroup cg1 = new CheckboxGroup();
  // types of Shapes we can draw
  public static final byte LINE = 0;
  public static final byte RECTANGLE = 1;
  public static final byte OVAL = 2;
  // panel1 Graphics area
  private Graphics gr1;
  // mouse down, current and previous Points
  private Point startPoint, previousPoint, currentPoint;
  // dragging and fill flag
  private boolean dragging = false;
  private boolean filled = false;
  // type of shape to draw
  private byte shape = LINE;
  // Item Listener object
  Choice1ItemEventHandler eh1;
  // Action Listener object
  Button1ActionHandler ah1;

  //Construct the applet
  public Applet1() {
  }

  //Initialize the applet
  public void init() {
    try  {
      jbInit();
    }
    catch(Exception e)  {
      e.printStackTrace();
    }
  }

  //Component initialization
  private void jbInit() throws Exception {
    label1.setText("Drag Mouse to draw shapes");
    this.setSize(new Dimension(400,300));
    this.setLayout(borderLayout1);
    panel1.setBackground(Color.white);

    this.add(label1, BorderLayout.SOUTH);
    this.add(panel1, BorderLayout.CENTER);
    // Left aligned flowlayout with hgap, vgap
    panel2.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
    this.add(panel2, BorderLayout.NORTH);
    choice1.addItem("Line");
    choice1.addItem("Rectangle");
    choice1.addItem("Oval");
    choice1.select(0);
    panel2.add(choice1, null);
    button1.setLabel("Clear");
    panel2.add(button1, null);
    checkbox1.setLabel("Outline");
    checkbox2.setLabel("Filled");
    // make them mutually exclusive
    checkbox1.setCheckboxGroup(cg1);
    checkbox2.setCheckboxGroup(cg1);
    checkbox1.setState(true);
    checkbox2.setState(false);
    panel2.add(checkbox1, null);
    panel2.add(checkbox2, null);
    // gr1 = panel1 Graphics area
    gr1 = panel1.getGraphics();
    // Construct the Item Listener object from inner class
    eh1 = new Choice1ItemEventHandler();
    // Construct the ActionListener object from outside class
    ah1 = new Button1ActionHandler(this);

    // add this Applet to mouse listener list kept by panel1
    panel1.addMouseListener(this);
    panel1.addMouseMotionListener(this);
    // add the listener object as an ItemListener on choice1
    choice1.addItemListener(eh1);
    // add the Action Listener to button1
    button1.addActionListener(ah1);

    // add Anonymous ItemListeners to the checkbox controls
    checkbox1.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent e){
        filled = false;
        label1.setText("Outlines");
      }
    });
    checkbox2.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent e){
        filled = true;
        label1.setText("Filled");
      }
    });

  }

  //Start the applet
  public void start() {
  }

  //Stop the applet
  public void stop() {
  }

  //Destroy the applet
  public void destroy() {
  }

  //Get Applet information
  public String getAppletInfo() {
    return "Mouse Listener Applet";
  }

  // Override update method to just do a paint()
  public void update(Graphics g) {
    paint(g);
  }

  // PAINT
  public void paint(Graphics g) {
    if (dragging | filled) {
      gr1.setXORMode(panel1.getBackground());
      drawShape(gr1);
    } else {
      gr1.setPaintMode();
      drawShape(gr1); 
    }
  }

  // depending on shape Choice Draw
  public void drawShape(Graphics g) {
    switch (shape) {
      case LINE:
        if (previousPoint != null)
          g.drawLine(startPoint.x, startPoint.y, previousPoint.x, previousPoint.y);
        if (currentPoint != null)
          g.drawLine(startPoint.x, startPoint.y, currentPoint.x, currentPoint.y);
        break;
      case RECTANGLE:
        if (previousPoint != null) {
          int fromX = Math.min(startPoint.x, previousPoint.x);
          int toX = Math.max(startPoint.x, previousPoint.x);
          int fromY = Math.min(startPoint.y, previousPoint.y);
          int toY = Math.max(startPoint.y, previousPoint.y);
          int w = toX - fromX;
          int h = toY - fromY;
          if (filled) {
            g.fillRect(fromX, fromY, w, h);
          } else {
            g.drawRect(fromX, fromY, w, h);
          }
        }
        if (currentPoint != null) {
          int fromX = Math.min(startPoint.x, currentPoint.x);
          int toX = Math.max(startPoint.x, currentPoint.x);
          int fromY = Math.min(startPoint.y, currentPoint.y);
          int toY = Math.max(startPoint.y, currentPoint.y);
          int w = toX - fromX;
          int h = toY - fromY;
          if (filled) {
            g.fillRect(fromX, fromY, w, h);
          } else {
            g.drawRect(fromX, fromY, w, h);
          }
        }
        break;
      case OVAL:
        if (previousPoint != null) {
          int fromX = Math.min(startPoint.x, previousPoint.x);
          int toX = Math.max(startPoint.x, previousPoint.x);
          int fromY = Math.min(startPoint.y, previousPoint.y);
          int toY = Math.max(startPoint.y, previousPoint.y);
          int w = toX - fromX;
          int h = toY - fromY;
          if (filled)
            g.fillOval(fromX, fromY, w, h);
          else
            g.drawOval(fromX, fromY, w, h);
        }
        if (currentPoint != null) {
          int fromX = Math.min(startPoint.x, currentPoint.x);
          int toX = Math.max(startPoint.x, currentPoint.x);
          int fromY = Math.min(startPoint.y, currentPoint.y);
          int toY = Math.max(startPoint.y, currentPoint.y);
          int w = toX - fromX;
          int h = toY - fromY;
          if (filled)
            g.fillOval(fromX, fromY, w, h);
          else
            g.drawOval(fromX, fromY, w, h);
        }
        break;
    }
  }

  // Protected to allow outside class (in the same package) access
  protected void clearPanel( ) {
    Dimension d = panel1.getSize();
    gr1.clearRect(0,0, d.width, d.height);
  }
  
  // MOUSE MOTION LISTENER
  // Mouse moved while button held down
  public void mouseDragged(MouseEvent e) {
    dragging = true;
    previousPoint = currentPoint;
    currentPoint = new Point(e.getX(), e.getY());
    repaint();
  }
  public void mouseMoved(MouseEvent e) {
  }

  // MOUSE LISTENER
  /**
   * Invoked when the mouse has been clicked on a component.
   */
  public void mouseClicked(MouseEvent e) {
    if (e.getClickCount() > 1)
      clearPanel();
  }

  /**
   * Invoked when a mouse button has been pressed on a component.
   */
  public void mousePressed(MouseEvent e) {
    previousPoint = null;
    currentPoint = null;
    startPoint = new Point(e.getX(), e.getY());
  }

  /**
   * Invoked when a mouse button has been released on a component.
   */
  public void mouseReleased(MouseEvent e) {
    dragging = false;
    previousPoint = currentPoint;
    currentPoint = new Point(e.getX(), e.getY());
    repaint();
  }

  /**
   * Invoked when the mouse enters a component.
   */
  public void mouseEntered(MouseEvent e) {
  }

  /**
   * Invoked when the mouse exits a component.
   */
  public void mouseExited(MouseEvent e) {
  }

  /* INNER CLASS (must not be public) can reference instance variables of containing class
   * listens for ItemEvents from the Choice component
   * There is no Adapter class for this Listener it only has 1 method
   */
  class Choice1ItemEventHandler implements ItemListener {
    // No constructor (default no args constructor is ok)
    
    public void itemStateChanged(ItemEvent e){
      if (e.getItem().equals("Line"))
        shape = LINE;
      else if (e.getItem().equals("Rectangle"))
        shape = RECTANGLE;
      else if (e.getItem().equals("Oval"))
        shape = OVAL;

    }
  }

}

// Standard Event Handler class
class Button1ActionHandler implements ActionListener {
  // reference to the calling Applet
  private Applet1 caller;

  // Constructor
  Button1ActionHandler(Applet1 a) {
    caller = a;
  }

  public void actionPerformed(ActionEvent e) {
    // call clear method in caller
    caller.clearPanel();
  }
}
package event1;

import java.awt.*;
import java.applet.*;
/* Simple AWT Applet draws shapes on a panel at a mouse_down location
 * Uses 'old' style Event handling in a handleEvent( ) method
 * Demo use of event.modifiers value ANDed with Event bit masks
 */

public class Applet1 extends Applet {
  boolean isStandalone = false;
  BorderLayout borderLayout1 = new BorderLayout();
  Label label1 = new Label();
  Panel panel1 = new Panel();
  // The Event (x,y coords & modifiers)
  Event ev1;
  // The panel's graphics area
  Graphics gr1;

  //Get a parameter value
  public String getParameter(String key, String def) {
    return isStandalone ? System.getProperty(key, def) :
      (getParameter(key) != null ? getParameter(key) : def);
  }

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
    label1.setText("label1");
    this.setSize(new Dimension(200, 150));
    this.setLayout(borderLayout1);
    panel1.setBackground(Color.white);
    this.add(label1, BorderLayout.SOUTH);
    this.add(panel1, BorderLayout.CENTER);

    gr1 = panel1.getGraphics();
  }

  //Get Applet information
  public String getAppletInfo() {
    return "Event Demo Applet1";
  }

  // override update to prevent clearing to background color
  public void update(Graphics g) {
    paint(g);
  }
  // paint / repaint the Applet
  public void paint(Graphics g) {
    // If a mouse_down happened draw at that point
    if (ev1 != null)
        drawShape();

  }
  private void drawShape( ) {
    if (ev1.modifiers == 0)
      gr1.drawRect(ev1.x - 5, ev1.y - 5, 10, 10);
    else if ((ev1.modifiers & Event.SHIFT_MASK) > 0)
          gr1.fillRect(ev1.x - 5, ev1.y - 5, 10, 10);
    else if ((ev1.modifiers & Event.ALT_MASK) > 0)
          gr1.drawOval(ev1.x - 5, ev1.y - 5, 10, 10);
    else if ((ev1.modifiers & Event.CTRL_MASK) > 0)
          gr1.fillOval(ev1.x - 5, ev1.y - 5, 10, 10);
    else if ((ev1.modifiers & Event.META_MASK) > 0) {
          int [] xar = {(ev1.x - 5), (ev1.x + 5),(ev1.x + 5)};
          int [] yar = {ev1.y, (ev1.y -5), (ev1.y + 5)};
          gr1.fillPolygon(xar, yar, 3);
         }
  }
  private void clearPanel( ) {
    Dimension d = panel1.getSize();
    gr1.clearRect(0,0, d.width, d.height);
  }

  /* All AWT events are propagated up the container heirarchy
   * to this method in Applet
   * return true if the Event if handled here
   * else call the super class' handleEvent( ) method
   */
  public boolean handleEvent(Event e) {
  
    if (e.id == Event.MOUSE_DOWN) {
      if (e.clickCount > 1) {
        clearPanel();
      } else {
        ev1 = e;
        label1.setText("Mouse down x=" + e.x + " y=" + e.y);
        repaint();
        return true;
      }
    }
    if (e.id == Event.MOUSE_ENTER) {
      ev1 = null;
      label1.setText("Click + SHIFT, ALT or CTRL");
      repaint();
      return true;
    }
    if (e.id == Event.MOUSE_EXIT) {
      ev1 = null;
      label1.setText("");
      repaint();
      return true;
    }
    return super.handleEvent(e);

  }

}
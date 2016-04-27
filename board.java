import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;

public class board extends JPanel{
      

	public void paintComponent(Graphics g1) {
      super.paintComponent(g1);
      Graphics2D g = (Graphics2D) g1.create();
      g.setStroke(new BasicStroke(2.0f));
      //outer board
      g.setColor(new Color(255,230,179));          //beige
      g.fill3DRect(750,225,600,600,true);          //x,y,width,height
      //inner area
      g.setColor(new Color(51,102,153));           //navy blue
      g.fill3DRect(775,250,550,550,true);
      //safe zones at the corners
      g.setColor(Color.RED);
      g.fill3DRect(750,225,25,25,true);
      g.fill3DRect(1325,225,25,25,true);
      g.fill3DRect(750,800,25,25,true);
      g.fill3DRect(1325,800,25,25,true);

      
      //marking territories
      g.setStroke(new BasicStroke(1.0f));
      double x = 300 - 75/Math.sqrt(2);
      g.setColor(Color.BLACK);
      g.draw(new Line2D.Double(750,225,750+x,225+x));
      g.draw(new Line2D.Double(1350,225,1350-x,225+x));
      g.draw(new Line2D.Double(750,825,750+x,825-x));
      g.draw(new Line2D.Double(1350,825,1350-x,825-x));

      double y = 25;
      g.draw(new Line2D.Double(750+y,225,750,225+y));
      g.draw(new Line2D.Double(1350-y,225,1350,225+y));
      g.draw(new Line2D.Double(750+y,825,750,825-y));
      g.draw(new Line2D.Double(1350-y,825,1350,825-y));

      



}

      

   }
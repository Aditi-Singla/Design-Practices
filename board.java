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

      //central circles to show number of lives remaining

      //top
      //outer circle - r = 75
      g.setColor(Color.BLACK);
      g.draw(new Arc2D.Double(975, 450, 150, 150, 45, 90, Arc2D.PIE));     //top
      g.setColor(new Color(255,230,179));
      g.fill(new Arc2D.Double(975, 450, 150, 150, 45, 90, Arc2D.PIE));     //top
      //second circle - r = 60
      g.setColor(Color.BLACK);
      g.draw(new Arc2D.Double(990, 465, 120, 120, 45, 90, Arc2D.PIE));     //top
      g.setColor(new Color(255,230,179));
      g.fill(new Arc2D.Double(990, 465, 120, 120, 45, 90, Arc2D.PIE));     //top
      //third circle - r = 45
      g.setColor(Color.BLACK);
      g.draw(new Arc2D.Double(1005, 480, 90, 90, 45, 90, Arc2D.PIE));     //top
      g.setColor(new Color(255,230,179));
      g.fill(new Arc2D.Double(1005, 480, 90, 90, 45, 90, Arc2D.PIE));     //top
   

      //left
      //outer circle - r = 75
      g.setColor(Color.BLACK);
      g.draw(new Arc2D.Double(975, 450, 150, 150, 135, 90, Arc2D.PIE));     //left
      g.setColor(new Color(255,230,179));
      g.fill(new Arc2D.Double(975, 450, 150, 150, 135, 90, Arc2D.PIE));     //left
      //second circle - r = 60
      g.setColor(Color.BLACK);
      g.draw(new Arc2D.Double(990, 465, 120, 120, 135, 90, Arc2D.PIE));     //left
      g.setColor(new Color(255,230,179));
      g.fill(new Arc2D.Double(990, 465, 120, 120, 135, 90, Arc2D.PIE));     //left
      //third circle - r = 45
      g.setColor(Color.BLACK);
      g.draw(new Arc2D.Double(1005, 480, 90, 90, 135, 90, Arc2D.PIE));     //left
      g.setColor(new Color(255,230,179));
      g.fill(new Arc2D.Double(1005, 480, 90, 90, 135, 90, Arc2D.PIE));     //left
      

      //right
      //outer circle - r = 75
      g.setColor(Color.BLACK);
      g.draw(new Arc2D.Double(975, 450, 150, 150, -45, 90, Arc2D.PIE));     //right
      g.setColor(new Color(255,230,179));
      g.fill(new Arc2D.Double(975, 450, 150, 150, -45, 90, Arc2D.PIE));     //right
      //second circle - r = 60
      g.setColor(Color.BLACK);
      g.draw(new Arc2D.Double(990, 465, 120, 120, -45, 90, Arc2D.PIE));     //right
      g.setColor(new Color(255,230,179));
      g.fill(new Arc2D.Double(990, 465, 120, 120, -45, 90, Arc2D.PIE));     //right
      //third circle - r = 45
      g.setColor(Color.BLACK);
      g.draw(new Arc2D.Double(1005, 480, 90, 90, -45, 90, Arc2D.PIE));     //right
      g.setColor(new Color(255,230,179));
      g.fill(new Arc2D.Double(1005, 480, 90, 90, -45, 90, Arc2D.PIE));     //right
      

      //bottom
      //outer circle - r = 75
      g.setColor(Color.BLACK);
      g.draw(new Arc2D.Double(975, 450, 150, 150, -135, 90, Arc2D.PIE));     //bottom
      g.setColor(new Color(255,230,179));
      g.fill(new Arc2D.Double(975, 450, 150, 150, -135, 90, Arc2D.PIE));     //bottom
      //second circle - r = 60
      g.setColor(Color.BLACK);
      g.draw(new Arc2D.Double(990, 465, 120, 120, -135, 90, Arc2D.PIE));     //bottom
      g.setColor(new Color(255,230,179));
      g.fill(new Arc2D.Double(990, 465, 120, 120, -135, 90, Arc2D.PIE));     //bottom
      //third circle - r = 45
      g.setColor(Color.BLACK);
      g.draw(new Arc2D.Double(1005, 480, 90, 90, -135, 90, Arc2D.PIE));     //bottom
      g.setColor(new Color(255,230,179));
      g.fill(new Arc2D.Double(1005, 480, 90, 90, -135, 90, Arc2D.PIE));     //bottom
      

      //fourth circle - r = 30
      g.setColor(Color.BLACK);
      g.draw(new Ellipse2D.Double(1020, 495, 60, 60));     
      g.setColor(new Color(51,102,153));
      g.fill(new Ellipse2D.Double(1020, 495, 60, 60));    

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
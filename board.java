import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;

public class board extends JPanel{
      
      int w,h;
      public board(int width,int height) {
            w = (width-600)/2;
            h = (height-600)/2;
            
      }

	public void paintComponent(Graphics g1) {            
      super.paintComponent(g1);
      Graphics2D g = (Graphics2D) g1.create();
      g.setStroke(new BasicStroke(2.0f));
      //outer board
      g.setColor(new Color(255,230,179));          //beige
      g.fill3DRect(w,h,600,600,true);          //x,y,width,height
      //inner area
      g.setColor(new Color(51,102,153));           //navy blue
      g.fill3DRect(25+w,25+h,550,550,true);
      //safe zones at the corners
      g.setColor(Color.RED);
      g.fill3DRect(w,h,25,25,true);
      g.fill3DRect(575+w,h,25,25,true);
      g.fill3DRect(w,575+h,25,25,true);
      g.fill3DRect(575+w,575+h,25,25,true);

      
      //marking territories
      g.setStroke(new BasicStroke(1.0f));
      double x = 300 - 75/Math.sqrt(2);
      g.setColor(Color.BLACK);
      g.draw(new Line2D.Double(w,h,x+w,x+h));
      g.draw(new Line2D.Double(600+w,h,600-x+w,x+h));
      g.draw(new Line2D.Double(w,600+h,x+w,600-x+h));
      g.draw(new Line2D.Double(600+w,600+h,600-x+w,600-x+h));

      double y = 25;
      g.draw(new Line2D.Double(y+w,h,w,y+h));
      g.draw(new Line2D.Double(600-y+w,h,600+w,y+h));
      g.draw(new Line2D.Double(y+w,600+h,w,600-y+h));
      g.draw(new Line2D.Double(600-y+w,600+h,600+w,600-y+h));
      }
}
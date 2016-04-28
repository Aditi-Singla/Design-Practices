import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class print_message extends JPanel {
   String message;
   int w,h;
   public print_message(String str,int w1,int h1) {
      message = str;
      w = w1;
      h = h1;
   }

   public void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D) g;
      if(message.equals("Timer for Special Power")) {
         g2.setFont(new Font("Serif", Font.BOLD,20));
         paintHorizontallyCenteredText(g2, "Timer for", w+275, h);
         paintHorizontallyCenteredText(g2, "Special Power", w+275, h+23);
      }
      else {
         g2.setFont(new Font("Serif", Font.PLAIN, 72));
         paintHorizontallyCenteredText(g2, message, w+300, h-20);
      }
   }
   protected void paintHorizontallyCenteredText(Graphics2D g2,
   String s, float centerX, float baselineY) {
      FontRenderContext frc = g2.getFontRenderContext();
      Rectangle2D bounds = g2.getFont().getStringBounds(s, frc);
      float width = (float) bounds.getWidth();
      float height = (float) bounds.getHeight();
      g2.setColor(Color.BLACK);
      g2.drawString(s, centerX - width / 2, baselineY);
   }
  
}
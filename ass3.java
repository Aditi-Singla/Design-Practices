import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;

public class ass3 {
   
   public static void main(String[] args) {
      JFrame frame = new JFrame();
      JLayeredPane lpane = new JLayeredPane();
      frame.setTitle("Ping Pong - 2014CS10217_2014CS10253_2014CS50277");
      
      frame.setLayout(new BorderLayout());
      paddle paddle1 = new paddle();
      board gameboard = new board();
      
      frame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent e) {
            System.exit(0);
         }
      });
      
      Container contentPane = frame.getContentPane();
   
      contentPane.add(lpane,BorderLayout.CENTER);
      gameboard.setBounds(0,0,2100,1050);
      paddle1.setBounds(0,0,2100,1050);
      paddle1.setOpaque(false);
      lpane.add(gameboard, new Integer(0), 0);

      lpane.add(paddle1, new Integer(1), 0);
        
      frame.setVisible(true);
      frame.setSize(2100,1050);
      
   }
}
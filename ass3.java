import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;

public class ass3 {
   
   public static void main(String[] args) {
      JFrame frame = new JFrame();
      JLayeredPane lpane = new JLayeredPane();                    //using JLayeredPane for multiple planes
      frame.setTitle("Ping Pong - 2014CS10217_2014CS10253_2014CS50277");
      
      frame.setLayout(new BorderLayout());
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      movingObjects ball_paddles = new movingObjects((screenSize.width-600)/2,(screenSize.height-600)/2);
      board gameboard = new board(screenSize.width,screenSize.height);
      /*Setting exit on close action*/
      frame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent e) {
            System.exit(0);
         }
      });
      
      Container contentPane = frame.getContentPane();
           
      contentPane.add(lpane,BorderLayout.CENTER);
      gameboard.setBounds(0,0,screenSize.width,screenSize.height);
      
      ball_paddles.setBounds(0,0,screenSize.width,screenSize.height);
      ball_paddles.setOpaque(false);

      
      /*Adding paddle, lives, board and ball to the frame*/
      lpane.add(gameboard, new Integer(0), 0);
      lpane.add(ball_paddles,new Integer(1), 0);
      
        
      frame.setVisible(true);
      frame.setSize(screenSize.width,screenSize.height);
   }
}
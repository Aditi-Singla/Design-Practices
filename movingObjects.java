import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;

public class movingObjects extends JPanel  implements KeyListener, ActionListener{

   paddle_h paddle0, paddle2;
   paddle_v paddle1, paddle3;
   lives lives1;
   ball ball1;
   Timer t = new Timer(3,this);
   int paddles[];
   JLayeredPane lpane;
   
   public movingObjects() {
      t.start();
      addKeyListener(this);
      setFocusable(true);
      setFocusTraversalKeysEnabled(false);
      lpane = new JLayeredPane();                    //using JLayeredPane for multiple planes
      
      this.setLayout(new BorderLayout());
      paddle2 = new paddle_h(225.0);         //top
      paddle0 = new paddle_h(800.0);         //bottom
      paddle1 = new paddle_v(750);           //left
      paddle3 = new paddle_v(1325);          //right
      ball1 = new ball();
      lives1 = new lives();

      paddles = new int[4];
      for (int i=0;i<4;i++) {
         paddles[i] = 1;
      }

      this.add(lpane,BorderLayout.CENTER);
      
      paddle0.setBounds(0,0,2100,1050);
      paddle0.setOpaque(false);
      paddle1.setBounds(0,0,2100,1050);
      paddle1.setOpaque(false);
      paddle2.setBounds(0,0,2100,1050);
      paddle2.setOpaque(false);
      paddle3.setBounds(0,0,2100,1050);
      paddle3.setOpaque(false);
      ball1.setBounds(0,0,2100,1050);
      ball1.setOpaque(false);
      lives1.setBounds(0,0,2100,1050);
      lives1.setOpaque(false);

      
      /*Adding paddle, board and ball to the frame*/
      lpane.add(lives1, new Integer(0), 0);
      lpane.add(paddle0, new Integer(1), 0);
      lpane.add(paddle1, new Integer(2), 0);
      lpane.add(paddle2, new Integer(3), 0);
      lpane.add(paddle3, new Integer(4), 0);
      lpane.add(ball1, new Integer(5), 0);
        
      

      
   }

   public void actionPerformed(ActionEvent e) {
      paddle0.move();
      paddle1.move();
      paddle2.move();
      paddle3.move();
      ball1.repaint();
      ball1.x += (ball1.velx);
      ball1.y += ball1.vely;
      int x_sign = (velx > 0) ? 1:-1;
      int y_sign = (vely > 0) ? 1:-1;
      double angle = Math.atan(vely/velx);
      /*Corner cases*/
      if(ball1.x <= 775 && ball1.y <= 250 && y_sign < 0) {                            //top left corner
         ball1.velx = x_sign * 13 * Math.cos(165 - x_sign * angle);
         ball1.vely = x_sign * 13 * Math.sin(165 - x_sign * angle);
      }  
      else if(ball1.x >= 1325 && ball1.y <= 250 && y_sign < 0) {                      //top right corner
         
      }
      else if(ball1.x <= 775 && ball1.y >= 800 && y_sign > 0) {                       //bottom left corner

      }
      else if(ball1.x >= 1325 && ball1.y >= 800 && y_sign > 0) {                      //bottom right corner

      }
      else {

      /*Collisions with wall and paddle*/

      if(ball1.x >= 1320 ) {         //right wall
         ball1.velx *= -1;
         lives1.setMiss(1);
         if(lives1.miss[1] == 3) {
            paddle3.y = 1350;
            paddles[3] = 0;
            paddle3.setVisible(false);
         }
      }
      else if(ball1.x <= 750) {     //left wall
         ball1.velx *= -1;
         lives1.setMiss(3);
         if(lives1.miss[3] == 3) {
            paddle1.y = 725;
            paddles[1] = 0;
            paddle1.setVisible(false);
         }
      }
      else if(paddles[3] == 1 && ball1.x >= 1295 && ball1.velx > 0 && ball1.y >= paddle3.y && ball1.y <= paddle3.y + 100 ) {         //right paddle
         ball1.velx *= -1;
         ball1.vely += paddle3.vel;
      }
      else if (paddles[1] == 1 && ball1.x <= 775 && ball1.velx < 0 && ball1.y >= paddle1.y && ball1.y <= paddle1.y + 100) {          //left paddle
         ball1.velx *= -1;
         ball1.vely += paddle1.vel;
      }

      else if(ball1.y >= 795) {     //bottom wall
         ball1.vely *= -1;
         lives1.setMiss(2);
         if(lives1.miss[2] == 3) {
            paddle0.x = 825;
            paddles[0] = 0;
            paddle0.setVisible(false);
         }
      }
      else if(ball1.y <= 225) {     //top wall
         ball1.vely *= -1;
         lives1.setMiss(0);
         if(lives1.miss[0] == 3) {
            paddle2.x = 200;
            paddles[2] = 0;
            paddle2.setVisible(false);
         }
      }
      else if(paddles[0] == 1 && ball1.y >= 765 && ball1.vely > 0 && ball1.x >= paddle0.x && ball1.x <= paddle0.x + 100) {           //bottom paddle
         ball1.vely *= -1;
         ball1.velx += paddle0.vel;
      }
      else if(paddles[2] == 1 && ball1.y <= 250 && ball1.vely < 0 && ball1.x >= paddle2.x && ball1.x <= paddle2.x + 100) {           //top paddle
         ball1.vely *= -1;
         ball1.velx += paddle2.vel;
      }
   }



      if((paddles[0]+1)*(paddles[1]+1)*(paddles[2]+1)*(paddles[3]+1) == 2) {
         int p;
         if (paddles[0] == 1)             //bottom
            p = 1;
         else if(paddles[1] == 1)         //left
            p = 2;
         else if(paddles[2] == 1)         //top
            p = 3;
         else                             //right
            p = 4;
         t.stop();
         ball1.x = 1035;
         ball1.y = 510;
         ball1.repaint();
         print_message message = new print_message("Player"+p+" won!");
         message.setBounds(0,0,2100,1050);
         message.setOpaque(false);
         lpane.add(message, new Integer(6), 0);
      }
     
   }

   /*Moving the paddle_h to its left when left key is pressed*/
   public void left() {
      if (paddle0.vel > 0 )
         paddle0.vel = 0;
      paddle0.vel = -12.0;
   }

   /*Moving the paddle_h to its right when right key is pressed*/
   public void right() {
      if (paddle0.vel < 0)
         paddle0.vel = 0;
      paddle0.vel = 12.0;
   }


   /*Stopping the paddle_h as the key is released*/
   public void stop() {
      paddle0.vel = 0;
   }

   /*Action when key is pressed*/
   public void keyPressed(KeyEvent e) {
      int code = e.getKeyCode();
      if (code == KeyEvent.VK_LEFT)
         left();
      else if (code == KeyEvent.VK_RIGHT)
         right();
   }

   public void keyTyped(KeyEvent e) {}

   /*Action when key is released*/
   public void keyReleased(KeyEvent e) {
      stop();
   }
}
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;

public class movingObjects extends JPanel  implements KeyListener, ActionListener{

   int w,h;

   paddle_h paddle0, paddle2;
   paddle_v paddle1, paddle3;
   lives lives1;
   ball ball1;
   Timer t = new Timer(3,this);
   int paddles[];
   JLayeredPane lpane;
   
   public movingObjects(int width,int height) {
      t.start();
      w = width;
      h = height;
      addKeyListener(this);
      setFocusable(true);
      setFocusTraversalKeysEnabled(false);
      lpane = new JLayeredPane();                    //using JLayeredPane for multiple planes
      
      this.setLayout(new BorderLayout());
      paddle2 = new paddle_h((double)(h),w);         //top
      paddle0 = new paddle_h((double)(h+575),w);         //bottom
      paddle1 = new paddle_v((double)(w),h);           //left
      paddle3 = new paddle_v((double)(w+575),h);          //right
      ball1 = new ball(w,h);
      lives1 = new lives(w,h);

      paddles = new int[4];
      for (int i=0;i<4;i++) {
         paddles[i] = 1;
      }

      this.add(lpane,BorderLayout.CENTER);
      
      paddle0.setBounds(0,0,(2*w)+600,(2*h)+600);
      paddle0.setOpaque(false);
      paddle1.setBounds(0,0,(2*w)+600,(2*h)+600);
      paddle1.setOpaque(false);
      paddle2.setBounds(0,0,(2*w)+600,(2*h)+600);
      paddle2.setOpaque(false);
      paddle3.setBounds(0,0,(2*w)+600,(2*h)+600);
      paddle3.setOpaque(false);
      ball1.setBounds(0,0,(2*w)+600,(2*h)+600);
      ball1.setOpaque(false);
      lives1.setBounds(0,0,(2*w)+600,(2*h)+600);
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
      if (ball1.x >= w+570)      //right wall
         ball1.x = w+570;
      else if (paddles[3] == 1 && ball1.x >= w+545 && ball1.y >= paddle3.y && ball1.y <= paddle3.y + 100)         //right paddle
         ball1.x = w+545;
      else if (ball1.x <= w)     //left wall
         ball1.x = w;
      else if (paddles[1] == 1 && ball1.x <= w+25 && ball1.y >= paddle1.y && ball1.y <= paddle1.y + 100)          //left paddle
         ball1.x = w+25;
      
      if (ball1.y >= h+570)      //bottom wall
         ball1.y = h+570;
      else if (paddles[0] == 1 && ball1.y >= h+545 && ball1.x >= paddle0.x && ball1.x <= paddle0.x + 100)         //bottom paddle
         ball1.y = h+545;
      else if (ball1.y <= h)     //top wall
         ball1.y = h;
      else if (paddles[2] == 1 && ball1.y <= h+25 && ball1.x >= paddle2.x && ball1.x <= paddle2.x + 100)           //top paddle
         ball1.y = h+25;

      int x_sign = (ball1.velx > 0) ? 1:-1;
      int y_sign = (ball1.vely > 0) ? 1:-1;
      double angle = Math.abs(Math.atan(ball1.vely/ball1.velx));      /*Corner cases*/
      
      if(ball1.x <= (w + 25) && ball1.y <= (h + 25)) {                            //top left corner
         if(y_sign < 0 && x_sign < 0) {
            ball1.velx = 13 * Math.cos(angle + Math.toRadians(15));
            ball1.vely = 13 * Math.sin(angle + Math.toRadians(15));
         }
      }  
      else if(ball1.x >= (w + 575) && ball1.y <= (h + 25)) {                      //top right corner
         if(y_sign < 0 && x_sign > 0) {
            ball1.velx = -13 * Math.cos(angle + Math.toRadians(15));
            ball1.vely = 13 * Math.sin(angle + Math.toRadians(15));
         }
      }
      else if(ball1.x <= (w + 25) && ball1.y >= (h + 575)) {                       //bottom left corner
         if(y_sign > 0 && x_sign < 0) {
            ball1.velx = 13 * Math.cos(angle + Math.toRadians(15));
            ball1.vely = -13 * Math.sin(angle + Math.toRadians(15));
         }
      }
      else if(ball1.x >= (w + 575) && ball1.y >= (h + 575)) {                      //bottom right corner
         if(y_sign > 0 && x_sign > 0) {
            ball1.velx = -13 * Math.cos(angle + Math.toRadians(15));
            ball1.vely = -13 * Math.sin(angle + Math.toRadians(15));
         }
      }
      else {

      /*Collisions with wall and paddle*/

      if(ball1.x == w+570 ) {         //right wall
         ball1.velx *= -1;
         lives1.setMiss(1);
         if(lives1.miss[1] == 3) {
            paddle3.x = w+650;
            paddles[3] = 0;
            paddle3.setVisible(false);
         }
      }
      else if(ball1.x == w) {     //left wall
         ball1.velx *= -1;
         lives1.setMiss(3);
         if(lives1.miss[3] == 3) {
            paddle1.x = w-50;
            paddles[1] = 0;
            paddle1.setVisible(false);
         }
      }
      else if(paddles[3] == 1 && ball1.x == w+545 && ball1.velx > 0 && ball1.y >= paddle3.y && ball1.y <= paddle3.y + 100 ) {         //right paddle
         ball1.velx *= -1;
         ball1.vely += paddle3.vel;
      }
      else if (paddles[1] == 1 && ball1.x == w+25 && ball1.velx < 0 && ball1.y >= paddle1.y && ball1.y <= paddle1.y + 100) {          //left paddle
         ball1.velx *= -1;
         ball1.vely += paddle1.vel;
      }

      else if(ball1.y == h+570) {     //bottom wall
         ball1.vely *= -1;
         lives1.setMiss(2);
         if(lives1.miss[2] == 3) {
            paddle0.y = h+650;
            paddles[0] = 0;
            paddle0.setVisible(false);
         }
      }
      else if(ball1.y == h) {     //top wall
         ball1.vely *= -1;
         lives1.setMiss(0);
         if(lives1.miss[0] == 3) {
            paddle2.y = h-50;
            paddles[2] = 0;
            paddle2.setVisible(false);
         }
      }
      else if(paddles[0] == 1 && ball1.y >= h+545 && ball1.vely > 0 && ball1.x >= paddle0.x && ball1.x <= paddle0.x + 100) {           //bottom paddle
         ball1.vely *= -1;
         ball1.velx += paddle0.vel;
      }
      else if(paddles[2] == 1 && ball1.y <= h+25 && ball1.vely < 0 && ball1.x >= paddle2.x && ball1.x <= paddle2.x + 100) {           //top paddle
         ball1.vely *= -1;
         ball1.velx += paddle2.vel;
      }

      else if (paddles[0] == 1 && ball1.y >= h+545) {        //bottom paddle
         if (ball1.x <= paddle0.x + 100 && ball1.x >= paddle0.x + 88){
            ball1.x = paddle0.x + 100;
            ball1.velx *= -1;
         }
         else if (ball1.x >= paddle0.x && ball1.x <= paddle0.x + 12){
            ball1.x = paddle0.x;
            ball1.velx *= -1;
         }
      }

      else if (paddles[1] == 1 && ball1.x <= w+25) {        //left paddle
         if (ball1.y <= paddle1.y + 100 && ball1.y >= paddle1.y + 88){
            ball1.y = paddle1.y + 100;
            ball1.vely *= -1;
         }
         else if (ball1.y >= paddle1.y && ball1.y <= paddle1.y + 12){
            ball1.y = paddle1.y;
            ball1.vely *= -1;
         }
      }
      else if (paddles[2] == 1 && ball1.y <= h+25) {        //top paddle
         if (ball1.x <= paddle2.x + 100 && ball1.x >= paddle2.x + 88){
            ball1.x = paddle2.x + 100;
            ball1.velx *= -1;
         }
         else if (ball1.x >= paddle2.x && ball1.x <= paddle2.x + 12){
            ball1.x = paddle2.x;
            ball1.velx *= -1;
         }
      } 
      else if (paddles[3] == 1 && ball1.x >= w+545) {        //right paddle
         if (ball1.y <= paddle3.y + 100 && ball1.y >= paddle3.y + 88){
            ball1.y = paddle3.y + 100;
            ball1.vely *= -1;
         }
         else if (ball1.y >= paddle3.y && ball1.y <= paddle3.y + 12){
            ball1.y = paddle3.y;
            ball1.vely *= -1;
         }
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
         ball1.x = w+285;
         ball1.y = h+285;
         ball1.repaint();
         print_message message = new print_message("Player"+p+" won!",w,h);
         message.setBounds(0,0,(2*w)+600,2*h+600);
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
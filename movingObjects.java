import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class movingObjects extends JPanel  implements KeyListener, ActionListener{

   int w,h;
   int prob;
   String level;

   paddle paddle0, paddle2;
   paddle paddle1, paddle3;
   lives lives1;
   ball ball1;
   powers powers1;
   Timer t = new Timer(3,this);
   int paddles[];
   JLayeredPane lpane;
   int temp[];
   double a,dist;
   Random random1;
   
   public movingObjects(int width,int height, String level1) {
      t.start();
      w = width;
      h = height;
      level = level1;


      lpane = new JLayeredPane();  //using JLayeredPane for multiple planes
      

      
      this.setLayout(new BorderLayout());
      paddle2 = new paddle((double)w,(double)(h),true);         //top
      paddle0 = new paddle((double)w,(double)(h+575),true);         //bottom
      paddle1 = new paddle((double)(w),(double)h,false);           //left
      paddle3 = new paddle((double)(w+575),(double)h,false);          //right
      ball1 = new ball(w,h);
      lives1 = new lives(w,h);
      powers1 = new powers(w+625,h+50);



      if(level.equals("Easy")) {
         prob = 4;
         a = 15;
      }
      else {
         prob = 8;
         a = 5;
      }
      temp = new int[3];
      
      dist = 100 * Math.random();


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
      powers1.setBounds(0,0,(2*w)+600,(2*h)+600);
      powers1.setOpaque(false);

      
      /*Adding paddle, board and ball to the frame*/
      lpane.add(lives1, new Integer(0), 0);
      lpane.add(paddle0, new Integer(1), 0);
      lpane.add(paddle1, new Integer(2), 0);
      lpane.add(paddle2, new Integer(3), 0);
      lpane.add(paddle3, new Integer(4), 0);
      lpane.add(ball1, new Integer(5), 0);
      lpane.add(powers1, new Integer(6), 0);

      addKeyListener(this);
      
      setFocusTraversalKeysEnabled(false);
      setFocusable(true);    

      this.addComponentListener(new ComponentAdapter() {
         public void componentShown(ComponentEvent e) {
            movingObjects.this.requestFocusInWindow();
         }
      });

   }

   public void actionPerformed(ActionEvent e) {
      if (paddles[0] ==1)
         powers1.incNum();
      paddle0.move();
      paddle1.move();
      paddle2.move();
      paddle3.move();
      ball1.repaint();
      
      
      int x_sign = (ball1.velx > 0) ? 1:-1;
      int y_sign = (ball1.vely > 0) ? 1:-1;
      double angle = Math.abs(Math.atan(ball1.vely/ball1.velx));



      /*Computer Algorithm for paddle1 (left)*/
      if(x_sign<0) {
         double diff = h - (ball1.y - (ball1.x - w)*ball1.vely/ball1.velx);
         double diff_ballpaddle = h - diff - paddle1.y;
         double temp_v = paddle1.vel;
         if(diff >= -600 - a && diff <= a) {

            if(diff_ballpaddle < 30)
               paddle1.vel = -12.0;
            else if(diff_ballpaddle <= 100)
               paddle1.vel = 0;
            else
               paddle1.vel = 12.0;

            if(diff_ballpaddle < -80 || diff_ballpaddle > 150) {
               if(temp[0] == 0) {                  //Undershoot
                  if(paddle1.vel == 0)
                     paddle1.vel = -12;
                  else if(paddle1.vel == -12){
                     if(diff_ballpaddle + 12 > -30)
                        paddle1.vel = 0;
                  }
                  else {
                     if(diff_ballpaddle - 12 <= 100)
                        paddle1.vel = 0;
                  }
               }

               else if(temp[0] == 1) {             //Overshoot
                  if(paddle1.vel == 0)
                     paddle1.vel = (temp_v == 0)?-12:temp_v;
               }
            }


         }
      }
      /////////////////////////////////////////


      /*Computer Algorithm for paddle3 (right)*/
      if(x_sign>0) {
         double diff = h - (ball1.y + (w + 600 - ball1.x)*ball1.vely/ball1.velx);
         double diff_ballpaddle = h - diff - paddle3.y;
         double temp_v = paddle3.vel;
         if(diff >= -600 - a && diff <= a) {

            if(diff_ballpaddle < 30)
               paddle3.vel = -12.0;
            else if(diff_ballpaddle <= 100)
               paddle3.vel = 0;
            else
               paddle3.vel = 12.0;

            if(diff_ballpaddle < -80 || diff_ballpaddle > 150) {
               if(temp[1] == 0) {                  //Undershoot
                  if(paddle3.vel == 0)
                     paddle3.vel = -12;
                  else if(paddle3.vel == -12){
                     if(diff_ballpaddle + 12 > -30)
                        paddle3.vel = 0;
                  }
                  else {
                     if(diff_ballpaddle - 12 <= 100)
                        paddle3.vel = 0;
                  }
               }

               else if(temp[1] == 1) {             //Overshoot
                  if(paddle3.vel == 0)
                     paddle3.vel = (temp_v == 0)?-12:temp_v;
               }
            }


         }
      }
      /////////////////////////////////////////


      /*Computer Algorithm for paddle2 (top)*/
      if(y_sign<0) {
         double diff = w - (ball1.x - (ball1.y - h)*ball1.velx/ball1.vely);
         double diff_ballpaddle = w - diff - paddle2.x;
         double temp_v = paddle2.vel;
         if(diff >= -600 - a && diff <= a) {

            if(diff_ballpaddle < 30)
               paddle2.vel = -12.0;
            else if(diff_ballpaddle <= 100)
               paddle2.vel = 0;
            else
               paddle2.vel = 12.0;

            if(diff_ballpaddle < -80 || diff_ballpaddle > 150) {
               if(temp[0] == 0) {                  //Undershoot
                  if(paddle2.vel == 0)
                     paddle2.vel = -12;
                  else if(paddle2.vel == -12){
                     if(diff_ballpaddle + 12 > -30)
                        paddle2.vel = 0;
                  }
                  else {
                     if(diff_ballpaddle - 12 <= 100)
                        paddle2.vel = 0;
                  }
               }

               else if(temp[0] == 1) {             //Overshoot
                  if(paddle2.vel == 0)
                     paddle2.vel = (temp_v == 0)?-12:temp_v;
               }
            }


         }
      }
      /////////////////////////////////////////


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

      /*Corner cases*/
      if(ball1.x <= (w + 30) && ball1.y <= (h + 30)) {                            //top left corner
         if(y_sign < 0 && x_sign < 0) {
            ball1.velx = 13 * Math.cos(angle + Math.toRadians(15));
            ball1.vely = 13 * Math.sin(angle + Math.toRadians(15));
         }
         else if(y_sign  < 0)
            ball1.vely *= -1;
         else
            ball1.velx *= -1;
      }  
      else if(ball1.x >= (w + 570) && ball1.y <= (h + 30)) {                      //top right corner
         if(y_sign < 0 && x_sign > 0) {
            ball1.velx = -13 * Math.cos(angle + Math.toRadians(15));
            ball1.vely = 13 * Math.sin(angle + Math.toRadians(15));
         }
         else if(y_sign < 0)
            ball1.vely *= -1;
         else
            ball1.velx *= -1;
      }
      else if(ball1.x <= (w + 30) && ball1.y >= (h + 570)) {                       //bottom left corner
         if(y_sign > 0 && x_sign < 0) {
            ball1.velx = 13 * Math.cos(angle + Math.toRadians(15));
            ball1.vely = -13 * Math.sin(angle + Math.toRadians(15));
         }
         else if(y_sign < 0)
            ball1.velx *= -1;
         else
            ball1.vely *= -1;
      }
      else if(ball1.x >= (w + 570) && ball1.y >= (h + 570)) {                      //bottom right corner
         if(y_sign > 0 && x_sign > 0) {
            ball1.velx = -13 * Math.cos(angle + Math.toRadians(15));
            ball1.vely = -13 * Math.sin(angle + Math.toRadians(15));
         }
         else if(y_sign > 0)
            ball1.vely *= -1;
         else
            ball1.velx *= -1;
      }
      else {

      /*Collisions with wall and paddle*/

      if(ball1.x == w+570 ) {         //right wall
         ball1.velx *= -1;
         lives1.setMiss(1);
         double speed = Math.sqrt((ball1.velx * ball1.velx) + (ball1.vely*ball1.vely));
         if(speed>13) {
            ball1.velx *= (13/speed);
            ball1.vely *= (13/speed);
         }
         if(level.equals("Easy")) {
            prob = 4;
            a = 15;
         }
         else {
            prob = 8;
            a = 5;
         }
         if(lives1.miss[1] == 3) {
            paddle3.x = w+650;
            paddles[3] = 0;
            paddle3.setVisible(false);
         }
      }
      else if(ball1.x == w) {     //left wall
         ball1.velx *= -1;
         lives1.setMiss(3);
         double speed = Math.sqrt((ball1.velx * ball1.velx) + (ball1.vely*ball1.vely));
         if(speed>13) {
            ball1.velx *= (13/speed);
            ball1.vely *= (13/speed);
         }
         if(level.equals("Easy")) {
            prob = 4;
            a = 15;
         }
         else {
            prob = 8;
            a = 5;
         }
         if(lives1.miss[3] == 3) {
            paddle1.x = w-50;
            paddles[1] = 0;
            paddle1.setVisible(false);
         }
      }
      else if(paddles[3] == 1 && ball1.x == w+545 && ball1.velx > 0 && ball1.y >= paddle3.y-5 && ball1.y <= paddle3.y + 105 ) {         //right paddle
         ball1.velx *= -1;
         // double speed = Math.sqrt((ball1.velx * ball1.velx) + (ball1.vely*ball1.vely));
         // if(speed>13) {
         //    ball1.velx *= (13/speed);
         //    ball1.vely *= (13/speed);
         // }
         if (paddle3.vel * ball1.vely > 0)
            ball1.vely += -3;
         if (paddle3.vel != 0)
            ball1.vely +=3;
         // ball1.vely += paddle3.vel;
         random1 = new Random();
         if(level.equals("Easy")) {
            prob = 4;
            a = 15;
         }
         else {
            prob = 8;
            a = 5;
         }
         temp[1] = random1.nextInt(prob);
      }
      else if (paddles[1] == 1 && ball1.x == w+25 && ball1.velx < 0 && ball1.y >= paddle1.y-5 && ball1.y <= paddle1.y + 105) {          //left paddle
         ball1.velx *= -1;
         double speed = Math.sqrt((ball1.velx * ball1.velx) + (ball1.vely*ball1.vely));
         // if(speed>13) {
         //    ball1.velx *= (13/speed);
         //    ball1.vely *= (13/speed);
         // }
         if (paddle1.vel * ball1.vely > 0)
            ball1.vely += -3;
         if (paddle1.vel != 0)
            ball1.vely +=3;
         // ball1.vely += paddle1.vel;
         random1 = new Random();
         if(level.equals("Easy")) {
            prob = 4;
            a = 15;
         }
         else {
            prob = 8;
            a = 5;
         }
         temp[0] = random1.nextInt(prob);
      }


      else if(ball1.y == h+570) {     //bottom wall
         ball1.vely *= -1;
         lives1.setMiss(2);
         powers1.num = 0;
         double speed = Math.sqrt((ball1.velx * ball1.velx) + (ball1.vely*ball1.vely));
         if(speed>13) {
            ball1.velx *= (13/speed);
            ball1.vely *= (13/speed);
         }
         if(level.equals("Easy")) {
            prob = 4;
            a = 15;
         }
         else {
            prob = 8;
            a = 5;
         }
         if(lives1.miss[2] == 3) {
            paddle0.y = h+650;
            paddles[0] = 0;
            paddle0.setVisible(false);
         }
      }
      else if(ball1.y == h) {     //top wall
         ball1.vely *= -1;
         lives1.setMiss(0);
         double speed = Math.sqrt((ball1.velx * ball1.velx) + (ball1.vely*ball1.vely));
         if(speed>13) {
            ball1.velx *= (13/speed);
            ball1.vely *= (13/speed);
         }
         if(level.equals("Easy")) {
            prob = 4;
            a = 15;
         }
         else {
            prob = 8;
            a = 5;
         }
         if(lives1.miss[0] == 3) {
            paddle2.y = h-50;
            paddles[2] = 0;
            paddle2.setVisible(false);
         }
      }
      else if(paddles[0] == 1 && ball1.y >= h+545 && ball1.vely > 0 && ball1.x >= paddle0.x-5 && ball1.x <= paddle0.x + 105) {           //bottom paddle
         ball1.vely *= -1;
         double speed = Math.sqrt((ball1.velx * ball1.velx) + (ball1.vely*ball1.vely));
         // if(speed>13) {
         //    ball1.velx *= (13/speed);
         //    ball1.vely *= (13/speed);
         // }
         if (paddle0.vel * ball1.velx > 0)
            ball1.velx += -3;
         if (paddle0.vel != 0)
            ball1.velx +=3;
         // ball1.velx += paddle0.vel;
         if(level.equals("Easy")) {
            prob = 4;
            a = 15;
         }
         else {
            prob = 8;
            a = 5;
         }
      }
      else if(paddles[2] == 1 && ball1.y <= h+25 && ball1.vely < 0 && ball1.x >= paddle2.x-5 && ball1.x <= paddle2.x + 105) {           //top paddle
         ball1.vely *= -1;
         double speed = Math.sqrt((ball1.velx * ball1.velx) + (ball1.vely*ball1.vely));
         // if(speed>13) {
         //    ball1.velx *= (13/speed);
         //    ball1.vely *= (13/speed);
         // }
         if (paddle2.vel * ball1.velx > 0)
            ball1.velx += -3;
         if (paddle2.vel != 0)
            ball1.velx +=3;
         // ball1.velx += paddle2.vel;
         random1 = new Random();
         if(level.equals("Easy")) {
            prob = 4;
            a = 15;
         }
         else {
            prob = 8;
            a = 5;
         }
         temp[2] = random1.nextInt(prob);
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
         powers1.num = -1;
         powers1.incNum();
         print_message message = new print_message("Player"+p+" won!",w,h);
         message.setBounds(0,0,(2*w)+600,2*h+600);
         message.setOpaque(false);
         lpane.add(message, new Integer(6), 0);
      }
     
   }

   /*Moving the paddle to its left when left key is pressed*/
   public void left() {
      if (paddle0.vel > 0 )
         paddle0.vel = 0;
      paddle0.vel = -12.0;
   }

   /*Moving the paddle to its right when right key is pressed*/
   public void right() {
      if (paddle0.vel < 0)
         paddle0.vel = 0;
      paddle0.vel = 12.0;
   }


   /*Stopping the paddle as the key is released*/
   public void stop() {
      paddle0.vel = 0;
   }

   /*Imparting extra speed*/
   public void springHit(int player) {
      // switch(player) {
      //    case 0: if(ball1.y == )
      // }
      // ball1.velx *= 1.5;
      // ball1.vely *= 1.5;
   }

   /*Spinning the ball in anticlockwise direction*/
   public void spinLeft(int player) {

   }

   /*Spinning the ball in clockwise direction*/
   public void spinRight(int player) {

   }

   /*Making the ball invisible for middle (three-fifth part) of the journey*/
   public void invisible(int player) {
      prob *= 0.75;
   }

   /*Action when key is pressed*/
   public void keyPressed(KeyEvent e) {
      int code = e.getKeyCode();
      if (code == KeyEvent.VK_LEFT)
         left();
      else if (code == KeyEvent.VK_RIGHT)
         right();
      else if(code == KeyEvent.VK_SPACE)
         springHit(0);
      else if(code == KeyEvent.VK_A)
         spinLeft(0);
      else if(code == KeyEvent.VK_D)
         spinRight(0);
      else if(code == KeyEvent.VK_S)
         invisible(0);
   }

   public void keyTyped(KeyEvent e) {}

   /*Action when key is released*/
   public void keyReleased(KeyEvent e) {
      stop();
   }
}
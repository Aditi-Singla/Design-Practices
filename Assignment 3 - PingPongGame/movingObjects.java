import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class movingObjects extends JPanel  implements ActionListener{

   int w,h;
   int prob;
   String level;

   paddle paddle0, paddle2;
   paddle paddle1, paddle3;
   lives lives1;
   ball ball1;
   powers powers1;
   Timer t = new Timer(20,this);
   int paddles[];
   JLayeredPane lpane;
   int temp[];
   double a;
   Random random1;
   double paddleVel; 
  int numOfPlayers;
  boolean isInitiator;
  int x_sign, y_sign;

  int counter = 0;
  print_message message3,message2,message1,messageGO;

  String host_name;

  int powerused = 0;

  String code = "";
  

   
   public movingObjects(int width,int height, String level1, int players_num,boolean flag_initiator, String host_address) {
      
      w = width;
      h = height;
      level = level1;
         numOfPlayers = players_num;
      isInitiator = flag_initiator;
      paddleVel = 30;
      host_name = host_address;

      // switch(numOfPlayers) {
      //    case 1:  code = "111";
      //             break;
      //    case 2:  code = "011";
      //             break;
      //    case 3:  code = "001";
      //             break;
      //    default: ;
      //    }

      lpane = new JLayeredPane();  //using JLayeredPane for multiple planes
                   
      
      this.setLayout(new BorderLayout());
      paddle2 = new paddle((double)w,(double)(h),true,100);         //top
      paddle0 = new paddle((double)w,(double)(h+575),true,100);         //bottom
      paddle1 = new paddle((double)(w),(double)h,false,100);           //left
      paddle3 = new paddle((double)(w+575),(double)h,false,100);          //right
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
      // if 
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

      ActionMap actionMap = getActionMap();
      int condition = JComponent.WHEN_IN_FOCUSED_WINDOW;
      InputMap inputMap = getInputMap(condition );

      
      inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "VK_LEFT");
      inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true), "released");
      inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "VK_RIGHT");
      inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true), "released");
      inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "VK_SPACE");
      inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), "VK_A");
      inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), "VK_D");
      inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), "VK_S");
     

      actionMap.put("VK_LEFT", new KeyControl("VK_LEFT"));
      actionMap.put("VK_RIGHT", new KeyControl("VK_RIGHT"));
      actionMap.put("VK_SPACE", new KeyControl("VK_SPACE"));
      actionMap.put("VK_A", new KeyControl("VK_A"));
      actionMap.put("VK_D", new KeyControl("VK_D"));
      actionMap.put("VK_S", new KeyControl("VK_S"));
      actionMap.put("released", new KeyControl("released"));


      
      if(numOfPlayers == 1) {
         t.start();
      }
      else {
               print_message message = new print_message("Waiting for players to connect...",w,h+336);
               message.setBounds(0,0,(2*w)+600,2*h+600);
               message.setOpaque(false);
               lpane.add(message, new Integer(6), 0);
      
               if (isInitiator)
               {
                  Initiator initiator = new Initiator(numOfPlayers,message,t,this);
               }
               else
               {
                  Player player = new Player(host_name,message,t,this); /**********************//**********************//**********************/
                  player.start(); /**********************//**********************//**********************//**********************/
               }
            }
   }

   public void actionPerformed(ActionEvent e) {
      if(counter<102) {
         counter++;
      if(counter == 1) {
         message3 = new print_message("3",w,h+336);
         message3.setBounds(0,0,(2*w)+600,2*h+600);
         message3.setOpaque(false);
         lpane.add(message3, new Integer(6), 0);
      }
      else if(counter == 31) {
         message3.setVisible(false);
         message2 = new print_message("2",w,h+336);
         message2.setBounds(0,0,(2*w)+600,2*h+600);
         message2.setOpaque(false);
         lpane.add(message2, new Integer(6), 0);
      }
      else if(counter == 61) {
         message2.setVisible(false);
         message1 = new print_message("1",w,h+336);
         message1.setBounds(0,0,(2*w)+600,2*h+600);
         message1.setOpaque(false);
         lpane.add(message1, new Integer(6), 0);
      }
      else if(counter == 91) {
         message1.setVisible(false);
         messageGO = new print_message("GO",w,h+336);
         messageGO.setBounds(0,0,(2*w)+600,2*h+600);
         messageGO.setOpaque(false);
         lpane.add(messageGO, new Integer(6), 0);
      }
      else if(counter == 101) {
         messageGO.setVisible(false);
      }
   }
   else {
      
       if (paddles[0] ==1)
         powers1.incNum();
      paddle0.move();
      if(numOfPlayers == 1 ) {
         
         paddle1.move();
         paddle2.move();
         paddle3.move();
      }
      else if (numOfPlayers==2 && isInitiator)
      {
         System.out.println("" + paddle1.x);
         paddle1.repaint();
         paddle2.move();
         paddle3.move();
      }
      else if (numOfPlayers==3 && isInitiator)
      {
         paddle1.repaint();
         paddle2.repaint();
         paddle3.move();
      }

      else {
         
         paddle1.repaint();
         paddle2.repaint();
         paddle3.repaint();
      }

      
      ball1.repaint();
      
      
      x_sign = (ball1.velx > 0) ? 1:-1;
      y_sign = (ball1.vely > 0) ? 1:-1;
      double angle = Math.abs(Math.atan(ball1.vely/ball1.velx));

      if (isInitiator)
      {
         switch(numOfPlayers) {
         case 1:  aiForPaddle1();
                  aiForPaddle2();
                  aiForPaddle3();
                  break;
         case 2:  aiForPaddle2();
                  aiForPaddle3();
                  break;
         case 3:  aiForPaddle3();
                  break;
         default: ;
         }
      }
      // if (isInitiator)
      //    callAIs(code);
      // else
      //    callAIs("111");
      
      

      ball1.x += ball1.velx;
      ball1.y += ball1.vely;
      
      /*Collisions with the paddles*/

      if(paddles[3] == 1 && ball1.x >= w+545 && ball1.y >= paddle3.y-5 && ball1.y <= paddle3.y + paddle3.l+5 ) {         //right paddle
         ball1.velx *= -1;
         ball1.x = w + 545;         
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
         random1 = new Random();
         temp[1] = random1.nextInt(prob);
      }
      else if (paddles[1] == 1 && ball1.x <= w+25 && ball1.y >= paddle1.y-5 && ball1.y <= paddle1.y + paddle1.l+5) {          //left paddle
         ball1.velx *= -1;
         ball1.x = w+25;
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
         random1 = new Random();
         temp[0] = random1.nextInt(prob);
      }
      else if(paddles[0] == 1 && ball1.y >= h+545 && ball1.x >= paddle0.x-5 && ball1.x <= paddle0.x + paddle0.l+5) {           //bottom paddle
         ball1.vely *= -1;
         ball1.y = h+545;
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
      else if(paddles[2] == 1 && ball1.y <= h+25 && ball1.x >= paddle2.x-5 && ball1.x <= paddle2.x + paddle2.l+5) {           //top paddle
         ball1.vely *= -1;
         ball1.y = h+25;
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
         random1 = new Random();
         temp[2] = random1.nextInt(prob);
      }

      /*Corner cases*/
      else if(ball1.x <= (w + 30) && ball1.y <= (h + 30)) {                            //top left corner
         if(y_sign < 0 && x_sign < 0) {
            double speed = Math.sqrt((ball1.velx * ball1.velx) + (ball1.vely*ball1.vely));
            ball1.velx = speed * Math.cos(angle + Math.toRadians(15));
            ball1.vely = speed * Math.sin(angle + Math.toRadians(15));
         }
         else if(y_sign  < 0)
            ball1.vely *= -1;
         else
            ball1.velx *= -1;
      }  
      else if(ball1.x >= (w + 570) && ball1.y <= (h + 30)) {                      //top right corner
         if(y_sign < 0 && x_sign > 0) {
            double speed = Math.sqrt((ball1.velx * ball1.velx) + (ball1.vely*ball1.vely));
            ball1.velx = -speed * Math.cos(angle + Math.toRadians(15));
            ball1.vely = speed * Math.sin(angle + Math.toRadians(15));
         }
         else if(y_sign < 0)
            ball1.vely *= -1;
         else
            ball1.velx *= -1;
      }
      else if(ball1.x <= (w + 30) && ball1.y >= (h + 570)) {                       //bottom left corner
         if(y_sign > 0 && x_sign < 0) {
            double speed = Math.sqrt((ball1.velx * ball1.velx) + (ball1.vely*ball1.vely));
            ball1.velx = speed * Math.cos(angle + Math.toRadians(15));
            ball1.vely = -speed * Math.sin(angle + Math.toRadians(15));
         }
         else if(y_sign < 0)
            ball1.velx *= -1;
         else
            ball1.vely *= -1;
      }
      else if(ball1.x >= (w + 570) && ball1.y >= (h + 570)) {                      //bottom right corner
         if(y_sign > 0 && x_sign > 0) {
            double speed = Math.sqrt((ball1.velx * ball1.velx) + (ball1.vely*ball1.vely));
            ball1.velx = -speed * Math.cos(angle + Math.toRadians(15));
            ball1.vely = -speed * Math.sin(angle + Math.toRadians(15));
         }
         else if(y_sign > 0)
            ball1.vely *= -1;
         else
            ball1.velx *= -1;
      }
      else {

      /*Collisions with wall*/

      if(ball1.x >= w+570 && !(paddles[3] == 1 && (ball1.y >= paddle3.y-35 && ball1.y <= paddle3.y + paddle3.l+5))) {         //right wall
         ball1.velx *= -1;
         lives1.setMiss(1);
         
         ball1.x = w+570;
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
         else if(level.equals("Hard")) {
            paddle3.l = paddle3.l - 20;
         }
         random1 = new Random();
         temp[1] = random1.nextInt(prob);
      }
      else if(ball1.x <= w && !(paddles[1] == 1 && (ball1.y >= paddle1.y-35 && ball1.y <= paddle1.y + paddle1.l+5))) {     //left wall
         ball1.velx *= -1;
         lives1.setMiss(3);
         ball1.x = w;
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
         else if(level.equals("Hard")) {
            paddle1.l = paddle1.l - 20;
         }
         random1 = new Random();
         temp[0] = random1.nextInt(prob);
      }



      else if(ball1.y >= h+570 && !(paddles[0] == 1 && (ball1.x >= paddle0.x-35 && ball1.x <= paddle0.x + paddle0.l+5))) {     //bottom wall
         ball1.vely *= -1;
         lives1.setMiss(2);
         powers1.num = 0;
         ball1.y = h+570;
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
         else if(level.equals("Hard")) {
            paddle0.l = paddle0.l - 20;
         }
      }
      else if(ball1.y <= h && !(paddles[2] == 1 && (ball1.x >= paddle2.x-35 && ball1.x <= paddle2.x + paddle2.l+5))) {     //top wall
         ball1.vely *= -1;
         lives1.setMiss(0);
         ball1.y = h;
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
         else if(level.equals("Hard")) {
            paddle2.l = paddle2.l - 20;
         }
         random1 = new Random();
         temp[2] = random1.nextInt(prob);
      }
      
      /*Edges of paddles*/

      else if (paddles[0] == 1 && ball1.y >= h+545) {        //bottom paddle
         if (ball1.x <= paddle0.x + paddle0.l && ball1.x >= paddle0.x + paddle0.l + 1 - paddleVel){
            ball1.x = paddle0.x + paddle0.l;
            ball1.velx *= -1;
         }
         else if (ball1.x >= paddle0.x - 30 && ball1.x <= paddle0.x - 31 + paddleVel){
            ball1.x = paddle0.x - 30;
            ball1.velx *= -1;
         }
      }

      else if (paddles[1] == 1 && ball1.x <= w+25) {        //left paddle
         if (ball1.y <= paddle1.y + paddle1.l && ball1.y >= paddle1.y + paddle1.l + 1 - paddleVel){
            ball1.y = paddle1.y + paddle1.l;
            ball1.vely *= -1;
         }
         else if (ball1.y >= paddle1.y - 30 && ball1.y <= paddle1.y - 31 + paddleVel){
            ball1.y = paddle1.y - 30;
            ball1.vely *= -1;
         }
      }
      else if (paddles[2] == 1 && ball1.y <= h+25) {        //top paddle
         if (ball1.x <= paddle2.x + paddle2.l && ball1.x >= paddle2.x + paddle2.l + 1 - paddleVel){
            ball1.x = paddle2.x + paddle2.l;
            ball1.velx = Math.abs(ball1.velx);
         }
         else if (ball1.x >= paddle2.x - 30 && ball1.x <= paddle2.x - 31 + paddleVel){
            ball1.x = paddle2.x - 30;
            ball1.velx = -1*Math.abs(ball1.velx);
         }
      } 
      else if (paddles[3] == 1 && ball1.x >= w+545) {        //right paddle
         if (ball1.y <= paddle3.y + paddle3.l && ball1.y >= paddle3.y + paddle3.l + 1 - paddleVel){
            ball1.y = paddle3.y + paddle3.l;
            ball1.vely *= -1;
         }
         else if (ball1.y >= paddle3.y - 30 && ball1.y <= paddle3.y - 31 + paddleVel){
            ball1.y = paddle3.y  -30;
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
}

      class KeyControl extends AbstractAction{
      String code;
      public KeyControl(String str){
         code = str;
         
      }

      public void actionPerformed(ActionEvent e) {
         
         if (code.equals("VK_LEFT"))
            left();
         else if (code.equals("VK_RIGHT"))
            right();
         else if(code.equals("VK_SPACE"))
            springHit(0);
         else if(code.equals("VK_A"))
            spinLeft(0);
         else if(code.equals("VK_D"))
            spinRight(0);
         else if(code.equals("VK_S"))
            invisible(0);
         else if(code.equals("released"))
            stop();
      }
}
      /*Moving the paddle to its left when left key is pressed*/
      public void left() {
         if (paddle0.vel > 0 )
            paddle0.vel = 0;
         paddle0.vel = -paddleVel;
      }

      /*Moving the paddle to its right when right key is pressed*/
      public void right() {
         if (paddle0.vel < 0)
            paddle0.vel = 0;
         paddle0.vel = paddleVel;
      }


      /*Stopping the paddle as the key is released*/
      public void stop() {
         paddle0.vel = 0;
      }

      /*Imparting extra speed*/
      public void springHit(int player) {
         
            boolean b;
            switch(player) {
               case 0:  b = (paddles[0] == 1 && ball1.y >= h+535 && ball1.x >= paddle0.x-10 && ball1.x <= paddle0.x + paddle0.l + 10);
                        break;
               case 1:  b = (paddles[1] == 1 && ball1.x <= w+35 && ball1.y >= paddle1.y-10 && ball1.y <= paddle1.y + paddle1.l + 10);
                        break;
               case 2:  b = (paddles[2] == 1 && ball1.y <= h+35 && ball1.x >= paddle2.x-10 && ball1.x <= paddle2.x + paddle2.l + 10);
                        break;
               default: b = (paddles[3] == 1 && ball1.x >= w+535 && ball1.y >= paddle3.y-10 && ball1.y <= paddle3.y + paddle3.l + 10);
                        break;
            }
            if(b) {
               ball1.velx *= 1.5;
               ball1.vely *= 1.5;
               powers1.num = 0;
            }
            
         
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


   
  public void aiForPaddle1() {
   /*Computer Algorithm for paddle1 (left)*/
      if(x_sign<0) {
         double diff = h - (ball1.y - (ball1.x - w -25)*ball1.vely/ball1.velx);
         double diff_ballpaddle = h - diff - paddle1.y;
         double temp_v = paddle1.vel;
         if(diff >= -600 - a && diff <= a) {

            if(diff_ballpaddle < -30)
               paddle1.vel = -paddleVel;
            else if(diff_ballpaddle <= paddle1.l)
               paddle1.vel = 0;
            else
               paddle1.vel = paddleVel;

            if(diff_ballpaddle < -80 || diff_ballpaddle > paddle1.l  +50) {
               if(temp[0] == 0) {                  //Undershoot
                  if(paddle1.vel == 0)
                     paddle1.vel = -paddleVel;
                  else if(paddle1.vel == -paddleVel){
                     if(diff_ballpaddle + paddleVel > -30)
                        paddle1.vel = 0;
                  }
                  else {
                     if(diff_ballpaddle - paddleVel <= paddle1.l)
                        paddle1.vel = 0;
                  }
               }

               else if(temp[0] == 1) {             //Overshoot
                  if(paddle1.vel == 0)
                     paddle1.vel = (temp_v == 0)?-paddleVel:temp_v;
               }
            }


         }
      }
      else
         paddle1.vel = 0;
      /////////////////////////////////////////


      


     

  }
  
  public void aiForPaddle2() {
    /*Computer Algorithm for paddle2 (top)*/
      if(y_sign<0) {
         double diff = w - (ball1.x - (ball1.y - h)*ball1.velx/ball1.vely);
         double diff_ballpaddle = w - diff - paddle2.x;
         double temp_v = paddle2.vel;
         if(diff >= -600 - a && diff <= a) {

            if(diff_ballpaddle < 30)
               paddle2.vel = -paddleVel;
            else if(diff_ballpaddle <= paddle2.l)
               paddle2.vel = 0;
            else
               paddle2.vel = paddleVel;

            if(diff_ballpaddle < -80 || diff_ballpaddle > paddle2.l  +50) {
               if(temp[0] == 0) {                  //Undershoot
                  if(paddle2.vel == 0)
                     paddle2.vel = -paddleVel;
                  else if(paddle2.vel == -paddleVel){
                     if(diff_ballpaddle + paddleVel > -30)
                        paddle2.vel = 0;
                  }
                  else {
                     if(diff_ballpaddle - paddleVel <= paddle2.l)
                        paddle2.vel = 0;
                  }
               }

               else if(temp[0] == 1) {             //Overshoot
                  if(paddle2.vel == 0)
                     paddle2.vel = (temp_v == 0)?-paddleVel:temp_v;
               }
            }


         }
      }
      else
         paddle2.vel = 0;
      /////////////////////////////////////////
  }
  
  public void aiForPaddle3() {
    /*Computer Algorithm for paddle3 (right)*/
      if(x_sign>0) {
         double diff = h - (ball1.y + (w + 600 - ball1.x)*ball1.vely/ball1.velx);
         double diff_ballpaddle = h - diff - paddle3.y;
         double temp_v = paddle3.vel;
         if(diff >= -600 - a && diff <= a) {

            if(diff_ballpaddle < 30)
               paddle3.vel = -paddleVel;
            else if(diff_ballpaddle <= paddle3.l)
               paddle3.vel = 0;
            else
               paddle3.vel = paddleVel;

            if(diff_ballpaddle < -80 || diff_ballpaddle > paddle3.l  +50) {
               if(temp[1] == 0) {                  //Undershoot
                  if(paddle3.vel == 0)
                     paddle3.vel = -paddleVel;
                  else if(paddle3.vel == -paddleVel){
                     if(diff_ballpaddle + paddleVel > -30)
                        paddle3.vel = 0;
                  }
                  else {
                     if(diff_ballpaddle - paddleVel <= paddle3.l)
                        paddle3.vel = 0;
                  }
               }

               else if(temp[1] == 1) {             //Overshoot
                  if(paddle3.vel == 0)
                     paddle3.vel = (temp_v == 0)?-paddleVel:temp_v;
               }
            }


         }
      }
      else
         paddle3.vel = 0;
      /////////////////////////////////////////
  }

  // public void callAIs(String code)
  // {
  //     switch (code)
  //     {
  //        case "000" : break;
  //        case "001" : aiForPaddle3(); break;
  //        case "010" : aiForPaddle2(); break;
  //        case "011" : aiForPaddle2(); aiForPaddle3(); break;
  //        case "100" : aiForPaddle1(); break;
  //        case "101" : aiForPaddle1(); aiForPaddle3(); break;
  //        case "110" : aiForPaddle1(); aiForPaddle2(); break;
  //        case "111" : aiForPaddle1(); aiForPaddle2(); aiForPaddle3(); break;
  //        default : ;
  //     }
  // }

}
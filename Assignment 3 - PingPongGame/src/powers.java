import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;

public class powers extends JPanel{
      int num;
      Graphics2D g;
      
      int w,h;
      public powers(int width,int height) {
            w = width;
            h = height;
            num = 0;
      }

      public void paintComponent(Graphics g1) {
      	super.paintComponent(g1);
      	g = (Graphics2D) g1.create();
      	g.setStroke(new BasicStroke(2.0f));
      	//levels to show time remaining to gain a new power
            draw();
	      

      }

      /*Increasing timer with time (controlled in movingObjects)*/
      public void incNum() {                           

            num += 1;
            if (num == 201) {
                  num = 200;
            }
            repaint();
      }


      /*Drawing levels according to the timer*/
      public void draw() {
            int n = num/20;
            switch(n) {
                  case 0:     //first (from top) - l = 100
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w,h,100,8,4,4));
                              g.setColor(new Color(102,102,102));
                              g.fill(new RoundRectangle2D.Double(w,h,100,8,4,4));
                              //second (from top) - l = 90
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+5*0.5,h+13,95,8,4,4));
                              g.setColor(new Color(102,102,102));
                              g.fill(new RoundRectangle2D.Double(w+5*0.5,h+13,95,8,4,4));
                              //third (from top) - l = 80
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+10*0.5,h+26,90,8,4,4));
                              g.setColor(new Color(102,102,102));
                              g.fill(new RoundRectangle2D.Double(w+10*0.5,h+26,90,8,4,4));
                              //fourth (from top) - l = 70
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+15*0.5,h+39,85,8,4,4));
                              g.setColor(new Color(102,102,102));
                              g.fill(new RoundRectangle2D.Double(w+15*0.5,h+39,85,8,4,4));
                              //fifth (from top) - l = 60
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+20*0.5,h+52,80,8,4,4));
                              g.setColor(new Color(102,102,102));
                              g.fill(new RoundRectangle2D.Double(w+20*0.5,h+52,80,8,4,4));
                              //sixth (from top) - l = 50
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+25*0.5,h+65,75,8,4,4));
                              g.setColor(new Color(102,102,102));
                              g.fill(new RoundRectangle2D.Double(w+25*0.5,h+65,75,8,4,4));
                              //seventh (from top) - l = 40
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+30*0.5,h+78,70,8,4,4));
                              g.setColor(new Color(102,102,102));
                              g.fill(new RoundRectangle2D.Double(w+30*0.5,h+78,70,8,4,4));
                              //eighth (from top) - l = 30
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+35*0.5,h+91,65,8,4,4));
                              g.setColor(new Color(102,102,102));
                              g.fill(new RoundRectangle2D.Double(w+35*0.5,h+91,65,8,4,4));
                              //ninth (from top) - l = 20
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+40*0.5,h+104,60,8,4,4));
                              g.setColor(new Color(102,102,102));
                              g.fill(new RoundRectangle2D.Double(w+40*0.5,h+104,60,8,4,4));  
                              //tenth (from top) - l = 10
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+45*0.5,h+117,55,8,4,4));
                              g.setColor(new Color(102,102,102));
                              g.fill(new RoundRectangle2D.Double(w+45*0.5,h+117,55,8,4,4));                            
                              break;
                  case 1:     //first (from top) - l = 100
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w,h,100,8,4,4));
                              g.setColor(new Color(102,102,102));
                              g.fill(new RoundRectangle2D.Double(w,h,100,8,4,4));
                              //second (from top) - l = 90
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+5*0.5,h+13,95,8,4,4));
                              g.setColor(new Color(102,102,102));
                              g.fill(new RoundRectangle2D.Double(w+5*0.5,h+13,95,8,4,4));
                              //third (from top) - l = 80
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+10*0.5,h+26,90,8,4,4));
                              g.setColor(new Color(102,102,102));
                              g.fill(new RoundRectangle2D.Double(w+10*0.5,h+26,90,8,4,4));
                              //fourth (from top) - l = 70
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+15*0.5,h+39,85,8,4,4));
                              g.setColor(new Color(102,102,102));
                              g.fill(new RoundRectangle2D.Double(w+15*0.5,h+39,85,8,4,4));
                              //fifth (from top) - l = 60
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+20*0.5,h+52,80,8,4,4));
                              g.setColor(new Color(102,102,102));
                              g.fill(new RoundRectangle2D.Double(w+20*0.5,h+52,80,8,4,4));
                              //sixth (from top) - l = 50
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+25*0.5,h+65,75,8,4,4));
                              g.setColor(new Color(102,102,102));
                              g.fill(new RoundRectangle2D.Double(w+25*0.5,h+65,75,8,4,4));
                              //seventh (from top) - l = 40
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+30*0.5,h+78,70,8,4,4));
                              g.setColor(new Color(102,102,102));
                              g.fill(new RoundRectangle2D.Double(w+30*0.5,h+78,70,8,4,4));
                              //eighth (from top) - l = 30
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+35*0.5,h+91,65,8,4,4));
                              g.setColor(new Color(102,102,102));
                              g.fill(new RoundRectangle2D.Double(w+35*0.5,h+91,65,8,4,4));
                              //ninth (from top) - l = 20
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+40*0.5,h+104,60,8,4,4));
                              g.setColor(new Color(102,102,102));
                              g.fill(new RoundRectangle2D.Double(w+40*0.5,h+104,60,8,4,4));  
                              //tenth (from top) - l = 10
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+45*0.5,h+117,55,8,4,4));
                              g.setColor(new Color(255,0,0));
                              g.fill(new RoundRectangle2D.Double(w+45*0.5,h+117,55,8,4,4));                            
                              break;
                  case 2:     //first (from top) - l = 100
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w,h,100,8,4,4));
                              g.setColor(new Color(102,102,102));
                              g.fill(new RoundRectangle2D.Double(w,h,100,8,4,4));
                              //second (from top) - l = 90
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+5*0.5,h+13,95,8,4,4));
                              g.setColor(new Color(102,102,102));
                              g.fill(new RoundRectangle2D.Double(w+5*0.5,h+13,95,8,4,4));
                              //third (from top) - l = 80
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+10*0.5,h+26,90,8,4,4));
                              g.setColor(new Color(102,102,102));
                              g.fill(new RoundRectangle2D.Double(w+10*0.5,h+26,90,8,4,4));
                              //fourth (from top) - l = 70
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+15*0.5,h+39,85,8,4,4));
                              g.setColor(new Color(102,102,102));
                              g.fill(new RoundRectangle2D.Double(w+15*0.5,h+39,85,8,4,4));
                              //fifth (from top) - l = 60
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+20*0.5,h+52,80,8,4,4));
                              g.setColor(new Color(102,102,102));
                              g.fill(new RoundRectangle2D.Double(w+20*0.5,h+52,80,8,4,4));
                              //sixth (from top) - l = 50
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+25*0.5,h+65,75,8,4,4));
                              g.setColor(new Color(102,102,102));
                              g.fill(new RoundRectangle2D.Double(w+25*0.5,h+65,75,8,4,4));
                              //seventh (from top) - l = 40
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+30*0.5,h+78,70,8,4,4));
                              g.setColor(new Color(102,102,102));
                              g.fill(new RoundRectangle2D.Double(w+30*0.5,h+78,70,8,4,4));
                              //eighth (from top) - l = 30
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+35*0.5,h+91,65,8,4,4));
                              g.setColor(new Color(102,102,102));
                              g.fill(new RoundRectangle2D.Double(w+35*0.5,h+91,65,8,4,4));
                              //ninth (from top) - l = 20
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+40*0.5,h+104,60,8,4,4));
                              g.setColor(new Color(255,0,0));
                              g.fill(new RoundRectangle2D.Double(w+40*0.5,h+104,60,8,4,4));  
                              //tenth (from top) - l = 10
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+45*0.5,h+117,55,8,4,4));
                              g.setColor(new Color(255,0,0));
                              g.fill(new RoundRectangle2D.Double(w+45*0.5,h+117,55,8,4,4));                            
                              break;
                  case 3:     //first (from top) - l = 100
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w,h,100,8,4,4));
                              g.setColor(new Color(102,102,102));
                              g.fill(new RoundRectangle2D.Double(w,h,100,8,4,4));
                              //second (from top) - l = 90
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+5*0.5,h+13,95,8,4,4));
                              g.setColor(new Color(102,102,102));
                              g.fill(new RoundRectangle2D.Double(w+5*0.5,h+13,95,8,4,4));
                              //third (from top) - l = 80
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+10*0.5,h+26,90,8,4,4));
                              g.setColor(new Color(102,102,102));
                              g.fill(new RoundRectangle2D.Double(w+10*0.5,h+26,90,8,4,4));
                              //fourth (from top) - l = 70
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+15*0.5,h+39,85,8,4,4));
                              g.setColor(new Color(102,102,102));
                              g.fill(new RoundRectangle2D.Double(w+15*0.5,h+39,85,8,4,4));
                              //fifth (from top) - l = 60
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+20*0.5,h+52,80,8,4,4));
                              g.setColor(new Color(102,102,102));
                              g.fill(new RoundRectangle2D.Double(w+20*0.5,h+52,80,8,4,4));
                              //sixth (from top) - l = 50
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+25*0.5,h+65,75,8,4,4));
                              g.setColor(new Color(102,102,102));
                              g.fill(new RoundRectangle2D.Double(w+25*0.5,h+65,75,8,4,4));
                              //seventh (from top) - l = 40
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+30*0.5,h+78,70,8,4,4));
                              g.setColor(new Color(102,102,102));
                              g.fill(new RoundRectangle2D.Double(w+30*0.5,h+78,70,8,4,4));
                              //eighth (from top) - l = 30
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+35*0.5,h+91,65,8,4,4));
                              g.setColor(new Color(255,0,0));
                              g.fill(new RoundRectangle2D.Double(w+35*0.5,h+91,65,8,4,4));
                              //ninth (from top) - l = 20
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+40*0.5,h+104,60,8,4,4));
                              g.setColor(new Color(255,0,0));
                              g.fill(new RoundRectangle2D.Double(w+40*0.5,h+104,60,8,4,4));  
                              //tenth (from top) - l = 10
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+45*0.5,h+117,55,8,4,4));
                              g.setColor(new Color(255,0,0));
                              g.fill(new RoundRectangle2D.Double(w+45*0.5,h+117,55,8,4,4));                            
                              break;
                  case 4:     //first (from top) - l = 100
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w,h,100,8,4,4));
                              g.setColor(new Color(102,102,102));
                              g.fill(new RoundRectangle2D.Double(w,h,100,8,4,4));
                              //second (from top) - l = 90
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+5*0.5,h+13,95,8,4,4));
                              g.setColor(new Color(102,102,102));
                              g.fill(new RoundRectangle2D.Double(w+5*0.5,h+13,95,8,4,4));
                              //third (from top) - l = 80
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+10*0.5,h+26,90,8,4,4));
                              g.setColor(new Color(102,102,102));
                              g.fill(new RoundRectangle2D.Double(w+10*0.5,h+26,90,8,4,4));
                              //fourth (from top) - l = 70
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+15*0.5,h+39,85,8,4,4));
                              g.setColor(new Color(102,102,102));
                              g.fill(new RoundRectangle2D.Double(w+15*0.5,h+39,85,8,4,4));
                              //fifth (from top) - l = 60
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+20*0.5,h+52,80,8,4,4));
                              g.setColor(new Color(102,102,102));
                              g.fill(new RoundRectangle2D.Double(w+20*0.5,h+52,80,8,4,4));
                              //sixth (from top) - l = 50
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+25*0.5,h+65,75,8,4,4));
                              g.setColor(new Color(102,102,102));
                              g.fill(new RoundRectangle2D.Double(w+25*0.5,h+65,75,8,4,4));
                              //seventh (from top) - l = 40
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+30*0.5,h+78,70,8,4,4));
                              g.setColor(new Color(255,0,0));
                              g.fill(new RoundRectangle2D.Double(w+30*0.5,h+78,70,8,4,4));
                              //eighth (from top) - l = 30
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+35*0.5,h+91,65,8,4,4));
                              g.setColor(new Color(255,0,0));
                              g.fill(new RoundRectangle2D.Double(w+35*0.5,h+91,65,8,4,4));
                              //ninth (from top) - l = 20
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+40*0.5,h+104,60,8,4,4));
                              g.setColor(new Color(255,0,0));
                              g.fill(new RoundRectangle2D.Double(w+40*0.5,h+104,60,8,4,4));  
                              //tenth (from top) - l = 10
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+45*0.5,h+117,55,8,4,4));
                              g.setColor(new Color(255,0,0));
                              g.fill(new RoundRectangle2D.Double(w+45*0.5,h+117,55,8,4,4));                            
                              break;
                  case 5:     //first (from top) - l = 100
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w,h,100,8,4,4));
                              g.setColor(new Color(102,102,102));
                              g.fill(new RoundRectangle2D.Double(w,h,100,8,4,4));
                              //second (from top) - l = 90
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+5*0.5,h+13,95,8,4,4));
                              g.setColor(new Color(102,102,102));
                              g.fill(new RoundRectangle2D.Double(w+5*0.5,h+13,95,8,4,4));
                              //third (from top) - l = 80
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+10*0.5,h+26,90,8,4,4));
                              g.setColor(new Color(102,102,102));
                              g.fill(new RoundRectangle2D.Double(w+10*0.5,h+26,90,8,4,4));
                              //fourth (from top) - l = 70
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+15*0.5,h+39,85,8,4,4));
                              g.setColor(new Color(102,102,102));
                              g.fill(new RoundRectangle2D.Double(w+15*0.5,h+39,85,8,4,4));
                              //fifth (from top) - l = 60
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+20*0.5,h+52,80,8,4,4));
                              g.setColor(new Color(102,102,102));
                              g.fill(new RoundRectangle2D.Double(w+20*0.5,h+52,80,8,4,4));
                              //sixth (from top) - l = 50
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+25*0.5,h+65,75,8,4,4));
                              g.setColor(new Color(255,0,0));
                              g.fill(new RoundRectangle2D.Double(w+25*0.5,h+65,75,8,4,4));
                              //seventh (from top) - l = 40
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+30*0.5,h+78,70,8,4,4));
                              g.setColor(new Color(255,0,0));
                              g.fill(new RoundRectangle2D.Double(w+30*0.5,h+78,70,8,4,4));
                              //eighth (from top) - l = 30
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+35*0.5,h+91,65,8,4,4));
                              g.setColor(new Color(255,0,0));
                              g.fill(new RoundRectangle2D.Double(w+35*0.5,h+91,65,8,4,4));
                              //ninth (from top) - l = 20
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+40*0.5,h+104,60,8,4,4));
                              g.setColor(new Color(255,0,0));
                              g.fill(new RoundRectangle2D.Double(w+40*0.5,h+104,60,8,4,4));  
                              //tenth (from top) - l = 10
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+45*0.5,h+117,55,8,4,4));
                              g.setColor(new Color(255,0,0));
                              g.fill(new RoundRectangle2D.Double(w+45*0.5,h+117,55,8,4,4));                            
                              break;
                  case 6:     //first (from top) - l = 100
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w,h,100,8,4,4));
                              g.setColor(new Color(102,102,102));
                              g.fill(new RoundRectangle2D.Double(w,h,100,8,4,4));
                              //second (from top) - l = 90
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+5*0.5,h+13,95,8,4,4));
                              g.setColor(new Color(102,102,102));
                              g.fill(new RoundRectangle2D.Double(w+5*0.5,h+13,95,8,4,4));
                              //third (from top) - l = 80
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+10*0.5,h+26,90,8,4,4));
                              g.setColor(new Color(102,102,102));
                              g.fill(new RoundRectangle2D.Double(w+10*0.5,h+26,90,8,4,4));
                              //fourth (from top) - l = 70
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+15*0.5,h+39,85,8,4,4));
                              g.setColor(new Color(102,102,102));
                              g.fill(new RoundRectangle2D.Double(w+15*0.5,h+39,85,8,4,4));
                              //fifth (from top) - l = 60
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+20*0.5,h+52,80,8,4,4));
                              g.setColor(new Color(255,0,0));
                              g.fill(new RoundRectangle2D.Double(w+20*0.5,h+52,80,8,4,4));
                              //sixth (from top) - l = 50
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+25*0.5,h+65,75,8,4,4));
                              g.setColor(new Color(255,0,0));
                              g.fill(new RoundRectangle2D.Double(w+25*0.5,h+65,75,8,4,4));
                              //seventh (from top) - l = 40
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+30*0.5,h+78,70,8,4,4));
                              g.setColor(new Color(255,0,0));
                              g.fill(new RoundRectangle2D.Double(w+30*0.5,h+78,70,8,4,4));
                              //eighth (from top) - l = 30
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+35*0.5,h+91,65,8,4,4));
                              g.setColor(new Color(255,0,0));
                              g.fill(new RoundRectangle2D.Double(w+35*0.5,h+91,65,8,4,4));
                              //ninth (from top) - l = 20
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+40*0.5,h+104,60,8,4,4));
                              g.setColor(new Color(255,0,0));
                              g.fill(new RoundRectangle2D.Double(w+40*0.5,h+104,60,8,4,4));  
                              //tenth (from top) - l = 10
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+45*0.5,h+117,55,8,4,4));
                              g.setColor(new Color(255,0,0));
                              g.fill(new RoundRectangle2D.Double(w+45*0.5,h+117,55,8,4,4));                            
                              break;
                  case 7:     //first (from top) - l = 100
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w,h,100,8,4,4));
                              g.setColor(new Color(102,102,102));
                              g.fill(new RoundRectangle2D.Double(w,h,100,8,4,4));
                              //second (from top) - l = 90
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+5*0.5,h+13,95,8,4,4));
                              g.setColor(new Color(102,102,102));
                              g.fill(new RoundRectangle2D.Double(w+5*0.5,h+13,95,8,4,4));
                              //third (from top) - l = 80
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+10*0.5,h+26,90,8,4,4));
                              g.setColor(new Color(102,102,102));
                              g.fill(new RoundRectangle2D.Double(w+10*0.5,h+26,90,8,4,4));
                              //fourth (from top) - l = 70
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+15*0.5,h+39,85,8,4,4));
                              g.setColor(new Color(255,0,0));
                              g.fill(new RoundRectangle2D.Double(w+15*0.5,h+39,85,8,4,4));
                              //fifth (from top) - l = 60
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+20*0.5,h+52,80,8,4,4));
                              g.setColor(new Color(255,0,0));
                              g.fill(new RoundRectangle2D.Double(w+20*0.5,h+52,80,8,4,4));
                              //sixth (from top) - l = 50
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+25*0.5,h+65,75,8,4,4));
                              g.setColor(new Color(255,0,0));
                              g.fill(new RoundRectangle2D.Double(w+25*0.5,h+65,75,8,4,4));
                              //seventh (from top) - l = 40
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+30*0.5,h+78,70,8,4,4));
                              g.setColor(new Color(255,0,0));
                              g.fill(new RoundRectangle2D.Double(w+30*0.5,h+78,70,8,4,4));
                              //eighth (from top) - l = 30
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+35*0.5,h+91,65,8,4,4));
                              g.setColor(new Color(255,0,0));
                              g.fill(new RoundRectangle2D.Double(w+35*0.5,h+91,65,8,4,4));
                              //ninth (from top) - l = 20
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+40*0.5,h+104,60,8,4,4));
                              g.setColor(new Color(255,0,0));
                              g.fill(new RoundRectangle2D.Double(w+40*0.5,h+104,60,8,4,4));  
                              //tenth (from top) - l = 10
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+45*0.5,h+117,55,8,4,4));
                              g.setColor(new Color(255,0,0));
                              g.fill(new RoundRectangle2D.Double(w+45*0.5,h+117,55,8,4,4));                            
                              break;
                  case 8:     //first (from top) - l = 100
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w,h,100,8,4,4));
                              g.setColor(new Color(102,102,102));
                              g.fill(new RoundRectangle2D.Double(w,h,100,8,4,4));
                              //second (from top) - l = 90
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+5*0.5,h+13,95,8,4,4));
                              g.setColor(new Color(102,102,102));
                              g.fill(new RoundRectangle2D.Double(w+5*0.5,h+13,95,8,4,4));
                              //third (from top) - l = 80
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+10*0.5,h+26,90,8,4,4));
                              g.setColor(new Color(255,0,0));
                              g.fill(new RoundRectangle2D.Double(w+10*0.5,h+26,90,8,4,4));
                              //fourth (from top) - l = 70
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+15*0.5,h+39,85,8,4,4));
                              g.setColor(new Color(255,0,0));
                              g.fill(new RoundRectangle2D.Double(w+15*0.5,h+39,85,8,4,4));
                              //fifth (from top) - l = 60
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+20*0.5,h+52,80,8,4,4));
                              g.setColor(new Color(255,0,0));
                              g.fill(new RoundRectangle2D.Double(w+20*0.5,h+52,80,8,4,4));
                              //sixth (from top) - l = 50
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+25*0.5,h+65,75,8,4,4));
                              g.setColor(new Color(255,0,0));
                              g.fill(new RoundRectangle2D.Double(w+25*0.5,h+65,75,8,4,4));
                              //seventh (from top) - l = 40
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+30*0.5,h+78,70,8,4,4));
                              g.setColor(new Color(255,0,0));
                              g.fill(new RoundRectangle2D.Double(w+30*0.5,h+78,70,8,4,4));
                              //eighth (from top) - l = 30
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+35*0.5,h+91,65,8,4,4));
                              g.setColor(new Color(255,0,0));
                              g.fill(new RoundRectangle2D.Double(w+35*0.5,h+91,65,8,4,4));
                              //ninth (from top) - l = 20
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+40*0.5,h+104,60,8,4,4));
                              g.setColor(new Color(255,0,0));
                              g.fill(new RoundRectangle2D.Double(w+40*0.5,h+104,60,8,4,4));  
                              //tenth (from top) - l = 10
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+45*0.5,h+117,55,8,4,4));
                              g.setColor(new Color(255,0,0));
                              g.fill(new RoundRectangle2D.Double(w+45*0.5,h+117,55,8,4,4));                            
                              break;
                  case 9:     //first (from top) - l = 100
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w,h,100,8,4,4));
                              g.setColor(new Color(102,102,102));
                              g.fill(new RoundRectangle2D.Double(w,h,100,8,4,4));
                              //second (from top) - l = 90
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+5*0.5,h+13,95,8,4,4));
                              g.setColor(new Color(255,0,0));
                              g.fill(new RoundRectangle2D.Double(w+5*0.5,h+13,95,8,4,4));
                              //third (from top) - l = 80
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+10*0.5,h+26,90,8,4,4));
                              g.setColor(new Color(255,0,0));
                              g.fill(new RoundRectangle2D.Double(w+10*0.5,h+26,90,8,4,4));
                              //fourth (from top) - l = 70
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+15*0.5,h+39,85,8,4,4));
                              g.setColor(new Color(255,0,0));
                              g.fill(new RoundRectangle2D.Double(w+15*0.5,h+39,85,8,4,4));
                              //fifth (from top) - l = 60
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+20*0.5,h+52,80,8,4,4));
                              g.setColor(new Color(255,0,0));
                              g.fill(new RoundRectangle2D.Double(w+20*0.5,h+52,80,8,4,4));
                              //sixth (from top) - l = 50
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+25*0.5,h+65,75,8,4,4));
                              g.setColor(new Color(255,0,0));
                              g.fill(new RoundRectangle2D.Double(w+25*0.5,h+65,75,8,4,4));
                              //seventh (from top) - l = 40
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+30*0.5,h+78,70,8,4,4));
                              g.setColor(new Color(255,0,0));
                              g.fill(new RoundRectangle2D.Double(w+30*0.5,h+78,70,8,4,4));
                              //eighth (from top) - l = 30
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+35*0.5,h+91,65,8,4,4));
                              g.setColor(new Color(255,0,0));
                              g.fill(new RoundRectangle2D.Double(w+35*0.5,h+91,65,8,4,4));
                              //ninth (from top) - l = 20
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+40*0.5,h+104,60,8,4,4));
                              g.setColor(new Color(255,0,0));
                              g.fill(new RoundRectangle2D.Double(w+40*0.5,h+104,60,8,4,4));  
                              //tenth (from top) - l = 10
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+45*0.5,h+117,55,8,4,4));
                              g.setColor(new Color(255,0,0));
                              g.fill(new RoundRectangle2D.Double(w+45*0.5,h+117,55,8,4,4));                            
                              break;
                  case 10:     //first (from top) - l = 100
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w,h,100,8,4,4));
                              g.setColor(new Color(255,0,0));
                              g.fill(new RoundRectangle2D.Double(w,h,100,8,4,4));
                              //second (from top) - l = 90
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+5*0.5,h+13,95,8,4,4));
                              g.setColor(new Color(255,0,0));
                              g.fill(new RoundRectangle2D.Double(w+5*0.5,h+13,95,8,4,4));
                              //third (from top) - l = 80
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+10*0.5,h+26,90,8,4,4));
                              g.setColor(new Color(255,0,0));
                              g.fill(new RoundRectangle2D.Double(w+10*0.5,h+26,90,8,4,4));
                              //fourth (from top) - l = 70
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+15*0.5,h+39,85,8,4,4));
                              g.setColor(new Color(255,0,0));
                              g.fill(new RoundRectangle2D.Double(w+15*0.5,h+39,85,8,4,4));
                              //fifth (from top) - l = 60
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+20*0.5,h+52,80,8,4,4));
                              g.setColor(new Color(255,0,0));
                              g.fill(new RoundRectangle2D.Double(w+20*0.5,h+52,80,8,4,4));
                              //sixth (from top) - l = 50
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+25*0.5,h+65,75,8,4,4));
                              g.setColor(new Color(255,0,0));
                              g.fill(new RoundRectangle2D.Double(w+25*0.5,h+65,75,8,4,4));
                              //seventh (from top) - l = 40
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+30*0.5,h+78,70,8,4,4));
                              g.setColor(new Color(255,0,0));
                              g.fill(new RoundRectangle2D.Double(w+30*0.5,h+78,70,8,4,4));
                              //eighth (from top) - l = 30
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+35*0.5,h+91,65,8,4,4));
                              g.setColor(new Color(255,0,0));
                              g.fill(new RoundRectangle2D.Double(w+35*0.5,h+91,65,8,4,4));
                              //ninth (from top) - l = 20
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+40*0.5,h+104,60,8,4,4));
                              g.setColor(new Color(255,0,0));
                              g.fill(new RoundRectangle2D.Double(w+40*0.5,h+104,60,8,4,4));  
                              //tenth (from top) - l = 10
                              g.setColor(Color.BLACK);
                              g.draw(new RoundRectangle2D.Double(w+45*0.5,h+117,55,8,4,4));
                              g.setColor(new Color(255,0,0));
                              g.fill(new RoundRectangle2D.Double(w+45*0.5,h+117,55,8,4,4));                            
                              break;
                  default:    num = 30;
                              draw();
            }
      }
      
 }    
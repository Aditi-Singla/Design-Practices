import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;

public class lives extends JPanel{
      int miss[];
      Graphics2D g;
      
      int w,h;
      public lives(int width,int height) {
            w = width;
            h = height;
            miss = new int[4];
            miss[0] = 0;                  //top
            miss[1] = 0;                  //right
            miss[2] = 0;                  //bottom
            miss[3] = 0;                  //left
      }

      public void paintComponent(Graphics g1) {
      	super.paintComponent(g1);
      	g = (Graphics2D) g1.create();
      	g.setStroke(new BasicStroke(2.0f));
      	//central circles to show number of lives remaining

	      //top
	      top(miss[0]);
	      //left
	      left(miss[3]);
	      //right
	      right(miss[1]);
	      //bottom
	      bottom(miss[2]);
	      
	      

	      //fourth circle - r = 30
	      g.setColor(Color.BLACK);
	      g.draw(new Ellipse2D.Double((w+270), (h+270), 60, 60));     
	      g.setColor(new Color(51,102,153));
	      g.fill(new Ellipse2D.Double((w+270), (h+270), 60, 60));    

      }

      /*Setting central segment red in case of a miss*/
      public void setMiss(int player) {                           //Player0 - top, Player1 - right, Player2 - bottom, Player3 - left

            miss[player] += 1;
            if (miss[player] == 4) {
                  miss[player] = 3;
            }
            repaint();
      }


      /*When player0 misses a ball*/
      public void top(int n) {
            
            switch(n) {
                  case 0:     //outer circle - r = 75
                              g.setColor(Color.BLACK);
                              g.draw(new Arc2D.Double((w+225), (h+225), 150, 150, 45, 90, Arc2D.PIE));     //top
                              g.setColor(new Color(255,230,179));
                              g.fill(new Arc2D.Double((w+225), (h+225), 150, 150, 45, 90, Arc2D.PIE));     //top
                              //second circle - r = 60
                              g.setColor(Color.BLACK);
                              g.draw(new Arc2D.Double((w+240), (h+240), 120, 120, 45, 90, Arc2D.PIE));     //top
                              g.setColor(new Color(255,230,179));
                              g.fill(new Arc2D.Double((w+240), (h+240), 120, 120, 45, 90, Arc2D.PIE));     //top
                              //third circle - r = 45
                              g.setColor(Color.BLACK);
                              g.draw(new Arc2D.Double((w+255), (h+255), 90, 90, 45, 90, Arc2D.PIE));     //top
                              g.setColor(new Color(255,230,179));
                              g.fill(new Arc2D.Double((w+255), (h+255), 90, 90, 45, 90, Arc2D.PIE));     //top
                              break;
                  case 1:     //outer circle - r = 75
                              g.setColor(Color.BLACK);
                              g.draw(new Arc2D.Double((w+225), (h+225), 150, 150, 45, 90, Arc2D.PIE));     //top
                              g.setColor(new Color(255,230,179));
                              g.fill(new Arc2D.Double((w+225), (h+225), 150, 150, 45, 90, Arc2D.PIE));     //top
                              //second circle - r = 60
                              g.setColor(Color.BLACK);
                              g.draw(new Arc2D.Double((w+240), (h+240), 120, 120, 45, 90, Arc2D.PIE));     //top
                              g.setColor(new Color(255,230,179));
                              g.fill(new Arc2D.Double((w+240), (h+240), 120, 120, 45, 90, Arc2D.PIE));     //top
                              //third circle - r = 45
                              g.setColor(Color.BLACK);
                              g.draw(new Arc2D.Double((w+255), (h+255), 90, 90, 45, 90, Arc2D.PIE));     //top
                              g.setColor(new Color(255,0,0));
                              g.fill(new Arc2D.Double((w+255), (h+255), 90, 90, 45, 90, Arc2D.PIE));     //top
                              break;
                  case 2:     //outer circle - r = 75
                              g.setColor(Color.BLACK);
                              g.draw(new Arc2D.Double((w+225), (h+225), 150, 150, 45, 90, Arc2D.PIE));     //top
                              g.setColor(new Color(255,230,179));
                              g.fill(new Arc2D.Double((w+225), (h+225), 150, 150, 45, 90, Arc2D.PIE));     //top
                              //second circle - r = 60
                              g.setColor(Color.BLACK);
                              g.draw(new Arc2D.Double((w+240), (h+240), 120, 120, 45, 90, Arc2D.PIE));     //top
                              g.setColor(new Color(255,0,0));
                              g.fill(new Arc2D.Double((w+240), (h+240), 120, 120, 45, 90, Arc2D.PIE));     //top
                              //third circle - r = 45
                              g.setColor(Color.BLACK);
                              g.draw(new Arc2D.Double((w+255), (h+255), 90, 90, 45, 90, Arc2D.PIE));     //top
                              g.setColor(new Color(255,0,0));
                              g.fill(new Arc2D.Double((w+255), (h+255), 90, 90, 45, 90, Arc2D.PIE));     //top
                              break;
                  case 3:     //outer circle - r = 75
                              g.setColor(Color.BLACK);
                              g.draw(new Arc2D.Double((w+225), (h+225), 150, 150, 45, 90, Arc2D.PIE));     //top
                              g.setColor(new Color(255,0,0));
                              g.fill(new Arc2D.Double((w+225), (h+225), 150, 150, 45, 90, Arc2D.PIE));     //top
                              //second circle - r = 60
                              g.setColor(Color.BLACK);
                              g.draw(new Arc2D.Double((w+240), (h+240), 120, 120, 45, 90, Arc2D.PIE));     //top
                              g.setColor(new Color(255,0,0));
                              g.fill(new Arc2D.Double((w+240), (h+240), 120, 120, 45, 90, Arc2D.PIE));     //top
                              //third circle - r = 45
                              g.setColor(Color.BLACK);
                              g.draw(new Arc2D.Double((w+255), (h+255), 90, 90, 45, 90, Arc2D.PIE));     //top
                              g.setColor(new Color(255,0,0));
                              g.fill(new Arc2D.Double((w+255), (h+255), 90, 90, 45, 90, Arc2D.PIE));     //top
                              break;
                  default:    top(3);
            }
      }

      /*When player1 misses a ball*/
      public void right(int n) {
            
            switch(n) {
                  case 0:     //outer circle - r = 75
                              g.setColor(Color.BLACK);
                              g.draw(new Arc2D.Double((w+225), (h+225), 150, 150, -45, 90, Arc2D.PIE));     //right
                              g.setColor(new Color(255,230,179));
                              g.fill(new Arc2D.Double((w+225), (h+225), 150, 150, -45, 90, Arc2D.PIE));     //right
                              //second circle - r = 60
                              g.setColor(Color.BLACK);
                              g.draw(new Arc2D.Double((w+240), (h+240), 120, 120, -45, 90, Arc2D.PIE));     //right
                              g.setColor(new Color(255,230,179));
                              g.fill(new Arc2D.Double((w+240), (h+240), 120, 120, -45, 90, Arc2D.PIE));     //right
                              //third circle - r = 45
                              g.setColor(Color.BLACK);
                              g.draw(new Arc2D.Double((w+255), (h+255), 90, 90, -45, 90, Arc2D.PIE));     //right
                              g.setColor(new Color(255,230,179));
                              g.fill(new Arc2D.Double((w+255), (h+255), 90, 90, -45, 90, Arc2D.PIE));     //right
                              break;
                  case 1:     //outer circle - r = 75
                              g.setColor(Color.BLACK);
                              g.draw(new Arc2D.Double((w+225), (h+225), 150, 150, -45, 90, Arc2D.PIE));     //right
                              g.setColor(new Color(255,230,179));
                              g.fill(new Arc2D.Double((w+225), (h+225), 150, 150, -45, 90, Arc2D.PIE));     //right
                              //second circle - r = 60
                              g.setColor(Color.BLACK);
                              g.draw(new Arc2D.Double((w+240), (h+240), 120, 120, -45, 90, Arc2D.PIE));     //right
                              g.setColor(new Color(255,230,179));
                              g.fill(new Arc2D.Double((w+240), (h+240), 120, 120, -45, 90, Arc2D.PIE));     //right
                              //third circle - r = 45
                              g.setColor(Color.BLACK);
                              g.draw(new Arc2D.Double((w+255), (h+255), 90, 90, -45, 90, Arc2D.PIE));     //right
                              g.setColor(new Color(255,0,0));
                              g.fill(new Arc2D.Double((w+255), (h+255), 90, 90, -45, 90, Arc2D.PIE));     //right
                              break;
                  case 2:     //outer circle - r = 75
                              g.setColor(Color.BLACK);
                              g.draw(new Arc2D.Double((w+225), (h+225), 150, 150, -45, 90, Arc2D.PIE));     //right
                              g.setColor(new Color(255,230,179));
                              g.fill(new Arc2D.Double((w+225), (h+225), 150, 150, -45, 90, Arc2D.PIE));     //right
                              //second circle - r = 60
                              g.setColor(Color.BLACK);
                              g.draw(new Arc2D.Double((w+240), (h+240), 120, 120, -45, 90, Arc2D.PIE));     //right
                              g.setColor(new Color(255,0,0));
                              g.fill(new Arc2D.Double((w+240), (h+240), 120, 120, -45, 90, Arc2D.PIE));     //right
                              //third circle - r = 45
                              g.setColor(Color.BLACK);
                              g.draw(new Arc2D.Double((w+255), (h+255), 90, 90, -45, 90, Arc2D.PIE));     //right
                              g.setColor(new Color(255,0,0));
                              g.fill(new Arc2D.Double((w+255), (h+255), 90, 90, -45, 90, Arc2D.PIE));     //right
                              break;
                  case 3:    //outer circle - r = 75
                              g.setColor(Color.BLACK);
                              g.draw(new Arc2D.Double((w+225), (h+225), 150, 150, -45, 90, Arc2D.PIE));     //right
                              g.setColor(new Color(255,0,0));
                              g.fill(new Arc2D.Double((w+225), (h+225), 150, 150, -45, 90, Arc2D.PIE));     //right
                              //second circle - r = 60
                              g.setColor(Color.BLACK);
                              g.draw(new Arc2D.Double((w+240), (h+240), 120, 120, -45, 90, Arc2D.PIE));     //right
                              g.setColor(new Color(255,0,0));
                              g.fill(new Arc2D.Double((w+240), (h+240), 120, 120, -45, 90, Arc2D.PIE));     //right
                              //third circle - r = 45
                              g.setColor(Color.BLACK);
                              g.draw(new Arc2D.Double((w+255), (h+255), 90, 90, -45, 90, Arc2D.PIE));     //right
                              g.setColor(new Color(255,0,0));
                              g.fill(new Arc2D.Double((w+255), (h+255), 90, 90, -45, 90, Arc2D.PIE));     //right
                              break;
                  default:    right(3);
            }
      }

      /*When player2 misses a ball*/
      public void bottom(int n) {
            
            switch(n) {
                  case 0:     //outer circle - r = 75
                              g.setColor(Color.BLACK);
                              g.draw(new Arc2D.Double((w+225), (h+225), 150, 150, -135, 90, Arc2D.PIE));     //bottom
                              g.setColor(new Color(255,230,179));
                              g.fill(new Arc2D.Double((w+225), (h+225), 150, 150, -135, 90, Arc2D.PIE));     //bottom
                              //second circle - r = 60
                              g.setColor(Color.BLACK);
                              g.draw(new Arc2D.Double((w+240), (h+240), 120, 120, -135, 90, Arc2D.PIE));     //bottom
                              g.setColor(new Color(255,230,179));
                              g.fill(new Arc2D.Double((w+240), (h+240), 120, 120, -135, 90, Arc2D.PIE));     //bottom
                              //third circle - r = 45
                              g.setColor(Color.BLACK);
                              g.draw(new Arc2D.Double((w+255), (h+255), 90, 90, -135, 90, Arc2D.PIE));     //bottom
                              g.setColor(new Color(255,230,179));
                              g.fill(new Arc2D.Double((w+255), (h+255), 90, 90, -135, 90, Arc2D.PIE));     //bottom
                              break;
                  case 1:     //outer circle - r = 75
                              g.setColor(Color.BLACK);
                              g.draw(new Arc2D.Double((w+225), (h+225), 150, 150, -135, 90, Arc2D.PIE));     //bottom
                              g.setColor(new Color(255,230,179));
                              g.fill(new Arc2D.Double((w+225), (h+225), 150, 150, -135, 90, Arc2D.PIE));     //bottom
                              //second circle - r = 60
                              g.setColor(Color.BLACK);
                              g.draw(new Arc2D.Double((w+240), (h+240), 120, 120, -135, 90, Arc2D.PIE));     //bottom
                              g.setColor(new Color(255,230,179));
                              g.fill(new Arc2D.Double((w+240), (h+240), 120, 120, -135, 90, Arc2D.PIE));     //bottom
                              //third circle - r = 45
                              g.setColor(Color.BLACK);
                              g.draw(new Arc2D.Double((w+255), (h+255), 90, 90, -135, 90, Arc2D.PIE));     //bottom
                              g.setColor(new Color(255,0,0));
                              g.fill(new Arc2D.Double((w+255), (h+255), 90, 90, -135, 90, Arc2D.PIE));     //bottom
                              break;
                  case 2:     //outer circle - r = 75
                              g.setColor(Color.BLACK);
                              g.draw(new Arc2D.Double((w+225), (h+225), 150, 150, -135, 90, Arc2D.PIE));     //bottom
                              g.setColor(new Color(255,230,179));
                              g.fill(new Arc2D.Double((w+225), (h+225), 150, 150, -135, 90, Arc2D.PIE));     //bottom
                              //second circle - r = 60
                              g.setColor(Color.BLACK);
                              g.draw(new Arc2D.Double((w+240), (h+240), 120, 120, -135, 90, Arc2D.PIE));     //bottom
                              g.setColor(new Color(255,0,0));
                              g.fill(new Arc2D.Double((w+240), (h+240), 120, 120, -135, 90, Arc2D.PIE));     //bottom
                              //third circle - r = 45
                              g.setColor(Color.BLACK);
                              g.draw(new Arc2D.Double((w+255), (h+255), 90, 90, -135, 90, Arc2D.PIE));     //bottom
                              g.setColor(new Color(255,0,0));
                              g.fill(new Arc2D.Double((w+255), (h+255), 90, 90, -135, 90, Arc2D.PIE));     //bottom
                              break;
                  case 3:    //outer circle - r = 75
                              g.setColor(Color.BLACK);
                              g.draw(new Arc2D.Double((w+225), (h+225), 150, 150, -135, 90, Arc2D.PIE));     //bottom
                              g.setColor(new Color(255,0,0));
                              g.fill(new Arc2D.Double((w+225), (h+225), 150, 150, -135, 90, Arc2D.PIE));     //bottom
                              //second circle - r = 60
                              g.setColor(Color.BLACK);
                              g.draw(new Arc2D.Double((w+240), (h+240), 120, 120, -135, 90, Arc2D.PIE));     //bottom
                              g.setColor(new Color(255,0,0));
                              g.fill(new Arc2D.Double((w+240), (h+240), 120, 120, -135, 90, Arc2D.PIE));     //bottom
                              //third circle - r = 45
                              g.setColor(Color.BLACK);
                              g.draw(new Arc2D.Double((w+255), (h+255), 90, 90, -135, 90, Arc2D.PIE));     //bottom
                              g.setColor(new Color(255,0,0));
                              g.fill(new Arc2D.Double((w+255), (h+255), 90, 90, -135, 90, Arc2D.PIE));     //bottom
                              break;
                  default:    bottom(3);
            }
      }

      /*When player3 misses a ball*/
      public void left(int n) {
            
            switch(n) {
                  case 0:     //outer circle - r = 75
                              g.setColor(Color.BLACK);
                              g.draw(new Arc2D.Double((w+225), (h+225), 150, 150, 135, 90, Arc2D.PIE));     //left
                              g.setColor(new Color(255,230,179));
                              g.fill(new Arc2D.Double((w+225), (h+225), 150, 150, 135, 90, Arc2D.PIE));     //left
                              //second circle - r = 60
                              g.setColor(Color.BLACK);
                              g.draw(new Arc2D.Double((w+240), (h+240), 120, 120, 135, 90, Arc2D.PIE));     //left
                              g.setColor(new Color(255,230,179));
                              g.fill(new Arc2D.Double((w+240), (h+240), 120, 120, 135, 90, Arc2D.PIE));     //left
                              //third circle - r = 45
                              g.setColor(Color.BLACK);
                              g.draw(new Arc2D.Double((w+255), (h+255), 90, 90, 135, 90, Arc2D.PIE));     //left
                              g.setColor(new Color(255,230,179));
                              g.fill(new Arc2D.Double((w+255), (h+255), 90, 90, 135, 90, Arc2D.PIE));     //left
                              break;
                  case 1:     //outer circle - r = 75
                              g.setColor(Color.BLACK);
                              g.draw(new Arc2D.Double((w+225), (h+225), 150, 150, 135, 90, Arc2D.PIE));     //left
                              g.setColor(new Color(255,230,179));
                              g.fill(new Arc2D.Double((w+225), (h+225), 150, 150, 135, 90, Arc2D.PIE));     //left
                              //second circle - r = 60
                              g.setColor(Color.BLACK);
                              g.draw(new Arc2D.Double((w+240), (h+240), 120, 120, 135, 90, Arc2D.PIE));     //left
                              g.setColor(new Color(255,230,179));
                              g.fill(new Arc2D.Double((w+240), (h+240), 120, 120, 135, 90, Arc2D.PIE));     //left
                              //third circle - r = 45
                              g.setColor(Color.BLACK);
                              g.draw(new Arc2D.Double((w+255), (h+255), 90, 90, 135, 90, Arc2D.PIE));     //left
                              g.setColor(new Color(255,0,0));
                              g.fill(new Arc2D.Double((w+255), (h+255), 90, 90, 135, 90, Arc2D.PIE));     //left
                              break;
                  case 2:     //outer circle - r = 75
                              g.setColor(Color.BLACK);
                              g.draw(new Arc2D.Double((w+225), (h+225), 150, 150, 135, 90, Arc2D.PIE));     //left
                              g.setColor(new Color(255,230,179));
                              g.fill(new Arc2D.Double((w+225), (h+225), 150, 150, 135, 90, Arc2D.PIE));     //left
                              //second circle - r = 60
                              g.setColor(Color.BLACK);
                              g.draw(new Arc2D.Double((w+240), (h+240), 120, 120, 135, 90, Arc2D.PIE));     //left
                              g.setColor(new Color(255,0,0));
                              g.fill(new Arc2D.Double((w+240), (h+240), 120, 120, 135, 90, Arc2D.PIE));     //left
                              //third circle - r = 45
                              g.setColor(Color.BLACK);
                              g.draw(new Arc2D.Double((w+255), (h+255), 90, 90, 135, 90, Arc2D.PIE));     //left
                              g.setColor(new Color(255,0,0));
                              g.fill(new Arc2D.Double((w+255), (h+255), 90, 90, 135, 90, Arc2D.PIE));     //left
                              break;
                  case 3:    //outer circle - r = 75
                              g.setColor(Color.BLACK);
                              g.draw(new Arc2D.Double((w+225), (h+225), 150, 150, 135, 90, Arc2D.PIE));     //left
                              g.setColor(new Color(255,0,0));
                              g.fill(new Arc2D.Double((w+225), (h+225), 150, 150, 135, 90, Arc2D.PIE));     //left
                              //second circle - r = 60
                              g.setColor(Color.BLACK);
                              g.draw(new Arc2D.Double((w+240), (h+240), 120, 120, 135, 90, Arc2D.PIE));     //left
                              g.setColor(new Color(255,0,0));
                              g.fill(new Arc2D.Double((w+240), (h+240), 120, 120, 135, 90, Arc2D.PIE));     //left
                              //third circle - r = 45
                              g.setColor(Color.BLACK);
                              g.draw(new Arc2D.Double((w+255), (h+255), 90, 90, 135, 90, Arc2D.PIE));     //left
                              g.setColor(new Color(255,0,0));
                              g.fill(new Arc2D.Double((w+255), (h+255), 90, 90, 135, 90, Arc2D.PIE));     //left
                              break;
                  default:    left(3);
            }
      }
      
 }    
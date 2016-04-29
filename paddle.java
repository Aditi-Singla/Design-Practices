import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;

public class paddle extends JPanel {

	double w,h;
	double x ,vel = 0,acc = 1,y;
	boolean isH;

	public paddle(double width,double height,boolean isHorizontal) {
		h = height;
		w = width;
		isH = isHorizontal;
		if(isH) {
			x = width + 25 + 450 * 0.5;
			y = height;
		}
		else {
			x = width;
			y = (height + 25) + 450 * 0.5;
		}
	}


	/*Drawing the paddle*/
	public void paintComponent(Graphics g1) {
		super.paintComponent(g1);
		Graphics2D g = (Graphics2D) g1;

		if(isH) {
			g.setColor(Color.BLACK);
			g.setStroke(new BasicStroke(2.0f));
			g.draw(new RoundRectangle2D.Double(x,y,100,25,12.5,12.5));
			g.setColor(new Color(204,0,0));
			g.fill(new RoundRectangle2D.Double(x,y,100,25,12.5,12.5));

			g.setColor(Color.BLACK);
			g.setStroke(new BasicStroke(2.0f));
			g.draw(new RoundRectangle2D.Double(x+15,y+6,70,13,12.5,12.5));
			g.setColor(new Color(167,0,0));
			g.fill(new RoundRectangle2D.Double(x+15,y+6,70,13,12.5,12.5));
		}
		else {
			g.setColor(Color.BLACK);
			g.setStroke(new BasicStroke(2.0f));
			g.draw(new RoundRectangle2D.Double(x,y,25,100,12.5,12.5));
			g.setColor(new Color(204,0,0));
			g.fill(new RoundRectangle2D.Double(x,y,25,100,12.5,12.5));

			g.setColor(Color.BLACK);
			g.setStroke(new BasicStroke(2.0f));
			g.draw(new RoundRectangle2D.Double(x+6,y+15,13,70,12.5,12.5));
			g.setColor(new Color(167,0,0));
			g.fill(new RoundRectangle2D.Double(x+6,y+15,13,70,12.5,12.5));
		} 

	}

	/*Moving the paddle_h according to its velocity*/
	public void move() {
		repaint();
		vel = (vel != 0)?(vel + acc):0;
		if(isH) {
			x += (vel );
			if (x < (w + 25) ) {
				x = (w + 25);
				vel = 0;
			}
			else if (x > (w + 475)) {
				x = (w + 475);
				vel = 0;
			}
		}

		else {
			y += (vel );
			if (y < (h + 25) ) {
				y = (h + 25);
				vel = 0;
			}
			else if (y > (h + 475)) {
				y = (h + 475);
				vel = 0;
			}
		}

	}
}
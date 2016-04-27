import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;

public class paddle_v extends JPanel {
	
	double y = 250 + 450 * 0.5,vel = 0,acc = 0,x;

	public paddle_v(double width) {
		x = width;
		
	}


	/*Drawing the paddle_v*/
	public void paintComponent(Graphics g1) {
		super.paintComponent(g1);
		Graphics2D g = (Graphics2D) g1;

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

	/*Moving the paddle_v according to its velocity*/
	public void move() {
		repaint();
		y += (vel + acc);
		if (y < 250 )
			y = 250;
		else if (y > 700)
			y = 700;
	}


	
}
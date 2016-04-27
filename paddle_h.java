import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;

public class paddle_h extends JPanel {

	int width;
	double x ,vel = 0,acc = 0,y;

	public paddle_h(double height,int w) {
		y = height;
		width = w;
		x = width + 25 + 450 * 0.5;
	}


	/*Drawing the paddle_h*/
	public void paintComponent(Graphics g1) {
		super.paintComponent(g1);
		Graphics2D g = (Graphics2D) g1;

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

	/*Moving the paddle_h according to its velocity*/
	public void move() {
		repaint();
		x += (vel + acc);
		if (x < (width + 25) )
			x = (width + 25);
		else if (x > (width + 475))
			x = (width + 475);
	}
}
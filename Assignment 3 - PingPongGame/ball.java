import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;

public class ball extends JPanel  {
	

	double x ,y ,velx = Math.sqrt(457.96),vely = 0;
	//velx = -21.4 + 42.8 * Math.random(),vely = ((2 * (int)(2 * Math.random()))-1) * Math.sqrt(457.96 - velx * velx);
	int w,h;
	public ball(int width, int height) {
		w = width;
		h = height;
		x = w*1.0 + 285;
		y = h*1.0 + 285;
	}
	
	public void paintComponent(Graphics g1) {
		super.paintComponent(g1);
		Graphics2D g = (Graphics2D) g1;
		//ball - r = 15
		if (x >= w+570)      //right wall
	        x = w+570;
		else if (x <= w)     //left wall
		    x = w;
		if (y >= h+570)      //bottom wall
		    y = h+570;
		else if (y <= h)     //top wall
		    y = h;
	    g.setColor(Color.BLACK);
	    g.draw(new Ellipse2D.Double(x, y, 30, 30));     
	    g.setColor(new Color(191,191,191));
	    g.fill(new Ellipse2D.Double(x, y, 30, 30));    
	}

	

	
}
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;

public class ball extends JPanel  {
	
	double x = 1035,y = 510,velx = -13 + 26 * Math.random(),vely = ((2 * (int)(2 * Math.random()))-1) * Math.sqrt(169 - velx * velx);

	
	public void paintComponent(Graphics g1) {
		super.paintComponent(g1);
		Graphics2D g = (Graphics2D) g1;
		//ball - r = 15
	    g.setColor(Color.BLACK);
	    g.draw(new Ellipse2D.Double(x, y, 30, 30));     
	    g.setColor(new Color(191,191,191));
	    g.fill(new Ellipse2D.Double(x, y, 30, 30));    
	}

	

	
}
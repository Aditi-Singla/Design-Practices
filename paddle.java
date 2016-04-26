import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;

public class paddle extends JPanel implements ActionListener, KeyListener {
	Timer t = new Timer(3,this);
	double x = 775 + 450 * 0.5,vel = 0,acc = 0;

	public paddle() {
		t.start();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
	}

	public void paintComponent(Graphics g1) {
		super.paintComponent(g1);
		Graphics2D g = (Graphics2D) g1;
		g.setColor(Color.BLACK);
		g.setStroke(new BasicStroke(2.0f));
		g.draw(new RoundRectangle2D.Double(x,800,100,25,12.5,12.5));
		g.setColor(new Color(204,0,0));
		g.fill(new RoundRectangle2D.Double(x,800,100,25,12.5,12.5));
		g.setColor(Color.BLACK);
		g.setStroke(new BasicStroke(2.0f));
		g.draw(new RoundRectangle2D.Double(x+15,800+6,70,13,12.5,12.5));
		g.setColor(new Color(167,0,0));
		g.fill(new RoundRectangle2D.Double(x+15,800+6,70,13,12.5,12.5));
	}

	public void actionPerformed(ActionEvent e) {
		repaint();
		x += (vel + acc);
		if (x < 775 )
			x = 775;
		else if (x > 1225)
			x = 1225;
	}

	public void left() {
		if (vel > 0 )
			vel = 0;
		vel -= 3.0;
	}

	public void right() {
		if (vel < 0)
			vel = 0;
		vel += 3.0;
	}

	public void stop() {
		vel = 0;
	}

	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_LEFT)
			left();
		else if (code == KeyEvent.VK_RIGHT)
			right();
	}

	public void keyTyped(KeyEvent e) {}

	public void keyReleased(KeyEvent e) {
		stop();
	}
}
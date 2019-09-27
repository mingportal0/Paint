import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
import java.util.Vector;
import javax.swing.*;

class Info implements Serializable{ //dist 지우개-연필-선-원-사각형(1-1-2-3)
	private int x,y,x1,y1;
	private int dist = 1;
	public Color color = Color.black;
	public int thickness = 10;
	public int getThick() {return thickness;}
	public void setThick(int thickness) {this.thickness = thickness;}
	public Color getColor() {return color;}
	public void setColor(Color color) {this.color = color;}
	public int getDist() {return dist;}
	public void setDist(int dist) {this.dist = dist;}
	public int getX() {return x;}
	public void setX(int x) {this.x = x;}
	public int getX1() {return x1;}
	public void setX1(int x1) {this.x1 = x1;}
	public int getY() {return y;}
	public void setY(int y) {this.y = y;}
	public int getY1() {return y1;}
	public void setY1(int y1) {this.y1 = y1;}
}
public class Board extends JPanel implements MouseMotionListener, MouseListener{
	public int x,y,x1,y1;
	public Vector vc = new Vector();
	public Color color = Color.black;
	public int shape = 1;
	public int thick = 10;
	
	Board(){		
		super.addMouseListener(this);
		super.addMouseMotionListener(this);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
	}
	@Override
	public void mousePressed(MouseEvent e) {
		x = e.getX();
		y = e.getY();
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		x1 = e.getX();
		y1 = e.getY();
		if(shape != 1) {
			Info d = new Info();
			d.setColor(color);
			d.setDist(shape);
			d.setX(x);
			d.setY(y);
			d.setX1(x1);
			d.setY1(y1);
			vc.add(d);
			this.paint(this.getGraphics());
			System.out.println(d.getX());
			System.out.println(d.getX1());
			System.out.println(d.getY());
			System.out.println(d.getY1());			
		}		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	@Override
	public void mouseExited(MouseEvent e) {
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		x1 = e.getX();
		y1 = e.getY();		
		if(shape == 1 ) {
			Info d = new Info();
			d.setColor(color);
			d.setDist(1);
			d.setX(x);
			d.setY(y);
			d.setX1(x1);
			d.setY1(y1);
			vc.add(d);
			this.paint(this.getGraphics());
			x = x1;
			y = y1;
		}		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
	}
	public void paint(Graphics g) {			
		for(int i=0; i < vc.size(); ++i) {
			Info d = (Info)vc.elementAt(i);
			if(d.getDist() == 1) {
				((Graphics2D) g).setStroke(new BasicStroke(d.getThick(), BasicStroke.CAP_ROUND,0));	
				g.setColor(d.getColor());
				g.drawLine(d.getX(),d.getY(),d.getX1(),d.getY1());
			}
			else if(d.getDist() == 2) {
				((Graphics2D) g).setStroke(new BasicStroke(d.getThick(), BasicStroke.CAP_ROUND,0));	
				g.setColor(d.getColor());
				g.drawLine(d.getX(),d.getY(),d.getX1(),d.getY1());
			}
			else if(d.getDist() == 3) {
				((Graphics2D) g).setStroke(new BasicStroke(d.getThick(), BasicStroke.CAP_ROUND,0));	
				g.setColor(d.getColor());
				g.drawOval(d.getX(),d.getY(),d.getX1()-d.getX(),d.getY1()-d.getY());
			}
			else if(d.getDist() == 4) {
				((Graphics2D) g).setStroke(new BasicStroke(d.getThick(), BasicStroke.CAP_ROUND,0));	
				g.setColor(d.getColor());
				g.drawRect(d.getX(),d.getY(),d.getX1()-d.getX(),d.getY1()-d.getY());
			}
		}
		
		if(shape == 1) {
			((Graphics2D) g).setStroke(new BasicStroke(thick, BasicStroke.CAP_ROUND,0));	
			g.setColor(color);
			g.drawLine(x, y, x1, y1);
		}
		else if(shape == 2) {
			((Graphics2D) g).setStroke(new BasicStroke(thick, BasicStroke.CAP_ROUND,0));	
			g.setColor(color);
			g.drawLine(x, y, x1, y1);
		}
		else if(shape == 3) {
			((Graphics2D) g).setStroke(new BasicStroke(thick, BasicStroke.CAP_ROUND,0));	
			g.setColor(color);
			g.drawOval(x, y, x1-x, y1-y);
		}
		else if(shape == 4) {
			((Graphics2D) g).setStroke(new BasicStroke(thick, BasicStroke.CAP_ROUND,0));	
			g.setColor(color);
			g.drawRect(x, y, x1-x, y1-y);
		}		
	}
}

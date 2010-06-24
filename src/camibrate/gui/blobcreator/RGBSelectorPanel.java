package camibrate.gui.blobcreator;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import camibrate.StaticFunctions;

public class RGBSelectorPanel extends JPanel implements MouseMotionListener, MouseListener{
	//public static final int RGBPANEL_WIDTH = 500;
	//public static final int RGBPANEL_Height = 500;
	
	BufferedImage image;
	int x=0,y=0,width=0,height=0;
	int x1=0,x2=0,y1=0,y2=0;
	
	Graphics mg;
	
	RGBSelectorPanel(BlobCreatorFrame parent,BufferedImage image){
		//this.image = image;
		this.image = StaticFunctions.scaleImageToFit(image, 500, 500);
		this.setPreferredSize(new Dimension(this.image.getWidth(),this.image.getHeight()));
		
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		//this.add(new DragyCanvas(this.image.getWidth(),this.image.getHeight()));
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		//System.out.println("omg");
		super.paintComponent(g);
		g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), this);
		
		
		if(x1 <= x2){
			x = x1;
			width = x2 - x1;
		}else{
			x = x2;
			width = x1 - x2;
		}
		if(y1 <= y2){
			y = y1;
			height = y2 - y1;
		}else{
			y = y2;
			height = y1 -y2;
		}
			
		g.drawRect(x, y, width, height);
		repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		x2 = e.getX();
		y2 = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {}
	@Override
	public void mouseClicked(MouseEvent arg0) {}
	@Override
	public void mouseEntered(MouseEvent arg0) {}
	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {
		x1 = arg0.getX();
		y1 = arg0.getY();
		x2 = arg0.getX();
		y2 = arg0.getY();
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		x1 = 0;
		y1 = 0;
		x2 = 0;
		y2 = 0;
	}
/*	
	class DragyCanvas extends Canvas{
		DragyCanvas(int width, int height){
			this.setSize(width, height);
		}

		@Override
		public void paint(Graphics g) {
			super.paint(g);
			if(x1 <= x2){
				x = x1;
				width = x2 - x1;
			}else{
				x = x2;
				width = x1 - x2;
			}
			if(y1 <= y2){
				y = y1;
				height = y2 - y1;
			}else{
				y = y2;
				height = y1 -y2;
			}
				
			g.drawRect(x, y, width, height);
			repaint();
		}
		
	}
	*/
}

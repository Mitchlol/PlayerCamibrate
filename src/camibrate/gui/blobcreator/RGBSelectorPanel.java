package camibrate.gui.blobcreator;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import camibrate.StaticFunctions;

public class RGBSelectorPanel extends JPanel implements MouseMotionListener, MouseListener{
	public static final int MODE_RGB = 0;
	public static final int MODE_YUV = 1;
	//public static final int RGBPANEL_WIDTH = 500;
	//public static final int RGBPANEL_Height = 500;
	BlobCreatorFrame parent;
	int mode;
	
	BufferedImage RGBImage;
	BufferedImage YUVImage;
	int x=0,y=0,width=0,height=0;
	int x1=0,x2=0,y1=0,y2=0;
	
	Graphics mg;
	
	RGBSelectorPanel(BlobCreatorFrame parent,BufferedImage RGBImage){
		this.parent = parent;
		//this.image = image;
		this.RGBImage = StaticFunctions.scaleImageToFit(RGBImage, 500, 400);
		this.setPreferredSize(new Dimension(this.RGBImage.getWidth(),this.RGBImage.getHeight()));
		this.YUVImage = StaticFunctions.RGBImageToYUVImage(this.RGBImage);
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		//this.add(new DragyCanvas(this.image.getWidth(),this.image.getHeight()));
		int mode = MODE_RGB;
	}
	
	public void updateImage(BufferedImage image){
		this.RGBImage = StaticFunctions.scaleImageToFit(image, 500, 500);
		this.setPreferredSize(new Dimension(this.RGBImage.getWidth(),this.RGBImage.getHeight()));
		
		this.YUVImage = StaticFunctions.RGBImageToYUVImage(RGBImage);
		
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		//System.out.println("omg");
		super.paintComponent(g);
		if(mode == MODE_RGB){
			g.drawImage(RGBImage, 0, 0, RGBImage.getWidth(), RGBImage.getHeight(), this);
		}else if(mode == MODE_YUV){
			g.drawImage(YUVImage, 0, 0, YUVImage.getWidth(), YUVImage.getHeight(), this);
		}
		
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
	
	public void setMode(int mode){
		this.mode = mode;
		repaint();
	}
	public int getMode(){
		return this.mode;
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
		//make sure sure square isn't out of images bounds
		if(x<0){
			x=0;
		}
		if(y<0){
			y=0;
		}
		if(x+width>=this.getWidth()){
			width = this.getWidth()-1-x;
		}
		if(y+height>=this.getHeight()){
			height = this.getHeight()-1-y;
		}
		System.out.println("x = " + x +", Y = " + y + ",Width = " + width + ",Height = " + height);
		parent.AddRGBRange(StaticFunctions.getRGBRange(x, y, width, height, RGBImage));
		parent.AddYUVRange(StaticFunctions.getYUVRange(x, y, width, height, YUVImage));
	}

}

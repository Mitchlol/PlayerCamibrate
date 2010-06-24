package camibrate.gui.camibrateviewer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import camibrate.StaticFunctions;
import camibrate.playerclient.CamibrateRobot;

@SuppressWarnings("serial")
public class ImageGalleryPanel extends JPanel {
	public static final int THUMBNAIL_WIDTH = 100;
	public static final int THUMBNAIL_HEIGHT = 100;
	
	CamibrateRobot robot;
	Vector<JPanel> imagePanels;
	CamibrateFrame parent;
	
	ImageGalleryPanel(CamibrateRobot robot, CamibrateFrame parent){
		this.robot = robot;
		this.parent = parent;
		imagePanels = new Vector<JPanel>();
		
		this.setBackground(Color.red);
		//this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		FlowLayout layout = new FlowLayout();
		layout.setVgap(0);
		this.setLayout(layout);
		//this.setPreferredSize(new Dimension(500 ,500));
		this.loadImages();
	}
	
	
	public void loadImages(){
		
		imagePanels.clear();
		this.removeAll();
		for(int i = 0; i < robot.capturedImages.size(); i++){
			//System.out.println("loadImages" + robot.capturedImages.size());
			ImagePanel mImagePanel = new ImagePanel(robot.capturedImages.get(i),i,this);
			//mImagePanel.setPreferredSize(new Dimension(THUMBNAIL_WIDTH,THUMBNAIL_HEIGHT));
			//mImagePanel.setBorder(BorderFactory.createLineBorder (Color.black, 2));
			this.add(mImagePanel);
		}
		this.setPreferredSize(new Dimension(THUMBNAIL_WIDTH, THUMBNAIL_HEIGHT * robot.capturedImages.size()));
		//validate is called in CamibrateFrame
	}
	
	class ImagePanel extends JPanel implements MouseListener{
		BufferedImage image;
		int id;
		ImageGalleryPanel gallery;
		double scaleFactor;
		ImagePanel(BufferedImage image, int id, ImageGalleryPanel gallery){
			this.image = image;
			this.id = id;
			this.gallery = gallery;
			this.addMouseListener(this);
			this.setPreferredSize(new Dimension(100,100));
			this.scaleFactor = StaticFunctions.getScaleFactor(image.getWidth(), image.getHeight(), THUMBNAIL_WIDTH, THUMBNAIL_HEIGHT);
			this.setBorder(BorderFactory.createLineBorder (Color.black, 2));
			this.setBackground(Color.CYAN);
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			//System.out.println("omg");
			super.paintComponent(g);
			g.drawImage(image, 0, 0, (int)(image.getWidth()*scaleFactor), (int)(image.getHeight()*scaleFactor), this);
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {}
		@Override
		public void mouseEntered(MouseEvent arg0) {}
		@Override
		public void mouseExited(MouseEvent arg0) {}
		@Override
		public void mousePressed(MouseEvent arg0) {}
		@Override
		public void mouseReleased(MouseEvent arg0) {
			robot.capturedImages.remove(id);
			gallery.loadImages();
			parent.repaint();
			parent.validate();
			System.out.println("elemnt clicked");
		}
	}

}

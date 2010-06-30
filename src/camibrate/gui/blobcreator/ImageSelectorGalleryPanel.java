package camibrate.gui.blobcreator;

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
import camibrate.gui.camibrateviewer.ImageGalleryPanel;

public class ImageSelectorGalleryPanel extends JPanel {
	public static final int THUMBNAIL_WIDTH = 100;
	public static final int THUMBNAIL_HEIGHT = 90;
	
	BlobCreatorData data;
	Vector<JPanel> imagePanels;
	BlobCreatorFrame parent;
	
	ImageSelectorGalleryPanel(BlobCreatorData data, BlobCreatorFrame parent){
		this.data = data;
		this.parent = parent;
		imagePanels = new Vector<JPanel>();
		
		this.setBackground(Color.darkGray);
		//this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		FlowLayout layout = new FlowLayout();
		layout.setVgap(0);
		layout.setHgap(0);
		this.setLayout(layout);
		//this.setPreferredSize(new Dimension(500 ,500));
		this.loadImages();
	}
	
	
	public void loadImages(){
		
		imagePanels.clear();
		this.removeAll();
		for(int i = 0; i < data.getImages().size(); i++){
			//System.out.println("loadImages" + robot.capturedImages.size());
			ImagePanel mImagePanel = new ImagePanel(data.getImages().get(i),i,this);
			//mImagePanel.setPreferredSize(new Dimension(THUMBNAIL_WIDTH,THUMBNAIL_HEIGHT));
			//mImagePanel.setBorder(BorderFactory.createLineBorder (Color.black, 2));
			this.add(mImagePanel);
		}
		this.setPreferredSize(new Dimension(THUMBNAIL_WIDTH * data.getImages().size(), THUMBNAIL_HEIGHT));
		//validate is called in CamibrateFrame
	}
	
	class ImagePanel extends JPanel implements MouseListener{
		BufferedImage image;
		int id;
		ImageSelectorGalleryPanel gallery;
		double scaleFactor;
		ImagePanel(BufferedImage image, int id, ImageSelectorGalleryPanel gallery){
			this.image = image;
			this.id = id;
			this.gallery = gallery;
			this.addMouseListener(this);
			this.setPreferredSize(new Dimension(THUMBNAIL_WIDTH,THUMBNAIL_HEIGHT));
			this.scaleFactor = StaticFunctions.getScaleFactor(image.getWidth(), image.getHeight(), THUMBNAIL_WIDTH, THUMBNAIL_HEIGHT);
			if(id == data.imageAt){
				this.setBorder(BorderFactory.createLineBorder (Color.green, 2));
			}else{
				this.setBorder(BorderFactory.createLineBorder (Color.lightGray, 2));
			}
			this.setBackground(Color.black);
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			//System.out.println("omg");
			super.paintComponent(g);
			int width = (int)(image.getWidth()*scaleFactor);
			int height = (int)(image.getHeight()*scaleFactor);
			int widthOffset = (THUMBNAIL_WIDTH - width)/2;
			int heightOffset = (THUMBNAIL_HEIGHT - height)/2;
			g.drawImage(image, widthOffset, heightOffset, width, height, this);
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
			data.setImageAt(id);
			parent.GoToCurrentImage();
			
			gallery.loadImages();
			parent.repaint();
			parent.validate();
			System.out.println("elemnt clicked");
			
		}
	}
}

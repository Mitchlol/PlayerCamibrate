package camibrate.gui.camibrateviewer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javaclient3.BlobfinderInterface;
import javaclient3.CameraInterface;
import javaclient3.structures.blobfinder.PlayerBlobfinderBlob;

import javax.swing.JPanel;

import camibrate.StaticFunctions;
import camibrate.playerclient.CamibrateRobot;

@SuppressWarnings("serial")
public class VideoPanel extends JPanel {
	
	public static final int VIDEOPANEL_HEIGHT = 500;
	public static final int VIDEOPANEL_WIDTH = 500;
	
	CamibrateRobot robot;
	int width;
	int height;
	double scaleFactor;
	int cameraWidth;
	int cameraHeight;
	
	boolean displayBlobs;
	
	VideoPanel(CamibrateRobot robot){
		this.robot = robot;
		this.width = VIDEOPANEL_WIDTH;
		this.height = VIDEOPANEL_HEIGHT;
		this.cameraWidth = robot.camera.getData().getWidth();
		this.cameraHeight = robot.camera.getData().getHeight();
		this.scaleFactor = StaticFunctions.getScaleFactor(this.cameraWidth,this.cameraHeight,this.width,this.height);
		
		
		this.setSize(width, height);
		this.setPreferredSize(new Dimension(width,height));
		this.setVisible(true);
		
		displayBlobs = true;
	}
	
	public void enableBlobs(){
		displayBlobs = true;
	}
	
	public void disableBlobs(){
		displayBlobs = false;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.blue);
		g.clearRect(0, 0, width, height);
		g.fillRect(0, 0, width, height);
		
		drawPlayerCameraImage(g,robot.camera);
		if(displayBlobs){
			drawPlayerBlobfinderData(g,robot.blobfinder);
		}
		this.repaint();
		
	}
	
	public boolean drawPlayerBlobfinderData(Graphics g, BlobfinderInterface blobfinder){
		while(!blobfinder.isDataReady()){
			
		}
		PlayerBlobfinderBlob[] blobs = blobfinder.getData().getBlobs();
		
		for(int i = 0; i < blobs.length; i++){
			int transparentColor = blobs[i].getColor()+0x80000000;
			g.setColor(new Color(transparentColor,true));
			int x = blobs[i].getLeft();
			int y = blobs[i].getTop();
			int width = blobs[i].getRight()-blobs[i].getLeft();
			int height = blobs[i].getBottom()-blobs[i].getTop();
			g.fillRect((int)(x*scaleFactor), (int)(y*scaleFactor), (int)(width*scaleFactor), (int)(height*scaleFactor));
			
		}
		
		
		return true;
	}
	
	public boolean drawPlayerCameraImage(Graphics g, CameraInterface camera){
		BufferedImage image;
		
		while(!camera.isDataReady()){
	        	
	    }
		image = StaticFunctions.toImage(cameraWidth,cameraHeight,robot.camera.getData().getImage());
		g.drawImage(image, 0, 0, (int)(this.cameraWidth*scaleFactor), (int)(this.cameraHeight*scaleFactor), this);
		
		return true;
	}

	
	
	
}

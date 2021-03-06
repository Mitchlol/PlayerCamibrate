package camibrate.gui.camibrateviewer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javaclient3.BlobfinderInterface;
import javaclient3.CameraInterface;
import javaclient3.structures.blobfinder.PlayerBlobfinderBlob;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import camibrate.StaticFunctions;
import camibrate.playerclient.CamibrateRobot;

@SuppressWarnings("serial")
public class VideoPanel extends JPanel {
	
	public static final int VIDEOPANEL_HEIGHT = 400;
	public static final int VIDEOPANEL_WIDTH = 500;
	
	CamibrateRobot robot;
	int width;
	int height;
	double scaleFactor;
	int cameraWidth;
	int cameraHeight;
	int imageWidth, imageHeight,imageWidthOffset, imageHeightOffset;
	
	boolean displayBlobs;
	
	VideoPanel(CamibrateRobot robot){
		this.robot = robot;
		this.width = VIDEOPANEL_WIDTH;
		this.height = VIDEOPANEL_HEIGHT;
		this.cameraWidth = robot.camera.getData().getWidth();
		this.cameraHeight = robot.camera.getData().getHeight();
		this.scaleFactor = StaticFunctions.getScaleFactor(this.cameraWidth,this.cameraHeight,this.width,this.height);
		
		this.imageWidth = (int)(this.cameraWidth*scaleFactor);
		this.imageHeight = (int)(this.cameraHeight*scaleFactor);
		this.imageWidthOffset = (width - imageWidth)/2;
		this.imageHeightOffset = (height - imageHeight)/2;
		
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
		//System.out.println("repaint");
		super.paintComponent(g);
		
		g.setColor(Color.black);
		g.clearRect(0, 0, width, height);
		g.fillRect(0, 0, width, height);
		drawPlayerCameraImage(g);
		if(displayBlobs){
			drawPlayerBlobfinderData(g,robot.blobfinder);
		}
		this.repaint();
		
	}
	
	public boolean drawPlayerBlobfinderData(Graphics g, BlobfinderInterface blobfinder){
		
		PlayerBlobfinderBlob[] blobs = robot.blobData.getBlobs();
		
		for(int i = 0; i < blobs.length; i++){
			int transparentColor = blobs[i].getColor()+0x80000000;
			g.setColor(new Color(transparentColor,true));
			int x = blobs[i].getLeft();
			int y = blobs[i].getTop();
			int width = blobs[i].getRight()-blobs[i].getLeft();
			int height = blobs[i].getBottom()-blobs[i].getTop();
			//g.fillRect((int)(x*scaleFactor), (int)(y*scaleFactor), (int)(width*scaleFactor), (int)(height*scaleFactor));
			g.fillRect((int)(x*scaleFactor)+imageWidthOffset, (int)(y*scaleFactor)+imageHeightOffset, (int)(width*scaleFactor), (int)(height*scaleFactor));
		}
		
		
		return true;
	}
	
	public boolean drawPlayerCameraImage(Graphics g){
		//BufferedImage image;
		
		//System.out.println("compression mode = " + robot.camera.getData().getCompression());
		//image = robot.getImage();
		//robot.updateImage();
		
		//g.drawImage(robot.currentImage, 0, 0, (int)(this.cameraWidth*scaleFactor), (int)(this.cameraHeight*scaleFactor), this);
		g.drawImage(robot.currentImage, imageWidthOffset, imageHeightOffset, imageWidth, imageHeight, this);
		return true;
	}

	
	
	
}

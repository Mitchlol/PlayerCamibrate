package camibrate.gui.blobcreator;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;

import camibrate.gui.Strings;

public class BlobCreatorFrame extends JFrame{
	//CamibrateRobot robot;
	int width, height;
	
	public BlobCreatorFrame(Vector<BufferedImage> images){
		//player stuff
		//this.robot = robot;
		
		//Main GUI config stuff icon, title, etc...
		this.setTitle(Strings.MAIN_LABEL);
		
		//this.width = robot.camera.getData().getWidth() *2;
		//this.height = robot.camera.getData().getHeight() + 100;
		this.setSize(1000, 700);
		
		JPanel RGBPanelHolder = new JPanel();
		RGBPanelHolder.setPreferredSize(new Dimension(500,500));
		RGBPanelHolder.add(new RGBSelectorPanel(this,images.get(0)));
		this.add(RGBPanelHolder);
	}
	
	public void AddRanges(){
		
	}

	
}

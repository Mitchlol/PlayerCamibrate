package camibrate.gui.blobcreator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;

import camibrate.CamibrateBlob;
import camibrate.RGBRange;
import camibrate.gui.Strings;

public class BlobCreatorFrame extends JFrame{
	//CamibrateRobot robot;
	int width, height;
	Vector<CamibrateBlob> blobs;
	RGBSegmentedDisplay mRGBSegmentedDisplay;
	
	public BlobCreatorFrame(Vector<BufferedImage> images){
		//player stuff
		//this.robot = robot;
		blobs = new Vector<CamibrateBlob>();
		
		blobs.add(new CamibrateBlob());
		blobs.get(0).setDisplayColor(Color.red);
		
		
		//Main GUI config stuff icon, title, etc...
		this.setTitle(Strings.MAIN_LABEL);
		
		//this.width = robot.camera.getData().getWidth() *2;
		//this.height = robot.camera.getData().getHeight() + 100;
		this.setSize(1000, 700);
		this.setLayout(new FlowLayout());
		
		JPanel RGBPanelHolder = new JPanel();
		RGBPanelHolder.setPreferredSize(new Dimension(500,500));
		RGBPanelHolder.add(new RGBSelectorPanel(this,images.get(0)));
		this.add(RGBPanelHolder);
		
		JPanel RGBSegmentedHolder = new JPanel();
		RGBSegmentedHolder.setPreferredSize(new Dimension(500,500));
		mRGBSegmentedDisplay = new RGBSegmentedDisplay(blobs,images.get(0));
		RGBSegmentedHolder.add(mRGBSegmentedDisplay);
		this.add(RGBSegmentedHolder);
	}
	
	public void AddRange(RGBRange range){
		blobs.get(0).setmRGBRange(range);
		mRGBSegmentedDisplay.update(blobs);
	}

	
}

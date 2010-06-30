package camibrate.gui.blobcreator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpringLayout;

import camibrate.RGBRange;
import camibrate.gui.Strings;

public class BlobCreatorFrame extends JFrame implements ActionListener{
	//CamibrateRobot robot;
	BlobCreatorData data;
	int width, height;
	RGBSelectorPanel mRGBSelectorPanel;
	RGBSegmentedDisplay mRGBSegmentedDisplay;
	
	public BlobCreatorFrame(BlobCreatorData data){
		//player stuff
		//this.robot = robot;
		this.data = data;
		
		
		
		//Main GUI config stuff icon, title, etc...
		this.setTitle(Strings.MAIN_LABEL);
		
		//this.width = robot.camera.getData().getWidth() *2;
		//this.height = robot.camera.getData().getHeight() + 100;
		this.setSize(1000+10, 700+10);
		FlowLayout layout = new FlowLayout();
		layout.setHgap(0);
		layout.setVgap(0);
		this.setLayout(layout);
		
		JPanel RGBPanelHolder = new JPanel();
		RGBPanelHolder.setPreferredSize(new Dimension(500,400));
		RGBPanelHolder.setBackground(Color.black);
		SpringLayout RGBPanelHolderLayout = new SpringLayout();
		RGBPanelHolder.setLayout(RGBPanelHolderLayout);
		mRGBSelectorPanel = new RGBSelectorPanel(this,data.getCurrentImage());
		RGBPanelHolder.add(mRGBSelectorPanel);
		RGBPanelHolderLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, mRGBSelectorPanel, 0, SpringLayout.HORIZONTAL_CENTER, RGBPanelHolder);
		RGBPanelHolderLayout.putConstraint(SpringLayout.VERTICAL_CENTER, mRGBSelectorPanel, 0, SpringLayout.VERTICAL_CENTER, RGBPanelHolder);
		this.add(RGBPanelHolder);
		
		JPanel RGBSegmentedHolder = new JPanel();
		RGBSegmentedHolder.setPreferredSize(new Dimension(500,400));
		RGBSegmentedHolder.setBackground(Color.white);
		
		SpringLayout RGBSegmentedHolderLayout = new SpringLayout();
		RGBSegmentedHolder.setLayout(RGBSegmentedHolderLayout);
		mRGBSegmentedDisplay = new RGBSegmentedDisplay(data.getBlobs(),data.getCurrentImage());
		RGBSegmentedHolder.add(mRGBSegmentedDisplay);
		RGBSegmentedHolderLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, mRGBSegmentedDisplay, 0, SpringLayout.HORIZONTAL_CENTER, RGBSegmentedHolder);
		RGBSegmentedHolderLayout.putConstraint(SpringLayout.VERTICAL_CENTER, mRGBSegmentedDisplay, 0, SpringLayout.VERTICAL_CENTER, RGBSegmentedHolder);
		this.add(RGBSegmentedHolder);
		
		
		JScrollPane mScrollPane = new JScrollPane(new ImageSelectorGalleryPanel(data,this));
		mScrollPane.setPreferredSize(new Dimension(1000,90+17));
		mScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		mScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		this.add(mScrollPane);
		this.add(new BlobCreatorButtonPanel(this));
	}
	
	public void AddRange(RGBRange range){
		data.getCurrentBlob().setmRGBRange(range);
		mRGBSegmentedDisplay.updateBlobs(data.getBlobs());
	}
	
	public void GoToNextImage(){
		data.nextImage();
		GoToCurrentImage();
	}
	
	public void GoToPreviousImage(){
		data.previousImage();
		GoToCurrentImage();
	}
	
	public void GoToCurrentImage(){
		mRGBSelectorPanel.updateImage(data.getCurrentImage());
		mRGBSegmentedDisplay.updateImage(data.getCurrentImage());
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
}

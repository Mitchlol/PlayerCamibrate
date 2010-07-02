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

import camibrate.CamibrateBlob;
import camibrate.RGBRange;
import camibrate.YUVRange;
import camibrate.gui.Strings;
import camibrate.gui.popupwindowutil.FunctionCaller;

public class BlobCreatorFrame extends JFrame implements ActionListener{
	//CamibrateRobot robot;
	BlobCreatorData data;
	int width, height;
	RGBSelectorPanel mRGBSelectorPanel;
	RGBSegmentedDisplay mRGBSegmentedDisplay;
	ImageSelectorGalleryPanel mImageSelectorGalleryPanel;
	BlobCreatorButtonPanel mBlobCreatorButtonPanel;
	
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
		
		
		mImageSelectorGalleryPanel = new ImageSelectorGalleryPanel(data,this);
		JScrollPane mScrollPane = new JScrollPane(mImageSelectorGalleryPanel);
		mScrollPane.setPreferredSize(new Dimension(1000,90+17));
		mScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		mScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		this.add(mScrollPane);
		
		mBlobCreatorButtonPanel = new BlobCreatorButtonPanel(this);
		this.add(mBlobCreatorButtonPanel);
	}
	
	public BlobCreatorData getData(){
		return data;
	}
	
	public void AddBlob(CamibrateBlob blob){
		data.addBlob(blob);
		SetCurrentBlob(data.getBlobs().size() -1);
		mBlobCreatorButtonPanel.PopulateColorsComboBox();
	}
	/*
	public void SetCurrentBlob(CamibrateBlob blob){
		Vector<CamibrateBlob> blobs = data.getBlobs();
		for(int i = 0; i < blobs.size(); i++){
			if (blobs.get(i).equals(blob)){
				data.setBlobAt(i);
				System.out.println("setting curent blob to" + i);
			}
		}
	}
	*/
	public void SetCurrentBlob(int blobAt){
			data.setBlobAt(blobAt);
	}
	
	public void DeleteCurrentBlob(){
		data.deleteBlob(data.blobAt);
		
		mBlobCreatorButtonPanel.PopulateColorsComboBox();
		mRGBSegmentedDisplay.updateBlobs(data.getBlobs());
	}
	
	public void AddRGBRange(RGBRange range){
		data.getCurrentBlob().setRGBRange(range);
		mRGBSegmentedDisplay.updateBlobs(data.getBlobs());
	}
	
	public void AddYUVRange(YUVRange range){
		data.getCurrentBlob().setYUVRange(range);
		mRGBSegmentedDisplay.updateBlobs(data.getBlobs());
	}
	
	public void GoToNextImage(){
		data.nextImage();
		GoToCurrentImage();
		mImageSelectorGalleryPanel.loadImages();
		mImageSelectorGalleryPanel.repaint();
		mImageSelectorGalleryPanel.validate();
	}
	
	public void GoToPreviousImage(){
		data.previousImage();
		GoToCurrentImage();
		mImageSelectorGalleryPanel.loadImages();
		mImageSelectorGalleryPanel.repaint();
		mImageSelectorGalleryPanel.validate();
	}
	
	public void GoToCurrentImage(){
		mRGBSelectorPanel.updateImage(data.getCurrentImage());
		mRGBSegmentedDisplay.updateImage(data.getCurrentImage());
	}
	
	public void SwitchMode(){
		if(mRGBSelectorPanel.getMode() == mRGBSelectorPanel.MODE_RGB){
			mRGBSelectorPanel.setMode(mRGBSelectorPanel.MODE_YUV);
			mRGBSegmentedDisplay.setMode(mRGBSegmentedDisplay.MODE_YUV);
		}else{
			mRGBSelectorPanel.setMode(mRGBSelectorPanel.MODE_RGB);
			mRGBSegmentedDisplay.setMode(mRGBSegmentedDisplay.MODE_RGB);
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
}

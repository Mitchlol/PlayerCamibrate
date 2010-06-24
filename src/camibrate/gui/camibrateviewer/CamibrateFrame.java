package camibrate.gui.camibrateviewer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpringLayout;

import camibrate.StaticFunctions;
import camibrate.gui.Strings;
import camibrate.gui.blobcreator.BlobCreatorFrame;
import camibrate.playerclient.CamibrateRobot;

@SuppressWarnings("serial")
public class CamibrateFrame extends JFrame implements ActionListener {
	
	CamibrateRobot robot;
	int width;
	int height;
	int leftPanelWidth;
	int leftPanelHeight;
	
	VideoPanel mVideoPanel;
	ButtonPanel mButtonPanel;
	JPanel leftPanel;
	ImageGalleryPanel mImageGalleryPanel;
	SpringLayout layout;
	JScrollPane mScrollPane;
	
	public CamibrateFrame(CamibrateRobot robot){
		//player stuff
		this.robot = robot;
		this.width = VideoPanel.VIDEOPANEL_WIDTH + ImageGalleryPanel.THUMBNAIL_WIDTH;
		this.height = VideoPanel.VIDEOPANEL_HEIGHT + ButtonPanel.BUTTONPANEL_HEIGHT;
		this.leftPanelWidth = robot.camera.getData().getWidth();
		this.leftPanelHeight = robot.camera.getData().getHeight() + ButtonPanel.BUTTONPANEL_HEIGHT;
		
		//Main GUI config stuff icon, title, etc...
		this.setTitle(Strings.MAIN_LABEL);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Frame settings
		layout = new SpringLayout();
		this.setLayout(layout);
		
		this.setSize(new Dimension(width+50, height+50));
		
		leftPanel = new JPanel();
		leftPanel.setBackground(Color.cyan);
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		//video
		mVideoPanel = new VideoPanel(robot);
		leftPanel.add(mVideoPanel);
		//buttons
		mButtonPanel = new ButtonPanel(this);
		leftPanel.add(mButtonPanel);
		//leftPanel.setPreferredSize(new Dimension(leftPanelWidth,leftPanelHeight));
		layout.putConstraint(SpringLayout.NORTH, leftPanel, 0, SpringLayout.NORTH, this.getContentPane());
		layout.putConstraint(SpringLayout.WEST, leftPanel, 0, SpringLayout.WEST, this.getContentPane());
		this.add(leftPanel);
		
		mImageGalleryPanel = new ImageGalleryPanel(robot,this);
		mScrollPane = new JScrollPane(mImageGalleryPanel);
		mScrollPane.setPreferredSize(new Dimension(ImageGalleryPanel.THUMBNAIL_WIDTH + 17,height));
		mScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		mScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		layout.putConstraint(SpringLayout.NORTH, mScrollPane, 0, SpringLayout.NORTH, this.getContentPane());
		layout.putConstraint(SpringLayout.WEST, mScrollPane, 0, SpringLayout.EAST, leftPanel);
		this.add(mScrollPane);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//no switch on strings BOOOOOOOO!
		//displayblobs checkbox
		if(ButtonPanel.ACTION_DISPLAYBLOBS.equals(e.getActionCommand())){
			if(((JCheckBox)e.getSource()).isSelected()){
				mVideoPanel.enableBlobs();
			}else{
				mVideoPanel.disableBlobs();
			}
		}else
		//launch blob creator GUI
		if(ButtonPanel.ACTION_LAUNCHBLOBCREATOR.equals(e.getActionCommand())){
			new BlobCreatorFrame(robot.capturedImages).setVisible(true);
		}
		//capture image
		if(ButtonPanel.ACTION_CAPTUREIMAGE.equals(e.getActionCommand())){
			robot.capturedImages.add(StaticFunctions.toImage(robot.camera.getData().getWidth(),robot.camera.getData().getHeight(),robot.camera.getData().getImage()));
			mImageGalleryPanel.loadImages();
			this.validate();
			mScrollPane.getVerticalScrollBar().setValue(mScrollPane.getVerticalScrollBar().getMaximum());
		}
	}

}

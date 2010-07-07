package camibrate.gui.camibrateviewer;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import camibrate.gui.Strings;

@SuppressWarnings("serial")
public class ButtonPanel extends JPanel {
	public static final int BUTTONPANEL_HEIGHT = 100;
	public static final int BUTTONPANEL_WIDTH = 500;
	
	public static final String ACTION_DISPLAYBLOBS = "DISPLAY_BLOBS";
	public static final String ACTION_LAUNCHBLOBCREATOR = "LAUNCH_BLOB_CREATOR";
	public static final String ACTION_CAPTUREIMAGE = "CAPTURE_IMAGE";
	
	//public CamibrateRobot robot;
	CamibrateFrame parent;
	
	JCheckBox displayBlobsCheckbox;
	JButton blobcreatorButton;
	JButton captureButton;
	
	ButtonPanel(CamibrateFrame parent){
		//this.robot = robotArg;
		this.parent = parent;
		
		SpringLayout layout = new SpringLayout();
		this.setLayout(layout);
		
		//blob display checkbox
		displayBlobsCheckbox = new JCheckBox(Strings.BUTTONPANEL_BLOBFINDERCHECKBOX_LABEL);
		displayBlobsCheckbox.setSelected(true);
		displayBlobsCheckbox.setActionCommand(ACTION_DISPLAYBLOBS);
		displayBlobsCheckbox.addActionListener(parent);
		displayBlobsCheckbox.setPreferredSize(new Dimension(BUTTONPANEL_WIDTH*2/5,BUTTONPANEL_HEIGHT/2));
		layout.putConstraint(SpringLayout.NORTH, displayBlobsCheckbox, 0, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, displayBlobsCheckbox, 0, SpringLayout.WEST, this);
		this.add(displayBlobsCheckbox);
		
		//create blob button
		blobcreatorButton = new JButton(Strings.BLOBCREATOR_LABEL);
		blobcreatorButton.setActionCommand(ACTION_LAUNCHBLOBCREATOR);
		blobcreatorButton.addActionListener(parent);
		blobcreatorButton.setPreferredSize(new Dimension(BUTTONPANEL_WIDTH*2/5,BUTTONPANEL_HEIGHT/2));
		layout.putConstraint(SpringLayout.SOUTH, blobcreatorButton, 0, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.WEST, blobcreatorButton, 0, SpringLayout.WEST, this);
		this.add(blobcreatorButton);
		
		//create test button"Display Blobs
		captureButton = new JButton(Strings.BUTTONPANEL_CAPTURE_LABEL);
		captureButton.setActionCommand(ACTION_CAPTUREIMAGE);
		captureButton.addActionListener(parent);
		captureButton.setPreferredSize(new Dimension(BUTTONPANEL_WIDTH*3/5,BUTTONPANEL_HEIGHT));
		layout.putConstraint(SpringLayout.WEST, captureButton, 0, SpringLayout.EAST, blobcreatorButton);
		layout.putConstraint(SpringLayout.EAST, captureButton, 0, SpringLayout.EAST, this);
		this.add(captureButton);
		
		//this.setBackground(Color.lightGray);
		this.setPreferredSize(new Dimension(BUTTONPANEL_WIDTH,BUTTONPANEL_HEIGHT));
	}
}
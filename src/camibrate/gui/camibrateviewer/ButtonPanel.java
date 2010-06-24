package camibrate.gui.camibrateviewer;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

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
		
		//blob display checkbox
		displayBlobsCheckbox = new JCheckBox(Strings.BUTTONPANEL_BLOBFINDERCHECKBOX_LABEL);
		displayBlobsCheckbox.setSelected(true);
		displayBlobsCheckbox.setActionCommand(ACTION_DISPLAYBLOBS);
		displayBlobsCheckbox.addActionListener(parent);
		
		this.add(displayBlobsCheckbox);
		
		//create blob button
		blobcreatorButton = new JButton(Strings.BLOBCREATOR_LABEL);
		blobcreatorButton.setActionCommand(ACTION_LAUNCHBLOBCREATOR);
		blobcreatorButton.addActionListener(parent);
		this.add(blobcreatorButton);
		
		//create test button"Display Blobs
		captureButton = new JButton(Strings.BUTTONPANEL_CAPTURE_LABEL);
		captureButton.setActionCommand(ACTION_CAPTUREIMAGE);
		captureButton.addActionListener(parent);
		this.add(captureButton);
		
		this.setBackground(Color.green);
		this.setPreferredSize(new Dimension(BUTTONPANEL_WIDTH,BUTTONPANEL_HEIGHT));
	}
}
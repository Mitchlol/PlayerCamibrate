package camibrate.gui.blobcreator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import camibrate.CamibrateBlob;

public class BlobCreatorButtonPanel extends JPanel {
	BlobCreatorFrame parent;
	
	JPanel ImageNavigationButtonsPanel;
	JButton nextImageButton, previousImageButton;
	
	JPanel ColorSelectorPanel;
	JComboBox ColorsComboBox;
	
	BlobCreatorButtonPanel(BlobCreatorFrame newParent){
		parent = newParent;
		this.setPreferredSize(new Dimension(1000,300));
		this.setBackground(Color.orange);
//image navigation		
		nextImageButton = new JButton("----->");
		previousImageButton = new JButton("<-----");
		nextImageButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				parent.GoToNextImage();
			}
		});
		previousImageButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				parent.GoToPreviousImage();
			}
		});
		
		ImageNavigationButtonsPanel = new JPanel();
		ImageNavigationButtonsPanel.setPreferredSize(new Dimension(1000,25));
		ImageNavigationButtonsPanel.setLayout(new BorderLayout());
		ImageNavigationButtonsPanel.add(previousImageButton,BorderLayout.WEST);
		ImageNavigationButtonsPanel.add(nextImageButton,BorderLayout.EAST);
		this.add(ImageNavigationButtonsPanel);
		
//color selection
		ColorSelectorPanel = new JPanel();
		
		ColorsComboBox = new JComboBox();
		PopulateColorsComboBox();
		
		ColorSelectorPanel.add(ColorsComboBox);
		this.add(ColorSelectorPanel);
	}
	
	public void PopulateColorsComboBox(){
		ColorsComboBox.removeAllItems();
		Vector<CamibrateBlob> blobs = parent.getData().getBlobs();
		for(int i = 0; i < blobs.size(); i++){
			ColorsComboBox.addItem(blobs.get(i).getName());
			//System.out.println(blobs.get(i).getName());
			
		}
	}

}

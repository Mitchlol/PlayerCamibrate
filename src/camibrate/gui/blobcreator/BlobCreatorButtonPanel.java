package camibrate.gui.blobcreator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class BlobCreatorButtonPanel extends JPanel {
	JButton nextImageButton, previousImageButton;
	BlobCreatorFrame parent;
	
	BlobCreatorButtonPanel(BlobCreatorFrame newParent){
		parent = newParent;
		this.setPreferredSize(new Dimension(1000,300));
		this.setBackground(Color.orange);
		
		nextImageButton = new JButton("Next Image ->");
		previousImageButton = new JButton("<- Previous Image");
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
		this.add(nextImageButton);
		this.add(previousImageButton);
	}

}

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
import camibrate.gui.Strings;
import camibrate.gui.popupwindowutil.FunctionCaller;
import camibrate.gui.popupwindowutil.TextInputPopupWindow;

public class BlobCreatorButtonPanel extends JPanel {
	BlobCreatorFrame parent;
	
	JPanel ImageNavigationButtonsPanel;
	JButton nextImageButton, previousImageButton, switchModeButton;
	
	JPanel ColorSelectorPanel;
	JComboBox ColorsComboBox;
	JButton addColorButton, deleteColorButton, resetColorButton, undoButton;
	
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
		
		switchModeButton = new JButton("RGB <-> YUV");
		switchModeButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				parent.SwitchMode();
			}
		});
		
		ImageNavigationButtonsPanel = new JPanel();
		ImageNavigationButtonsPanel.setPreferredSize(new Dimension(1000,25));
		ImageNavigationButtonsPanel.setLayout(new BorderLayout());
		ImageNavigationButtonsPanel.add(previousImageButton,BorderLayout.WEST);
		ImageNavigationButtonsPanel.add(nextImageButton,BorderLayout.EAST);
		ImageNavigationButtonsPanel.add(switchModeButton,BorderLayout.CENTER);
		this.add(ImageNavigationButtonsPanel);
		
//color selection
		ColorSelectorPanel = new JPanel();
		
		ColorsComboBox = new JComboBox();
		ColorsComboBox.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//CamibrateBlob source = (CamibrateBlob)ColorsComboBox.getSelectedIndex();
				if(ColorsComboBox.getSelectedIndex() > -1){
					parent.SetCurrentBlob(ColorsComboBox.getSelectedIndex());
				}
			}
		});
		PopulateColorsComboBox();
		ColorSelectorPanel.add(ColorsComboBox);
		
		addColorButton = new JButton(Strings.ADD_NEW_COLOR);
		addColorButton.addActionListener(new AddColorActionListener(parent));
		ColorSelectorPanel.add(addColorButton);
		
		deleteColorButton = new JButton(Strings.DELETE_COLOR);
		deleteColorButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				parent.DeleteCurrentBlob();
			}
		});
		ColorSelectorPanel.add(deleteColorButton);
		
		undoButton = new JButton(Strings.UNDO_RANGE);
		undoButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				parent.UndoLastRange();
			}
		});
		ColorSelectorPanel.add(undoButton);
		
		resetColorButton = new JButton(Strings.RESET_COLOR);
		resetColorButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				parent.ClearCurrentBlob();
			}
		});
		ColorSelectorPanel.add(resetColorButton);
		
		this.add(ColorSelectorPanel);
		
		
	}
	
	public void PopulateColorsComboBox(){
		ColorsComboBox.removeAllItems();
		Vector<CamibrateBlob> blobs = parent.getData().getBlobs();
		for(int i = 0; i < blobs.size(); i++){
			ColorsComboBox.addItem(blobs.get(i));
			//ColorsComboBox.addItem(blobs.get(i).getName());
			//System.out.println(blobs.get(i).getName());
		}
		ColorsComboBox.setSelectedIndex(ColorsComboBox.getItemCount() -1);
	}
	
	class AddColorActionListener implements ActionListener{
		BlobCreatorFrame parent;
		public AddColorActionListener(BlobCreatorFrame parent){
			this.parent = parent;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			//NewColorPopupWindow popupWindow = new NewColorPopupWindow(parent);
			TextInputPopupWindow popupWindow = new TextInputPopupWindow(
														Strings.ADD_NEW_COLOR,
														Strings.BLOB_NAME_PROPMPT,
														Strings.BLOB_NAME_DEFAULT,
														new FunctionCaller(){
															@Override
															public void callFunction(String s) {
																CamibrateBlob blob = new CamibrateBlob(s);
																parent.AddBlob(blob);
															}
															
														});
			popupWindow.setVisible(true);
		}
	}
	/*
	class AddColorFunctionCaller implements FunctionCaller{

		@Override
		public void callFunction(String s) {
			CamibrateBlob blob = new CamibrateBlob(s);
			parent.AddBlob(blob);
		}
		
	}
	*/
}

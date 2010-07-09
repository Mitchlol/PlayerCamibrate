package camibrate.gui.blobcreator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import camibrate.CamibrateBlob;
import camibrate.gui.Strings;
import camibrate.gui.popupwindowutil.ColorFunctionCaller;
import camibrate.gui.popupwindowutil.ColorInputPopupWindow;
import camibrate.gui.popupwindowutil.SingleTextInputPopupWindow;
import camibrate.gui.popupwindowutil.StringFunctionCaller;

public class BlobCreatorButtonPanel extends JPanel {
	BlobCreatorFrame parent;
	
	JPanel ImageNavigationButtonsPanel;
	JButton nextImageButton, previousImageButton, switchModeButton;
	
	JPanel ColorSelectorPanel;
	JComboBox ColorsComboBox;
	JButton addColorButton, deleteColorButton, resetColorButton, undoButton, exportCMVConfButton;
	
	BlobCreatorButtonPanel(BlobCreatorFrame newParent){
		parent = newParent;
		this.setPreferredSize(new Dimension(1000,200));
		this.setBackground(Color.orange);
		FlowLayout layout = new FlowLayout();
		layout.setHgap(0);
		layout.setVgap(0);
		this.setLayout(layout);
		
		
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
		ImageNavigationButtonsPanel.setPreferredSize(new Dimension(1000,50));
		ImageNavigationButtonsPanel.setLayout(new BorderLayout());
		ImageNavigationButtonsPanel.add(previousImageButton,BorderLayout.WEST);
		ImageNavigationButtonsPanel.add(nextImageButton,BorderLayout.EAST);
		ImageNavigationButtonsPanel.add(switchModeButton,BorderLayout.CENTER);
		Border border = BorderFactory.createLineBorder(Color.black);
		//Border border = BorderFactory.createLoweredBevelBorder();
		ImageNavigationButtonsPanel.setBorder(border);
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
		
		exportCMVConfButton = new JButton(Strings.EXPORT_CMV_CONF);
		exportCMVConfButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				LaunchCMVFileInputPopupWindow();
			}
		});
		ColorSelectorPanel.add(exportCMVConfButton);
		
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
			SingleTextInputPopupWindow popupWindow = new SingleTextInputPopupWindow(
														Strings.ADD_NEW_COLOR,
														Strings.BLOB_NAME_PROPMPT,
														Strings.BLOB_NAME_DEFAULT,
														new StringFunctionCaller(){
															@Override
															public void callFunction(String s) {
																CamibrateBlob blob = new CamibrateBlob(s);
																parent.AddBlob(blob);
																LaunchColorInputPopupWindow();
															}
															
														});
			popupWindow.setVisible(true);
			
		}
	}
	
	private void LaunchColorInputPopupWindow(){
		ColorInputPopupWindow popupWindow = new ColorInputPopupWindow(
				Strings.SET_COLOR_RGB,
				Strings.SET_RGB_PROMPT,
				new ColorFunctionCaller(){
					@Override
					public void callFunction(Color c) {
						System.out.println(c);
						parent.SetRGBDisplayColor(c);
					}
					
				});
		popupWindow.setVisible(true);
	}
	
	private void LaunchCMVFileInputPopupWindow(){
		SingleTextInputPopupWindow popupWindow = new SingleTextInputPopupWindow(
				Strings.EXPORT_CMV_CONF,
				Strings.EXPORT_CMV_CONF_PROMPT,
				Strings.EXPORT_CMV_CONF_DEFAULT,
				new StringFunctionCaller(){
					@Override
					public void callFunction(String s) {
						parent.data.exportCMVColorfile(s);
					}
					
				});
		popupWindow.setVisible(true);
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

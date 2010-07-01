package camibrate.gui.blobcreator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import camibrate.CamibrateBlob;
import camibrate.gui.Strings;

public class NewColorPopupWindow extends JFrame {
	
	BlobCreatorFrame parent;
	NewColorPopupWindow(BlobCreatorFrame parent){
		this.parent = parent;
		create();
	}
	NewColorPopupWindow(String S){
		if(!S.equals("TEST")){
			System.exit(ERROR);
		}
		create();
	}
	
	private void create(){
		//frame
		//JFrame popupWindow = new JFrame();
		this.setTitle(Strings.ADD_NEW_COLOR);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(300+25,100);
		this.setLayout(new FlowLayout());
		
		//New Name box
		JPanel namePanel = new JPanel();
		namePanel.setPreferredSize(new Dimension(300,30));
		namePanel.setBackground(Color.red);
		//label
		JLabel nameLabel = new JLabel(Strings.BLOB_NAME_PROPMPT);
		nameLabel.setPreferredSize(new Dimension(140,20));
		namePanel.add(nameLabel);
		//input feild
		JTextField nameInput = new JTextField(Strings.BLOB_NAME_DEFAULT);
		nameInput.setPreferredSize(new Dimension(140,20));
		namePanel.add(nameInput);
		
		//Buttons Box
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setPreferredSize(new Dimension(300,30));
		buttonsPanel.setBackground(Color.green);
		//ok Button
		JButton okButton = new JButton(Strings.ACCEPT);
		okButton.setPreferredSize(new Dimension(140,20));
		okButton.addActionListener(new AcceptActionListener(this, nameInput, parent));
		buttonsPanel.add(okButton);
		//cancelButton
		JButton cancelButton = new JButton(Strings.CANCEL);
		cancelButton.setPreferredSize(new Dimension(140,20));
		cancelButton.addActionListener(new CancelActionListener(this));
		buttonsPanel.add(cancelButton);
		
		this.add(namePanel);
		this.add(buttonsPanel);
	}
	
	public class CancelActionListener implements ActionListener{
		NewColorPopupWindow frame;
		public CancelActionListener(NewColorPopupWindow frame){
			this.frame = frame;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			frame.dispose();
		}
	}
	
	public class AcceptActionListener implements ActionListener{
		NewColorPopupWindow frame;
		JTextField nameInput;
		BlobCreatorFrame parent;
		public AcceptActionListener(NewColorPopupWindow frame, JTextField nameInput, BlobCreatorFrame parent){
			this.frame = frame;
			this.nameInput = nameInput;
			this.parent = parent;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			CamibrateBlob blob = new CamibrateBlob(nameInput.getText());
			parent.AddBlob(blob);
			frame.dispose();
		}
	}
	
	public static void main(String[] args) {
		NewColorPopupWindow m = new NewColorPopupWindow("TEST");
		m.setVisible(true);
	}
}

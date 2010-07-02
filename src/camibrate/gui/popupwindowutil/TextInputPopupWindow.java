package camibrate.gui.popupwindowutil;

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

import camibrate.gui.Strings;

public class TextInputPopupWindow extends JFrame {
	String title, prompt, defaultInput;
	FunctionCaller caller;
	public TextInputPopupWindow(String title, String prompt, String defaultInput, FunctionCaller caller){
		this.title = title;
		this.prompt = prompt;
		this.defaultInput = defaultInput;
		this.caller = caller;
		create();
	}
	
	private void create(){
		//frame
		//JFrame popupWindow = new JFrame();
		this.setTitle(title);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(300+25,100);
		this.setLayout(new FlowLayout());
		
		//New Name box
		JPanel namePanel = new JPanel();
		namePanel.setPreferredSize(new Dimension(300,30));
		namePanel.setBackground(Color.red);
		//label
		JLabel nameLabel = new JLabel(prompt);
		nameLabel.setPreferredSize(new Dimension(140,20));
		namePanel.add(nameLabel);
		//input feild
		JTextField nameInput = new JTextField(defaultInput);
		nameInput.setPreferredSize(new Dimension(140,20));
		namePanel.add(nameInput);
		
		//Buttons Box
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setPreferredSize(new Dimension(300,30));
		buttonsPanel.setBackground(Color.green);
		//ok Button
		JButton okButton = new JButton(Strings.ACCEPT);
		okButton.setPreferredSize(new Dimension(140,20));
		okButton.addActionListener(new AcceptActionListener(nameInput, caller, this));
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
		TextInputPopupWindow frame;
		public CancelActionListener(TextInputPopupWindow frame){
			this.frame = frame;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			frame.dispose();
		}
	}
	
	public class AcceptActionListener implements ActionListener{
		TextInputPopupWindow frame;
		FunctionCaller caller;
		JTextField nameInput;
		public AcceptActionListener(JTextField nameInput, FunctionCaller caller, TextInputPopupWindow frame){
			this.frame = frame;
			this.caller = caller;
			this.nameInput = nameInput;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			caller.callFunction(nameInput.getText());
			frame.dispose();
		}
	}

}

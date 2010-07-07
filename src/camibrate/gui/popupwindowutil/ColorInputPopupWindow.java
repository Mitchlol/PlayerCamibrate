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

@SuppressWarnings("serial")
public class ColorInputPopupWindow extends JFrame {
	String title, prompt;
	ColorFunctionCaller caller;
	Color color;
	
	JTextField redTextField, greenTextField, blueTextField;
	public ColorInputPopupWindow(String title, String prompt, ColorFunctionCaller caller){
		this.title = title;
		this.prompt = prompt;
		this.caller = caller;
		color = Color.black;
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
		nameLabel.setPreferredSize(new Dimension(110,20));
		namePanel.add(nameLabel);
		//input feilds
		/*
		JTextField nameInput = new JTextField(defaultInput);
		nameInput.setPreferredSize(new Dimension(140,20));
		namePanel.add(nameInput);
		*/
		redTextField = new JTextField("255");
		greenTextField = new JTextField("255");
		blueTextField = new JTextField("255");
		redTextField.setPreferredSize(new Dimension(30,20));
		greenTextField.setPreferredSize(new Dimension(30,20));
		blueTextField.setPreferredSize(new Dimension(30,20));
		redTextField.addActionListener(new InputActionListener());
		greenTextField.addActionListener(new InputActionListener());
		blueTextField.addActionListener(new InputActionListener());
		
		namePanel.add(new JLabel("R:"));
		namePanel.add(redTextField);
		namePanel.add(new JLabel("G:"));
		namePanel.add(greenTextField);
		namePanel.add(new JLabel("B:"));
		namePanel.add(blueTextField);
		
		//Buttons Box
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setPreferredSize(new Dimension(300,30));
		buttonsPanel.setBackground(Color.green);
		//ok Button
		JButton okButton = new JButton(Strings.ACCEPT);
		okButton.setPreferredSize(new Dimension(140,20));
		okButton.addActionListener(new AcceptActionListener(caller, this));
		buttonsPanel.add(okButton);
		//cancelButton
		JButton cancelButton = new JButton(Strings.CANCEL);
		cancelButton.setPreferredSize(new Dimension(140,20));
		cancelButton.addActionListener(new CancelActionListener(this));
		buttonsPanel.add(cancelButton);
		
		this.add(namePanel);
		this.add(buttonsPanel);
	}
	
	private void updateColor(){
		int red, green, blue;
		try{
			red = Integer.parseInt(redTextField.getText());
		}catch(NumberFormatException ex){
			red = 0;
		}
		try{
			green = Integer.parseInt(greenTextField.getText());
		}catch(NumberFormatException ex){
			green = 0;
		}
		try{
			blue = Integer.parseInt(blueTextField.getText());
		}catch(NumberFormatException ex){
			blue = 0;
		}
		color = new Color(red,green,blue);
	}
	
	public class InputActionListener implements ActionListener{
		ColorInputPopupWindow frame;
		public InputActionListener(){
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			updateColor();
		}
	}
	
	public class CancelActionListener implements ActionListener{
		ColorInputPopupWindow frame;
		public CancelActionListener(ColorInputPopupWindow frame){
			this.frame = frame;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			frame.dispose();
		}
	}
	
	public class AcceptActionListener implements ActionListener{
		ColorInputPopupWindow frame;
		ColorFunctionCaller caller;
		
		public AcceptActionListener(ColorFunctionCaller caller, ColorInputPopupWindow frame){
			this.frame = frame;
			this.caller = caller;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			frame.updateColor();
			caller.callFunction(frame.color);
			frame.dispose();
		}
	}

}


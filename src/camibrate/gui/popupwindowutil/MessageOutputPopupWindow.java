package camibrate.gui.popupwindowutil;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import camibrate.gui.Strings;


public class MessageOutputPopupWindow extends JFrame {
	String title, message;
	public MessageOutputPopupWindow(String title, String message){
		this.title = title;
		this.message = message;
		create();
	}
	
	private void create(){
		//frame
		//JFrame popupWindow = new JFrame();
		this.setTitle(title);
		//this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(300+25,100);
		this.setLayout(new FlowLayout());
		Toolkit tk = Toolkit.getDefaultToolkit();
	    Dimension screenSize = tk.getScreenSize();
	    int screenHeight = screenSize.height;
	    int screenWidth = screenSize.width;
	    this.setLocation(screenWidth/2 - 325/2, screenHeight / 4);
		
		//New Name box
		JPanel namePanel = new JPanel();
		namePanel.setPreferredSize(new Dimension(300,30));
		//namePanel.setBackground(Color.red);
		//label
		JLabel nameLabel = new JLabel(message);
		nameLabel.setPreferredSize(new Dimension(280,20));
		nameLabel.setHorizontalAlignment(JLabel.CENTER);
		namePanel.add(nameLabel);
		
		//Buttons Box
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setPreferredSize(new Dimension(300,30));
		//buttonsPanel.setBackground(Color.green);
		//ok Button
		JButton okButton = new JButton(Strings.ACCEPT);
		okButton.setPreferredSize(new Dimension(140,20));
		okButton.addActionListener(new AcceptActionListener(this));
		buttonsPanel.add(okButton);
		
		this.add(namePanel);
		this.add(buttonsPanel);
	}
	
	public class AcceptActionListener implements ActionListener{
		MessageOutputPopupWindow frame;
		public AcceptActionListener(MessageOutputPopupWindow frame){
			this.frame = frame;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			frame.dispose();
		}
	}
	
	

}

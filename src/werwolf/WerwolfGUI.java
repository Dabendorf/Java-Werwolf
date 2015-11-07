package werwolf;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextField;

public class WerwolfGUI {
	
	private JFrame frame1 = new JFrame("Werwolf");
	private DefaultListModel<String> chatListModel = new DefaultListModel<String>();
    private JList<String> chatList = new JList<String>(chatListModel);
	private JTextField textInput = new JTextField();
	
	public WerwolfGUI() {
		
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.setPreferredSize(new Dimension(600,400));
		//frame1.setMinimumSize(new Dimension(300,200));
		//frame1.setMaximumSize(new Dimension(450,300));
		frame1.setResizable(true);
		
		Container cp = frame1.getContentPane();
		//cp.setLayout(new GridLayout(8,8));
		cp.setLayout(new GridBagLayout());
		cp.add(chatList, new GridBagFelder(0,0,1,1,1,0.9));
		cp.add(textInput, new GridBagFelder(0,1,1,1,1,0.1));
		textInput.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				receiveText(textInput.getText());
			}
		});
		
		frame1.pack();
		frame1.setLocationRelativeTo(null);
		frame1.setVisible(true);
	}
	
	public void receiveText(String text) {
		chatList.setSelectionBackground(new Color(0x33B200));
		chatListModel.addElement(text);
		textInput.setText("");
	}
	
	/*public String sendText() {
		
	}*/
	
	public static void main(String[] args) {
		new WerwolfGUI();
	}
}
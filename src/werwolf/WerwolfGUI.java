package werwolf;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;

public class WerwolfGUI {
	
	private JFrame frame1 = new JFrame("Werwolf");
	private int anzPlayers;
	
	public WerwolfGUI(int anzPlayers) {
		this.anzPlayers = anzPlayers;
		
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.setPreferredSize(new Dimension(600,400));
		//frame1.setMinimumSize(new Dimension(300,200));
		//frame1.setMaximumSize(new Dimension(450,300));
		frame1.setResizable(true);
		Container cp = frame1.getContentPane();
		cp.setLayout(new GridLayout(8,8));
		for(int a=0;a<8;a++) {
			for(int b=0;b<8;b++) {
				cp.add(new PlayerPanel());
			}
		}
		//cp.setLayout(new GridBagLayout());
		
		frame1.pack();
		frame1.setLocationRelativeTo(null);
		frame1.setVisible(true);
		
		
		//Das ist ein Kommentar
	}
}
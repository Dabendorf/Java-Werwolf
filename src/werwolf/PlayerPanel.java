package werwolf;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

public class PlayerPanel extends JPanel {
	
	public PlayerPanel() {
		Random r = new Random();
		this.setBackground(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
	}
	
	public void paintComponent(Graphics g) { 
        super.paintComponent(g);
        
    }

}
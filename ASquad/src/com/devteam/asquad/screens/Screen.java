package com.devteam.asquad.screens;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


public abstract class Screen extends JPanel {

	private static final long serialVersionUID = 1955123981707790575L;
	
	//Sets the nice background image
	protected ImageIcon backgroundImage = new ImageIcon("background.png");

	//Default color behind the image is black
	public Screen() {
		setBackground(Color.BLACK);
	}
	
	//Graphics tool to match the paint component of the background image
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        backgroundImage.paintIcon(this, g, 0, 0);
    }
}
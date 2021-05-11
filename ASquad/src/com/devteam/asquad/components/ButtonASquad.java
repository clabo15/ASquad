package com.devteam.asquad.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

import com.devteam.asquad.FrameASquad;

public class ButtonASquad extends JComponent {

	private static final long serialVersionUID = 2727443966742539578L;
	
	//Button effects
	protected ImageIcon buttonStatic = new ImageIcon("button.png");
	protected ImageIcon buttonPressed = new ImageIcon("button_pressed.png");
	protected boolean ispressed = false;
	protected String text;
	protected int x, y;

	//This method handles the functionality of the buttons
	public ButtonASquad(String text, int x, int y) {
		this.text = text;
		this.x = x;
		this.y = y;
		enableInputMethods(true);
		setAlignmentX(Component.CENTER_ALIGNMENT);
		addMouseListener(new MouseAdapter() {
			//Reads in mouse interaction
			public void mouseClicked(MouseEvent e) {
				if (e.getX() >= x && e.getX() <= (x + buttonStatic.getIconWidth()) && e.getY() >= y && e.getY() <= (y + buttonStatic.getIconHeight())) {
					ispressed = true;
					FrameASquad.repaint();
				}
			}
		});
	}
	
	//Button function when pressed
	public boolean isPressed() {
		return ispressed;
	}
	
	//This method handles the Graphics of the icon
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		buttonStatic.paintIcon(this, g, x, y);
		if (ispressed)
			buttonPressed.paintIcon(this, g, x, y);
		g.setColor(Color.WHITE);
		g.drawString(text, (x + (buttonStatic.getIconWidth() / 2) - 15), (y + (buttonStatic.getIconHeight() / 2)));
	}
}
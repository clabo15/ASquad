package com.devteam.asquad.screens;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.devteam.asquad.FrameASquad;
import com.devteam.asquad.components.ButtonASquad;
import com.devteam.asquad.networking.Server;

public class ScreenRegister extends Screen {
	
	private static final long serialVersionUID = -2790636754461672583L;

	public ScreenRegister() {
		super();
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		//Creates the Register boxes
		JTextField usernameField = new JTextField("username...");
		JPasswordField passwordField = new JPasswordField("testtest");
		ButtonASquad registerButton = new ButtonASquad("Register", 260, 80);
		ButtonASquad backButton = new ButtonASquad("Back", 260, 80);
		
		//Sets the position and alignment of the boxes
		usernameField.setAlignmentX(Component.CENTER_ALIGNMENT);
		usernameField.setMaximumSize(new Dimension(80, 80));
		passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
		passwordField.setMaximumSize(new Dimension(80, 80));
		
		//Event listener for mouse
		registerButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (registerButton.isPressed())
					if(Server.createUser(usernameField.getText(), new String(passwordField.getPassword())))
						FrameASquad.setContentScreen(new ScreenLogin());
			}
		});
		
		//Event listener for mouse on back button
		backButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (backButton.isPressed())
					FrameASquad.setContentScreen(new ScreenLogin());
			}
		});
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		add(Box.createVerticalGlue());
		add(usernameField);
		add(passwordField);
		add(registerButton);
		add(backButton);
		add(Box.createVerticalGlue());
	}
}
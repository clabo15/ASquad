package com.devteam.asquad.screens;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.devteam.asquad.FrameASquad;
import com.devteam.asquad.components.ButtonASquad;
import com.devteam.asquad.networking.Server;

public class ScreenLogin extends Screen {

	private static final long serialVersionUID = 2094680362633157756L;

	//Handles the login boxes
	public ScreenLogin() {
		super();
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		//Handles the username box and password box
		JTextField usernameField = new JTextField("username...");
		JPasswordField passwordField = new JPasswordField("testtest");
		ButtonASquad loginButton = new ButtonASquad("Login", 260, 80);
		JLabel createAccountLabel = new JLabel("Create New Account");

		//Handles the positioning of the boxes
		usernameField.setAlignmentX(Component.CENTER_ALIGNMENT);
		usernameField.setMaximumSize(new Dimension(100, 100)); //Max Size
		passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
		passwordField.setMaximumSize(new Dimension(100, 100)); //Max Size

		createAccountLabel.setForeground(Color.WHITE); //Color of the inside of the boxes
		createAccountLabel.setAlignmentX(Component.CENTER_ALIGNMENT); //Alignment of the boxes
		createAccountLabel.setMaximumSize(new Dimension(160, 80)); //Max size
		
		//Event listener for mouse
		createAccountLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				FrameASquad.setContentScreen(new ScreenRegister());
			}
		});

		//Mouse functionality across boxes
		loginButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//Check if account exists and checks for correct password
				if (loginButton.isPressed()) {
					String password = Server.getPassword(usernameField.getText());
					if (password.equals("NULL")) {
						JOptionPane.showMessageDialog(null, "Account doesn't exist!");
					} else if (password.equals(new String(passwordField.getPassword()))) {
						JOptionPane.showMessageDialog(null, "Login Successful!");
						FrameASquad.setContentScreen(new ScreenMain(Server.getUserInformation(usernameField.getText())));
					} else {
						JOptionPane.showMessageDialog(null, "Login Unsuccessful!");
					}
				}
			}
		});

		add(Box.createVerticalGlue());
		add(usernameField);
		add(passwordField);
		add(loginButton);
		add(Box.createVerticalGlue());
		add(createAccountLabel);
		add(Box.createVerticalGlue());
	}
}
package com.devteam.asquad.screens;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;

import com.devteam.asquad.FrameASquad;
import com.devteam.asquad.components.ButtonASquad;
import com.devteam.asquad.networking.UserASquad;

public class ScreenMyProfile extends Screen {

	private static final long serialVersionUID = 7299018241695599618L;
	
	//This method handles the options menu 
	public ScreenMyProfile(UserASquad user) {
		super();
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		//Buttons for Back, Messages, and Edit Bio options
		ButtonASquad backButton = new ButtonASquad("Back", 260, 0);
		ButtonASquad messageButton = new ButtonASquad("Messsages", 260, 0);
		ButtonASquad editButton = new ButtonASquad("Edit Bio", 260, 0);
		
		//Event listener for mouse on back button
		backButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (backButton.isPressed()) {
					user.update();
					FrameASquad.setContentScreen(new ScreenMain(user));
				}
			}
		});
		
		//Event listener for mouse on check messages button
		messageButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//Checks to see if there are unread messages and displays them if there are unread messages
				if (messageButton.isPressed()) {
					ArrayList<String> messages = user.getMessages();
					if(messages == null) {
						JOptionPane.showMessageDialog(null, "You have 0 unread messages!");
					} else if(messages.size() == 0) {
						JOptionPane.showMessageDialog(null, "You have 0 unread messages!");
					} else {
						if(JOptionPane.showConfirmDialog(null, "You have " + messages.size() + " unread messages!\nWould you like to read them?") == 0) {
							for(String message : messages) {
								JOptionPane.showMessageDialog(null, message);
							}
						}
					}
				}
			}
		});
		
		//Event listener for mouse on Edit bio button
		editButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (editButton.isPressed())
					user.setBio(JOptionPane.showInputDialog("(Bio Limit 255 Characters)"));
			}
		});
		
		add(Box.createVerticalGlue());
		add(backButton);
		add(messageButton);
		add(editButton);
		add(Box.createVerticalGlue());
	}
}
package com.devteam.asquad.screens;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;

import com.devteam.asquad.FrameASquad;
import com.devteam.asquad.components.ButtonASquad;
import com.devteam.asquad.components.PaneUserInfo;
import com.devteam.asquad.networking.Server;
import com.devteam.asquad.networking.UserASquad;

public class ScreenMain extends Screen {

	private static final long serialVersionUID = -272567799070901650L;
	
	//Random user select for messaging a random registered user
	private UserASquad randomUser;
	
	//This method handles what you see when you first login
	public ScreenMain(UserASquad user){
		super();
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		//Random user element
		randomizeUser(user);
		
		//Buttons for Logout, Next User, Message User, My Profile, and random user display for message board
		ButtonASquad logoutButton = new ButtonASquad("Logout", 525, 0);
		ButtonASquad randomizeButton = new ButtonASquad("Next User", 260, 0);
		ButtonASquad messageButton = new ButtonASquad("Message User", 260, 0);
		ButtonASquad profileButton = new ButtonASquad("My Profile", 0, 14);
		PaneUserInfo userFindPane = new PaneUserInfo(randomUser);
		
		//Event listener for mouse on logout button
		logoutButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (logoutButton.isPressed()) {
					FrameASquad.setContentScreen(new ScreenLogin());
				}
			}
		});
		
		//Event listener for mouse on profile button
		profileButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (profileButton.isPressed())
					FrameASquad.setContentScreen(new ScreenMyProfile(user));
			}
		});
		
		//Event listener for mouse on next user button
		randomizeButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (randomizeButton.isPressed()) {
					randomizeUser(user);
					userFindPane.changeUser(randomUser);
				}
			}
		});
		
		//Event listener for mouse on send message button
		messageButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				randomUser.sendMessage(user.getUsername(), JOptionPane.showInputDialog("(Message Limit 255 Characters)"));
			}
		});
		
		add(logoutButton);
		add(Box.createVerticalGlue());
		add(randomizeButton);
		add(Box.createVerticalGlue());
		
		userFindPane.addToPanel(this);
		
		add(Box.createVerticalGlue());
		add(messageButton);
		add(Box.createVerticalGlue());
		add(profileButton);
	}
	
	//This method handles the selection of the random user for messaging from the mysql database
	public void randomizeUser(UserASquad user) {
		UserASquad newRandomUser = Server.getRandomUser();
		if((newRandomUser == null)) {
			randomizeUser(user);
		} else if(newRandomUser.getUsername().equalsIgnoreCase(user.getUsername())) {
			randomizeUser(user);
		} else if(randomUser != null) {
			if(newRandomUser.getUsername().equalsIgnoreCase(randomUser.getUsername())) {
				randomizeUser(user);
			} else {
				randomUser = newRandomUser;
				System.out.println("Random User is now : " + randomUser.getUsername());
			}
		} else {
			randomUser = newRandomUser;
			System.out.println("Random User is now : " + randomUser.getUsername());
		}
	}
}
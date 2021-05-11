package com.devteam.asquad.networking;

import java.util.ArrayList;

public class UserASquad {
	
	private String username;
	private String bio;
	
	//This method matches username and bio
	public UserASquad(String username, String bio) {
		this.username = username;
		this.bio = bio;
	}
	
	//This method outputs any unread messages
	public ArrayList<String> getMessages(){
		return Server.getMessages(username);
	}
	
	//This method handles the actual sending of the message from user side
	public void sendMessage(String sender, String message) {
		if(message != null)
			if(message.length() > 0)
				Server.sendMessage(sender, username, message);
	}
	
	//Gets username
	public String getUsername() {
		return username;
	}
	
	//Gets user bio
	public String getBio() {
		return bio;
	}
	
	//Saves user bio
	public void setBio(String bio) {
		this.bio = bio;
	}
	
	//Updates all input information back to the server
	public void update() {
		Server.updateUserInformation(this);
	}
}
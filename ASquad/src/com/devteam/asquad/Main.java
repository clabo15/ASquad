package com.devteam.asquad;

import javax.swing.JOptionPane;

import com.devteam.asquad.networking.Server;
import com.devteam.asquad.screens.ScreenLogin;

public class Main {

	//This is the driver code that powers the panel
	public static void main(String[] args) {
		FrameASquad.create("ASquad", 800, 600);
		FrameASquad.center();

		if (!Server.tryConnection()) {
			JOptionPane.showMessageDialog(null, "Failed to connect to ASquad Server!");
			FrameASquad.destroy();
		} else {
			System.out.println("Successfully connected to the ASquad Server!");
		}

		FrameASquad.setVisibility(true);
		FrameASquad.setContentScreen(new ScreenLogin());
	}
}
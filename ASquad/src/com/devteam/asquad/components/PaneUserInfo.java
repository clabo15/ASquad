package com.devteam.asquad.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.devteam.asquad.networking.UserASquad;

public class PaneUserInfo {
	
	private JLabel usernameLabel = new JLabel();
	private JTextArea bioTextArea = new JTextArea();
	private JScrollPane bioScrollPane = new JScrollPane(bioTextArea);
	
	//This method handles the user input of information
	public PaneUserInfo(UserASquad user) {
		super();
		
		usernameLabel.setFont(new Font("Verdana", 1, 20));
		usernameLabel.setPreferredSize(new Dimension(300, 100));
		usernameLabel.setMaximumSize(new Dimension(300, 100));
		usernameLabel.setMinimumSize(new Dimension(300, 100));
		usernameLabel.setText(user.getUsername());
		usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		usernameLabel.setOpaque(true);
		usernameLabel.setBackground(Color.LIGHT_GRAY);
		
        bioTextArea.setFont(new Font("Serif", Font.PLAIN, 16));
        bioTextArea.setLineWrap(true);
        bioTextArea.setWrapStyleWord(true);
        bioTextArea.setEditable(false);
        bioTextArea.setText(user.getBio());
		
        bioScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        bioScrollPane.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createCompoundBorder(
                                BorderFactory.createTitledBorder("Bio"),
                                BorderFactory.createEmptyBorder(5,5,5,5)),
                bioScrollPane.getBorder()));
        bioScrollPane.setPreferredSize(new Dimension(300, 100));
        bioScrollPane.setMaximumSize(new Dimension(300, 100));
        bioScrollPane.setMinimumSize(new Dimension(300, 100));
        bioScrollPane.setBackground(Color.LIGHT_GRAY);
        bioScrollPane.setForeground(Color.WHITE);
	}

	//Adds username and bio to panel
	public void addToPanel(JPanel panel) {
		panel.add(usernameLabel);
		panel.add(bioScrollPane);
	}
	
	//Select different user profile to interact with
	public void changeUser(UserASquad user) {
		bioTextArea.setText(user.getBio());
		usernameLabel.setText(user.getUsername());
	}
}
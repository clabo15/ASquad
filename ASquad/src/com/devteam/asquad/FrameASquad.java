package com.devteam.asquad;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.devteam.asquad.networking.Server;
import com.devteam.asquad.screens.Screen;

public class FrameASquad {
	
	//This app is designed using JFrame
	private static JFrame frame;
	
	//This method creates and structures the frame of the app
	public static void create(String title, int width, int height) {
		frame = new JFrame(); 
		frame.setVisible(false);
		frame.setTitle(title);
		frame.setResizable(false);
		frame.setPreferredSize(new Dimension(width, height));
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
            	if(JOptionPane.showConfirmDialog(null, "Are you sure you want to close?") == 0)
            		destroy();	
            }
        });
	}
	
	//This method updates the GUI
	public static void repaint() {
		frame.repaint();
	}
	
	//This method centers the position of the app to the center of your monitor
	public static void center() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getPreferredSize().width/2, dim.height/2-frame.getPreferredSize().height/2);
	}
	
	//This method sets the visible flag on the frame
	public static void setVisibility(boolean flag) {
		frame.setVisible(flag);
	}
	
	//This method sets the content to screen
	public static void setContentScreen(Screen screen) {
		frame.setContentPane(screen);
		frame.validate();
		frame.pack();
	}
	
	//This method terminates the processes when closed
	public static void destroy() {
		frame.dispose();
		if(Server.closeConnection()) {
			System.out.println("Server connection closed!");
		} else {
			System.out.println("Unable to close Server Connection!");
		}
		frame = null;
		System.gc();
		System.exit(0);
	}
}
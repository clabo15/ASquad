package com.devteam.asquad.networking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

import com.devteam.asquad.FrameASquad;

public class Server {

	
	private static Connection connection = null;

	//Connects application to a mysql server to store information 
	public static boolean tryConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			//Replace IP with the ip from the READ ME text file and replace user and password with the provided credientials from the READ ME text file
			//Additionaly, I will also provide what the line below should look like to allow access to the mysql server
			connection = DriverManager.getConnection(
					"jdbc:mysql://IP:3306/users?connectTimeout=4000&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"user", "password");
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	//Closes the connection
	public static boolean closeConnection() {
		try {
			if (connection != null)
				connection.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	//Matches username and password when login
	public static String getPassword(String username) {
		String password = "NULL";
		try {
			ResultSet set = Server
					.executeQueryResult("SELECT * FROM `userlist` WHERE `username` = '" + username + "' LIMIT 1");
			while (set.next()) {
				password = set.getString(2);
			}
			set.close();
		} catch (Exception e) {
			lostConnection();
		}
		return password;
	}

	//Stores input user information when creating an account
	public static boolean createUser(String username, String password) {
		boolean flag = false;
		if (username.equalsIgnoreCase("username...")) {
			JOptionPane.showMessageDialog(null, "You must specify a username!");
		} else {
			if (getPassword(username).equals("NULL")) {
				flag = true;
				Server.executeQuery(
						" insert into userlist (username, password) values ('" + username + "', '" + password + "')");
				Server.executeQuery(" insert into userinfo (username, bio) values ('" + username + "', 'bio...')");
				JOptionPane.showMessageDialog(null, "Account created! Please log in!");
			} else {
				JOptionPane.showMessageDialog(null, "Account already exist!");
			}
		}
		return flag;
	}

	//This method gets user information from the database
	public static UserASquad getUserInformation(String username) {
		try {
			UserASquad user = null;
			ResultSet set = Server
					.executeQueryResult("SELECT * FROM `userinfo` WHERE `username` = '" + username + "' LIMIT 1");
			while (set.next())
				user = new UserASquad(set.getString(1), set.getString(2));
			set.close();
			return user;
		} catch (Exception e) {
			lostConnection();
			return null;
		}
	}

	//This method is used to get a random user when using the chat feature
	public static UserASquad getRandomUser() {
		try {
			ArrayList<UserASquad> allUsers = new ArrayList<UserASquad>();
			ResultSet set = Server.executeQueryResult("SELECT * FROM userinfo");
			while (set.next())
				allUsers.add(getUserInformation(set.getString(1)));
			System.out.println("Busy!");
			set.close();
			return allUsers.get(new Random().nextInt(allUsers.size()));
		} catch (Exception e) {
			lostConnection();
			return null;
		}
	}

	//This method performs updates when new user information is entered
	public static void updateUserInformation(UserASquad user) {
		Server.executeQuery(
				"update userinfo set bio = '" + user.getBio() + "' where username = '" + user.getUsername() + "'");
	}

	//This method sends message to the intended reciver
	public static void sendMessage(String sender, String receiver, String message) {
		Server.executeQuery(" insert into usermessages (sender, receiver, message) values ('" + sender + "', '"
				+ receiver + "', '" + message + "')");
	}

	//This method pulls the stored message and displays it to the screen of the intended receiver
	public static ArrayList<String> getMessages(String username) {
		try {
			ArrayList<String> messages = new ArrayList<String>();
			ResultSet set = Server
					.executeQueryResult("SELECT * FROM `usermessages` WHERE `receiver` = '" + username + "'");
			while (set.next())
				messages.add("From " + set.getString(1) + " : " + set.getString(3));
			System.out.println("Busy!");
			Server.executeQuery("delete from usermessages where receiver = '" + username + "'");
			set.close();
			return messages;
		} catch (Exception e) {
			lostConnection();
			return null;
		}
	}

	//This method handles a sudden loss of connection
	private static void lostConnection() {
		JOptionPane.showMessageDialog(null, "Lost Connection from Server!");
		FrameASquad.destroy();
	}

	//SQL exception method in the event of connection based termination
	public static ResultSet executeQueryResult(String query) throws SQLException {
		return connection.prepareStatement(query).executeQuery();
	}

	//Executes the exception message query
	public static void executeQuery(String query) {
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			lostConnection();
		}
	}
}

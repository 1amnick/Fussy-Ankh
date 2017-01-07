package com.iamnick.code;

import java.sql.*;

public class DB {

	public static boolean isSubbed(String nameQuery) throws Exception {
		// TODO check user if they are subscribers
		Connection conn = DBConnect("ExternalSubDB");
		String query = "SELECT * from ExternalSub where User like '" + nameQuery + "'";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		try{
			rs.getString("User");
			return true;
		}catch (SQLException c) {
			return false;
		}
	}
	public static void updateJoin(String nameQuery) throws SQLException, Exception{
		Connection conn = DBConnect("JoinDB");
		String query = "SELECT * from JoinEvent where Name like '" + nameQuery + "'";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		int ID = rs.getInt("ID");
		System.out.println(ID);
	}


	static Connection conn = null;

	static String home = System.getProperty("user.home");
	public void getHome(){
		System.out.println(home);
	}
	public static Connection DBConnect(String table) throws Exception{

		try {
			// db parameters
			String url = "jdbc:sqlite:" + home + "\\AppData\\Roaming\\AnkhHeart\\AnkhBotR2\\Twitch\\Databases\\" + table + ".sqlite";//+ table +".sqlite";
			// create a connection to the database
			System.out.println(url);
			conn = DriverManager.getConnection(url);

			System.out.println("Connected to SQLite Database");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} 

		return conn;
	}

}

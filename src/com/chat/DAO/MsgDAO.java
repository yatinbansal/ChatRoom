package com.chat.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class MsgDAO {
	private static String dbURL = "jdbc:postgresql://localhost:5432/chatDB";
	private static String name = "postgres";
	private static String passwd = "ytnbnsl";
	public static Connection getConnection() throws ClassNotFoundException, SQLException{
		Connection conn = null;
		
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(dbURL, name, passwd);
			return conn;
	}
	public static void insertData(String user, String msg, String ts) throws ClassNotFoundException, SQLException {
		    Statement stmt = null;
			Connection conn = getConnection();
			stmt = conn.createStatement();
			String sql = "INSERT INTO chat_msg3 (user_name,msg,ts)"
					+ "VALUES ('" + user + "', '" + msg + "', '" + ts + "')";
			stmt.executeUpdate(sql);
			conn.close();
	}

	public static HashMap<Integer, String> getAllData(String lastTime) throws ClassNotFoundException, SQLException {

		Connection conn = getConnection();
		Statement stmt = null;
		HashMap<Integer, String> userMsg = null;
			stmt = conn.createStatement();
			String sql = "SELECT id,user_name,msg FROM chat_msg3 where ts >='"
					+ lastTime + "' order by ts";
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			userMsg = new HashMap<>();
			while (rs.next()) {
				System.out.println("inside while");
				int id = rs.getInt("id");
				String user = rs.getString("user_name");
				String message = rs.getString("msg");
				userMsg.put(id, user + " : " + message);
			}
			conn.close();
			return userMsg;
	}
	
	public static Boolean validateUser(String username,String password) throws ClassNotFoundException, SQLException{
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		String sql = "SELECT passwd from users where userid ='" + username+"'";
		ResultSet rs = stmt.executeQuery(sql);
		System.out.println(password);
		while(rs.next()){
			String passwd = rs.getString("passwd");
			System.out.println(passwd);
			if(passwd.equals(password)) return true;
		}
		conn.close();
		return false;
	}
	
	public static Boolean addUser(String username,String password) throws ClassNotFoundException, SQLException{
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		String sql = "select * from users where userid='"+ username+"'";
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()){
			return false;
		}
		String sqlupdate = "insert into users values (?,?)";
		PreparedStatement ps = conn.prepareStatement(sqlupdate); 
		ps.setString(1, username);
		ps.setString(2, password);
		ps.executeUpdate();
		conn.close();
		return true;
	}
}

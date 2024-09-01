package com.saran.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.saran.models.User;

public class UserDao {
	private Connection con = null;
	
	final private String URL = System.getenv("DB_URL");
	final private String USER = System.getenv("DB_USER");
	final private String PASSWORD = System.getenv("DB_PASSWORD");
	
	public void connect() {
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB connected");
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public void disconnect() {
		if(con == null) {
			return;
		}
		try {
			if( con.isClosed()) {
				return;
			}
			con.close();
			con = null;
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public boolean addUser(String fullname, String username, String email, String pass) {
		String sql = "INSERT INTO users(fullname, username, email, pass) VALUES (?, ?, ?, ?)";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, fullname);
			pst.setString(2, username);
			pst.setString(3, email);
			pst.setString(4, pass);
			int count = pst.executeUpdate();
			System.out.println(count + " Rows affected");
			return true;
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	public User getUser(String email, String pass) {
		String sql = "SELECT * FROM users WHERE email=? AND pass=?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, email);
			pst.setString(2, pass);
			ResultSet rs = pst.executeQuery();
			if(!rs.next()) {
				throw new Exception("User not found!");
			}
			User user = new User();
			user.setUid(rs.getInt("uid"));
			user.setFullname(rs.getString("fullname"));
			user.setUsername(rs.getString("username"));
			user.setEmail(rs.getString("email"));
			user.setPass(rs.getString("pass"));
			return user;
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public boolean checkUser(String email, String pass) {
		User user = this.getUser(email, pass);
		return user != null && user.getPass().equals(pass);
	}
	
	public boolean updateUser(User user) {
		Integer uid = user.getUid();
		if(uid == null) {
			System.out.println("User Id is not provided");
			return false;
		}
		
		StringBuilder sql = new StringBuilder("UPDATE users SET ");
		
		boolean isFirstValue = true;
		String fullname = user.getFullname();
		String username = user.getUsername();
		String email = user.getEmail();
		
		if(fullname != null) {
			sql.append("fullname = ? ");
			isFirstValue = false;
		}
		
		if(username != null) {
			if(!isFirstValue) {
				sql.append(", ");
			}
			sql.append("username = ? ");
			isFirstValue = false;
		}
		
		if(email != null) {
			if(!isFirstValue) {
				sql.append(", ");
			}
			sql.append("email = ? ");
			isFirstValue = false;
		}
		
		if(isFirstValue) {
			System.out.println("No details provided for updation");
			return false;
		}
		
		sql.append(" WHERE uid = ?");
		
		try {
			PreparedStatement pst = con.prepareStatement(sql.toString());
			int parameterIndex = 1;
			
			if(fullname != null) {
				pst.setString(parameterIndex, fullname);
				parameterIndex++;
			}
			
			if(username != null) {
				pst.setString(parameterIndex, username);
				parameterIndex++;
			}
			
			if(email != null) {
				pst.setString(parameterIndex, email);
				parameterIndex++;
			}
			
			pst.setInt(parameterIndex, uid);
			
			int updateCount = pst.executeUpdate();
			
			if(updateCount == 0) {
				System.out.println("User not found!");
				return false;
			}
			
			System.out.println("User updated successfully");
			return true;
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
		
	}
	
	public boolean changePassword(Integer uid, String oldPassword, String newPassword) {
		if(uid == null) {
			System.out.println("User Id is not provided");
			return false;
		}
		
		
		String sql = "UPDATE users SET pass = ? WHERE uid = ? AND pass = ?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, newPassword);
			pst.setInt(2, uid);
			pst.setString(3, oldPassword);
			int updateCount = pst.executeUpdate();
			if(updateCount == 0) {
				System.out.println("User not found!");
				return false;
			}
			else {
				System.out.println("Password updated successfully");
				return true;
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean changePassword(Integer uid, String username, String email, String password) {
		if(uid == null) {
			System.out.println("User Id is not provided");
			return false;
		}
		
		String sql = "UPDATE users SET pass = ? WHERE uid = ? AND username = ? AND email = ?";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, password);
			pst.setInt(2, uid);
			pst.setString(3, username);
			pst.setString(4, email);
			int updateCount = pst.executeUpdate();
			if(updateCount == 0) {
				System.out.println("User not found!");
				return false;
			}
			else {
				System.out.println("Password updated successfully");
				return true;
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}








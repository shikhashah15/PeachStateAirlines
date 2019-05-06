package UI;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import BL.Flights;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;

public class DBConnector {
	
	static String databaseURL = "jdbc:mysql://localhost:3306/flight_app?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	static String DBusername = "root";
	static String DBpassword = "1234abcd";
	
	
	public static Connection getConnection() throws SQLException {
		
//		try {
//			
//			Class.forName("com.mysql.jdbc.Driver");
//			
//		} catch (ClassNotFoundException e) {
//			
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			
//		}
//		
		Connection con = DriverManager.getConnection(databaseURL, DBusername, DBpassword);

		return con;
		
	}
	
	
	public static boolean isValidUser(String username, String pass) {
		
		
		boolean isValid = false;
		
		try {
			
			Connection con = DBConnector.getConnection();
			
			String query = "SELECT `username`, `pass` FROM `flightAppUser`";
			
			PreparedStatement ps = con.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				//this gets the username and password in each row
				String usernameFromDB = rs.getString("username");
				String passFromDB = rs.getString("pass");
				
				if (username.equals(usernameFromDB) && pass.equals(passFromDB) ) {
					
					isValid = true;
					con.close();
					break;
					
				}
				
				
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return isValid;

		
	}
	
	public static boolean validInputs(TextField a, TextField b, TextField c, TextField d, TextField e, TextField f, TextField g, TextField h, TextField i, TextField j) {
		
		boolean validInputs = true; 
		
		if (a.getText().isEmpty() || b.getText().isEmpty() || c.getText().isEmpty() || 
				d.getText().isEmpty()|| e.getText().isEmpty()|| f.getText().isEmpty()|| 
				g.getText().isEmpty()|| h.getText().isEmpty()|| i.getText().isEmpty()|| j.getText().isEmpty()) {
			
			validInputs = false; 
			
		}
		
		return validInputs;
	}
	
	public static boolean userExists(String username) {
		
		boolean userExists = false; 
		
		try {
			
			Connection myConn = DBConnector.getConnection(); 
			String sql = "Select `username` from `flightAppUser`";
			PreparedStatement myStmt = myConn.prepareStatement(sql);
			ResultSet rs = myStmt.executeQuery(); 
			while (rs.next()) {
				
				String usernameFromDB = rs.getString("username");
				
				if (username.equals(usernameFromDB)) {
					
					userExists = true;
					myConn.close();
					break;
					
				}
				
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return userExists; 
		
	}
	
	public static String displaySecurityQuestion(String username) {
		
		String question = ""; 
		
		try {
			
			Connection myConn = DBConnector.getConnection(); 
			String sql = "Select `question` from `flightAppUser` where `username` = ?";
			PreparedStatement myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, username);
			ResultSet rs = myStmt.executeQuery();
			rs.next(); 
			question = rs.getString("question"); 
			
		}
		
		catch (SQLException ex){
			
			ex.printStackTrace();
			
		}
		
		return question; 
		
	}
	
	public static boolean verifyAnswer(String username, String answer) {
		
		boolean correct = false; 

		try {
			
			Connection myConn = DBConnector.getConnection(); 
			String sql = "Select `answer` from `flightAppUser` where `username` = ?";
			PreparedStatement myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, username);
			ResultSet rs = myStmt.executeQuery();
			rs.next(); 
			String correctAnswer = rs.getString("answer");
			
			if(answer.equals(correctAnswer))
				correct = true; 
			
		}
		
		catch (SQLException ex) {
			
			ex.printStackTrace();
			
		}
		
		return correct; 
		
	}
	
	public static String displayPassword(String username) {
		
		String password = ""; 
		
		try {
			
			Connection myConn = DBConnector.getConnection(); 
			String sql = "Select `pass` from `flightAppUser` where `username` = ?";
			PreparedStatement ps = myConn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery(); 
			rs.next(); 
			password = rs.getString("pass"); 
			
		} catch (SQLException ex) {
			
			ex.printStackTrace();
			
		}
		
		return password; 
	}
	
	public static void insertFlightDB (Flights obj) {
		
		try {
			
		
		Connection con = DBConnector.getConnection();
		
		String query = "INSERT INTO `flights`(`origin`, `destination`, `time`, `date`,`username`) VALUES(?,?,?,?,?) ";
		
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, obj.getOrigin());
		ps.setString(2, obj.getDestination());
		ps.setString(3, obj.getTime());
		ps.setString(4, obj.getDate());
		ps.setString(5, obj.getUsername());
		
		ps.executeUpdate();
		
		} catch (Exception ex) {
			
			ex.printStackTrace();
			
		}
		
		
	}
	
	public static ObservableList<Flights> getFlights(String username) {
		
		ObservableList<Flights> obLIST = FXCollections.observableArrayList();
		
		try {
		Connection con = DBConnector.getConnection();
		String query = "SELECT * FROM `flights`";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			if (username.equals(rs.getString("username"))) {
				obLIST.add(new Flights(rs.getString("origin"),rs.getString("destination"),rs.getString("time"), rs.getString("date"), rs.getString("username")));	
			}
		}
		
		} catch(Exception ex) {
			ex.printStackTrace();
		}


		return obLIST; 
		
	}
	
	public static ObservableList<Flights> getAllFlights() {
		
		ObservableList<Flights> allFlights = FXCollections.observableArrayList();
		
		try {
			
			Connection con = DBConnector.getConnection();
			String sql = "Select distinct * from `flights`"; 
			PreparedStatement ps = con.prepareStatement(sql); 
			ResultSet rs = ps.executeQuery();  
			
			while (rs.next()) {
				
				allFlights.add(new Flights(rs.getString("origin"),rs.getString("destination"),rs.getString("time"),rs.getString("date"),rs.getString("username"))); 
				
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return allFlights; 
	}
	
	public static void deleteFlight(String username, Flights selectedFlight) {
		
		try {
			
			Connection con = DBConnector.getConnection();
			String sql = "DELETE from `flights` where `username` = ? and `destination` = ?"; 
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, selectedFlight.getDestination());
			ps.execute(); 
			System.out.println("Flight Deleted"); 
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
	}
	
	public static void adminDeleteFlight(Flights selectedFlight) {
		
		try {
			
			Connection con = DBConnector.getConnection();
			String sql = "DELETE from `flights` where `origin` = ? and `destination` = ? and time = ? and date = ? "; 
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, selectedFlight.getOrigin());
			ps.setString(2, selectedFlight.getDestination());
			ps.setString(3, selectedFlight.getTime());
			ps.setString(4, selectedFlight.getDate());
			ps.execute(); 
			
		} catch (SQLException ex) {
			
			ex.printStackTrace();
			
		}
		
	}
	
	public static Flights adminUpdateFlight(Flights selectedFlight, TextField origin, TextField destination, 
			TextField time, TextField date) {
		
		Flights newFlight = null; 
		
		try {
			
			Connection con = DBConnector.getConnection();
			String sql = "UPDATE `flights` set `origin` = ?, `destination` = ?, `time` = ?,`date` =? "
					+ "where `origin` = ? and `destination` = ? and time = ? and date = ? "; 
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, origin.getText());
			ps.setString(2, destination.getText());
			ps.setString(3, time.getText());
			ps.setString(4, date.getText());
			ps.setString(5, selectedFlight.getOrigin());
			ps.setString(6, selectedFlight.getDestination());
			ps.setString(7, selectedFlight.getTime());
			ps.setString(8, selectedFlight.getDate());
			ps.execute(); 
			
			String sql2 = "SELECT * from `flights` where origin = ? and destination = ? and time = ? and date = ?";
			PreparedStatement ps2 = con.prepareStatement(sql2);
			ps2.setString(1, origin.getText());
			ps2.setString(2, destination.getText());
			ps2.setString(3, time.getText());
			ps2.setString(4, date.getText());
			
			ResultSet rs = ps2.executeQuery(); 
			
			rs.next(); 
			
			newFlight = (new Flights(rs.getString("origin"),rs.getString("destination"),
						rs.getString("time"),rs.getString("date"),rs.getString("username")));

			return newFlight;  
				

			
		} catch (SQLException ex) {
			
			ex.printStackTrace();
			
		}
		
		return newFlight; 
	}
	
	public static Flights getFlight(String origin, String destination, String date, String time) {
		
		Flights selectedFlight = null; 
		
		try {
			
			Connection con = DBConnector.getConnection();

			String sql2 = "SELECT * from `flights` where origin = ? and destination = ? and time = ? and date = ?";
			PreparedStatement ps2 = con.prepareStatement(sql2);
			ps2.setString(1, origin);
			ps2.setString(2, destination);
			ps2.setString(3, time);
			ps2.setString(4, date);
			
			ResultSet rs = ps2.executeQuery(); 
		
			rs.next(); 
		
			selectedFlight = (new Flights(rs.getString("origin"),rs.getString("destination"),
					rs.getString("time"),rs.getString("date"),rs.getString("username")));
			return selectedFlight; 
			
		}
		catch (SQLException ex) {
			return selectedFlight; 
		}
		
	}
	
	public static boolean flightExists(Flights flight, String origin, String destination, String date, String time) {
		
		boolean exists = false; 
		
		try {
			
			Connection con = DBConnector.getConnection();
			String sql = "SELECT * FROM `flights`"; 
			PreparedStatement ps = con.prepareStatement(sql); 
			ResultSet rs = ps.executeQuery(); 
			
			while (rs.next()) {
				
				if(origin.equals("origin") && destination.equals("destination") && date.equals("date") && time.equals("time") ) {
					
					exists = true; 
					
				}
				
			}
			
			
		} catch (SQLException ex) {
			
			ex.printStackTrace();
			
		}
		
		return exists; 
	}

}

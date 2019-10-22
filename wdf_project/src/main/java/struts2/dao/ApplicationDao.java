package struts2.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class containing all database related functions
 *
 */
public class ApplicationDao {
	
	/**
	 * Instantiates a connection to the database
	 * @return Returns the Connection object if successful, otherwise null
	 */
	public static Connection getConnection() {
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("JDBC driver registered");
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ddi_project?serverTimezone=UTC", 
					"root", 
					"password");
		} catch (ClassNotFoundException e) {
			System.out.print("JDBC driver not found");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("DB Connection failed");
			e.printStackTrace();
		}
		
		if (connection != null) {
			System.out.println("DB Connection success");
		}
		
		return connection;
	}
	
	/**
	 * Creates a new user record in the database
	 * Key user info is inserted into 'user' table
	 * 
	 * @param givenName Received from user input
	 * @param surname Received from user input
	 * @param email Received from user input
	 * @param encryptedPassword This is processed in CreateUser.java from user input 
	 * @return Returns 1 if success, 0 if failure
	 */
	public int createUser(String givenName, String surname, String email, String encryptedPassword) {
		int rowsAffected = 0;
		
		try {
			String sql = "insert into user (given_name, surname, email, encrypted_password, date_joined) "
					+ "values (?, ?, ?, ?, CURRENT_DATE())";
			PreparedStatement statement = getConnection().prepareStatement(sql);
			statement.setString(1, givenName);
			statement.setString(2, surname);
			statement.setString(3, email);
			statement.setString(4, encryptedPassword);
			
			rowsAffected = statement.executeUpdate();
			getConnection().close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowsAffected;
	}
	
	/**
	 * Searches the database for a user.
	 * @param email Received from user input
	 * @return Returns null if there is no user record, a ResultSet if otherwise
	 */
	public ResultSet pullUser(String email) {
		ResultSet rs = null;
		
		try {
			String sql = "select * from user where email = ?";
			PreparedStatement statement = getConnection().prepareStatement(sql);
			statement.setString(1, email);
			rs = statement.executeQuery();
			getConnection().close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
	/**
	 * Updates user's password in the database
	 * @param email Received from hidden user input
	 * @param encryptedPassword Received from user input
	 * @return Returns 0 if failure, 1 if success
	 */
	public int changePassword(String email, String encryptedPassword) {
		int rowsAffected = 0;
		
		try {
			String sql = "update user set encrypted_password=? where email=?";
			PreparedStatement statement = getConnection().prepareStatement(sql);
			statement.setString(1, encryptedPassword);
			statement.setString(2, email);
			rowsAffected = statement.executeUpdate();
			getConnection().close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowsAffected;
	}
	
	/**
	 * Searches the database for users based on their name.
	 * Search is case-insensitive.
	 * @param input Received from user input
	 * @return Returns null if there is no user record, a ResultSet if otherwise
	 */
	public ResultSet searchUser(String input) {
		ResultSet rs = null;
		
		try {
			String sql = "select * from userinfolist "
					+ "where given_name collate utf8mb4_general_ci like ? "
					+ "or surname collate utf8mb4_general_ci like ? ";
			PreparedStatement statement = getConnection().prepareStatement(sql);
			statement.setString(1, "%" + input + "%");
			statement.setString(2, "%" + input + "%");
			rs = statement.executeQuery();
			getConnection().close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
	/**
	 * Updates user record in 'user' table
	 * @param givenName Retrieved from user input
	 * @param surname Retrieved from user input
	 * @param email Retrieved from session object
	 * @param contact Retrieved from user input
	 * @param country Retrieved from user input
	 * @param city Retrieved from user input
	 * @param about Retrieved from user input
	 * @param encryptedPassword Retrieved from user input
	 * @return Returns 0 if failure, 1 if success
	 */
	public int updateUser(String givenName, String surname, 
			String email, String contact, String country, 
			String city, String about, String encryptedPassword) {
		int rowsAffected = 0;
		
		try {
			String sql = "update user set given_name=?, surname=?, contact=?, "
					+ "country=?, city=?, about=?, encrypted_password=? where email=?";
			PreparedStatement statement = getConnection().prepareStatement(sql);
			statement.setString(1, givenName);
			statement.setString(2, surname);
			statement.setString(8, email);
			statement.setString(3, contact);
			statement.setString(4, country);
			statement.setString(5, city);
			statement.setString(6, about);
			statement.setString(7, encryptedPassword);
			
			rowsAffected = statement.executeUpdate();
			getConnection().close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rowsAffected;
	}
	
	/**
	 * Searches the database for a user's experience records.
	 * @param userId Received from user model
	 * @return Returns null if there is no user record, a ResultSet if otherwise
	 */
	public ResultSet pullUserExperience(int userId) {
		ResultSet rs = null;
		
		try {
			String sql = "select * from programmers_experience where user_id = ?";
			PreparedStatement statement = getConnection().prepareStatement(sql);
			statement.setInt(1, userId);
			rs = statement.executeQuery();
			getConnection().close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
	/**
	 * Updates user record in 'programmers_experience' table
	 * @param expId Retrieved from form parameters
	 * @param expTitle Retrieved from user input
	 * @param expDesc Retrieved from user input
	 * @param expDuration Retrieved from user input
	 * @param expCompany Retrieved from user input
	 * @return Returns 0 if failure, 1 if success
	 */
	public int updateExperience(int expId, String expTitle, String expDesc, 
			String expDuration, String expCompany) {
		int rowsAffected = 0;
		
		try {
			String sql = "update programmers_experience set exp_title=?, exp_desc=?, exp_duration=?, "
					+ "exp_company=? where exp_id=?";
			PreparedStatement statement = getConnection().prepareStatement(sql);
			statement.setString(1, expTitle);
			statement.setString(2, expDesc);
			statement.setString(3, expDuration);
			statement.setString(4, expCompany);
			statement.setInt(5, expId);
			
			rowsAffected = statement.executeUpdate();
			getConnection().close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rowsAffected;
	}
	
	/**
	 * Delete a user record in "programmers_experience" table
	 * @param expId Retrieved from form parameters
	 * @return Returns 0 if failure, 1 if success
	 */
	public int deleteExperience(int expId) {
		int rowsAffected = 0;
		
		try {
			String sql = "delete from programmers_experience where exp_id = ?";
			PreparedStatement statement = getConnection().prepareStatement(sql);
			statement.setInt(1, expId);
			
			rowsAffected = statement.executeUpdate();
			getConnection().close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowsAffected;
	}
	
	/**
	 * Insert a new user record into "programmers_experience" table
	 * @param expTitle Retrieved from user input
	 * @param expCompany Retrieved from user input
	 * @param expDesc Retrieved from user input
	 * @param expDuration Retrieved from user input
	 * @param userId Retrieved from session object
	 * @return Returns 0 if failure, 1 if success 
	 */
	public int insertExperience(String expTitle, String expCompany, String expDesc, 
			String expDuration, int userId) {
		int rowsAffected = 0;
		
		try {
			String sql = "insert into programmers_experience "
					+ "(exp_title, exp_company, exp_desc, exp_duration, user_id) values (?, ?, ?, ?, ?)";
			PreparedStatement statement = getConnection().prepareStatement(sql);
			statement.setString(1, expTitle);
			statement.setString(2, expCompany);
			statement.setString(3, expDesc);
			statement.setString(4, expDuration);
			statement.setInt(5, userId);
			
			rowsAffected = statement.executeUpdate();
			getConnection().close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowsAffected;
	}
	
	/**
	 * Searches the database for a user's education records.
	 * @param userId Received from user model
	 * @return Returns null if there is no user record, a ResultSet if otherwise
	 */
	public ResultSet pullUserEducation(int userId) {
		ResultSet rs = null;
		
		try {
			String sql = "select * from programmers_education where user_id = ?";
			PreparedStatement statement = getConnection().prepareStatement(sql);
			statement.setInt(1, userId);
			rs = statement.executeQuery();
			getConnection().close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
	/**
	 * Updates user record in 'programmers_education' table
	 * @param eduId Retrieved from form parameters
	 * @param eduTitle Retrieved from user input
	 * @param eduInstitution Retrieved from user input
	 * @param eduDuration Retrieved from user input
	 * @return Returns 0 if failure, 1 if success
	 */
	public int updateEducation(int eduId, String eduTitle, String eduInstitution, 
			String eduDuration) {
		int rowsAffected = 0;
		
		try {
			String sql = "update programmers_education set edu_title=?, edu_institution=?, "
					+ "edu_duration=? where edu_id=?";
			PreparedStatement statement = getConnection().prepareStatement(sql);
			statement.setString(1, eduTitle);
			statement.setString(2, eduInstitution);
			statement.setString(3, eduDuration);
			statement.setInt(4, eduId);
			
			rowsAffected = statement.executeUpdate();
			getConnection().close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rowsAffected;
	}
	
	/**
	 * Delete a user record in "programmers_education" table
	 * @param eduId Retrieved from form parameters
	 * @return Returns 0 if failure, 1 if success
	 */
	public int deleteEducation(int eduId) {
		int rowsAffected = 0;
		
		try {
			String sql = "delete from programmers_education where edu_id = ?";
			PreparedStatement statement = getConnection().prepareStatement(sql);
			statement.setInt(1, eduId);
			
			rowsAffected = statement.executeUpdate();
			getConnection().close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowsAffected;
	}
	
	/**
	 * Insert a new user record into "programmers_education" table
	 * @param eduTitle Retrieved from user input
	 * @param eduInstitution Retrieved from user input
	 * @param eduDuration Retrieved from user input
	 * @param userId Retrieved from session object
	 * @return Returns 0 if failure, 1 if success
	 */
	public int insertEducation(String eduTitle, String eduInstitution, String eduDuration, int userId) {
		int rowsAffected = 0;
		
		try {
			String sql = "insert into programmers_education "
					+ "(edu_title, edu_institution, edu_duration, user_id) values (?, ?, ?, ?)";
			PreparedStatement statement = getConnection().prepareStatement(sql);
			statement.setString(1, eduTitle);
			statement.setString(2, eduInstitution);
			statement.setString(3, eduDuration);
			statement.setInt(4, userId);
			
			rowsAffected = statement.executeUpdate();
			getConnection().close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowsAffected;
	}
	
	/**
	 * Deletes a user record from 'user' table
	 * @param email Retrieved from hidden form
	 * @return Returns 0 if failure, 1 if success
	 */
	public int deleteUser(String email) {
		int rowsAffected = 0;
		
		try {
			String sql = "delete from user where email=?";
			PreparedStatement statement = getConnection().prepareStatement(sql);
			statement.setString(1, email);
			
			rowsAffected = statement.executeUpdate();
			getConnection().close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowsAffected;
	}
	
	/**
	 * Retrieves all information from 'mailing_list' table
	 * @return Returns null if there is no information, a ResultSet if otherwise
	 */
	public ResultSet retrieveMailingList() {
		ResultSet rs = null;
		
		try {
			String sql = "select * from mailing_list";
			rs = getConnection().createStatement().executeQuery(sql);
			getConnection().close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
	/**
	 * Inserts a record into 'mailing_list' table
	 * @param mailName Retrieved from user input
	 * @param mailEmail Retrieved from user input
	 * @return Returns 0 if failure, 1 if success
	 */
	public int addMailingList(String mailName, String mailEmail) {
		int rowsAffected = 0;
		
		try {
			String sql = "insert into mailing_list (mail_name, mail_email) values (?, ?)";
			PreparedStatement statement = getConnection().prepareStatement(sql);
			statement.setString(1, mailName);
			statement.setString(2, mailEmail);
			
			rowsAffected = statement.executeUpdate();
			getConnection().close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowsAffected;
	}
	
	/**
	 * Delete a record from 'mailing_list' table
	 * @param email Retrieved from user input
	 * @return Returns 0 if failure, 1 if success
	 */
	public int deleteMailingList(String email) {
		int rowsAffected = 0;
		
		try {
			String sql = "delete from mailing_list where mail_email = ?";
			PreparedStatement statement = getConnection().prepareStatement(sql);
			statement.setString(1, email);
			
			rowsAffected = statement.executeUpdate();
			getConnection().close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowsAffected;
	}
	
	/**
	 * Deletes ALL records from 'mailing_list' table
	 */
	public void deleteMailingList() {

		try {
			String sql = "truncate mailing_list";
			getConnection().createStatement().executeUpdate(sql);
			getConnection().close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
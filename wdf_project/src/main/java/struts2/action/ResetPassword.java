package struts2.action;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jasypt.util.password.BasicPasswordEncryptor;

import com.opensymphony.xwork2.ActionSupport;

import struts2.dao.ApplicationDao;

/**
 * Checks database for user's account.
 * If user account exists, user will be able to change password
 *
 */
public class ResetPassword extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
	private String email;
	private String password;
	private int userId;
	private int result;
	
	/**
	 * Throws an error to the user if email input does not exist in the database
	 * 
	 */
	public String checkUser() {
		ApplicationDao dao = new ApplicationDao();
		userId = 0;
		result = 1;
		
		try {
			ResultSet rs = dao.pullUser(email);
			while (rs.next()) {
				userId = rs.getInt("user_id");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (userId > 0) {
			return "success";
		} else {
			return "input";
		}
	}
	
	/**
	 * Uses jasypt utility to encrypt password
	 * 
	 */
	public String changePassword() {
		ApplicationDao dao = new ApplicationDao();
		result = 0;
		
		BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
		String encryptedPassword = passwordEncryptor.encryptPassword(password);
		
		result = dao.changePassword(email, encryptedPassword);
		if (result == 1) {
			return "success";
		} else {
			return "input";
		}	
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}
}

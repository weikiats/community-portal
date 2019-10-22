package struts2.action;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.jasypt.util.password.BasicPasswordEncryptor;

import com.opensymphony.xwork2.ActionSupport;

import struts2.dao.ApplicationDao;

/**
 * Validate user credentials for login.
 * Invalidate session objects created for logout.
 *
 */
public class UserLogin extends ActionSupport implements SessionAware{
	private static final long serialVersionUID = 1L;
	
	private String email;
	private String password;
	private String result;
	private SessionMap<String, Object> sessionMap;
	
	public String login() {
		ApplicationDao dao = new ApplicationDao();
		result = "input";
		int userId = 0;
		String givenName = null;
		String databaseEncryptedPassword = null;
		int role = 0;
		BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
		
		try {
			ResultSet rs = dao.pullUser(email);
			
			while (rs.next()) {
				userId = rs.getInt("user_id");
				givenName = rs.getString("given_name");
				databaseEncryptedPassword = rs.getString("encrypted_password");
				role = rs.getInt("role");
			}
			
			// If login credentials are valid, add various session attributes
			if (passwordEncryptor.checkPassword(password, databaseEncryptedPassword)) {
				result = "success";
				sessionMap.put("login", true);
				sessionMap.put("name", givenName);
				sessionMap.put("email", email);
				sessionMap.put("userId", userId);
				
				// If user is administrator, return an admin result
				if (role == 1) {
					result = "admin";
				}
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public String logout() {
		sessionMap.invalidate();
		
		return SUCCESS;
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
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	public Map<String, Object> getSession() {
		return sessionMap;
	}
	
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = (SessionMap<String, Object>)sessionMap;
	}
}

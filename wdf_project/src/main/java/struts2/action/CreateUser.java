package struts2.action;

import org.jasypt.util.password.BasicPasswordEncryptor;

import com.opensymphony.xwork2.ActionSupport;

import struts2.dao.ApplicationDao;

/**
 * Creates a user record in the database.
 * User input is taken and validated.
 * Upon successful validation, user password will be encrypted with 'jasypt' utility
 * before inserting into the database.
 *
 */
public class CreateUser extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
	private String givenName;
	private String surname;
	private String email;
	private String password;
	private String cfPassword;
	private int result;
	
	public String execute() {
		ApplicationDao dao = new ApplicationDao();
		result = 0;
		
		try {
			BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
			String encryptedPassword = passwordEncryptor.encryptPassword(password);
			
			result = dao.createUser(givenName, surname, email, encryptedPassword);
			if (result == 1) {
				System.out.println("Submission success!");
			} else {
				System.out.println("Submission error!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	/**
	 * Validates user input.
	 * Given Name and Surname cannot contain numerics or symbols.
	 * Password needs to be at least 6 characters.
	 * Confirm Password field must match Password field.
	 * 
	 */
	public void validate() {
		if (!getGivenName().matches("^[ A-Za-z]+$")) {
			addFieldError(givenName, "Given Name cannot contain numerics or symbols");
		}
		if (!getSurname().matches("^[ A-Za-z]+$")) {
			addFieldError(surname, "Surname cannot contain numerics or symbols");
		}
		if (getPassword().length()<6) {
			addFieldError(password, "Password needs to be at least 6 characters");
		}
		if (!getPassword().equals(getCfPassword())) {
			addFieldError(cfPassword, "Passwords do not match");
		}
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
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

	public String getCfPassword() {
		return cfPassword;
	}

	public void setCfPassword(String cfPassword) {
		this.cfPassword = cfPassword;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}	
}

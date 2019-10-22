package struts2.action;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jasypt.util.password.BasicPasswordEncryptor;

import com.opensymphony.xwork2.ActionSupport;

import struts2.dao.ApplicationDao;

/**
 * Retrieves user information from 'user' table.
 * Allows user to update information pulled.
 *
 */
public class UpdateUser extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
	private String givenName;
	private String surname;
	private String email;
	private String contact;
	private String country;
	private String city;
	private String about;
	private String password;
	private boolean error;
	
	public String pullUser() {
		ApplicationDao dao = new ApplicationDao();
		ResultSet rs = dao.pullUser(email);
		
		try {
			while (rs.next()) {				
				givenName = rs.getString("given_name");
				surname = rs.getString("surname");
				contact = rs.getString("contact");
				country = rs.getString("country");
				city = rs.getString("city");
				about = rs.getString("about");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	/**
	 * Uses jasypt utility to encrypt password
	 * 
	 */
	public String updateUser() {
		ApplicationDao dao = new ApplicationDao();
		
		BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
		String encryptedPassword = passwordEncryptor.encryptPassword(password);
		
		int result = dao.updateUser(givenName, surname, email, 
				contact, country, city, about, encryptedPassword);
		
		if (result!=1) {
			setError(true);
			return "input";
		} else {
			return "success";
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

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}
}

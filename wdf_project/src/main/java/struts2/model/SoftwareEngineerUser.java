package struts2.model;

/**
 * Model for pulling user information from 'user' table
 *
 */
public class SoftwareEngineerUser {
	private int userId;
	private String givenName;
	private String surname;
	private String email;
	private String contact;
	private String country;
	private String city;
	private String displayPicture;
	private String about;
	private String dateJoined;
	private String encryptedPassword;
	private int role;
	private String lastLogin;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
	public String getDisplayPicture() {
		return displayPicture;
	}
	public void setDisplayPicture(String displayPicture) {
		this.displayPicture = displayPicture;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public String getDateJoined() {
		return dateJoined;
	}
	public void setDateJoined(String dateJoined) {
		this.dateJoined = dateJoined;
	}
	public String getEncryptedPassword() {
		return encryptedPassword;
	}
	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public String getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}
}

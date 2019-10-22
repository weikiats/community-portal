package struts2.model;

/**
 * Model for pulling user information from 'programmers_experience' table
 *
 */
public class SoftwareEngineerExperience {
	private int expId;
	private String expTitle;
	private String expDesc;
	private String expDuration;
	private String expCompany;
	
	public int getExpId() {
		return expId;
	}
	public void setExpId(int expId) {
		this.expId = expId;
	}
	public String getExpTitle() {
		return expTitle;
	}
	public void setExpTitle(String expTitle) {
		this.expTitle = expTitle;
	}
	public String getExpDesc() {
		return expDesc;
	}
	public void setExpDesc(String expDesc) {
		this.expDesc = expDesc;
	}
	public String getExpDuration() {
		return expDuration;
	}
	public void setExpDuration(String expDuration) {
		this.expDuration = expDuration;
	}
	public String getExpCompany() {
		return expCompany;
	}
	public void setExpCompany(String expCompany) {
		this.expCompany = expCompany;
	}
}

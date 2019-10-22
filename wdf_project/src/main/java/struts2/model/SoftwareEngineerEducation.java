package struts2.model;

/**
 * Model for pulling user information from 'programmers_education' table
 *
 */
public class SoftwareEngineerEducation {
	private int eduId;
	private String eduTitle;
	private String eduInstitution;
	private String eduDuration;
	
	public int getEduId() {
		return eduId;
	}
	public void setEduId(int eduId) {
		this.eduId = eduId;
	}
	public String getEduTitle() {
		return eduTitle;
	}
	public void setEduTitle(String eduTitle) {
		this.eduTitle = eduTitle;
	}
	public String getEduInstitution() {
		return eduInstitution;
	}
	public void setEduInstitution(String eduInstitution) {
		this.eduInstitution = eduInstitution;
	}
	public String getEduDuration() {
		return eduDuration;
	}
	public void setEduDuration(String eduDuration) {
		this.eduDuration = eduDuration;
	}
}

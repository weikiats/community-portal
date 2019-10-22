package struts2.action;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import struts2.dao.ApplicationDao;
import struts2.model.SoftwareEngineerEducation;

/**
 * Retrieves user information from 'programmers_education' table.
 * Allows user to update information pulled.
 *
 */
public class UpdateEducation extends ActionSupport implements SessionAware {
	private static final long serialVersionUID = 1L;
	
	private SoftwareEngineerEducation userEdu;
	private List<SoftwareEngineerEducation> userEduList;
	private SessionMap<String, Object> sessionMap;
	
	private int eduId;
	private String eduTitle;
	private String eduInstitution;
	private String eduDuration;
	private boolean error;
	
	public String pullUserEducation() {
		ApplicationDao dao = new ApplicationDao();
		userEduList = new ArrayList<SoftwareEngineerEducation>();
		ResultSet rs = dao.pullUserEducation((Integer)sessionMap.get("userId"));
		
		try {
			while (rs.next()) {
				userEdu = new SoftwareEngineerEducation();
				userEdu.setEduId(rs.getInt("edu_id"));
				userEdu.setEduTitle(rs.getString("edu_title"));
				userEdu.setEduInstitution(rs.getString("edu_institution"));
				userEdu.setEduDuration(rs.getString("edu_duration"));
				userEduList.add(userEdu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String updateUserEducation() {
		ApplicationDao dao = new ApplicationDao();
		int result = dao.updateEducation(eduId, eduTitle, eduInstitution, eduDuration);
		
		if (result!=1) {
			return "input";
		} else {
			return "success";
		}
	}
	
	public String deleteUserEducation() {
		ApplicationDao dao = new ApplicationDao();
		int result = dao.deleteEducation(eduId);

		if (result!=1) {
			return "input";
		} else {
			return "success";
		}
	}
	
	public String insertUserEducation() {
		ApplicationDao dao = new ApplicationDao();
		int result = dao.insertEducation(eduTitle, eduInstitution, eduDuration, 
				(Integer)sessionMap.get("userId"));
		
		if (result!=1) {
			setError(true);
			return "input";
		} else {
			return "success";
		}
	}

	public SoftwareEngineerEducation getUserEdu() {
		return userEdu;
	}

	public void setUserEdu(SoftwareEngineerEducation userEdu) {
		this.userEdu = userEdu;
	}

	public List<SoftwareEngineerEducation> getUserEduList() {
		return userEduList;
	}

	public void setUserEduList(List<SoftwareEngineerEducation> userEduList) {
		this.userEduList = userEduList;
	}

	public Map<String, Object> getSession() {
		return sessionMap;
	}
	
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = (SessionMap<String, Object>)sessionMap;
	}

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

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}
}

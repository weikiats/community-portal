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
import struts2.model.SoftwareEngineerExperience;

/**
 * Retrieves user information from 'programmers_experience' table.
 * Allows user to update information pulled.
 *
 */
public class UpdateExperience extends ActionSupport implements SessionAware {
	private static final long serialVersionUID = 1L;
	
	private SoftwareEngineerExperience userExp;
	private List<SoftwareEngineerExperience> userExpList;
	private SessionMap<String, Object> sessionMap;
	
	private int expId;
	private String expTitle;
	private String expDesc;
	private String expDuration;
	private String expCompany;
	private boolean error;
	
	public String pullUserExperience() {
		ApplicationDao dao = new ApplicationDao();
		userExpList = new ArrayList<SoftwareEngineerExperience>();
		ResultSet rs = dao.pullUserExperience((Integer)sessionMap.get("userId"));
		
		try {
			while (rs.next()) {
				userExp = new SoftwareEngineerExperience();
				userExp.setExpId(rs.getInt("exp_id"));
				userExp.setExpTitle(rs.getString("exp_title"));
				userExp.setExpCompany(rs.getString("exp_company"));
				userExp.setExpDesc(rs.getString("exp_desc"));
				userExp.setExpDuration(rs.getString("exp_duration"));
				userExpList.add(userExp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String updateUserExperience() {
		ApplicationDao dao = new ApplicationDao();
		int result = dao.updateExperience(expId, expTitle, expDesc, expDuration, expCompany);
		
		if (result!=1) {
			return "input";
		} else {
			return "success";
		}
	}
	
	public String deleteUserExperience() {
		ApplicationDao dao = new ApplicationDao();
		int result = dao.deleteExperience(expId);
		
		if (result!=1) {
			return "input";
		} else {
			return "success";
		}
	}
	
	public String insertUserExperience() {
		ApplicationDao dao = new ApplicationDao();
		int result = dao.insertExperience(expTitle, expCompany, expDesc, expDuration, 
				(Integer)sessionMap.get("userId"));
		
		if (result!=1) {
			setError(true);
			return "input";
		} else {
			return "success";
		}
	}
	
	public SoftwareEngineerExperience getUserExp() {
		return userExp;
	}
	
	public void setUserExp(SoftwareEngineerExperience userExp) {
		this.userExp = userExp;
	}

	public List<SoftwareEngineerExperience> getUserExpList() {
		return userExpList;
	}

	public void setUserExpList(List<SoftwareEngineerExperience> userExpList) {
		this.userExpList = userExpList;
	}

	public Map<String, Object> getSession() {
		return sessionMap;
	}
	
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = (SessionMap<String, Object>)sessionMap;
	}

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

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}
}

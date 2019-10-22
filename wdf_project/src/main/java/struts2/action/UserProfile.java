package struts2.action;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import struts2.dao.ApplicationDao;
import struts2.model.SoftwareEngineerEducation;
import struts2.model.SoftwareEngineerExperience;
import struts2.model.SoftwareEngineerUser;

/**
 * Search the database for a user's profile.
 * Display key information, user's experience, and user's education.
 *
 */
public class UserProfile extends ActionSupport {
	private static final long serialVersionUID = 1L;

	private String email;
	private SoftwareEngineerUser user;
	private SoftwareEngineerExperience userExp;
	private List<SoftwareEngineerExperience> userExpList;
	private SoftwareEngineerEducation userEdu;
	private List<SoftwareEngineerEducation> userEduList;
	
	public String execute() {
		ApplicationDao dao = new ApplicationDao();
		ResultSet rs = dao.pullUser(email);
		
		try {
			while (rs.next()) {
				user = new SoftwareEngineerUser();
				user.setUserId(rs.getInt("user_id"));
				user.setGivenName(rs.getString("given_name"));
				user.setSurname(rs.getString("surname"));
				user.setEmail(rs.getString("email"));
				user.setCountry(rs.getString("country"));
				user.setCity(rs.getString("city"));
				user.setAbout(rs.getString("about"));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		userExpList = new ArrayList<SoftwareEngineerExperience>();
		rs = dao.pullUserExperience(user.getUserId());
		
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
		
		userEduList = new ArrayList<SoftwareEngineerEducation>();
		rs = dao.pullUserEducation(user.getUserId());
		
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public SoftwareEngineerUser getUser() {
		return user;
	}

	public void setUser(SoftwareEngineerUser user) {
		this.user = user;
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
}

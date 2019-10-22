package struts2.action;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import javax.mail.Message.RecipientType;

import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.email.Recipient;
import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.MailerBuilder;
import org.simplejavamail.mailer.config.TransportStrategy;

import com.opensymphony.xwork2.ActionSupport;

import struts2.dao.ApplicationDao;
import struts2.model.SoftwareEngineerEducation;
import struts2.model.SoftwareEngineerExperience;
import struts2.model.SoftwareEngineerUser;

public class Administrator extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
	private String method;
	private String search;
	private String email;
	private int result;
	private String confirm;
	private String mailName;
	private String mailEmail;
	
	private SoftwareEngineerUser user;
	private List<SoftwareEngineerUser> searchList;
	private SoftwareEngineerExperience userExp;
	private List<SoftwareEngineerExperience> userExpList;
	private SoftwareEngineerEducation userEdu;
	private List<SoftwareEngineerEducation> userEduList;
	private List<SoftwareEngineerUser> mailList;
	
	public String search() {
		ApplicationDao dao = new ApplicationDao();
		ResultSet rs = dao.searchUser(search);
		searchList = new ArrayList<SoftwareEngineerUser>();
		method = "search";
		
		try {
			while (rs.next()) {
				user = new SoftwareEngineerUser();
				user.setUserId(rs.getInt("user_id"));
				user.setGivenName(rs.getString("given_name"));
				user.setSurname(rs.getString("surname"));
				user.setEmail(rs.getString("email"));
				user.setContact(rs.getString("contact"));
				user.setCountry(rs.getString("country"));
				user.setCity(rs.getString("city"));
				user.setDateJoined(rs.getString("date_joined"));
				searchList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String userProfile() {
		ApplicationDao dao = new ApplicationDao();
		ResultSet rs = dao.pullUser(email);
		method = "userProfile";
		
		try {
			while (rs.next()) {
				user = new SoftwareEngineerUser();
				user.setUserId(rs.getInt("user_id"));
				user.setGivenName(rs.getString("given_name"));
				user.setSurname(rs.getString("surname"));
				user.setEmail(rs.getString("email"));
				user.setContact(rs.getString("contact"));
				user.setCountry(rs.getString("country"));
				user.setCity(rs.getString("city"));
				user.setDateJoined(rs.getString("date_joined"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (user==null) {
			result = 2;
			return "input";
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
	
	public String deleteUser() {
		ApplicationDao dao = new ApplicationDao();
		
		if (Objects.equals(method, "confirmDelete")) {
			
			if (confirm.equals("delete")) {
				result = dao.deleteUser(email);
			}
			
			if (result==1) {
				return SUCCESS;
			} else {
				result = 2;
				return "input";
			}
			
		} else {
			method = "deleteUser";
			ResultSet rs = dao.pullUser(email);
			
			try {
				while (rs.next()) {
					user = new SoftwareEngineerUser();
					user.setUserId(rs.getInt("user_id"));
					user.setGivenName(rs.getString("given_name"));
					user.setSurname(rs.getString("surname"));
					user.setEmail(rs.getString("email"));
					user.setContact(rs.getString("contact"));
					user.setCountry(rs.getString("country"));
					user.setCity(rs.getString("city"));
					user.setAbout(rs.getString("about"));
					user.setDateJoined(rs.getString("date_joined"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			if (user==null) {
				result = 2;
				return "input";
			} else {
				return SUCCESS;
			}
		}
	}
	
	public String retrieveMailingList() {
		ApplicationDao dao = new ApplicationDao();
		ResultSet rs = dao.retrieveMailingList();
		mailList = new ArrayList<SoftwareEngineerUser>();
		method = "mailingList";
		
		try {
			while (rs.next()) {
				user = new SoftwareEngineerUser();
				user.setGivenName(rs.getString("mail_name"));
				user.setEmail(rs.getString("mail_email"));
				mailList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		
		return SUCCESS;
	}
	
	public String addMailingList() {
		ApplicationDao dao = new ApplicationDao();
		result = dao.addMailingList(mailName, mailEmail);
		
		if (result==1) {
			return SUCCESS;
		} else {
			result = 2;
			return "input";
		}
	}
	
	public String deleteMailingList() {
		ApplicationDao dao = new ApplicationDao();
		result = dao.deleteMailingList(email);
		
		if (result==1) {
			return SUCCESS;
		} else {
			result = 2;
			return "input";
		}
	}
	
	public String sendBulkMail() {
		ApplicationDao dao = new ApplicationDao();
		ResultSet rs = dao.retrieveMailingList();
		mailList = new ArrayList<SoftwareEngineerUser>();
		Collection<Recipient> recipients = new ArrayList<Recipient>();
				
		try {
			while (rs.next()) {
				user = new SoftwareEngineerUser();
				user.setGivenName(rs.getString("mail_name"));
				user.setEmail(rs.getString("mail_email"));
				mailList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for (SoftwareEngineerUser user : mailList) {
			Recipient recipient = new Recipient(user.getGivenName(), user.getEmail(), RecipientType.TO);
			recipients.add(recipient);
		}
		
		Email email = EmailBuilder.startingBlank()
				.from("ABC Jobs", "abcjobsburner@gmail.com")
				.to(recipients)
				.withSubject("Invitation to Community Portal")
				.withHTMLText("Hello!<br><br>ABC Jobs cordially invites you to join the Community Portal!<br>"
						+ "You may find out more at <a href='localhost:8080/wdf_project'>localhost:8080/wdf_project</a>"
						+ "<br>Hope to see you there!<br><br>From the guys at ABC Jobs")
				.buildEmail();
		
		Mailer mailer = MailerBuilder
				.withSMTPServer("smtp.gmail.com", 587, "abcjobsburner@gmail.com", "abcjobs2019")
				.withTransportStrategy(TransportStrategy.SMTP_TLS)
				.buildMailer();
		
		mailer.sendMail(email, true);
		
		dao.deleteMailingList();
		
		return SUCCESS;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public String getMailName() {
		return mailName;
	}

	public void setMailName(String mailName) {
		this.mailName = mailName;
	}

	public String getMailEmail() {
		return mailEmail;
	}

	public void setMailEmail(String mailEmail) {
		this.mailEmail = mailEmail;
	}

	public SoftwareEngineerUser getUser() {
		return user;
	}

	public void setUser(SoftwareEngineerUser user) {
		this.user = user;
	}

	public List<SoftwareEngineerUser> getSearchList() {
		return searchList;
	}

	public void setSearchList(List<SoftwareEngineerUser> searchList) {
		this.searchList = searchList;
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

	public List<SoftwareEngineerUser> getMailList() {
		return mailList;
	}

	public void setMailList(List<SoftwareEngineerUser> mailList) {
		this.mailList = mailList;
	}
}
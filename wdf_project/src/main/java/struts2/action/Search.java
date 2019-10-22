package struts2.action;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import struts2.dao.ApplicationDao;
import struts2.model.SoftwareEngineerUser;

/**
 * Searches the database for users based on search input.
 * Stores the information (if any) in a list to be displayed
 *
 */
public class Search extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
	private SoftwareEngineerUser user;
	private List<SoftwareEngineerUser> searchList;
	private String search;
	private int numberOfResults;
	
	public String execute() {
		ApplicationDao dao = new ApplicationDao();
		numberOfResults = 0;
		searchList = new ArrayList<SoftwareEngineerUser>();
		ResultSet rs = dao.searchUser(search);
		
		try {
			while (rs.next()) {
				user = new SoftwareEngineerUser();
				user.setGivenName(rs.getString("given_name"));
				user.setSurname(rs.getString("surname"));
				user.setEmail(rs.getString("email"));
				user.setCountry(rs.getString("country"));
				searchList.add(user);
				numberOfResults++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
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

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public int getNumberOfResults() {
		return numberOfResults;
	}

	public void setNumberOfResults(int numberOfResults) {
		this.numberOfResults = numberOfResults;
	}
	
}

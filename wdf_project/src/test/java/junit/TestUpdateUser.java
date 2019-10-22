package junit;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import struts2.action.UpdateUser;

public class TestUpdateUser {
	
	static UpdateUser test;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		test = new UpdateUser();
		test.setGivenName("Wei Kiat");
		test.setSurname("Soh");
		test.setEmail("inexistent@email.com");
		test.setPassword("asdasd");
		test.setContact("12345678");
		test.setCountry("Singapore");
		test.setCity("Singapore");
		test.setAbout("My About");
	}

	@Test
	public void testUpdateUser() {
		assertEquals("success", test.updateUser());
	}

}

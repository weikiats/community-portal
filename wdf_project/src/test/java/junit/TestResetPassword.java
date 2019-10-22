package junit;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import struts2.action.ResetPassword;

public class TestResetPassword {
	
	static ResetPassword test;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		test = new ResetPassword();
		test.setEmail("fake@email.com");
		test.setPassword("asdasd");
	}

	@Test
	public void testCheckUser() {
		assertEquals("success", test.checkUser());
	}

	@Test
	public void testChangePassword() {
		assertEquals("success", test.changePassword());
	}

}

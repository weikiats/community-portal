package junit;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import struts2.action.Administrator;

/*
 * Prior to running TestAdministrator, ensure that database table 
 * 'mailing_list' holds valid information to ensure test success
 */
public class TestAdministrator {
	
	static Administrator test;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		test = new Administrator();
	}
	
	@Test
	public void testSendBulkMail() {
		assertEquals("success", test.sendBulkMail());
	}
}

package junit;

import static org.junit.Assert.*;

import java.util.Collections;

import org.junit.BeforeClass;
import org.junit.Test;

import struts2.action.CreateUser;

public class TestCreateUser {
	
	static CreateUser test;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		test = new CreateUser();
		test.setGivenName("t3st");
		test.setSurname("t3st");
		test.setEmail("email@ad.asd");
		test.setPassword("password");
		test.setCfPassword("didntmatch");
	}
	
	@Test
	public void testValidate() {
		test.validate();
		assertEquals(Collections.emptyMap(), test.getFieldErrors());
	}

	@Test
	public void testExecute() {
		test.execute();
		assertEquals(1, test.getResult());
	}
}

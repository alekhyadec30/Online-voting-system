package com.voting.jpa;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.time.Duration;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.voting.jpa.dao.AdminDaoImpl;
import com.voting.jpa.dto.Admin;
import com.voting.jpa.dto.Nominee;
import com.voting.jpa.service.AdminService;


/**
 * @author alekhya , sushma , poojitha
 *
 */
//A JUnit Runner is class that extends JUnit's abstract Runner class. Runners are used for running test classes. 
//The Runner that should be used to run a test can be set using the @RunWith annotation
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestAdmin {

	private static final Logger logger = LoggerFactory.getLogger(TestAdmin.class);

	@Autowired
	private AdminService adminService;

	/*
	 * Mock objects to the spring application context. The mock will replace any
	 * existing bean of the same type in the application context. if no bean of the
	 * same type is defined, a new one will be added. This annotation is useful in
	 * integration tests where a particular bean – for example, an external service
	 * – needs to be mocked.
	 */
	@MockBean
	private AdminDaoImpl adminDaoImpl;

	private Admin admin;
	private Admin admin1;
	private Admin admin2;
	private List<Object[]> obj1 = new ArrayList<Object[]>();;
	private Object[] obj;
	private List<Object[]> obj2 = new ArrayList<Object[]>();;
	private Object[] obj3;
	private List<Object[]> obj4 = new ArrayList<Object[]>();;
	private Object[] obj5;
	private Object[] res;
	private Object[] res1;
	private Object[] res2;
	private Object[] res3;
	private Object[] res4;
	private Object[] res5;
	List<Object[]> list = new ArrayList<Object[]>();;
	List<Object[]> list1 = new ArrayList<Object[]>();;
	List<Object[]> list2 = new ArrayList<Object[]>();;
	List<Nominee> nomin = new ArrayList<Nominee>();;

	@Before
	public void before() {
		logger.info("startup");
		admin = new Admin("Alekhya", "admin");
		admin1 = new Admin("Aparna", "admin");
		admin2 = new Admin("Aparna", "appu");
		obj = new Object[] { "pavan", "xyz", 4 };
		obj1.add(obj);
		obj3 = new Object[] { "pavan", "xyz", 3 };
		obj2.add(obj3);
		obj5 = new Object[] { "amith", "abc", 4 };
		obj4.add(obj5);
		res = new Object[] { "pavan", "xyz", 4 };
		res1 = new Object[] { "amith", "abc", 2 };
		list.add(res);
		list.add(res1);
		res2 = new Object[] { "pavan", "xyz", 4 };
		res3 = new Object[] { "amith", "abc", 3 };
		list1.add(res2);
		list1.add(res3);
		res4 = new Object[] { "pavan", "xyz", 4 };
		res5 = new Object[] { "amith", "abc", 4 };
		list2.add(res4);
		list2.add(res5);
		Nominee nominee = new Nominee(5, "pavan", "xyz");
		Nominee nominee1 = new Nominee(6, "amith", "abc");
		nomin.add(nominee);
		nomin.add(nominee1);
	}

	@After
	public void after() {
		logger.info("finalize");
		list.clear();
		list1.clear();
		list2.clear();
		obj1.clear();
		obj2.clear();
		obj4.clear();
	}

	/*
	 * This is the login method that test whether the expected and actual values are
	 * equal are not
	 * 
	 * @author sushma
	 */
	@Test
	public void login() {
		Mockito.when(adminDaoImpl.login(admin)).thenReturn("Login Successful!!");
		assertEquals("Login Successful!!", adminService.adminLogin(admin));

	}

	/*
	 * This is the login method that test whether the expected and actual values are
	 * equal are not
	 * 
	 * @author sushma
	 */
	@Test
	public void login1() {
		Mockito.when(adminDaoImpl.login(admin1)).thenReturn("Invalid Credentials!!");
		assertEquals("Invalid Credentials!!", adminService.adminLogin(admin1));

	}

	/*
	 * This is the login method that test whether the expected and actual values are
	 * equal are not
	 * 
	 * @author sushma
	 */
	@Test
	public void login2() {
		Mockito.when(adminDaoImpl.login(admin2)).thenReturn("Invalid Credentials!!");
		assertEquals("Invalid Credentials!!", adminService.adminLogin(admin2));

	}

	/*
	 * This is the winner method that test whether the expected and actual values
	 * are equal are not
	 * 
	 * @author alekhya
	 */
	@Test
	public void winner() throws Exception {
		Mockito.when(adminDaoImpl.winner()).thenReturn(obj1);
		assertEquals(obj1, adminService.winner());
	}

	/*
	 * This is the winner method that test whether the expected and actual values
	 * are equal are not
	 * 
	 * @author alekhya
	 */
	@Test
	public void winner1() throws Exception {
		Mockito.when(adminDaoImpl.winner()).thenReturn(obj2);
		assertEquals(obj1, adminService.winner());
	}

	/*
	 * This is the winner method that test whether the expected and actual values
	 * are equal are not
	 * 
	 * @author alekhya
	 */
	@Test
	public void winner2() throws Exception {
		Mockito.when(adminDaoImpl.winner()).thenReturn(obj4);
		assertEquals(obj1, adminService.winner());
	}

	/*
	 * This is the result method that test whether the expected and actual values
	 * are equal are not
	 * 
	 * @author poojitha
	 */
	@Test
	public void results() throws Exception {
		Mockito.when(adminDaoImpl.results()).thenReturn(list);
		assertEquals(list, adminService.results());
	}

	/*
	 * This is the result method that test whether the expected and actual values
	 * are equal are not
	 * 
	 * @author poojitha
	 */
	@Test
	public void results1() throws Exception {
		Mockito.when(adminDaoImpl.results()).thenReturn(list1);
		assertEquals(list1, adminService.results());
	}

	/*
	 * This is the result method that test whether the expected and actual values
	 * are equal are not
	 * 
	 * @author poojitha
	 */
	@Test
	public void results2() throws Exception {
		Mockito.when(adminDaoImpl.results()).thenReturn(list2);
		assertEquals(list2, adminService.results());
	}

	/*
	 * This is the nomineeDetails method that test whether the expected and actual
	 * values are equal are not
	 * 
	 * @author sushma
	 */
	@Test
	public void nomineeDetails() throws Exception {
		Mockito.when(adminDaoImpl.addNominee()).thenReturn(nomin);
		assertEquals(nomin, adminService.addNominee());
	}

	/*
	 * This is the time method that test whether the test case executes in given
	 * time or not
	 * 
	 * @author alekhya
	 */
	@Test
	public void time() throws Exception {
		Assertions.assertTimeout(Duration.ofMillis(20), () -> adminService.winner());
	}

	/*
	 * This is the time1 method that test whether the test case executes in given
	 * time or not
	 * 
	 * @author sushma
	 */
	@Test
	public void time1() throws Exception {
		Assertions.assertTimeout(Duration.ofMillis(20), () -> adminService.addNominee());
	}

	/*
	 * This is the time2 method that test whether the test case executes in given
	 * time or not
	 * 
	 * @author poojitha
	 */
	@Test
	public void time2() throws Exception {
		Assertions.assertTimeout(Duration.ofMillis(20), () -> adminService.results());
	}

}

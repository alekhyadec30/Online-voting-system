package com.voting.jpa;

import static org.junit.Assert.assertEquals;

import java.time.Duration;

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

import com.voting.jpa.dao.VotesDaoImpl;
import com.voting.jpa.dto.Votes;
import com.voting.jpa.service.VoteService;

/**
 * @author alekhya
 *
 */
//A JUnit Runner is class that extends JUnit's abstract Runner class. Runners are used for running test classes. 
//The Runner that should be used to run a test can be set using the @RunWith annotation
@RunWith(SpringRunner.class)
//Spring Boot provides a convenient way to start up an application context to be used in a test
@SpringBootTest
public class TestVotes {

	private static final Logger logger = LoggerFactory.getLogger(TestVotes.class);

	@Autowired
	private VoteService voteService;

	/*
	 * Mock objects to the spring application context. The mock will replace any
	 * existing bean of the same type in the application context. if no bean of the
	 * same type is defined, a new one will be added. This annotation is useful in
	 * integration tests where a particular bean – for example, an external service
	 * – needs to be mocked.
	 */
	@MockBean
	private VotesDaoImpl votesDaoImpl;

	private Votes vote;
	private Votes vote1;
	private Votes vote2;
	private Votes vote3;
	private Votes vote4;
	private Votes vote5;

	@Before
	public void before() {
		logger.info("startup");
		vote = new Votes(1, "amith", "abc", "yyy1234567");
		vote1 = new Votes(2, "pavan", "xyz", "yyy1234568");
		vote2 = new Votes(8, "amith", "abc", "yyy2334330");
		vote3 = new Votes(9, "amith", "abc", "yyy0204567");
		vote4 = new Votes(10, "pavan", "xyz", "yyy1234213");
		vote5 = new Votes(11, "amith", "xyz", "yyy4567123");
	}

	@After
	public void after() {
		logger.info("finalize");
	}

	/*
	 * This is the vote method that test whether the expected and actual values are
	 * equal are not
	 * 
	 * @author alekhya
	 */
	@Test
	public void vote() throws Exception {
		Mockito.when(votesDaoImpl.voteParty(vote)).thenReturn("Illegal");
		assertEquals("Illegal", voteService.voteParty(vote));
	}

	/*
	 * This is the vote method that test whether the expected and actual values are
	 * equal are not
	 * 
	 * @author alekhya
	 */
	@Test
	public void vote1() throws Exception {
		Mockito.when(votesDaoImpl.voteParty(vote1)).thenReturn("Illegal");
		assertEquals("Illegal", voteService.voteParty(vote1));
	}

	/*
	 * This is the vote method that test whether the expected and actual values are
	 * equal are not
	 * 
	 * @author alekhya
	 */
	@Test
	public void vote2() throws Exception {
		Mockito.when(votesDaoImpl.voteParty(vote2)).thenReturn("success");
		assertEquals("success", voteService.voteParty(vote2));
	}

	/*
	 * This is the vote method that test whether the expected and actual values are
	 * equal are not
	 * 
	 * @author alekhya
	 */
	@Test
	public void vote3() throws Exception {
		Mockito.when(votesDaoImpl.voteParty(vote3)).thenReturn("success");
		assertEquals("success", voteService.voteParty(vote3));
	}

	/*
	 * This is the vote method that test whether the expected and actual values are
	 * equal are not
	 * 
	 * @author alekhya
	 */
	@Test
	public void vote4() throws Exception {
		Mockito.when(votesDaoImpl.voteParty(vote4)).thenReturn("Invalid Credentials");
		assertEquals("Invalid Credentials", voteService.voteParty(vote4));
	}

	/*
	 * This is the vote method that test whether the expected and actual values are
	 * equal are not
	 * 
	 * @author alekhya
	 */
	@Test
	public void vote5() throws Exception {
		Mockito.when(votesDaoImpl.voteParty(vote5)).thenReturn("Invalid Credentials");
		assertEquals("Invalid Credentials", voteService.voteParty(vote5));
	}

	/*
	 * This is the time method that test whether the test case executes in given
	 * time or not
	 * 
	 * @author alekhya
	 */
	@Test
	public void time() throws Exception {
		Assertions.assertTimeout(Duration.ofMillis(20), () -> voteService.voteParty(vote1));
	}

	/*
	 * This is the time1 method that test whether the test case executes in given
	 * time or not
	 * 
	 * @author alekhya
	 */
	@Test
	public void time1() throws Exception {
		Assertions.assertTimeout(Duration.ofMillis(20), () -> voteService.voteParty(vote2));
	}

	/*
	 * This is the time2 method that test whether the test case executes in given
	 * time or not
	 * 
	 * @author alekhya
	 */
	@Test
	public void time2() throws Exception {
		Assertions.assertTimeout(Duration.ofMillis(20), () -> voteService.voteParty(vote3));
	}

}

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

import com.voting.jpa.dao.CandidateDaoImpl;
import com.voting.jpa.dto.Candidate;
import com.voting.jpa.service.CandidateService;


/**
 * @author harshini
 *
 */
//A JUnit Runner is class that extends JUnit's abstract Runner class. Runners are used for running test classes. 
//The Runner that should be used to run a test can be set using the @RunWith annotation
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCandidate {

	private static final Logger logger = LoggerFactory.getLogger(TestCandidate.class);

	@Autowired
	private CandidateService candidateService;

	/*
	 * Mock objects to the spring application context. The mock will replace any
	 * existing bean of the same type in the application context. if no bean of the
	 * same type is defined, a new one will be added. This annotation is useful in
	 * integration tests where a particular bean – for example, an external service
	 * – needs to be mocked.
	 */
	@MockBean
	private CandidateDaoImpl candidateDaoImpl;

	private Candidate candidate;
	private Candidate candidate1;
	private Candidate candidate2;
	private Candidate candidate3;

	@Before
	public void before() {
		logger.info("startup");
		candidate = new Candidate(9, "lakshmi", "linga", 'f', "yyy2234568", "6309799271", "1984-11-09",
				"lakshminov09@gmail.com", "");
		candidate1 = new Candidate(11, "ammulu", "linga", 'f', "yyy3234568", "6309799272", "1980-10-09",
				"ammulunov09@gmail.com", "");
		candidate2 = new Candidate(12, "godha", "bandari", 'f', "yyy4234568", "6309799273", "1999-04-19",
				"godhadevi@gmail.com", "");
		candidate3 = new Candidate(13, "lucky", "linga", 'f', "yyy5234568", "6309799274", "1996-07-01",
				"lucky@gmail.com", "");
	}

	@After
	public void after() {
		logger.info("finalize");
	}

	/*
	 * This is the candidate method that test whether the expected and actual values
	 * are equal are not
	 * 
	 * @author harshini
	 */
	@Test
	public void candidate1() throws Exception {
		Mockito.when(candidateDaoImpl.addCandidate(candidate)).thenReturn(true);
		assertEquals(true, candidateService.addCandidate(candidate));
	}

	/*
	 * This is the candidate method that test whether the expected and actual values
	 * are equal are not
	 * 
	 * @author harshini
	 */
	@Test
	public void candidate2() throws Exception {
		Mockito.when(candidateDaoImpl.addCandidate(candidate1)).thenReturn(true);
		assertEquals(true, candidateService.addCandidate(candidate1));
	}

	/*
	 * This is the candidate method that test whether the expected and actual values
	 * are equal are not
	 * 
	 * @author harshini
	 */
	@Test
	public void candidate3() throws Exception {
		Mockito.when(candidateDaoImpl.addCandidate(candidate2)).thenReturn(true);
		assertEquals(true, candidateService.addCandidate(candidate2));
	}

	/*
	 * This is the candidate method that test whether the expected and actual values
	 * are equal are not
	 * 
	 * @author harshini
	 */
	@Test
	public void candidate4() throws Exception {
		Mockito.when(candidateDaoImpl.addCandidate(candidate3)).thenReturn(true);
		assertEquals(true, candidateService.addCandidate(candidate3));
	}

	/*
	 * This is the time method that test whether the test case executes in given
	 * time or not
	 * 
	 * @author harshini
	 */
	@Test
	public void time() throws Exception {
		Assertions.assertTimeout(Duration.ofMillis(20), () -> candidateService.addCandidate(candidate));
	}

	/*
	 * This is the time1 method that test whether the test case executes in given
	 * time or not
	 * 
	 * @author harshini
	 */
	@Test
	public void time1() throws Exception {
		Assertions.assertTimeout(Duration.ofMillis(20), () -> candidateService.addCandidate(candidate1));
	}

	/*
	 * This is the time2 method that test whether the test case executes in given
	 * time or not
	 * 
	 * @author harshini
	 */
	@Test
	public void time2() throws Exception {
		Assertions.assertTimeout(Duration.ofMillis(20), () -> candidateService.addCandidate(candidate2));
	}

}

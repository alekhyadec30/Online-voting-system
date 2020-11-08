package com.voting.jpa.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.voting.jpa.dto.Admin;
import com.voting.jpa.dto.Nominee;
import com.voting.jpa.service.AdminService;

/**
 * @author alekhya , sushma , poojitha
 *
 */
//In Spring’s approach to building RESTful web services, HTTP requests are handled by a controller. 
@RestController
//Helps to map user request URL to the specific controller class/method it can be placed at both class level and method level
@RequestMapping("/admin")
public class AdminController {
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	// enables you to inject the object dependency implicitly. It internally uses
	// setter or constructor injection.
	@Autowired
	private AdminService adminService;

	/*
	 * This is the admin method which checks whether login credentials of admin are
	 * valid or not
	 * 
	 * @author sushma
	 * 
	 * @method get
	 * 
	 * @parameter admin
	 * 
	 * @return ResponseEntity<String>
	 */

	// http://localhost:9093/api/admin/login
	@RequestMapping(method = RequestMethod.GET, value = "/login")
	public ResponseEntity<String> adminLogin(@RequestBody Admin admin) {
		logger.info(" Admin trying to login ");
		String result = adminService.adminLogin(admin);
		if (result.isEmpty()) {
			logger.error("No Records Found");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		logger.info("Admin details found" + result);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	/*
	 * This is the addNominee method where admin seperate nominees and users and add
	 * nominees into a nominee table
	 * 
	 * @author sushma
	 * 
	 * @method post
	 * 
	 * @return ResponseEntity<List<Nominee>>
	 */
	// http://localhost:9093/api/admin/addnominee
	// Response body+ HTTP response code
	@RequestMapping(method = RequestMethod.POST, value = "/addnominee")
	//
	public ResponseEntity<List<Nominee>> addNominee() {
		logger.info("Trying to add nominees ");
		List<Nominee> result = adminService.addNominee();
		if (result.isEmpty()) {
			logger.error("No Records Found");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		logger.info("Nominee got added" + result);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	/*
	 * This is the deleteCandidate method where admin have the right to delete a
	 * user based on there particular id
	 * 
	 * @author poojitha
	 * 
	 * @method delete
	 * 
	 * @parameter candidate_id
	 * 
	 * @return ResponseEntity<Object>
	 */
	// http://localhost:9093/api/admin/delete/{vid}
	@RequestMapping(method = RequestMethod.DELETE, value = "/delete/{candidate_id}")
	public ResponseEntity<String> deleteCandidate(@PathVariable Integer candidate_id) {

		logger.info("Trying to delete a candidate ");
		String result = adminService.deleteCandidate(candidate_id);
		logger.info("candidate deleted ");
		return new ResponseEntity<>(result, HttpStatus.OK);

	}

	/*
	 * This is the result method declare the results of all the candidates
	 * 
	 * @author poojitha
	 * 
	 * @method get
	 * 
	 * @return ResponseEntity<List<Object[]>
	 */
	// http://localhost:9093/api/admin/result
	// Response body+ HTTP response code
	@RequestMapping(method = RequestMethod.GET, value = "/result")
	public ResponseEntity<List<Object[]>> results() {
		logger.info("Trying to fetch results of online voting ");
		List<Object[]> result = adminService.results();
		if (result.isEmpty()) {
			logger.error("No Records Found");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		logger.info("results found " + result);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	/*
	 * This is the winner method declare the winner based on the maximum votes
	 * 
	 * @author alekhya
	 * 
	 * @method get
	 * 
	 * @return ResponseEntity<List<Object[]>>
	 */
	// http://localhost:9093/api/admin/winner
	@RequestMapping(method = RequestMethod.GET, value = "/winner")
	public ResponseEntity<List<Object[]>> winner() {
		logger.info("Trying to fetch Winner ");
		List<Object[]> winner = adminService.winner();
		if (winner.isEmpty()) {
			logger.error("No Records Found");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		logger.info("Winner found " + winner);
		return new ResponseEntity<>(winner, HttpStatus.OK);

	}

	/*
	 * when ever admin wants to see the candidate details to whom they made a vote
	 * he can get all the details from the candidate table it self as there is an
	 * association mapping between votes and candidate
	 * 
	 * @author alekhya
	 * 
	 * @method get
	 * 
	 * @return ResponseEntity<List<Object[]>>
	 */
	// http://localhost:9093/api/admin/candidateDetails
	@RequestMapping(method = RequestMethod.GET, value = "/candidateDetails")
	public ResponseEntity<List<Object[]>> candidateDetails() {
		logger.info("Trying to fetch Candidate details ");
		List<Object[]> result = adminService.candidateDetails();
		if (result.isEmpty()) {
			logger.error("No Records Found");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		logger.info("Candidate details found " + result);
		return new ResponseEntity<>(result, HttpStatus.OK);

	}

}

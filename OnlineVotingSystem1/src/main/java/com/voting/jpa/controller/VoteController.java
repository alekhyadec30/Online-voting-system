package com.voting.jpa.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.voting.jpa.dto.Votes;
import com.voting.jpa.service.VoteService;

/**
 * @author alekhya
 *
 */
//used to mark a class as a restful web service and equivalent of @controller and @repository
@RestController
//helps to map user request URL to the specific controller class/method it can be placed at both class level and method level
@RequestMapping("/vote")
public class VoteController {
	// loggers are pre-configured to use console output
	private static final Logger logger = LoggerFactory.getLogger(VoteController.class);
	// wiring one bean into another bean
	@Autowired
	private VoteService voteService;

	/*
	 * This is the addVote method which checks whether the voter is making a valid
	 * vote or not
	 * 
	 * @author alekhya
	 * 
	 * @method post
	 * 
	 * @parameter vote
	 * 
	 * @return ResponseEntity<String>
	 */

	// http://localhost:9092/api/vote/candidate
	@RequestMapping(method = RequestMethod.POST, value = "/candidate")
	// Response body+ HTTP response code
	// all the data will be pass to the server through a full JSON format
	public ResponseEntity<String> addVote(@RequestBody Votes vote) {
		logger.info("Trying to vote a candidate ");
		String result = voteService.voteParty(vote);
		if (result.isEmpty()) {
			logger.error("No Records Found");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		logger.info("Vote added successfully " + result);
		return new ResponseEntity<>(result, HttpStatus.OK);

	}
}

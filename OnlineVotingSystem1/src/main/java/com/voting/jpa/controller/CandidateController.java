package com.voting.jpa.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.voting.jpa.dto.Candidate;
import com.voting.jpa.service.CandidateService;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author harshini
 *
 */
//used to mark a class as a restful web service and equivalent of @controller and @repository
@RestController
//helps to map user request URL to the specific controller class/method it can be placed at both class level and method level
@RequestMapping("/candidate")
public class CandidateController {
	private static final Logger logger = LoggerFactory.getLogger(CandidateController.class);
	// wiring one bean into another bean
	@Autowired
	private CandidateService candidateService;

	/*
	 * This is the addCandidate method to add the users
	 * 
	 * @author harshini
	 * 
	 * @method post
	 * 
	 * @parameter candidate
	 * 
	 * @return boolean
	 */
	// http://localhost:9092/api/candidate/add
	// {"firstName":"Alekhya","lastName":"gadipudi","sex":"f","votercardnumber":"yyy1234567","contact":"9989504998","dob":"12-30-1997","email":"alekhyadec30@gmail.com","party":"p_name"}
	@RequestMapping(method = RequestMethod.POST, value = "/add")
	// Response body+ HTTP response code
	// all the data will be pass to the server through a full JSON format
	public ResponseEntity<String> addCandidate(@RequestBody @Valid Candidate candidate) {
		logger.info("Trying to add a candidate ");
		candidateService.addCandidate(candidate);
		logger.info("candidate got added ");
		return new ResponseEntity<>("User data is valid", HttpStatus.OK);

	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

		return errors;
	}
}

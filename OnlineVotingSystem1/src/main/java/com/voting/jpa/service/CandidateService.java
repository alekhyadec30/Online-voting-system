package com.voting.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voting.jpa.dao.CandidateDao;
import com.voting.jpa.dto.Candidate;
import com.voting.jpa.exception.CandidateException;

/**
 * @author harshini
 *
 */
//Business logic method we can use service
@Service
public class CandidateService {
	// wiring one bean into another bean
	@Autowired
	private CandidateDao candidateDaoImpl;

	/*
	 * This is the addCandidate method to add the users
	 * 
	 * @author harshini
	 * 
	 * @return boolean
	 * 
	 * @throws CandidateException
	 */
	public boolean addCandidate(Candidate candidate) throws CandidateException {
		return candidateDaoImpl.addCandidate(candidate);
	}
}

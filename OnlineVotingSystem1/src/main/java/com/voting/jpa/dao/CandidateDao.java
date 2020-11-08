package com.voting.jpa.dao;

import com.voting.jpa.dto.Candidate;
import com.voting.jpa.exception.CandidateException;

/**
 * @author harshini
 *
 */
public interface CandidateDao {
	/*
	 * This is the addCandidate method to add the users
	 * 
	 * @author harshini
	 * 
	 * @return boolean
	 * 
	 * @throws CandidateException
	 */
	public boolean addCandidate(Candidate candidate) throws CandidateException;

}

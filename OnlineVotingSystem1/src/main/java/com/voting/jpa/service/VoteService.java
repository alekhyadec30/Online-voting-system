package com.voting.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voting.jpa.dao.VotesDao;
import com.voting.jpa.dto.Votes;
import com.voting.jpa.exception.VoteException;

/**
 * @author alekhya
 *
 */
//Business logic method we can use service
@Service
public class VoteService {
	// wiring one bean into another bean
	@Autowired
	private VotesDao votesDaoImpl;

	/*
	 * This is the voteParty method which checks whether the voter is making a valid
	 * vote or not
	 * 
	 * @parameter vote
	 * 
	 * @return String.
	 * 
	 * @throws VoteException
	 */
	public String voteParty(Votes vote) throws VoteException {
		return votesDaoImpl.voteParty(vote);
	}
}

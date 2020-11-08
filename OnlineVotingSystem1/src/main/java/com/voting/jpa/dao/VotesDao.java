package com.voting.jpa.dao;

import com.voting.jpa.dto.Votes;
import com.voting.jpa.exception.VoteException;

/**
 * @author alekhya
 *
 */
public interface VotesDao {
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
	public String voteParty(Votes vote) throws VoteException;

}

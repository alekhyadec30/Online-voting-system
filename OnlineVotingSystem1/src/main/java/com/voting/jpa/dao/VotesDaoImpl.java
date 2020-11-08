package com.voting.jpa.dao;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.voting.jpa.dto.Candidate;
import com.voting.jpa.dto.Votes;
import com.voting.jpa.exception.VoteException;

/**
 * @author alekhya
 *
 * @throws VoteException
 */
//will treat it as DAO bean to implement database operations
@Repository("votesDaoImpl")
public class VotesDaoImpl implements VotesDao {
	// we are able to connect to the database. entities are managed by
	// javax.persistence.EntityManager instances using persistence context
	@PersistenceContext
	EntityManager entityManager;
	private static Logger myLogger;
	// wiring one bean into another bean
	/*
	 * static block to declare logger and create the instance of DaoImpl
	 */
	static {
		myLogger = LoggerFactory.getLogger(AdminDaoImpl.class);
	}
	LocalDateTime current = LocalDateTime.now();
	private final static LocalDateTime start = LocalDateTime.of(2020, 11, 07, 6, 00, 00);
	private final static LocalDateTime end = LocalDateTime.of(2020, 11, 07, 20, 00, 00);

	/*
	 * This is the voteParty method which checks whether the voter is making a valid
	 * vote or not
	 * 
	 * @param vote
	 * 
	 * @return String.
	 * 
	 * @throws VoteException
	 */
	@Transactional
	public String voteParty(Votes vote) throws VoteException {
		myLogger.info("-----------vote------------");
		String card = "";
		int voterid = 0;
		// this query retrieves all the records from the candidate table
		Query query = entityManager.createQuery("select c from Candidate c");
		List<Candidate> list = query.getResultList();
		Query query1 = entityManager.createQuery("select v.votercardnumber from Votes v");
		List<String> list1 = query1.getResultList();
		String result = "Invalid Credentials";
		for (Candidate v : list) {
			card = v.getVotercardnumber();
			voterid = v.getCandidate_id();
			if (card.equals(vote.getVotercardnumber()) && voterid == vote.getVoter_id()) {
				if (!(list1.contains(vote.getVotercardnumber()))) {
					if ((current.compareTo(start) >= 0) && (current.compareTo(end) <= 0)) {
						entityManager.persist(vote);
						result = "success";
						break;
					} else {
						result = "Time Out";
						break;
					}
				} else {
					result = "Illegal";
					break;
				}
			}
		}
		myLogger.info("vote: " + vote);
		if (vote == null) {
			myLogger.error("vote Exception");
			throw new VoteException("Vote Exception");
		}
		return result;
	}
}

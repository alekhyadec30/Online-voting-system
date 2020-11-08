package com.voting.jpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.voting.jpa.dto.Candidate;
import com.voting.jpa.exception.CandidateException;

/**
 * @author harshini
 *
 */
//will treat it as DAO bean to implement database operations
@Repository("candidateDaoImpl")
public class CandidateDaoImpl implements CandidateDao {
	private static Logger myLogger;
	/*
	 * static block to declare logger and create the instance of DaoImpl
	 */
	static {
		myLogger = LoggerFactory.getLogger(CandidateDaoImpl.class);
	}
	// we are able to connect to the database. entities are managed by
	// javax.persistence.EntityManager instances using persistence context
	@PersistenceContext
	EntityManager entityManager;
	// wiring one bean into another bean

	/*
	 * This is the addCandidate method to add the users
	 * 
	 * @author harshini
	 * 
	 * @return boolean
	 * 
	 * @throws CandidateException
	 */
	@Transactional
	public boolean addCandidate(Candidate candidate) throws CandidateException {
		myLogger.info("-----------Adding Candidate------------");
		Query query = entityManager.createQuery("select v.party from Candidate v");
		List<String> list = query.getResultList();
		for (String str : list) {
			if (candidate.getParty().equals("")) {
				entityManager.persist(candidate);
			}
			if (str.equals(candidate.getParty())) {
				candidate.setParty("");
			}
		}
		myLogger.info("Candidate: " + candidate);
		if (candidate == null) {
			myLogger.error("No candidate found");
		}
		entityManager.persist(candidate);
		return true;
	}

}

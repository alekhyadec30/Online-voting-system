package com.voting.jpa.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.voting.jpa.dto.Admin;
import com.voting.jpa.dto.Candidate;
import com.voting.jpa.dto.Nominee;
import com.voting.jpa.exception.AdminException;

/**
 * @author alekhya , sushma , poojitha
 *
 */
//will treat it as DAO bean to implement database operations
@Repository("adminDaoImpl")
public class AdminDaoImpl implements AdminDao {

	// we are able to connect to the database. entities are managed by
	// javax.persistence.EntityManager instances using persistence context
	@PersistenceContext
	EntityManager entityManager;
	// wiring one bean into another bean
	private static Logger myLogger;
	/*
	 * static block to declare logger and create the instance of DaoImpl
	 */
	static {
		myLogger = LoggerFactory.getLogger(AdminDaoImpl.class);
	}

	/*
	 * This is the admin method which checks whether login credentials of admin are
	 * valid or not
	 * 
	 * @author sushma
	 * 
	 * @parameter admin
	 * 
	 * @return String
	 */
	@Override
	public String login(Admin admin) throws AdminException {
		myLogger.info("-----------Login------------");
		String result = "Invalid Credentials!!";
		Query query = entityManager.createQuery("select a from Admin a");
		List<Admin> list = query.getResultList();
		for (Admin up : list) {
			if (up.getUsername().equals(admin.getUsername()) && up.getPassword().equals(admin.getPassword())) {
				result = "Login Successful!!";
			}
		}
		myLogger.info("Login: " + result);
		if (list == null) {
			myLogger.error("No login details found");
		}
		// TODO Auto-generated method stub
		return result;
	}

	/*
	 * This is the result method declare the results of all the candidates
	 * 
	 * @author poojitha
	 * 
	 * @return List<Object[]
	 * 
	 * @throws AdminException
	 */
	@Override
	public List<Object[]> results() throws AdminException {
		myLogger.info("-----------viewAllResults------------");
		Query query = entityManager
				.createQuery("select v.candidate,v.party,count(v.candidate) from Votes v group by v.candidate");
		List<Object[]> list = query.getResultList();
		myLogger.info("Results: " + list);
		if (list == null) {
			myLogger.error("Result list was empty");
		}
		return list;
	}

	/*
	 * This is the winner method declare the winner based on the maximum votes
	 * 
	 * @author alekhya
	 * 
	 * @return List<Object[]>
	 * 
	 * @throws AdminException
	 */
	@Override
	public List<Object[]> winner() throws AdminException {
		myLogger.info("-----------Winner------------");
		List<Object[]> res = new ArrayList<Object[]>();
		Query query = entityManager
				.createQuery("select v.candidate,v.party,count(v.candidate) from Votes v group by v.candidate");
		List<Object[]> list = query.getResultList();
		List<Long> list1 = new ArrayList<Long>();
		Long numvotes;
		for (Object[] votes : list) {
			numvotes = (Long) votes[2];
			list1.add(numvotes);
		}
		Long maxi = Collections.max(list1);
		for (Object[] votes : list) {
			numvotes = (Long) votes[2];
			if (numvotes == maxi) {
				res.add(votes);
			}
		}
		myLogger.info("Winner: " + res);
		if (res == null) {
			myLogger.error("Could not found the winner");
		}
		return res;
	}

	/*
	 * This is the addNominee method where admin seperate nominees and users and add
	 * nominees into a nominee table
	 * 
	 * @author sushma
	 * 
	 * @return List<Nominee>
	 * 
	 * @throws AdminException
	 */
	@Transactional
	@Override
	public List<Nominee> addNominee() throws AdminException {
		myLogger.info("-----------Adding Nominee------------");
		List<Nominee> res = new ArrayList<Nominee>();
		Query query = entityManager.createQuery("select c from Candidate c where c.party!='' ");
		List<Candidate> list = query.getResultList();
		for (Candidate candidate : list) {
			Nominee nominee = new Nominee();
			nominee.setNominee_id(candidate.getCandidate_id());
			nominee.setCandidate(candidate.getFirstname());
			nominee.setParty(candidate.getParty());
			entityManager.persist(nominee);
			res.add(nominee);
		}
		myLogger.info("Nominees are: " + res);
		if (res == null) {
			myLogger.error("No nominees found");
		}
		return res;
	}

	/*
	 * when ever admin wants to see the candidate details to whom they made a vote
	 * he can get all the details from the candidate table it self as there is an
	 * association mapping between votes and candidate
	 * 
	 * @author alekhya
	 * 
	 * @return List<Object[]>
	 * 
	 * @throws AdminException
	 */
	@Override
	public List<Object[]> candidateDetails() throws AdminException {
		myLogger.info("-----------Candidate Details------------");
		List<Object[]> res = new ArrayList<Object[]>();
		Query query = entityManager.createQuery(
				"select c.candidate_id,c.firstname,c.lastname,c.dob,c.votes.candidate,c.votes.party,c.votercardnumber,c.gender,c.contact,c.email from Candidate c");
		List<Object[]> list = query.getResultList();
		myLogger.info("Candidate details: " + res);
		if (list == null) {
			myLogger.error("No candidate details found");
		}
		return list;
	}

	/*
	 * This is the deleteCandidate method where admin have the right to delete a
	 * user based on there particular id
	 * 
	 * @author poojitha
	 * 
	 * @parameter candidateid
	 */
	@Transactional
	@Override
	public String deleteById(Integer candidateid) throws AdminException {
		String result = "";
		myLogger.info("-----------Delete Candidate Details------------");
		Candidate candidate = entityManager.find(Candidate.class, candidateid);
		entityManager.remove(candidate);
		result = "deleted successfully";
		myLogger.info("Candidate details: " + result);
		if (candidate == null) {
			myLogger.error("No candidate details found");
			result = "User not found";
		}
		return result;
	}
}

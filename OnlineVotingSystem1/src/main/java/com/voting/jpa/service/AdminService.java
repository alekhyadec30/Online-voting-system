package com.voting.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voting.jpa.dao.AdminDao;
import com.voting.jpa.dto.Admin;
import com.voting.jpa.dto.Nominee;
import com.voting.jpa.exception.AdminException;

/**
 * @author alekhya , sushma , poojitha
 *
 */
//Business logic method we can use service
@Service
public class AdminService {

	// wiring one bean into another bean
	@Autowired
	private AdminDao adminDaoImpl;

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
	public String adminLogin(Admin admin) throws AdminException {
		return adminDaoImpl.login(admin);
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
	public List<Object[]> winner() throws AdminException {
		return adminDaoImpl.winner();
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
	public List<Object[]> results() {
		return adminDaoImpl.results();
	}

	/*
	 * This is the deleteCandidate method where admin have the right to delete a
	 * user based on there particular id
	 * 
	 * @author poojitha
	 * 
	 * @parameter candidate_id
	 */
	public String deleteCandidate(Integer candidateid) throws AdminException {
		return adminDaoImpl.deleteById(candidateid);
	}

	/*
	 * This is the addNominee method where admin seperate nominees and users and add
	 * nominees into a nominee table
	 * 
	 * @author sushma
	 * 
	 * @method post
	 * 
	 * @return List<Nominee>
	 * 
	 * @throws AdminException
	 */
	public List<Nominee> addNominee() throws AdminException {
		return adminDaoImpl.addNominee();
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
	public List<Object[]> candidateDetails() throws AdminException {
		return adminDaoImpl.candidateDetails();
	}
}

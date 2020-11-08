package com.voting.jpa.dao;

import java.util.List;

import com.voting.jpa.dto.Admin;
import com.voting.jpa.dto.Nominee;
import com.voting.jpa.exception.AdminException;

/**
 * @author alekhya , sushma , poojitha
 *
 */
public interface AdminDao {

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
	public String login(Admin admin) throws AdminException;

	/*
	 * This is the winner method declare the winner based on the maximum votes
	 * 
	 * @author alekhya
	 * 
	 * @return List<Object[]>
	 * 
	 * @throws AdminException
	 */
	public List<Object[]> winner() throws AdminException;

	/*
	 * This is the result method declare the results of all the candidates
	 * 
	 * @author poojitha
	 * 
	 * @return List<Object[]
	 * 
	 * @throws AdminException
	 */
	public List<Object[]> results() throws AdminException;

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
	public List<Nominee> addNominee() throws AdminException;

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
	public List<Object[]> candidateDetails() throws AdminException;

	String deleteById(Integer candidateid) throws AdminException;

}

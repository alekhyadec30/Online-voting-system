package com.voting.jpa.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author alekhya
 *
 */
@Entity
//if the table name is different from class name
@Table(name = "vote")
public class Votes {
	public Votes(Integer voter_id, String candidate, String party, String votercardnumber) {
		super();
		this.voter_id = voter_id;
		this.candidate = candidate;
		this.party = party;
		this.votercardnumber = votercardnumber;
	}

	public Votes() {

	}

	// primary key
	@Id
	private Integer voter_id;
	private String candidate;
	private String party;
	// if the column name is different from variable name
	@Column(unique = true)
	private String votercardnumber;
	// doesn't load the relationships unless explicitly asked for via getters
	// association mapping
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "voter_id")
	private Candidate rcandidate;

	public String getCandidate() {
		return candidate;
	}

	public void setCandidate(String candidate) {
		this.candidate = candidate;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public Candidate getRcandidate() {
		return rcandidate;
	}

	public void setRcandidate(Candidate rcandidate) {
		this.rcandidate = rcandidate;
	}

	public Integer getVoter_id() {
		return voter_id;
	}

	public void setVoter_id(Integer voter_id) {
		this.voter_id = voter_id;
	}

	public String getVotercardnumber() {
		return votercardnumber;
	}

	public void setVotercardnumber(String votercardnumber) {
		this.votercardnumber = votercardnumber;
	}

	// converts object to string
	@Override
	public String toString() {
		return "Votes [voter_id=" + voter_id + ", candidate=" + candidate + ", party=" + party + ", votercardnumber="
				+ votercardnumber + ", rcandidate=" + rcandidate + "]";
	}

}

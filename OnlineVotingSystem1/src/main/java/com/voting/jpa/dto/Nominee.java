package com.voting.jpa.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
//if the table name is different from class name
@Table(name = "nominee")
public class Nominee {
	public Nominee(Integer nominee_id, String candidate, String party) {
		super();
		this.nominee_id = nominee_id;
		this.candidate = candidate;
		this.party = party;
	}

	public Nominee() {

	}

	@Id
	// @GeneratedValue (strategy = GenerationType.AUTO)
	private Integer nominee_id;
	private String candidate;
	private String party;

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

	public Integer getNominee_id() {
		return nominee_id;
	}

	public void setNominee_id(Integer nominee_id) {
		this.nominee_id = nominee_id;
	}

	@Override
	public String toString() {
		return "Nominee [nominee_id=" + nominee_id + ", candidate=" + candidate + ", party=" + party + "]";
	}

}

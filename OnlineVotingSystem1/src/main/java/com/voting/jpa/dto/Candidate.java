package com.voting.jpa.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
//if the table name is different from class name
@Table(name = "voters_list")
//@SequenceGenerator(sequenceName="next_val", name = "next_val",initialValue=12,allocationSize=1)
public class Candidate {
	public Candidate(Integer candidate_id,
			@NotEmpty(message = "First name is required") @Pattern(regexp = "^[a-zA-Z]{3,}$") String firstname,
			@NotEmpty(message = "Last name is required") @Pattern(regexp = "^[a-zA-Z]{3,}$") String lastname,
			char gender,
			@NotEmpty(message = "voter card number is required") @Pattern(regexp = "^([a-zA-Z]){3}([0-9]){7}?$", message = "Voter card number is invalid") String votercardnumber,
			@NotEmpty(message = "Phone number is required") @Pattern(regexp = "^[6-9]{1}[0-9]{9}$", message = "Mobile number is invalid") String contact,
			String dob,
			@NotEmpty(message = "Email is required") @Pattern(regexp = "^[a-zA-Z]{1}[a-zA-Z0-9]{2,}@[a-z]{5}.com$", message = "Email address is invalid") String email,
			String party) {
		super();
		this.candidate_id = candidate_id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.gender = gender;
		this.votercardnumber = votercardnumber;
		this.contact = contact;
		this.dob = dob;
		this.email = email;
		this.party = party;
	}

	public Candidate() {
		super();
	}

	@Id
	// for generating automatic PK values
	// @GeneratedValue(strategy=GenerationType.IDENTITY,generator="next_val")
	private Integer candidate_id;

	@NotEmpty(message = "First name is required")
	@Pattern(regexp = "^[a-zA-Z]{3,}$")
	private String firstname;

	@NotEmpty(message = "Last name is required")
	@Pattern(regexp = "^[a-zA-Z]{3,}$")
	private String lastname;

	private char gender;

	@Column(unique = true)
	@NotEmpty(message = "voter card number is required")
	@Pattern(regexp = "^([a-zA-Z]){3}([0-9]){7}?$", message = "Voter card number is invalid")
	private String votercardnumber;

	@Column(unique = true)
	@NotEmpty(message = "Phone number is required")
	@Pattern(regexp = "^[6-9]{1}[0-9]{9}$", message = "Mobile number is invalid")
	// @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[-
	// ]?(\\d{4})$",message="Mobile number is invalid")
	private String contact;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private String dob;

	@Column(unique = true)
	@NotEmpty(message = "Email is required")
	@Pattern(regexp = "^[a-zA-Z]{1}[a-zA-Z0-9]{2,}@[a-z]{5}.com$", message = "Email address is invalid")
	private String email;

	private String party;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "rcandidate")
	private Votes votes;

	@ElementCollection
	private List<@NotEmpty String> response;

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getVotercardnumber() {
		return votercardnumber;
	}

	public void setVotercardnumber(String votercardnumber) {
		this.votercardnumber = votercardnumber;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public List<String> getResponse() {
		return response;
	}

	public void setResponse(List<String> response) {
		this.response = response;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Votes getVotes() {
		return votes;
	}

	public void setVotes(Votes votes) {
		this.votes = votes;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public Integer getCandidate_id() {
		return candidate_id;
	}

	public void setCandidate_id(Integer candidate_id) {
		this.candidate_id = candidate_id;
	}

	@Override
	public String toString() {
		return "Candidate [candidate_id=" + candidate_id + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", gender=" + gender + ", votercardnumber=" + votercardnumber + ", contact=" + contact + ", dob="
				+ dob + ", email=" + email + ", party=" + party + ", votes=" + votes + ", response=" + response + "]";
	}
}

package com.jobapplication.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "employer")
public class Employer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "userid")
	private User user;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy = "employer",cascade = CascadeType.ALL)
	private List<Job> jobs;

	@Column(name = "username")
	private String username;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "phone")
	private String phone;

	@Column(name = "company")
	private String company;

	@Column(name = "position")
	private String position;

	public Employer() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPosition() {
		return position;
	}


	public void setPosition(String position) {
		this.position = position;
	}

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}
	
	

	public Employer(List<Job> jobs, String username, String firstName, String lastName, String email, String phone,
			String company, String position) {
		this.jobs = jobs;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.company = company;
		this.position = position;
	}
	
	@Override
	public String toString() {
		return "Employer [id=" + id + ", user=" + user + ", jobs=" + jobs + ", username=" + username + ", firstName="
				+ firstName + ", lastName=" + lastName + ", email=" + email + ", phone=" + phone + ", company="
				+ company + ", position=" + position + "]";
	}

	public void addJobs(Job theJob){
		if(jobs == null){
			jobs = new ArrayList<>();
		}
		jobs.add(theJob);
	}
}

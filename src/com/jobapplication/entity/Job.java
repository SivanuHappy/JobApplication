package com.jobapplication.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.jobapplication.validation.SalaryValid;

@Entity
@Table(name = "job")
public class Job {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "title")
	@NotEmpty(message="Mandatory field")
	private String title;

	@Column(name = "company")
	@NotEmpty(message="Mandatory field")
	private String company;

	@Column(name = "salary")
	@SalaryValid(message = "Salary cannot be 0 or greater than $9,999,999.99")
	private String salary;

	@Column(name = "skills")
	@NotEmpty(message="Mandatory field")
	private String skills;

	@Column(name = "city")
	@NotEmpty(message="Mandatory field")
	private String city;

	@Column(name = "state")
	@NotEmpty(message="Mandatory field")
	private String state;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employerid")
	private Employer employer;

	public Job() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public Employer getEmployer() {
		return employer;
	}

	public void setEmployer(Employer employer) {
		this.employer = employer;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public Job(String title, String company, String salary, String skills, String city, String state) {
		this.title = title;
		this.company = company;
		this.salary = salary;
		this.skills = skills;
		this.city = city;
		this.state = state;
	}

	@Override
	public String toString() {
		return "Job [id=" + id + ", title=" + title + ", company=" + company + ", salary=" + salary + ", skills="
				+ skills + ", city=" + city + ", state=" + state + ", employer=" + employer + "]";
	}


}

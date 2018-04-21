package com.jobapplication.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.jobapplication.validation.EmailValid;
import com.jobapplication.validation.PhoneValid;


@PhoneValid(message="Invalid phone number")
@Entity
@Table(name="applicant")
public class Applicant {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="userid")
	private User user;
	
	
	@Column(name="username")
	private String username;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	@Column(name="email")
	@EmailValid(message="Invalid email address")
	private String email;

	@Column(name="phone")
	private String phone;

	@Column(name="address1")
	private String address1;

	@Column(name="address2")
	private String address2;

	@Column(name="city")
	private String city;

	@Column(name="state")
	private String state;

	@Column(name="zip")
	private int zip;

	@Column(name="country")
	private String country;

	@Column(name="institution")
	private String institution;

	@Column(name="degree")
	private String degree;

	@Column(name="major")
	private String major;

	@Column(name="company")
	private String company;

	@Column(name="designation")
	private String designation;

	@Column(name="skills")
	private String skills;


	public Applicant() {

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

	public String getAddress1() {
		return address1;
	}


	public void setAddress1(String address1) {
		this.address1 = address1;
	}


	public String getAddress2() {
		return address2;
	}


	public void setAddress2(String address2) {
		this.address2 = address2;
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


	public int getZip() {
		return zip;
	}


	public void setZip(int zip) {
		this.zip = zip;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getInstitution() {
		return institution;
	}


	public void setInstitution(String institution) {
		this.institution = institution;
	}


	public String getDegree() {
		return degree;
	}


	public void setDegree(String degree) {
		this.degree = degree;
	}


	public String getMajor() {
		return major;
	}


	public void setMajor(String major) {
		this.major = major;
	}


	public String getCompany() {
		return company;
	}


	public void setCompany(String company) {
		this.company = company;
	}


	public String getDesignation() {
		return designation;
	}


	public void setDesignation(String designation) {
		this.designation = designation;
	}


	public String getSkills() {
		return skills;
	}


	public void setSkills(String skills) {
		this.skills = skills;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "Applicant [id=" + id + ", user=" + user + ", username=" + username + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", phone=" + phone + ", address1=" + address1
				+ ", address2=" + address2 + ", city=" + city + ", state=" + state + ", zip=" + zip + ", country="
				+ country + ", institution=" + institution + ", degree=" + degree + ", major=" + major + ", company="
				+ company + ", designation=" + designation + ", skills=" + skills + "]";
	}
	
	public Applicant(String username, String firstName, String lastName, String email, String phone, String address1,
			String address2, String city, String state, int zip, String country, String institution, String degree,
			String major, String company, String designation, String skills) {
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.country = country;
		this.institution = institution;
		this.degree = degree;
		this.major = major;
		this.company = company;
		this.designation = designation;
		this.skills = skills;
	}

}

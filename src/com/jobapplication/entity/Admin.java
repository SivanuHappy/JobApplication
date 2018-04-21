package com.jobapplication.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="admin")
public class Admin {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;
	
	@Column(name="password")
	private String password;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Admin(){
		
	}
	
	public Admin(int id, String username, String firstName, String lastName, String password) {
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", username=" + username + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", password=" + password + "]";
	}

}

package com.jobapplication.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.jobapplication.validation.PasswordMatch;

@PasswordMatch(message="Password does not match")
@Entity
@Table(name="user")
public class User {
 
@Id 
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name="id")
private int id;
	
@Column(name="username")
@NotEmpty(message="Mandatory field")
private String username;

@Column(name="first_name")
@NotEmpty(message="Mandatory field")
private String firstName;
 
@Column(name="last_name")
@NotEmpty(message="Mandatory field")
private String lastName;
 
@Column(name="role")
@NotEmpty(message="Select your role")
private String role;

@Column(name="password")
@NotEmpty(message="Password is required")
@Size(min=6, message="Length must be minimum 6 characters")
private String password;

@NotEmpty(message="Confirm Password is required")
@Column(name="confirmpassword")
private String confirmpassword;

public User() {
	}

public int getId() {
	return id;
}


public void setId(int id) {
	this.id = id;
}


public String getConfirmpassword() {
	return confirmpassword;
}


public void setConfirmpassword(String confirmpassword) {
	this.confirmpassword = confirmpassword;
}


public String getPassword() {
	return password;
}


public void setPassword(String password) {
	this.password = password;
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

public String getRole() {
	return role;
}

public void setRole(String role) {
	this.role = role;
}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public User(@NotEmpty(message = "Mandatory field") String username,
		@NotEmpty(message = "Mandatory field") String firstName, @NotEmpty(message = "Mandatory field") String lastName,
		@NotEmpty(message = "Select your role") String role,
		@NotEmpty(message = "Password is required") @Size(min = 6, message = "Length must be minimum 6 characters") String password,
		@NotEmpty(message = "Confirm Password is required") String confirmpassword) {
	this.username = username;
	this.firstName = firstName;
	this.lastName = lastName;
	this.role = role;
	this.password = password;
	this.confirmpassword = confirmpassword;
}


@Override
public String toString() {
	return "User [id=" + id + ", username=" + username + ", firstName=" + firstName + ", lastName=" + lastName
			+ ", role=" + role + ", password=" + password + ", confirmpassword=" + confirmpassword + "]";
}
}


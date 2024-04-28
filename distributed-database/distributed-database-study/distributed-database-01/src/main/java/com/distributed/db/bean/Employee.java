package com.distributed.db.bean;

import java.io.Serializable;

public class Employee implements Serializable {

	private static final long serialVersionUID = -1213437434677255221L;

	private Long id;
	private String lastName;
	private String gender;
	private String email;

	public Employee() {

	}

	public Employee(String lastName, String gender, String email) {
		super();
		this.lastName = lastName;
		this.gender = gender;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return this.id + "_" + this.lastName + "_" + this.gender + "_" + this.email;
	}

}

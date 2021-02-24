package com.assignment.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Heroe {

	@Id
	private Long id;
	private String firstName;
	private String lastName;
	private String nickName;
	
	protected Heroe() {
		
	}
	
	public Heroe(String firstName, String lastName, String nickName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.nickName = nickName;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public String getNickName() {
		return nickName;
	}
	
}

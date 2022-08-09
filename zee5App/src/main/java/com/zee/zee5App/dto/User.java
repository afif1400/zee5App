package com.zee.zee5App.dto;

import java.time.LocalDate;

import lombok.Setter;
import lombok.ToString;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Setter // annotation : metadata
@Getter
//@AllArgsConstructor
public class User {
	
	public User() {
		
	}

	public User(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public User(String userId, String firstName, String lastName, String email, LocalDate doj, LocalDate dob,
			boolean active) {
		this(userId, firstName, lastName, email, doj, dob);
		this.active = active;
	}

	public User(String userId, String firstName, String lastName, String email, LocalDate doj, LocalDate dob) {
		this(firstName, lastName, email);
		this.userId = userId;
		this.doj = doj;
		this.dob = dob;
	}

	// @Setter(value = AccessLevel.NONE)
	private String userId; // instance reference
	private String firstName;
	private String lastName;
	private String email;
	private LocalDate doj;
	private LocalDate dob;
	private boolean active;
	
	

}

package com.cisc181.core;

import java.util.Calendar;

public class PersonException extends Exception {
	private Person person;
	
	public PersonException(String message, Person person) {
		super(message);
		this.person = person;
	}
	
	
}

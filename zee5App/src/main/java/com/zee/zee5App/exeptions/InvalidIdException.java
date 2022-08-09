package com.zee.zee5App.exeptions;

public class InvalidIdException extends Exception {
	// toString method and super call must be there

	public InvalidIdException(String msg) {
		// TODO Auto-generated constructor stub
		super(msg);
	}

	@Override
	public String toString() {
		return super.getMessage();
	}
}

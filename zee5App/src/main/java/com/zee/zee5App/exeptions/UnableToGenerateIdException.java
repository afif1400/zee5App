package com.zee.zee5App.exeptions;

@SuppressWarnings("serial")
public class UnableToGenerateIdException extends Exception {
	public UnableToGenerateIdException(String msg) {
		super(msg);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.getMessage();
	}
}

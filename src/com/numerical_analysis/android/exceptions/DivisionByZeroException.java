package com.numerical_analysis.android.exceptions;

public class DivisionByZeroException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DivisionByZeroException() {
		super();
	}

	public DivisionByZeroException(String message) {
		super(message);
	}

}

package com.numerical_analysis.android.exceptions;

public class NoUniqueSolutionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public NoUniqueSolutionException() {
		super();
	}

	/**
	 * @param detailMessage
	 */
	public NoUniqueSolutionException(String detailMessage) {
		super(detailMessage);
	}
}

package com.numerical_analysis.android.exceptions;

public class MultipleRootFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public MultipleRootFoundException() {
		super();
	}

	/**
	 * @param detailMessage
	 */
	public MultipleRootFoundException(String detailMessage) {
		super(detailMessage);
	}
}

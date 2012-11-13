package com.numerical_analysis.android.methods;

import java.util.ArrayList;

import com.numerical_analysis.android.exceptions.DivisionByZeroException;

public class Interpolation implements MethodGroup {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double[] x;
	private double[] y;
	private ArrayList<Double[]> executionTable;

	public String newton() throws DivisionByZeroException {
		Double executionTable[][] = new Double[x.length][x.length + 1];
		for (int i = 0; i < x.length; i++) {
			executionTable[i][0] = x[i];
			executionTable[i][1] = y[i];
		}
		String polynomial = executionTable[0][1].toString();
		for (int i = 2; i < x.length + 1; i++) {
			for (int j = i + 1; j < x.length; j++) {
				double denominator = executionTable[0][0]
						- executionTable[j][0];
				if (denominator != 0) {
					executionTable[i][j] = (executionTable[i - 1][j - 1] - executionTable[i - 1][j])
							/ denominator;
					if (j == i + 1) {
						polynomial += "+" + executionTable[i][j];
						for (int k = j - 1; k >= 0; k--) {
							polynomial += "*(x-" + executionTable[j][j + 1]
									+ ")";
						}
					}
				} else {
					throw new DivisionByZeroException("Division by zero");
				}
			}
		}
		return polynomial;
	}

	public ArrayList<Double[]> getExecutionTable() {
		return executionTable;
	}

	/**
	 * @return the x
	 */
	public double[] getX() {
		return x;
	}

	/**
	 * @param ds
	 *            the x to set
	 */
	public void setX(double[] ds) {
		this.x = ds;
	}

	/**
	 * @return the y
	 */
	public double[] getY() {
		return y;
	}

	/**
	 * @param ds
	 *            the y to set
	 */
	public void setY(double[] ds) {
		this.y = ds;
	}
}

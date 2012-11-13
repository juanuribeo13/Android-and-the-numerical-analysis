package com.numerical_analysis.android.methods;

import java.util.ArrayList;
import java.util.Arrays;

import org.nfunk.jep.JEP;

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
			int row = 0;
			for (int j = i - 1; j < x.length; j++) {
				double denominator = executionTable[row][0]
						- executionTable[j][0];
				row++;
				if (denominator != 0) {
					executionTable[j][i] = (executionTable[j - 1][i - 1] - executionTable[j][i - 1])
							/ denominator;
					if (j == i - 1) {
						polynomial += "+" + executionTable[j][i];
						for (int k = j - 1; k >= 0; k--) {
							polynomial += "*(x-" + executionTable[k][0] + ")";
						}
					}
				} else {
					throw new DivisionByZeroException("Division by zero");
				}
			}
		}
		this.executionTable = new ArrayList(Arrays.asList(executionTable));
		return polynomial;
	}

	public String[] lagrange() {
		String[] result = new String[x.length + 1];
		result[0] = "";
		for (int i = 0; i < x.length; i++) {
			result[0] += "L" + i + "*" + y[i];
			if (i != x.length - 1) {
				result[0] += "+";
			}
			String numerator = "(";
			String divisor = "(";
			for (int j = 0; j < x.length; j++) {
				if (j != i) {
					numerator += "(x-" + x[j] + ")";
					divisor += "(" + x[i] + "-" + x[j] + ")";
					if (j != x.length - 1) {
						numerator += "*";
						divisor += "*";
					}
				}
			}
			if (i == x.length - 1) {
				numerator = numerator.substring(0, numerator.length() - 1);
				divisor = divisor.substring(0, divisor.length() - 1);
			}
			numerator += ")";
			divisor += ")";
			result[i + 1] = numerator + "/" + divisor;
		}
		return result;
	}

	public double[] lagrange(String equations[], double x0) {
		double[] result = new double[equations.length];
		for (int i = equations.length - 1; i > 0; i--) {
			result[i] = evaluatePolynomial(equations[i], x0);
		}
		result[0] = evaluateLagrange(equations[0], result);
		return result;
	}

	public double neville(double x0) throws DivisionByZeroException {
		Double executionTable[][] = new Double[x.length][x.length + 1];
		for (int i = 0; i < x.length; i++) {
			executionTable[i][0] = x[i];
			executionTable[i][1] = y[i];
		}
		for (int i = 2; i < x.length + 1; i++) {
			int row = 0;
			for (int j = i - 1; j < x.length; j++) {
				double denominator = executionTable[j][0]
						- executionTable[row][0];
				if (denominator != 0) {
					executionTable[j][i] = ((x0 - executionTable[row][0])
							* executionTable[j][i - 1] - (x0 - executionTable[j][0])
							* executionTable[j - 1][i - 1])
							/ denominator;
				} else {
					throw new DivisionByZeroException("Division by zero");
				}
				row++;
			}
		}
		this.executionTable = new ArrayList(Arrays.asList(executionTable));
		return executionTable[x.length - 1][x.length];
	}

	private double evaluateLagrange(String polynomial, double values[]) {
		JEP jep = new JEP();
		jep.addStandardFunctions();
		for (int i = 1; i < values.length; i++) {
			jep.addVariable("L" + (i - 1), values[i]);
		}
		jep.parseExpression(polynomial);
		return jep.getValue();
	}

	public double evaluatePolynomial(String polynomial, double value) {
		JEP jep = new JEP();
		jep.addStandardFunctions();
		jep.addVariable("x", value);
		jep.parseExpression(polynomial);
		return jep.getValue();
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

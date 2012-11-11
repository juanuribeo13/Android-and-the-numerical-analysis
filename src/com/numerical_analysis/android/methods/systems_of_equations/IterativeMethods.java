package com.numerical_analysis.android.methods.systems_of_equations;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import org.nfunk.jep.JEP;

import com.numerical_analysis.android.exceptions.MaximumNumberOfIterationsExceededExeption;

public class IterativeMethods implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Double[]> executionTable;

	private String[] independentTerms;
	private Double[] initialValues;

	public ArrayList<Double> jacobi(int iterations, double tolerance)
			throws MaximumNumberOfIterationsExceededExeption {
		Double[] initialValues = this.initialValues.clone();
		ArrayList<Double> result = null;
		executionTable = new ArrayList<Double[]>();
		int counter = 1;
		double error = tolerance + 1;
		Double[] row = new Double[independentTerms.length + 2];
		row[0] = (double) 0;
		for (int i = 0; i < initialValues.length; i++) {
			row[i + 1] = initialValues[1];
		}
		row[independentTerms.length + 1] = error;
		executionTable.add(row);
		while (error > tolerance && counter <= iterations) {
			error = 0;
			Double values[] = new Double[initialValues.length];
			row = new Double[independentTerms.length + 2];
			row[0] = (double) counter;
			for (int i = 0; i < independentTerms.length; i++) {
				values[i] = evaluateFunction(independentTerms[i], initialValues);
				row[i + 1] = values[i];
				error += Math.pow(values[i] - initialValues[i], 2);
			}
			error = Math.sqrt(error);
			row[independentTerms.length + 1] = error;
			executionTable.add(row);
			initialValues = values;
			counter++;
		}
		if (error <= tolerance) {
			result = new ArrayList(Arrays.asList(initialValues));
			result.add(error);
		} else {
			throw new MaximumNumberOfIterationsExceededExeption(
					"Maximum number of iterations exceeded");
		}
		return result;
	}

	public double evaluateFunction(String function, Double[] values) {
		JEP jep = new JEP();
		jep.addStandardFunctions();
		for (int i = 0; i < values.length; i++) {
			jep.addVariable("x" + (i + 1), values[i]);
		}
		jep.parseExpression(function);
		return jep.getValue();
	}

	/**
	 * @return the independentTerms
	 */
	public String[] getIndependentTerms() {
		return independentTerms;
	}

	/**
	 * @param independentTerms
	 *            the independentTerms to set
	 */
	public void setIndependentTerms(String[] independentTerms) {
		this.independentTerms = independentTerms;
	}

	/**
	 * @return the initialValues
	 */
	public Double[] getInitialValues() {
		return initialValues;
	}

	/**
	 * @param initialValues
	 *            the initialValues to set
	 */
	public void setInitialValues(Double[] initialValues) {
		this.initialValues = initialValues;
	}
}

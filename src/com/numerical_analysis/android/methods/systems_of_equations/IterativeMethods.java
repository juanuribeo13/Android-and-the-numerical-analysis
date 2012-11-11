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

	public ArrayList<Double> jacobi(String[] b, Double[] initialValues,
			int iterations, double tolerance)
			throws MaximumNumberOfIterationsExceededExeption {
		ArrayList<Double> result = null;
		executionTable = new ArrayList<Double[]>();
		int counter = 1;
		double error = tolerance + 1;
		Double[] row = new Double[b.length + 2];
		row[0] = (double) 0;
		for (int i = 0; i < initialValues.length; i++) {
			row[i + 1] = initialValues[1];
		}
		row[b.length + 1] = error;
		executionTable.add(row);
		while (error > tolerance && counter <= iterations) {
			error = 0;
			Double values[] = new Double[initialValues.length];
			row = new Double[b.length + 2];
			row[0] = (double) counter;
			for (int i = 0; i < b.length; i++) {
				values[i] = evaluateFunction(b[i], initialValues);
				row[i + 1] = values[i];
				error += Math.pow(values[i] - initialValues[i], 2);
			}
			error = Math.sqrt(error);
			row[b.length + 1] = error;
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
}

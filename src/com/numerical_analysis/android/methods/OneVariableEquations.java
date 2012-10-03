package com.numerical_analysis.android.methods;

import java.io.Serializable;
import java.util.ArrayList;

import org.nfunk.jep.JEP;

import com.numerical_analysis.android.exceptions.RootFoundException;
import com.numerical_analysis.android.exceptions.RootNotFoundException;

public class OneVariableEquations implements Serializable {

	private static final long serialVersionUID = 1L;
	private String function;
	private ArrayList<double[]> executionTable;

	public double[] incrementalSearch(double x0, double delta, int iterations)
			throws RootNotFoundException, RootFoundException {
		double[] interval = new double[2];
		double y0 = evaluateFunction(x0);

		if (y0 == 0) {
			throw new RootFoundException("The root is " + x0);
		} else {
			double x1 = x0 + delta;
			int counter = 1;
			double y1 = evaluateFunction(x1);
			executionTable = new ArrayList<double[]>();
			double row[] = new double[5];
			row[0] = counter;
			row[1] = x0;
			row[2] = x1;
			row[3] = y0;
			row[4] = y1;
			executionTable.add(row);
			while ((y0 * y1) > 0 && counter < iterations) {
				x0 = x1;
				y0 = y1;
				x1 = x0 + delta;
				y1 = evaluateFunction(x1);
				counter++;
				row = new double[5];
				row[0] = counter;
				row[1] = x0;
				row[2] = x1;
				row[3] = y0;
				row[4] = y1;
				executionTable.add(row);
			}
			if (y1 == 0) {
				throw new RootFoundException("The root is " + x1);
			} else if ((y0 * y1) < 0) {
				interval[0] = x0;
				interval[1] = x1;
			} else {
				throw new RootNotFoundException(
						"Root not found in these iterations");
			}
		}
		return interval;
	}

	public double evaluateFunction(double value) {
		JEP jep = new JEP();
		jep.addStandardFunctions();
		jep.addVariable("x", value);
		jep.parseExpression(function);
		return jep.getValue();
	}

	/**
	 * @return the function
	 */
	public String getFunction() {
		return function;
	}

	/**
	 * @param function
	 *            the function to set
	 */
	public void setFunction(String function) {
		this.function = function;
	}

	/**
	 * @return the executionTable generated in the last execution
	 */
	public ArrayList<double[]> getExecutionTable() {
		return executionTable;
	}
}

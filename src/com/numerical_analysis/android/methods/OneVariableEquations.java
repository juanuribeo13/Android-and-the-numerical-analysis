package com.numerical_analysis.android.methods;

import java.io.Serializable;

import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlEngine;

import org.apache.commons.jexl2.MapContext;

import com.numerical_analysis.android.exceptions.RootNotFounException;

public class OneVariableEquations implements Serializable {

	private static final long serialVersionUID = 1L;
	private String function;

	public double[] incrementalSearch(double x0, double delta, int iterations)
			throws RootNotFounException {
		double[] interval = new double[2];
		double y0 = evaluateFunction(x0);

		if (y0 == 0) {
			// TODO: Ask Pacho if we shuold return x0 and x0 or x0 and x0 +
			// delta
			interval[0] = x0;
			interval[1] = x0;
		} else {
			double x1 = x0 + delta;
			int counter = 1;
			double y1 = evaluateFunction(x1);
			while ((y0 * y1) > 0 && counter < iterations) {
				x0 = x1;
				y0 = y1;
				x1 = x0 + delta;
				y1 = evaluateFunction(x1);
				counter++;
			}
			if (y1 == 0) {
				// TODO: Ask Pacho if we shuold return x1 and x1 or x0 and x1
				interval[0] = x1;
				interval[1] = x1;
			} else if ((y0 * y1) < 0) {
				interval[0] = x0;
				interval[1] = x1;
			} else {
				throw new RootNotFounException("Root not found in these iterations");
			}
		}
		return interval;
	}

	public double evaluateFunction(double value) {
		JexlEngine engine = new JexlEngine();
		Expression expression = engine.createExpression(function);
		MapContext context = new MapContext();
		context.set("x", value);
		return (Double) expression.evaluate(context);
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
}

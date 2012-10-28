package com.numerical_analysis.android.methods;

import java.io.Serializable;
import java.util.ArrayList;

import org.nfunk.jep.JEP;

import com.numerical_analysis.android.exceptions.MultipleRootFoundException;
import com.numerical_analysis.android.exceptions.RootFoundException;
import com.numerical_analysis.android.exceptions.RootNotFoundException;

public class OneVariableEquations implements Serializable {

	private static final long serialVersionUID = 1L;
	private String function;
	private ArrayList<double[]> executionTable;

	/**
	 * 
	 * @param x0
	 *            The value from which you want to start searching
	 * @param delta
	 *            The value in which you want x0 to be incremented on each
	 *            iteration
	 * @param iterations
	 *            The maximum number of iterations
	 * @return An array of doubles with two positions, the position 0 contains
	 *         the lower limit of the interval and the position 1 contains the
	 *         upper limit of the interval
	 * @throws RootNotFoundException
	 *             When the method doesn't find an interval that contains a root
	 * @throws RootFoundException
	 *             When the method finds a root
	 */
	public double[] incrementalSearch(double x0, double delta, int iterations)
			throws RootNotFoundException, RootFoundException {
		double[] interval = new double[2];
		double y0 = evaluateFunction(function, x0);

		if (y0 == 0) {
			throw new RootFoundException("The root is " + x0);
		} else {
			double x1 = x0 + delta;
			int counter = 1;
			double y1 = evaluateFunction(function, x1);
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
				y1 = evaluateFunction(function, x1);
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

	/**
	 * 
	 * @param x0
	 *            One of the limits of the interval
	 * @param x1
	 *            One of the limits of the interval
	 * @param iterations
	 *            The maximum number of iterations
	 * @param tolerance
	 *            The maximum error of the answer
	 * @return An array of doubles with two positions, the position 0 contains
	 *         the root and the position 1 contains the relative error
	 * @throws RootFoundException
	 *             When the method finds a root
	 * @throws RootNotFoundException
	 *             When the method doesn't find a root
	 */
	public double[] bisection(double x0, double x1, int iterations,
			double tolerance) throws RootFoundException, RootNotFoundException {
		double root[] = new double[2];
		double y0 = evaluateFunction(function, x0);
		double y1 = evaluateFunction(function, x1);

		if (y0 == 0) {
			throw new RootFoundException(x0 + " is root");
		} else if (y1 == 0) {
			throw new RootFoundException(x1 + " is root");
		} else if ((y0 * y1) < 0) {
			double xm = (x0 + x1) / 2;
			double ym = evaluateFunction(function, xm);
			int counter = 1;
			double error = tolerance + 1;
			while (ym != 0 && error > tolerance && counter < iterations) {
				if ((y0 * ym) < 0) {
					x1 = xm;
					y1 = ym;
				} else {
					x0 = xm;
					y0 = ym;
				}
				double xmAux = xm;
				xm = (x0 + x1) / 2;
				ym = evaluateFunction(function, xm);
				error = Math.abs(xm - xmAux);
				counter++;
			}

			if (ym == 0) {
				throw new RootFoundException(xm + " is root");
			} else if (error <= tolerance) {
				root[0] = xm;
				root[1] = error;
			} else {
				throw new RootNotFoundException(
						"Root not found in these iterations");
			}
		}
		return root;
	}

	/**
	 * 
	 * @param x0
	 *            One of the limits of the interval
	 * @param x1
	 *            One of the limits of the interval
	 * @param iterations
	 *            The maximum number of iterations
	 * @param tolerance
	 *            The maximum error of the answer
	 * @return An array of doubles with two positions, the position 0 contains
	 *         the root and the position 1 contains the relative error
	 * @throws RootFoundException
	 *             When the method finds a root
	 * @throws RootNotFoundException
	 *             When the method doesn't find a root
	 */
	public double[] falseRule(double x0, double x1, int iterations,
			double tolerance) throws RootFoundException, RootNotFoundException {
		double root[] = new double[2];
		double y0 = evaluateFunction(function, x0);
		double y1 = evaluateFunction(function, x1);

		if (y0 == 0) {
			throw new RootFoundException(x0 + " is root");
		} else if (y1 == 0) {
			throw new RootFoundException(x1 + " is root");
		} else if ((y0 * y1) < 0) {
			double xm = (x0 + x1) / 2;
			double ym = evaluateFunction(function, xm);
			int counter = 1;
			double error = tolerance + 1;
			while (ym != 0 && error > tolerance && counter < iterations) {
				if ((y0 * ym) < 0) {
					x1 = xm;
					y1 = ym;
				} else {
					x0 = xm;
					y0 = ym;
				}
				double xmAux = xm;
				xm = x0 - ((y0 * (x1 - x0)) / (y1 - y0));
				ym = evaluateFunction(function, xm);
				error = Math.abs(xm - xmAux);
				counter++;
			}

			if (ym == 0) {
				throw new RootFoundException(xm + " is root");
			} else if (error <= tolerance) {
				root[0] = xm;
				root[1] = error;
			} else {
				throw new RootNotFoundException(
						"Root not found in these iterations");
			}
		}
		return root;
	}

	/**
	 * 
	 * @param x0
	 *            Start point
	 * @param iterations
	 *            The maximum number of iterations
	 * @param tolerance
	 *            The maximum error of the answer
	 * @param g
	 *            The other function
	 * @return An array of doubles with two positions, the position 0 contains
	 *         the root and the position 1 contains the relative error
	 * @throws RootFoundException
	 *             When the method finds a root
	 * @throws RootNotFoundException
	 *             When the method doesn't find a root
	 */
	public double[] fixedPoint(double x0, int iterations, double tolerance,
			String g) throws RootFoundException, RootNotFoundException {
		double root[] = new double[2];
		double y0 = evaluateFunction(function, x0);
		int counter = 0;
		double error = tolerance + 1;
		while (y0 != 0 && error > tolerance && counter < iterations) {
			double xn = evaluateFunction(g, x0);
			y0 = evaluateFunction(function, xn);
			error = Math.abs(xn - x0);
			x0 = xn;
			counter++;
		}
		if (y0 == 0) {
			throw new RootFoundException(x0 + " is root");
		} else if (error <= tolerance) {
			root[0] = x0;
			root[1] = error;
		} else {
			throw new RootNotFoundException(
					"Root not found in these iterations");
		}
		return root;
	}

	// TODO do the first iteration before the while
	// TODO change the x1 type to double (primitive)
	/**
	 * 
	 * @param x0
	 *            Start point
	 * @param iterations
	 *            The maximum number of iterations
	 * @param tolerance
	 *            The maximum error of the answer
	 * @param derivative
	 *            The first derivative of the function
	 * @return An array of doubles with two positions, the position 0 contains
	 *         the root and the position 1 contains the relative error
	 * @throws RootFoundException
	 *             When the method finds a root
	 * @throws MultipleRootFoundException
	 *             When the method finds a possible multiple root
	 * @throws RootNotFoundException
	 *             When the method doesn't find a root
	 */
	public double[] newton(double x0, int iterations, double tolerance,
			String derivative) throws RootFoundException,
			MultipleRootFoundException, RootNotFoundException {
		double root[] = new double[2];
		double y0 = evaluateFunction(function, x0);
		double dy = evaluateFunction(derivative, x0);
		int counter = 0;
		double error = tolerance + 1;
		Double x1 = null;
		while (y0 != 0 && dy != 0 && error > tolerance && counter < iterations) {
			x1 = x0 - (y0 / dy);
			y0 = evaluateFunction(function, x1);
			dy = evaluateFunction(derivative, x1);
			error = Math.abs(x1 - x0);
			x0 = x1;
			counter++;
		}
		if (y0 == 0) {
			throw new RootFoundException(x0 + " is root");
		} else if (error <= tolerance) {
			root[0] = x0;
			root[1] = error;
		} else if (dy == 0) {
			throw new MultipleRootFoundException(x1
					+ " is a possible multiple root");
		} else {
			throw new RootNotFoundException(
					"Root not found in these iterations");
		}
		return root;
	}

	/**
	 * 
	 * @param x0
	 *            Start value
	 * @param x1
	 *            Start value
	 * @param tolerance
	 *            The maximum error of the answer
	 * @param iterations
	 *            The maximum number of iterations
	 * @return An array of doubles with two positions, the position 0 contains
	 *         the root and the position 1 contains the relative error
	 * @throws RootFoundException
	 *             When the method finds a root
	 * @throws MultipleRootFoundException
	 *             When the method finds a possible multiple root
	 * @throws RootNotFoundException
	 *             When the method doesn't find a root
	 */
	public double[] secant(double x0, double x1, int iterations,
			double tolerance) throws RootFoundException,
			MultipleRootFoundException, RootNotFoundException {
		double root[] = new double[2];
		double y0 = evaluateFunction(function, x0);
		if (y0 == 0) {
			throw new RootFoundException(x0 + " is root");
		} else {
			double y1 = evaluateFunction(function, x1);
			int counter = 0;
			double error = tolerance + 1;
			double denominator = y1 - y0;
			while (y1 != 0 && denominator != 0 && error > tolerance
					&& counter < iterations) {
				double x2 = x1 - y1 * (x1 - x0) / denominator;
				error = Math.abs(x2 - x1);
				x0 = x1;
				y0 = evaluateFunction(function, x0);
				x1 = x2;
				y1 = evaluateFunction(function, x1);
				denominator = y1 - y0;
				counter++;
			}
			if (y1 == 0) {
				throw new RootFoundException(x0 + " is root");
			} else if (error <= tolerance) {
				root[0] = x0;
				root[1] = error;
			} else if (denominator == 0) {
				throw new MultipleRootFoundException(
						"There is a possible multiple root");
			} else {
				throw new RootNotFoundException(
						"Root not found in these iterations");
			}

		}
		return root;
	}

	public double evaluateFunction(String function, double value) {
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

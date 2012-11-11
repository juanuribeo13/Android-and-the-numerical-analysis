package com.numerical_analysis.android.utilities;

import java.io.Serializable;

public class Matrix implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double[][] matrix;
	private int rows;
	private int columns;
	private int[] marks;
	private double[] b;

	public double[] getB() {
		return b;
	}

	public void setB(double[] b) {
		this.b = b;
	}

	/**
	 * 
	 * @param rows
	 *            The number of rows in the matrix
	 * @param columns
	 *            The number of columns in the matrix
	 */
	public Matrix(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		matrix = new double[rows][columns];

		// Initialize the matrix to zeros
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++)
				matrix[i][j] = 0;
		}

		// Initialize the marks
		marks = new int[columns];
		for (int i = 0; i < columns; i++)
			marks[i] = i + 1;
	}

	public Matrix(int size) {
		this.rows = size;
		this.columns = size;
		matrix = new double[rows][columns];

		// Initialize the matrix to zeros
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++)
				matrix[i][j] = 0;
		}

		// Initialize the marks
		marks = new int[columns];
		for (int i = 0; i < columns; i++)
			marks[i] = i + 1;
	}

	/**
	 * 
	 * @param matrix
	 *            The matrix of the class
	 */
	public Matrix(double[][] matrix) {
		this.matrix = matrix;
		this.rows = matrix.length;
		this.columns = matrix[0].length;

		// Initialize the marks
		marks = new int[columns];
		for (int i = 0; i < columns; i++)
			marks[i] = i + 1;
	}

	public Matrix(double[][] matrix, int[] marks) {
		this.matrix = matrix;
		this.rows = matrix.length;
		this.columns = matrix[0].length;
		this.marks = marks;
	}

	public double[] getAsLinearArray() {
		double[] temp = new double[rows * columns];
		int i = 0;
		for (int k = 0; k < rows; k++) {
			for (int l = 0; l < columns; l++) {
				temp[i] = matrix[k][l];
				i++;
			}
		}
		return temp;
	}

	public Matrix createAugmentedMatrix(Matrix matrixA, double[] b) {
		int n = matrixA.getColumns();
		int n1 = n + 1;
		double[][] a = matrixA.getMatrix();
		double[][] ab = new double[n][n1];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				ab[i][j] = a[i][j];
			}
		}
		for (int w = 0; w < n; w++) {
			ab[w][n] = b[w];
		}
		matrixA.setMatrix(ab);
		return matrixA;
	}

	public void swapRows(int row1, int row2) {
		double[] temp = matrix[row1];
		matrix[row1] = matrix[row2];
		matrix[row2] = temp;
	}

	public void swapColumns(int column1, int column2) {
		for (int row = 0; row < rows; row++) {
			double temp = matrix[row][column1];
			matrix[row][column1] = matrix[row][column2];
			matrix[row][column2] = temp;
		}
	}

	public void swapMarks(int a, int b) {
		int temp = marks[a];
		marks[a] = marks[b];
		marks[b] = temp;
	}

	public double[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(double[][] matrix) {
		this.matrix = matrix;
		this.rows = matrix.length;
		this.columns = matrix[0].length;
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	public int[] getMarks() {
		return marks;
	}

	public void setMarks(int[] marks) {
		this.marks = marks;
	}

	// Only for testing
	public static void main(String[] args) {
		Matrix matrix = new Matrix(2, 2);
		double[][] m = { { 1, 2 }, { 2, 3 } };
		matrix.setMatrix(m);
		double[] b = { 5, 6 };
		matrix = matrix.createAugmentedMatrix(matrix, b);
		matrix.swapRows(0, 1);
		matrix.swapColumns(0, 1);
		m = matrix.getMatrix();
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				System.out.print(m[i][j] + " ");
			}
			System.out.println("\n");
		}
		System.out.println("\n\n");
		double[] x = matrix.getAsLinearArray();
		for (double d : x) {
			System.out.println(d + " ");
		}

	}

}

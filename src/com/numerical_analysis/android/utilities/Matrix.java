package com.numerical_analysis.android.utilities;

public class Matrix {

	private double[][] matrix;

	private int m;
	private int n;

	/**
	 * 
	 * @param m
	 *            The number of rows in the matrix
	 * @param n
	 *            The number of columns in the matrix
	 */
	public Matrix(int m, int n) {
		this.m = m;
		this.n = n;
		matrix = new double[m][n];

		// Initialize the matrix to zeros
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++)
				matrix[i][j] = 0;
		}

	}

	/**
	 * 
	 * @param matrix
	 *            The matrix of the class
	 */
	public Matrix(double[][] matrix) {
		this.matrix = matrix;
		this.m = matrix.length;
		this.n = matrix[0].length;
	}

	public Matrix createAugmentedMatrix(Matrix matrixA, double[] b) {
		int n = matrixA.getN();
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

	public double[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(double[][] matrix) {
		this.matrix = matrix;
		this.m = matrix.length;
		this.n = matrix[0].length;
	}

	public int getM() {
		return m;
	}

	public int getN() {
		return n;
	}

	// Only for testing
	public static void main(String[] args) {
		Matrix matrix = new Matrix(2, 2);
		double[][] m = { { 1, 2 }, { 2, 3 } };
		matrix.setMatrix(m);
		double[] b = { 5, 6 };
		matrix = matrix.createAugmentedMatrix(matrix, b);
		matrix.swapRows(0, 1);
		m = matrix.getMatrix();
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				System.out.print(m[i][j] + " ");
			}
			System.out.println("\n");
		}

	}

}

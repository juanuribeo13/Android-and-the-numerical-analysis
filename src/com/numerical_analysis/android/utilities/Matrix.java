package com.numerical_analysis.android.utilities;

public class Matrix {

	public double[][] createAugmentedMatrix(double[][] m, double[] b) {
		int n = m.length;
		int n1 = m.length + 1;
		double[][] a = new double[n][n1];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				a[i][j] = m[i][j];
			}
		}
		for (int w = 0; w < n; w++) {
			a[w][n] = b[w];
		}
		return a;
	}

	//Only for testing
	public static void main(String[] args) {
		Matrix matrix = new Matrix();
		double[][] m = { { 1, 2 }, { 2, 3 } };
		double[] b = { 5, 6 };
		m = matrix.createAugmentedMatrix(m, b);
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				System.out.print(m[i][j] + " ");
			}
			System.out.println("\n");
		}

	}

}

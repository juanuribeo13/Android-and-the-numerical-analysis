package com.numerical_analysis.android.methods.systems_of_equations;

import java.io.Serializable;

import com.numerical_analysis.android.exceptions.NoUniqueSolutionException;
import com.numerical_analysis.android.utilities.Matrix;

public class DirectMethods implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DirectMethods() {
	}

	public double[] simpleGaussianElimination(Matrix matrixA, double[] b) {
		int n = matrixA.getColumns();
		Matrix matrixAb = matrixA.createAugmentedMatrix(matrixA, b);
		double[][] ab = matrixA.getMatrix();
		for (int k = 0; k < n - 1; k++) {
			for (int i = k + 1; i < n; i++) {
				double multiplier = ab[i][k] / ab[k][k];
				for (int j = k; j < n + 1; j++) {
					ab[i][j] = ab[i][j] - multiplier * ab[k][j];
				}
			}
		}
		matrixAb.setMatrix(ab);
		return regressiveSubstitution(matrixAb);
	}

	public double[] regressiveSubstitution(Matrix matrixAb) {
		int n = matrixAb.getRows();
		double[] x = new double[n];
		double[][] ab = matrixAb.getMatrix();
		n--;
		x[n] = ab[n][n + 1] / ab[n][n];
		double summation = 0;
		for (int i = n - 1; i >=0; i--) {
			summation = 0;
			for (int p = i + 1; p < n; p++) {
				summation += ab[i][p] * x[p];
			}
			x[i] = (ab[i][n + 1] - summation) / ab[i][i];
		}
		return x;
	}

	public Matrix partialPivoting(Matrix matrixAb, int k)
			throws NoUniqueSolutionException {
		double[][] ab = matrixAb.getMatrix();
		double higher = Math.abs(ab[k][k]);
		int highestRow = k;
		int n = ab.length;
		for (int s = k + 1; s < n; s++) {
			if (Math.abs(ab[s][k]) > higher) {
				higher = Math.abs(ab[s][k]);
				highestRow = s;
			}
		}
		if (higher == 0) {
			throw new NoUniqueSolutionException(
					"The system has no unique solution");
		} else if (highestRow != k) {
			matrixAb.swapRows(highestRow, k);
		}
		return matrixAb;
	}

	public Matrix totalPivoting(Matrix matrixAb, int k)
			throws NoUniqueSolutionException {
		double[][] ab = matrixAb.getMatrix();
		int n = ab.length;
		double higher = 0;
		int highestRow = k;
		int highestColumn = k;
		for (int r = k; r < n; r++) {
			for (int s = k; s < n; s++) {
				if (Math.abs(ab[r][s]) > higher) {
					higher = ab[r][s];
					highestRow = r;
					highestColumn = s;
				}
			}
		}
		if (higher == 0) {
			throw new NoUniqueSolutionException(
					"The system has no unique solution");
		} else {

			if (highestRow != k)
				matrixAb.swapRows(highestRow, k);
			if (highestColumn != k) {
				matrixAb.swapColumns(highestColumn, k);
				matrixAb.swapMarks(highestColumn, k);
			}
		}

		return matrixAb;
	}
}

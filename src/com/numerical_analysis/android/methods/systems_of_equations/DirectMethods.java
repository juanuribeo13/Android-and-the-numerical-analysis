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

	public Matrix simpleGaussianElimination(Matrix matrixA, double[] b) {
		int n = matrixA.getN();
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
		return matrixAb;
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
		matrixAb.setMatrix(ab);
		if (higher == 0) {
			throw new NoUniqueSolutionException(
					"The system has no unique solution");
		} else if (highestRow != k) {
			matrixAb.swapRows(highestRow, k);
		}
		return matrixAb;
	}

}

package com.numerical_analysis.android.methods.systems_of_equations;

import java.io.Serializable;
import java.util.ArrayList;

import com.numerical_analysis.android.exceptions.NoUniqueSolutionException;
import com.numerical_analysis.android.utilities.Matrix;

public class DirectMethods implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2009332928662559506L;
	private ArrayList<double[][]> gaussianEliminationExecution;
	double[][] uExecution;
	double[][] lExecution;

	public DirectMethods() {
		gaussianEliminationExecution = new ArrayList<double[][]>();
	}

	public double[] simpleGaussianElimination(Matrix matrixA, double[] b) {
		gaussianEliminationExecution = new ArrayList<double[][]>();
		Matrix matrixAb = matrixA.createAugmentedMatrix(matrixA, b);
		double[][] ab = matrixAb.getMatrix();
		int n = ab.length;
		gaussianEliminationExecution.add(ab);
		for (int k = 0; k < n - 1; k++) {
			for (int i = k + 1; i < n; i++) {
				double multiplier = ab[i][k] / ab[k][k];
				for (int j = k; j <= n; j++) {
					ab[i][j] = ab[i][j] - (multiplier * ab[k][j]);
				}
			}
			gaussianEliminationExecution.add(ab);
		}
		matrixAb.setMatrix(ab);
		double[] x = regressiveSubstitution(matrixAb);
		return x;
	}

	public double[][] getuExecution() {
		return uExecution;
	}

	public double[][] getlExecution() {
		return lExecution;
	}

	public double[] gaussianEliminationWithPartialPivoting(Matrix matrixA,
			double[] b) throws NoUniqueSolutionException {
		gaussianEliminationExecution = new ArrayList<double[][]>();
		Matrix matrixAb = matrixA.createAugmentedMatrix((Matrix) matrixA, b);
		double[][] ab = matrixAb.getMatrix();
		int n = ab.length;
		gaussianEliminationExecution.add(matrixAb.copy(ab));
		for (int k = 0; k < n - 1; k++) {
			matrixAb = partialPivoting(matrixAb, k);
			for (int i = k + 1; i < n; i++) {
				double multiplier = ab[i][k] / ab[k][k];
				for (int j = k; j <= n; j++) {
					ab[i][j] = ab[i][j] - (multiplier * ab[k][j]);
				}
			}
			gaussianEliminationExecution.add(matrixAb.copy(ab));
		}
		matrixAb.setMatrix(ab);
		double[] x = regressiveSubstitution(matrixAb);
		return x;
	}

	public double[] gaussianEliminationWithTotalPivoting(Matrix matrixA,
			double[] b) throws NoUniqueSolutionException {
		gaussianEliminationExecution = new ArrayList<double[][]>();
		Matrix matrixAb = matrixA.createAugmentedMatrix((Matrix) matrixA, b);
		double[][] ab = matrixAb.getMatrix().clone();
		int n = ab.length;
		gaussianEliminationExecution.add(matrixAb.copy(ab));
		for (int k = 0; k < n - 1; k++) {
			matrixAb = totalPivoting(matrixAb, k);
			for (int i = k + 1; i < n; i++) {
				double multiplier = ab[i][k] / ab[k][k];
				for (int j = k; j <= n; j++) {
					ab[i][j] = ab[i][j] - (multiplier * ab[k][j]);
				}
			}
			gaussianEliminationExecution.add(matrixAb.copy(ab));
		}
		matrixAb.setMatrix(ab.clone());
		double[] x = regressiveSubstitution(matrixAb);
		return x;
	}

	public ArrayList<double[][]> getGaussianEliminationExecution() {
		return gaussianEliminationExecution;
	}

	public double[] regressiveSubstitution(Matrix matrixAb) {
		int n = matrixAb.getRows();
		double[] x = new double[n];
		double[][] ab = matrixAb.getMatrix();
		n--;
		x[n] = ab[n][n + 1] / ab[n][n];
		double summation = 0;
		for (int i = n - 1; i >= 0; i--) {
			summation = 0;
			for (int p = i + 1; p < n; p++) {
				summation += ab[i][p] * x[p];
			}
			x[i] = (ab[i][n + 1] - summation) / ab[i][i];
		}
		return x;
	}

	public double[] progressiveSubstitution(Matrix matrixAb) {
		int n = matrixAb.getRows();
		double[][] ab = matrixAb.getMatrix();
		double x[] = new double[n];
		for (int i = 0; i < n; i++) {
			double summation = 0;
			for (int j = 0; j < n; j++) {
				summation = summation + ab[i][j] * x[j];
			}
			x[i] = (ab[i][n] - summation) / ab[i][i];
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

	private double[][] getIdentityMatrix(int r, int c) {
		double[][] m = new double[r][c];
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[0].length; j++) {
				if (i == j) {
					m[i][j] = 1;
				} else {
					m[i][j] = 0;
				}
			}
		}
		return m;
	}

	public double[] lUmatrixFactorizationWithSimpleGaussianElimination(
			Matrix matrixA, double[] b) {
		double[][] u = matrixA.getMatrix();
		int n = u.length;
		double[][] l = getIdentityMatrix(n, n);
		for (int k = 0; k < n - 1; k++) {
			for (int i = k + 1; i < n; i++) {
				double multiplier = u[i][k] / u[k][k];
				l[i][k] = multiplier;
				for (int j = k; j < n; j++) {
					u[i][j] = u[i][j] - (multiplier * u[k][j]);
				}
			}
		}
		Matrix matrixLb = matrixA.createAugmentedMatrix(new Matrix(l), b);
		double[] z = progressiveSubstitution(matrixLb);
		Matrix matrixUz = matrixA.createAugmentedMatrix(new Matrix(u), z);
		double[] x = regressiveSubstitution(matrixUz);
		lExecution = l.clone();
		uExecution = u.clone();
		return x;
	}

	public int getColumnHigher(int k, double[][] m) {
		int max = k;
		for (int i = k + 1; i < m.length; i++) {
			if (Math.abs(m[max][k]) < Math.abs(m[i][k])) {
				max = i;
			}
		}
		return max;
	}

	public double[] lUMatrixFactorizationWithPartialPivoting(Matrix matrixA,
			double[] b) {
		int n = matrixA.getRows();
		int[] marks = new int[n];
		for (int i = 0; i < n; i++) {
			marks[i] = i;
		}
		double[][] u = matrixA.getMatrix();
		double[][] l = getIdentityMatrix(n, n);
		for (int k = 0; k < n - 1; k++) {
			int higher = getColumnHigher(k, u);
			if (u[higher][k] == 0.0) {
				return null;
			}
			if (k != higher) {
				int mTemp = marks[k];
				marks[k] = higher;
				marks[higher] = mTemp;
				for (int i = k; i < u[0].length; i++) {
					double temp = u[k][i];
					u[k][i] = u[higher][i];
					u[higher][i] = temp;
				}
			}
			for (int i = k + 1; i < n; i++) {
				double multiplier = u[i][k] / u[k][k];
				l[i][k] = multiplier;
				for (int j = k; j < n; j++) {
					u[i][j] = u[i][j] - (multiplier * u[k][j]);
				}
			}

		}
		double[] z = b;
		for (int t = 0; t < z.length - 1; t++) {
			if (marks[t] != t) {
				double temp = z[marks[t]];
				z[marks[t]] = z[t];
				z[t] = temp;
			}
			for (int i = t + 1; i < z.length; i++) {
				double multiplier = l[i][t];
				z[i] = z[i] - (multiplier * z[t]);
			}
		}
		Matrix matrixUz = matrixA.createAugmentedMatrix(new Matrix(u), z);
		double[] x = regressiveSubstitution(matrixUz);
		lExecution = l.clone();
		uExecution = u.clone();
		return x;
	}

	public double[] cholesky(Matrix matrixA) {
		int n = matrixA.getRows();
		double[][] a = matrixA.getMatrix();
		double[][] u = new double[n][n];
		double[][] l = new double[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				l[i][j] = 0;
				u[i][j] = 0;
			}
		}
		for (int k = 0; k < n; k++) {
			double sum = 0;
			for (int p = 0; p < k - 1; p++) {
				sum += l[k][p] * u[p][k];
			}
			l[k][k] = Math.sqrt(a[k][k] - sum);
			for (int i = k + 1; i < n; i++) {
				sum = 0;
				for (int r = 0; r < k - 1; r++) {
					sum += l[i][r] * u[r][k];
				}
				l[i][k] = (a[i][k] - sum) / l[k][k];
			}
			for (int j = k + 1; j < n; j++) {
				sum = 0;
				for (int s = 0; s < k - 1; s++) {
					sum += l[k][s] * u[s][j];
				}
				u[k][j] = (a[k][j] - sum) / l[k][k];
			}
		}
		for (int i = 0; i < n; i++) {
			u[i][i] = l[i][i];
		}
		Matrix matrixLb = matrixA.createAugmentedMatrix(new Matrix(l),
				matrixA.getB());
		double[] z = progressiveSubstitution(matrixLb);
		Matrix matrixUz = matrixA.createAugmentedMatrix(new Matrix(u), z);
		double[] x = regressiveSubstitution(matrixUz);
		lExecution = l;
		uExecution = u;
		return x;
	}

	public double[] crout(Matrix matrixA) {
		int n = matrixA.getRows();
		double[][] a = matrixA.getMatrix();
		double[][] u = new double[n][n];
		double[][] l = new double[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				l[i][j] = 0;
				if (i == j) {
					u[i][j] = 1;
				} else {
					u[i][j] = 0;
				}
			}
		}
		for (int k = 0; k < n; k++) {
			double sum = 0;
			for (int p = 0; p < k - 1; p++) {
				sum += l[k][p] * u[p][k];
			}
			l[k][k] = a[k][k] - sum;
			for (int i = k + 1; i < n; i++) {
				sum = 0;
				for (int r = 0; r < k - 1; r++) {
					sum += l[i][r] * u[r][k];
				}
				l[i][k] = a[i][k] - sum;
			}
			for (int j = k + 1; j < n; j++) {
				sum = 0;
				for (int s = 0; s < k - 1; s++) {
					sum += l[k][s] * u[s][j];
				}
				u[k][j] = (a[k][j] - sum) / l[k][k];
			}
		}
		Matrix matrixLb = matrixA.createAugmentedMatrix(new Matrix(l),
				matrixA.getB());
		double[] z = progressiveSubstitution(matrixLb);
		Matrix matrixUz = matrixA.createAugmentedMatrix(new Matrix(u), z);
		double[] x = regressiveSubstitution(matrixUz);
		lExecution = l;
		uExecution = u;
		return x;
	}

	public double[] doolittle(Matrix matrixA) {
		int n = matrixA.getRows();
		double[][] a = matrixA.getMatrix();
		double[][] u = new double[n][n];
		double[][] l = new double[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				u[i][j] = 0;
				if (i == j) {
					l[i][j] = 1;
				} else {
					l[i][j] = 0;
				}
			}
		}
		for (int k = 0; k < n; k++) {
			double sum = 0;
			for (int p = 0; p < k - 1; p++) {
				sum += l[k][p] * u[p][k];
			}
			u[k][k] = a[k][k] - sum;
			for (int i = k + 1; i < n; i++) {
				sum = 0;
				for (int r = 0; r < k - 1; r++) {
					sum += l[i][r] * u[r][k];
				}
				l[i][k] = (a[i][k] - sum) / u[k][k];
			}
			for (int j = k + 1; j < n; j++) {
				sum = 0;
				for (int s = 0; s < k - 1; s++) {
					sum += l[k][s] * u[s][j];
				}
				u[k][j] = a[k][j] - sum;
			}
		}
		Matrix matrixLb = matrixA.createAugmentedMatrix(new Matrix(l),
				matrixA.getB());
		double[] z = progressiveSubstitution(matrixLb);
		Matrix matrixUz = matrixA.createAugmentedMatrix(new Matrix(u), z);
		double[] x = regressiveSubstitution(matrixUz);
		lExecution = l;
		uExecution = u;
		return x;
	}
}

package com.numerical_analysis.android.methods;

public class ErrorTheory {

	public double smallestPositiveNumberDouble() {
		double d = 0.5;
		double aux = d;
		while (d != 0) {
			aux = d;
			d = d / 2;
		}
		return aux;
	}

	public float smallestPositiveNumberSimple() {
		float d = 0.5f;
		float aux = d;
		while (d != 0) {
			aux = d;
			d = d / 2;
		}
		return aux;
	}

	public double machineEpsilonDouble() {
		double d = 0.5;
		double aux = d;
		while (d + 1 != 1) {
			aux = d;
			d = d / 2;
		}
		return aux;
	}

	public float machineEpsilonSimple() {
		float d = 0.5f;
		float aux = d;
		while (d + 1 != 1) {
			aux = d;
			d = d / 2;
		}
		return aux;
	}
}

package com.numerical_analysis.android.methods;

import java.io.Serializable;
import java.util.ArrayList;

public interface MethodGroup extends Serializable {

	public ArrayList<Double[]> getExecutionTable();
}

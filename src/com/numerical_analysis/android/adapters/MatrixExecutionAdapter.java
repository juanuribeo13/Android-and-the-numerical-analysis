package com.numerical_analysis.android.adapters;

import java.io.Serializable;

import android.app.Activity;
import android.widget.TableRow;

public interface MatrixExecutionAdapter extends Serializable {

	public TableRow getTitle(int columns, Activity activity);

	public TableRow getRow(double[] ds, Activity activity);
}

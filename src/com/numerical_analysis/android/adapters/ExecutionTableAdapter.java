package com.numerical_analysis.android.adapters;

import java.io.Serializable;

import android.app.Activity;
import android.widget.TableRow;

public interface ExecutionTableAdapter extends Serializable{

	public TableRow getTitle(Activity activity);

	public TableRow getRow(Double[] row, Activity activity);
}

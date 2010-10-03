package com.linkmongrel.pizza;

import android.provider.BaseColumns;

public interface Constants extends BaseColumns {
	public static final String TABLE_NAME = "pizza";
	
	// Columns in the Events database
	public static final String SIZE = "size";
	public static final String CRUST = "crust";
	public static final String LAYOUT = "layout";
	public static final String TOPPINGS = "toppings";
}

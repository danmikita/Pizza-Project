package com.linkmongrel.pizza;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static android.provider.BaseColumns._ID;
import static com.linkmongrel.pizza.Constants.TABLE_NAME;
import static com.linkmongrel.pizza.Constants.SIZE;
import static com.linkmongrel.pizza.Constants.CRUST;
import static com.linkmongrel.pizza.Constants.TOPPINGS_WHOLE;
import static com.linkmongrel.pizza.Constants.TOPPINGS_LEFT;
import static com.linkmongrel.pizza.Constants.TOPPINGS_RIGHT;



public class PizzaData extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "pizza.db";
	private static final int DATABASE_VERSION = 4;
	
	public PizzaData(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + SIZE
				+ " TEXT NOT NULL, " + CRUST + " TEXT NOT NULL, " + TOPPINGS_WHOLE + " TEXT, " + TOPPINGS_LEFT + 
				" TEXT, " +  TOPPINGS_RIGHT + " TEXT);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);
	}
	

   
}

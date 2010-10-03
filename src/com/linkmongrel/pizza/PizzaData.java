package com.linkmongrel.pizza;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static android.provider.BaseColumns._ID;
import static com.linkmongrel.pizza.Constants.TABLE_NAME;
import static com.linkmongrel.pizza.Constants.SIZE;
import static com.linkmongrel.pizza.Constants.CRUST;
import static com.linkmongrel.pizza.Constants.LAYOUT;
import static com.linkmongrel.pizza.Constants.TOPPINGS;



public class PizzaData extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "pizza.db";
	private static final int DATABASE_VERSION = 2;
	
	public PizzaData(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CRUST
				+ " TEXT NOT NULL, " + TOPPINGS + " TEXT NOT NULL, " + LAYOUT + " INTEGER, " + SIZE + " TEXT NOT NULL);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);
	}
	

   
}

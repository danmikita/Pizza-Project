package com.linkmongrel.pizza;

import static android.provider.BaseColumns._ID;
import static com.linkmongrel.pizza.Constants.TABLE_NAME;
import static com.linkmongrel.pizza.Constants.SIZE;
import static com.linkmongrel.pizza.Constants.CRUST;
import static com.linkmongrel.pizza.Constants.LAYOUT;
import static com.linkmongrel.pizza.Constants.TOPPINGS;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.SimpleCursorAdapter;

public class OrderPage extends ListActivity implements OnClickListener{
	private PizzaData data;
	private boolean hasShown = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order_page);
		data = new PizzaData(this);
		try{
			addEvent("Large", "Thin", 0, "Pepperoni, Peppers, Mushrooms");
			Cursor cursor = getEvents();
			showEvents(cursor);
		} finally {
			data.close();
		}
		// Set up click listeners for all the buttons
        View getNewPizzaButton = findViewById(R.id.new_pizza_button);
        getNewPizzaButton.setOnClickListener(this);
        View getEditOrderButton = findViewById(R.id.edit_order_button);
        getEditOrderButton.setOnClickListener(this);
        View getCheckoutButton = findViewById(R.id.checkout_button);
        getCheckoutButton.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.new_pizza_button:
			if(!hasShown) {
				openHowToDialog();
				hasShown = true;
			} else {
				openSizeSelectionDialog();
			}
			break;
		case R.id.edit_order_button:
			break;
		case R.id.checkout_button:
			checkOutDialog();
			break;
		// More buttons go here (if any) ...
		}
	}
	
	private void openHowToDialog() { 
		new AlertDialog.Builder(this)
			.setTitle(R.string.how_to_title)
			.setMessage(R.string.how_to_text).setCancelable(false)
			.setNeutralButton("OK", 
			new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				openSizeSelectionDialog();
			}
		}).show();
	}

	private void openSizeSelectionDialog() {
		new AlertDialog.Builder(this)
			.setTitle(R.string.pizza_size)
			.setItems(R.array.pizza_size, 
			new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				String size;
				if(which == 0)
					size = "Small";
				else if(which == 1)
					size = "Medium";
				else if(which == 2)
					size = "Large";
				else
					size = "Party";
				openCrustSelectionDialog();
			}
		}).show();
	}

	protected void openCrustSelectionDialog() {
		new AlertDialog.Builder(this)
		.setTitle(R.string.crust_selection)
		.setItems(R.array.pizza_crust, 
		new DialogInterface.OnClickListener() {
		
		@Override
		public void onClick(DialogInterface dialog, int which) {
			
			openLayoutSelectionDialog();
		}
	}).show();
		
	}

	protected void openLayoutSelectionDialog() {
		new AlertDialog.Builder(this)
		.setTitle(R.string.half_whole_label)
		.setItems(R.array.pizza_layout, 
		new DialogInterface.OnClickListener() {
		
		@Override
		public void onClick(DialogInterface dialog, int which) {
			if(which == 0)
				startHalfPizzaCreation();
			else
				startWholePizzaCreation();
		}
	}).show();
		
	}

	protected void startHalfPizzaCreation() {
		Intent intent = new Intent(OrderPage.this, LeftHalfPizza.class);
		startActivity(intent);
	}

	protected void startWholePizzaCreation() {
		Intent intent = new Intent(OrderPage.this, NewWholePizza.class);
		startActivity(intent);
	}
	
	private void checkOutDialog() { 
		new AlertDialog.Builder(this)
			.setTitle(R.string.checkout_title)
			.setMessage(R.string.checkout_text).setCancelable(false)
			.setNeutralButton("OK", 
			new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
			}
		}).show();
	}
	
	private void addEvent(String size, String crust, int layout, String toppings) {
		SQLiteDatabase db = data.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(SIZE, size);
		values.put(CRUST, crust);
		values.put(LAYOUT, layout);
		values.put(TOPPINGS, toppings);
		db.insertOrThrow(TABLE_NAME, null, values);
	}
	
	private static String[] FROM = { _ID, SIZE, CRUST, LAYOUT, TOPPINGS };
	private static String ORDER_BY = SIZE + " DESC";
	private Cursor getEvents() {
		SQLiteDatabase db = data.getReadableDatabase();
		Cursor cursor = db.query(TABLE_NAME, FROM, null, null, null, null, ORDER_BY);
		startManagingCursor(cursor);
		return cursor;
	}
	
	private static int[] TO = { R.id.time, R.id.title, };
	private void showEvents(Cursor cursor) {
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.item, cursor, FROM, TO);
		setListAdapter(adapter);
		}
		
	
}

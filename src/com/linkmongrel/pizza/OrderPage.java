package com.linkmongrel.pizza;

import static com.linkmongrel.pizza.Constants.CRUST;
import static com.linkmongrel.pizza.Constants.SIZE;
import static com.linkmongrel.pizza.Constants.TABLE_NAME;
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
	protected void onResume() {
		super.onResume();
		try{
			Cursor cursor = getEvents();
			showEvents(cursor);
		} finally {
			data.close();
		}
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
				openCrustSelectionDialog(size);
			}
		}).show();
	}

	protected void openCrustSelectionDialog(final String size) {
		new AlertDialog.Builder(this)
		.setTitle(R.string.crust_selection)
		.setItems(R.array.pizza_crust, 
		new DialogInterface.OnClickListener() {
		
		@Override
		public void onClick(DialogInterface dialog, int which) {
			String crust;
			if(which == 0)
				crust = "Thin";
			else if(which == 1)
				crust = "Thick";
			else if(which == 2)
				crust = "Deepdish";
			else
				crust = "Stuffed";
			addEvent(size, crust, "pick");
			startPizzaCreation();
		}
	}).show();
		
	}

	protected void startPizzaCreation() {
		Intent intent = new Intent(OrderPage.this, NewPizza.class);
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
				data.getWritableDatabase().delete(TABLE_NAME, null, null);
				finish();
			}
		}).show();
	}
	
	private void addEvent(String size, String crust, String toppings) {
		SQLiteDatabase db = data.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(SIZE, size);
		values.put(CRUST, crust);
		values.put(TOPPINGS, toppings);
		db.insertOrThrow(TABLE_NAME, null, values);
	}
	
	private static String[] FROM = {SIZE, CRUST, TOPPINGS};
	private static String ORDER_BY = SIZE + " DESC";
	private Cursor getEvents() {
		SQLiteDatabase db = data.getReadableDatabase();
		Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, ORDER_BY);
		startManagingCursor(cursor);
		return cursor;
	}
	
	private static int[] TO = { R.id.size, R.id.crust, R.id.toppings };
	private void showEvents(Cursor cursor) {
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.item, cursor, FROM, TO);
		setListAdapter(adapter);
		}
		
	
}

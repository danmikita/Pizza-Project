package com.linkmongrel.pizza;

import static android.provider.BaseColumns._ID;
import static com.linkmongrel.pizza.Constants.SIZE;
import static com.linkmongrel.pizza.Constants.TABLE_NAME;
import static com.linkmongrel.pizza.Constants.TOPPINGS_LEFT;
import static com.linkmongrel.pizza.Constants.TOPPINGS_RIGHT;
import static com.linkmongrel.pizza.Constants.TOPPINGS_WHOLE;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Gallery;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class NewPizza extends Activity implements OnClickListener {

	private RadioButton wholeRadio;
	private RadioButton leftRadio;
	private TextView wholeText;
	private TextView leftText;
	private TextView rightText;
	private ArrayList<String> wholeList = new ArrayList<String>();
	private ArrayList<String> leftList = new ArrayList<String>();
	private ArrayList<String> rightList = new ArrayList<String>();
	private ArrayList<String> toppingsList = new ArrayList<String>();
	private PizzaData data;
	int id = 999;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_pizza);
		data = new PizzaData(this);
		createToppingList();

		Bundle bundle = this.getIntent().getExtras();
		if (bundle != null && bundle.size() > 0)
			id = bundle.getInt(_ID);

		wholeRadio = (RadioButton) findViewById(R.id.whole);
		leftRadio = (RadioButton) findViewById(R.id.left);
		wholeText = (TextView) findViewById(R.id.whole_text);
		leftText = (TextView) findViewById(R.id.left_text);
		rightText = (TextView) findViewById(R.id.right_text);

		if (id != 999) {
			Cursor pizzaCursor = getPizza();
			if (pizzaCursor.moveToFirst()) {
				wholeText.setText(pizzaCursor.getString(pizzaCursor.getColumnIndex(TOPPINGS_WHOLE)));
				leftText.setText(pizzaCursor.getString(pizzaCursor.getColumnIndex(TOPPINGS_LEFT)));
				rightText.setText(pizzaCursor.getString(pizzaCursor.getColumnIndex(TOPPINGS_RIGHT)));
				String[] wholeToppings = wholeText.getText().toString().split("[,]+");
				setArrayList(wholeList, wholeToppings);
				String[] leftToppings = leftText.getText().toString().split("[,]+");
				setArrayList(leftList, leftToppings);
				String[] rightToppings = rightText.getText().toString().split("[,]+");
				setArrayList(rightList, rightToppings);
			}
		}

		Gallery gallery = (Gallery) findViewById(R.id.gallery);
		gallery.setAdapter(new ImageAdapter(this));

		gallery.setOnItemClickListener(new OnItemClickListener() {
			@SuppressWarnings("rawtypes")
			public void onItemClick(AdapterView parent, View v, int position, long id) {
				if (wholeList.isEmpty())
					wholeList.clear();
				if (leftList.isEmpty())
					leftList.clear();
				if (rightList.isEmpty())
					rightList.clear();
				if (wholeRadio.isChecked()) { // Whole Pizza
					if (wholeList.contains(toppingsList.get(position))) {
						displayMessage(position, " removed");
						wholeList.remove(wholeList.indexOf(toppingsList.get(position)));
						if (wholeList.isEmpty())
							wholeText.setText("");
						else
							wholeText.setText(editString(wholeList));
					} else if (rightList.contains(toppingsList.get(position))) {
						rightList.remove(rightList.indexOf(toppingsList.get(position)));
						wholeList.add(toppingsList.get(position));
						rightText.setText(editString(rightList));
						wholeText.setText(editString(wholeList));
						displayMessage(position, " added");
					} else if (leftList.contains(toppingsList.get(position))) {
						leftList.remove(leftList.indexOf(toppingsList.get(position)));
						wholeList.add(toppingsList.get(position));
						leftText.setText(editString(leftList));
						wholeText.setText(editString(wholeList));
						displayMessage(position, " added");
					} else {
						displayMessage(position, " added");
						wholeList.add(toppingsList.get(position));
						wholeText.setText(editString(wholeList));
					}
				} else if (leftRadio.isChecked()) { // Left side of pizza.
					if (leftList.contains(toppingsList.get(position))) {
						displayMessage(position, " removed");
						leftList.remove(leftList.indexOf(toppingsList.get(position)));
						if (leftList.isEmpty())
							leftText.setText("");
						else
							leftText.setText(editString(leftList));
					} else if (rightList.contains(toppingsList.get(position))) { // Checking if the same topping is on the right side of pizza.
						displayMessage(position, " added to whole pizza");
						rightList.remove(rightList.indexOf(toppingsList.get(position)));
						rightText.setText(editString(rightList));
						wholeList.add(toppingsList.get(position));
						wholeText.setText(editString(wholeList));
					} else if (wholeList.contains(toppingsList.get(position))) {
						displayMessage(position, " have already been added to the whole pizza");
					} else {
						displayMessage(position, " added");
						leftList.add(toppingsList.get(position));
						leftText.setText(editString(leftList));
					}
				} else { // Right side of pizza.
					if (rightList.contains(toppingsList.get(position))) {
						displayMessage(position, " removed");
						rightList.remove(rightList.indexOf(toppingsList.get(position)));
						if (rightList.isEmpty())
							rightText.setText("");
						else
							rightText.setText(editString(rightList));
					} else if (leftList.contains(toppingsList.get(position))) { // Checking if the same topping is on the left side of pizza.
						displayMessage(position, " added to whole pizza");
						leftList.remove(leftList.indexOf(toppingsList.get(position)));
						leftText.setText(editString(leftList));
						wholeList.add(toppingsList.get(position));
						wholeText.setText(editString(wholeList));
					} else if (wholeList.contains(toppingsList.get(position))) {
						displayMessage(position, " have already been added to the whole pizza");
					} else {
						displayMessage(position, " added");
						rightList.add(toppingsList.get(position));
						rightText.setText(editString(rightList));
					}
				}
			}

		});

		View getAddToCartButton = findViewById(R.id.add_to_cart_button);
		getAddToCartButton.setOnClickListener(this);
		View getCancelButton = findViewById(R.id.cancel_button);
		getCancelButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.add_to_cart_button:
			addToCart();
			finish();
			break;
		case R.id.cancel_button:
			if (id == 999)
				data.getWritableDatabase().delete(TABLE_NAME, TOPPINGS_WHOLE + "='none'", null);
			else
				data.getWritableDatabase().delete(TABLE_NAME, _ID + "='" + id + "'", null);
			finish();
			break;
		}

	}

	private void addToCart() {
		String toppingsWhole = "";
		String toppingsLeft = "";
		String toppingsRight = "";
		if (wholeList.size() > 0) {
			toppingsWhole = toppingsWhole + editString(wholeList);
		}
		if (leftList.size() > 0) {
			toppingsLeft = toppingsLeft + editString(leftList);
		}
		if (rightList.size() > 0) {
			toppingsRight = toppingsRight + editString(rightList);
		}
		updatePizza(toppingsWhole, toppingsLeft, toppingsRight);
	}

	private void createToppingList() {
		toppingsList.add("Anchovies");
		toppingsList.add("Bacon");
		toppingsList.add("Banana Peppers");
		toppingsList.add("Black Olives");
		toppingsList.add("Chicken");
		toppingsList.add("Green Peppers");
		toppingsList.add("Ham");
		toppingsList.add("Jalapeno Peppers");
		toppingsList.add("Extra Cheese");
		toppingsList.add("Mushrooms");
		toppingsList.add("Onion");
		toppingsList.add("Pepperoni");
		toppingsList.add("Pineapple");
		toppingsList.add("Sausage");
		toppingsList.add("Roma Tomatoes");
	}

	private void displayMessage(int position, String message) {
		Toast.makeText(NewPizza.this, toppingsList.get(position) + message, Toast.LENGTH_SHORT).show();
	}

	private String editString(List<String> list) {
		String toppings = "";
		String withOutComma;
		for (String item : list) {
			toppings += item + ", ";
		}
		if (toppings.equals(""))
			withOutComma = "";
		else
			withOutComma = toppings.substring(0, toppings.length() - 2);
		return withOutComma;
	}

	private void updatePizza(String toppingsWhole, String toppingsLeft, String toppingsRight) {
		SQLiteDatabase db = data.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(TOPPINGS_WHOLE, toppingsWhole);
		values.put(TOPPINGS_LEFT, toppingsLeft);
		values.put(TOPPINGS_RIGHT, toppingsRight);
		if (id == 999)
			db.update(TABLE_NAME, values, TOPPINGS_WHOLE + "='none'", null);
		else
			db.update(TABLE_NAME, values, _ID + "='" + id + "'", null);
	}

	private static String ORDER_BY = SIZE + " DESC";

	private Cursor getPizza() {
		SQLiteDatabase db = data.getReadableDatabase();
		Cursor cursor = db.query(TABLE_NAME, null, _ID + "='" + id + "'", null, null, null, ORDER_BY);
		startManagingCursor(cursor);
		return cursor;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.settings:
			// startActivity(new Intent(this, Prefs.class));
			return true;
		case R.id.help:
			new AlertDialog.Builder(this).setTitle(R.string.help_title).setMessage(R.string.add_ingredients_help).setCancelable(false)
					.setNeutralButton("OK", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					}).show();
			return true;
		case R.id.exit:
			new AlertDialog.Builder(this).setTitle(R.string.exit).setMessage("Are you sure you want to exit?").setCancelable(true)
					.setNeutralButton("Yes", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							System.exit(0);
						}
					}).setNegativeButton("No", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					}).show();
			return true;
		}
		return false;
	}

	private void setArrayList(ArrayList<String> list, String[] toppings) {
		for (int i = 0; i < toppings.length; i++) {
			list.add(toppings[i].trim());
		}
	}

}

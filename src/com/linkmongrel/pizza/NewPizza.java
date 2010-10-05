package com.linkmongrel.pizza;

import static com.linkmongrel.pizza.Constants.TABLE_NAME;
import static com.linkmongrel.pizza.Constants.TOPPINGS;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
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
	private List<String> wholeList = new ArrayList<String>();
	private List<String> leftList = new ArrayList<String>();
	private List<String> rightList = new ArrayList<String>();
	private List<String> toppingsList = new ArrayList<String>();
	private PizzaData data;
	Long id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_pizza);
		data = new PizzaData(this);
		createToppingList();

		Bundle bundle = this.getIntent().getExtras();
		id = bundle.getLong("ID", 0);
		
		wholeRadio = (RadioButton) findViewById(R.id.whole);
		leftRadio = (RadioButton) findViewById(R.id.left);
		wholeText = (TextView) findViewById(R.id.whole_text);
		leftText = (TextView) findViewById(R.id.left_text);
		rightText = (TextView) findViewById(R.id.right_text);

		Gallery gallery = (Gallery) findViewById(R.id.gallery);
		gallery.setAdapter(new ImageAdapter(this));

		gallery.setOnItemClickListener(new OnItemClickListener() {
			@SuppressWarnings("rawtypes")
			public void onItemClick(AdapterView parent, View v, int position,
					long id) {
				if (wholeRadio.isChecked()) { // Whole Pizza
					if (wholeList.contains(toppingsList.get(position))) {
						displayMessage(position, " removed");
						wholeList.remove(wholeList.indexOf(toppingsList.get(position)));
						wholeText.setText(editString(wholeList));
					} else {
						displayMessage(position, " added");
						wholeList.add(toppingsList.get(position));
						wholeText.setText(editString(wholeList));
					}
				} else if (leftRadio.isChecked()) { // Left side of pizza.
					if (leftList.contains(toppingsList.get(position))) {
						displayMessage(position, " removed");
						leftList.remove(leftList.indexOf(toppingsList.get(position)));
						leftText.setText(editString(leftList));
					} else if(rightList.contains(toppingsList.get(position))){  // Checking if the same topping is on the right side of pizza.
						displayMessage(position, " added to whole pizza");
						rightList.remove(rightList.indexOf(toppingsList.get(position)));
						rightText.setText(editString(rightList));
						wholeList.add(toppingsList.get(position));
						wholeText.setText(editString(wholeList));
					} else if(wholeList.contains(toppingsList.get(position))) {
						displayMessage(position, " have already been added to the whole pizza");
					} else {
						displayMessage(position, " added");
						leftList.add(toppingsList.get(position));
						leftText.setText(editString(leftList));
					}
				} else {  // Right side of pizza.
					if (rightList.contains(toppingsList.get(position))) {
						displayMessage(position, " removed");
						rightList.remove(rightList.indexOf(toppingsList.get(position)));
						rightText.setText(editString(rightList));
					} else if(leftList.contains(toppingsList.get(position))){ // Checking if the same topping is on the left side of pizza.
						displayMessage(position, " added to whole pizza");
						leftList.remove(leftList.indexOf(toppingsList.get(position)));
						leftText.setText(editString(leftList));
						wholeList.add(toppingsList.get(position));
						wholeText.setText(editString(wholeList));
					} else if(wholeList.contains(toppingsList.get(position))) {
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
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.add_to_cart_button:
			addToCart();
			finish();
			break;
		}

	}

	private void addToCart() {
		String toppings = "";
		if(wholeList.size() > 0) {
			toppings = toppings + "Whole Pizza: " + editString(wholeList);
		}
		if(leftList.size() > 0) {
			toppings = toppings + "Left Half: " + editString(leftList);
		}
		if(rightList.size() > 0) {
			toppings = toppings + "Right Half: " + editString(rightList);
		}
		updatePizza(toppings);
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
		Toast.makeText(NewPizza.this,
				toppingsList.get(position) + message,
				Toast.LENGTH_SHORT).show();
	}
	
	private String editString(List<String> list) {
		String toppings = "";
		for (String item : list) {
			toppings += item + ", ";
		}
		String withOutComma = toppings.substring(0, toppings.length() - 2);
		return withOutComma;
	}
	
	private void updatePizza(String toppings) {
		SQLiteDatabase db = data.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(TOPPINGS, toppings);
		db.update(TABLE_NAME, values, TOPPINGS + "='pick'", null);
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
//			startActivity(new Intent(this, Prefs.class));
			return true;
		case R.id.help:
			new AlertDialog.Builder(this)
			.setTitle(R.string.help_title)
			.setMessage(R.string.enter_name_text).setCancelable(false)
			.setNeutralButton("OK", 
			new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		}).show();
			return true;
		}
		return false;
	}


}

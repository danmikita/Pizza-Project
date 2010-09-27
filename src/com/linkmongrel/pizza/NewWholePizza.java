package com.linkmongrel.pizza;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.ImageView;

public class NewWholePizza extends Activity implements OnClickListener{
	
	private static final int PEPPERONI = 0;



	private static final int ONION = 1;



	private static final int PEPPERS = 2;



	private static final int SAUSAGE = 3;



	private static final int MUSHROOM = 4;



	private static final int EXTRA_CHEESE = 5;
	
	
	
	private ImageView pizzaImage;
	private ImageView onionImage;
	private ImageView pepperoniImage;
	private ImageView peppersImage;
	private ImageView sausageImage;
	private CheckBox pepperoniBox;
	private CheckBox onionBox;
	private CheckBox peppersBox;
	private CheckBox sausageBox;
	private CheckBox cheeseBox;
	private CheckBox mushroomBox;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_pizza);
		
		// Initialize pizza images.
		pizzaImage = (ImageView) findViewById(R.id.pizza_image);
		onionImage = (ImageView) findViewById(R.id.onion_image);
		pepperoniImage = (ImageView) findViewById(R.id.pepperoni_image);
		peppersImage = (ImageView) findViewById(R.id.peppers_image);
		sausageImage = (ImageView) findViewById(R.id.sausage_image);
		
		onionImage.setVisibility(0);
		pepperoniImage.setVisibility(0);
		peppersImage.setVisibility(0);
		sausageImage.setVisibility(0);
		
		// Initialize checkboxes.
		pepperoniBox = (CheckBox) findViewById(R.id.pepperoni_checkbox);
		onionBox = (CheckBox) findViewById(R.id.onion_checkbox);
		peppersBox = (CheckBox) findViewById(R.id.peppers_checkbox);
		sausageBox = (CheckBox) findViewById(R.id.sausage_checkbox);
		mushroomBox = (CheckBox) findViewById(R.id.mushroom_checkbox);
		cheeseBox = (CheckBox) findViewById(R.id.extra_cheese_checkbox);
		
		View getAddToCartButton = findViewById(R.id.add_to_cart_button);
		getAddToCartButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.add_to_cart_button:
			addToCart();
			Intent intent = new Intent(NewWholePizza.this, OrderPage.class);
			startActivity(intent);
			break;
		case R.id.pepperoni_checkbox:
			displayImage(PEPPERONI);
			break;
		case R.id.onion_checkbox:
			displayImage(ONION);
			break;
		case R.id.peppers_checkbox:
			displayImage(PEPPERS);
			break;
		case R.id.sausage_checkbox:
			displayImage(SAUSAGE);
			break;
		case R.id.mushroom_checkbox:
			displayImage(MUSHROOM);
			break;
		case R.id.extra_cheese_checkbox:
			displayImage(EXTRA_CHEESE);
			break;
		// More buttons go here (if any) ...
		}
		
	}

	private void displayImage(int topping) {
		switch(topping) {
		case PEPPERONI:
			if(pepperoniBox.isChecked()) {
				pepperoniImage.setVisibility(1);
			} else {
				pepperoniImage.setVisibility(0);
			}
		case ONION:
			if(pepperoniBox.isChecked()) {
				onionImage.setVisibility(1);
			} else {
				onionImage.setVisibility(0);
			}
		case PEPPERS:
			if(pepperoniBox.isChecked()) {
				peppersImage.setVisibility(1);
			} else {
				peppersImage.setVisibility(0);
			}
		case SAUSAGE:
			if(pepperoniBox.isChecked()) {
				sausageImage.setVisibility(1);
			} else {
				sausageImage.setVisibility(0);
			}
		}
		
	}

	private void addToCart() {
		// TODO Add an insert statement for the SQLite database.
		
	}

}

package com.linkmongrel.pizza;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CheckBox;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

public class NewWholePizza extends Activity implements OnClickListener {

	private static final int PEPPERONI = 0;
	private static final int ONION = 1;
	private static final int PEPPERS = 2;
	private static final int SAUSAGE = 3;
	private static final int MUSHROOM = 4;
	private static final int EXTRA_CHEESE = 5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_pizza);

		
		Gallery gallery = (Gallery) findViewById(R.id.gallery);
	    gallery.setAdapter(new ImageAdapter(this));

		gallery.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView parent, View v, int position, long id) {
				Toast.makeText(NewWholePizza.this, "" + position, Toast.LENGTH_SHORT).show();
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
//		case R.id.pepperoni_checkbox:
//			displayImage(PEPPERONI);
//			break;
//		case R.id.onion_checkbox:
//			displayImage(ONION);
//			break;
//		case R.id.peppers_checkbox:
//			displayImage(PEPPERS);
//			break;
//		case R.id.sausage_checkbox:
//			displayImage(SAUSAGE);
//			break;
//		case R.id.mushroom_checkbox:
//			displayImage(MUSHROOM);
//			break;
//		case R.id.extra_cheese_checkbox:
//			displayImage(EXTRA_CHEESE);
//			break;
		// More buttons go here (if any) ...
		}

	}

//	private void displayImage(int topping) {
//		switch (topping) {
//		case PEPPERONI:
//			if (pepperoniBox.isChecked()) {
//				pepperoniImage.setVisibility(View.VISIBLE);
//			} else {
//				pepperoniImage.setVisibility(View.INVISIBLE);
//			}
//		case ONION:
//			if (onionBox.isChecked()) {
//				onionImage.setVisibility(View.VISIBLE);
//			} else {
//				onionImage.setVisibility(View.INVISIBLE);
//			}
//		case PEPPERS:
//			if (peppersBox.isChecked()) {
//				peppersImage.setVisibility(View.VISIBLE);
//			} else {
//				peppersImage.setVisibility(View.INVISIBLE);
//			}
//		case SAUSAGE:
//			if (sausageBox.isChecked()) {
//				sausageImage.setVisibility(View.VISIBLE);
//			} else {
//				sausageImage.setVisibility(View.INVISIBLE);
//			}
//		case MUSHROOM:
//			if (mushroomBox.isChecked()) {
//				mushroomImage.setVisibility(View.VISIBLE);
//			} else {
//				mushroomImage.setVisibility(View.INVISIBLE);
//			}
//		case EXTRA_CHEESE:
//			if (cheeseBox.isChecked()) {
//				cheeseImage.setVisibility(View.VISIBLE);
//				pizzaImage.setVisibility(View.INVISIBLE);
//			} else {
//				cheeseImage.setVisibility(View.INVISIBLE);
//				pizzaImage.setVisibility(View.VISIBLE);
//			}
//		}
//
//	}

	private void addToCart() {
		

	}

}

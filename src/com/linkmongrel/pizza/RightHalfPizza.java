package com.linkmongrel.pizza;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.ImageView;

public class RightHalfPizza extends Activity implements OnClickListener {

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
	private ImageView mushroomImage;
	private ImageView cheeseImage;
	private CheckBox pepperoniBox;
	private CheckBox onionBox;
	private CheckBox peppersBox;
	private CheckBox sausageBox;
	private CheckBox cheeseBox;
	private CheckBox mushroomBox;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.right_half);

		// Initialize pizza images.
		pizzaImage = (ImageView) findViewById(R.id.pizza_image);
		onionImage = (ImageView) findViewById(R.id.onion_image);
		pepperoniImage = (ImageView) findViewById(R.id.pepperoni_image);
		peppersImage = (ImageView) findViewById(R.id.peppers_image);
		sausageImage = (ImageView) findViewById(R.id.sausage_image);
		mushroomImage = (ImageView) findViewById(R.id.mushrooms_image);
		cheeseImage = (ImageView) findViewById(R.id.extracheese_image);

		onionImage.setVisibility(View.INVISIBLE);
		pepperoniImage.setVisibility(View.INVISIBLE);
		peppersImage.setVisibility(View.INVISIBLE);
		sausageImage.setVisibility(View.INVISIBLE);
		mushroomImage.setVisibility(View.INVISIBLE);
		cheeseImage.setVisibility(View.INVISIBLE);

		// Initialize checkboxes.
		pepperoniBox = (CheckBox) findViewById(R.id.pepperoni_checkbox);
		pepperoniBox.setOnClickListener(this);
		onionBox = (CheckBox) findViewById(R.id.onion_checkbox);
		onionBox.setOnClickListener(this);
		peppersBox = (CheckBox) findViewById(R.id.peppers_checkbox);
		peppersBox.setOnClickListener(this);
		sausageBox = (CheckBox) findViewById(R.id.sausage_checkbox);
		sausageBox.setOnClickListener(this);
		mushroomBox = (CheckBox) findViewById(R.id.mushroom_checkbox);
		mushroomBox.setOnClickListener(this);
		cheeseBox = (CheckBox) findViewById(R.id.extra_cheese_checkbox);
		cheeseBox.setOnClickListener(this);

		View goToLeftSideButton = findViewById(R.id.left_half_button);
		goToLeftSideButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.left_half_button:
			finish();
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
		switch (topping) {
		case PEPPERONI:
			if (pepperoniBox.isChecked()) {
				pepperoniImage.setVisibility(View.VISIBLE);
			} else {
				pepperoniImage.setVisibility(View.INVISIBLE);
			}
		case ONION:
			if (onionBox.isChecked()) {
				onionImage.setVisibility(View.VISIBLE);
			} else {
				onionImage.setVisibility(View.INVISIBLE);
			}
		case PEPPERS:
			if (peppersBox.isChecked()) {
				peppersImage.setVisibility(View.VISIBLE);
			} else {
				peppersImage.setVisibility(View.INVISIBLE);
			}
		case SAUSAGE:
			if (sausageBox.isChecked()) {
				sausageImage.setVisibility(View.VISIBLE);
			} else {
				sausageImage.setVisibility(View.INVISIBLE);
			}
		case MUSHROOM:
			if (mushroomBox.isChecked()) {
				mushroomImage.setVisibility(View.VISIBLE);
			} else {
				mushroomImage.setVisibility(View.INVISIBLE);
			}
		case EXTRA_CHEESE:
			if (cheeseBox.isChecked()) {
				cheeseImage.setVisibility(View.VISIBLE);
				pizzaImage.setVisibility(View.INVISIBLE);
			} else {
				cheeseImage.setVisibility(View.INVISIBLE);
				pizzaImage.setVisibility(View.VISIBLE);
			}
		}

	}
}
package com.linkmongrel.pizza;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class OrderPage extends Activity implements OnClickListener{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order_page);
		
		// Set up click listeners for all the buttons
        View getNewPizzaButton = findViewById(R.id.new_pizza_button);
        getNewPizzaButton.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.new_pizza_button:
			openHowToDialog();
			
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
			startPizzaCreation();
		}
	}).show();
		
	}

	protected void startPizzaCreation() {
		Intent intent = new Intent(OrderPage.this, NewWholePizza.class);
		startActivity(intent);
	}
}

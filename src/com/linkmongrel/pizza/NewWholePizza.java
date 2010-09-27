package com.linkmongrel.pizza;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class NewWholePizza extends Activity implements OnClickListener{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_pizza);
		
		View pizzaImage = findViewById(R.id.pizza_image);
		
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
		// More buttons go here (if any) ...
		}
		
	}

	private void addToCart() {
		// TODO Add an insert statement for the SQLite database.
		
	}

}

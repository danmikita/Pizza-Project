package com.linkmongrel.pizza;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class Pizza extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // Set up click listeners for all the buttons
        View getStartedButton = findViewById(R.id.get_started_button);
        getStartedButton.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.get_started_button:
			Intent i = new Intent(this, OrderPage.class);
			startActivity(i);
			break;
		// More buttons go here (if any) ...
		}
	}
}
package com.example.loavesfishesandcomputers;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.text.util.Linkify;

public class Help extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help);
		
		//Linkify.addLinks(text, Linkify.PHONE_NUMBERS);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.help, menu);
		return true;
	}
	
	

}

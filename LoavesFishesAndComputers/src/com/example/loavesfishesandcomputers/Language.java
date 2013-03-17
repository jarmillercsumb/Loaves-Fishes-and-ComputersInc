package com.example.loavesfishesandcomputers;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class Language extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_language);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.language, menu);
		return true;
	}
	public void sendMessage(View view) {
		if(view.getId()==R.id.english_button){
			Intent intent = new Intent(this, MainMenu.class);
			startActivity(intent);
		}
		if(view.getId()==R.id.spanish_button){
			Intent intent = new Intent(this, SpanishMainMenu.class);
			startActivity(intent);
		}
	    
	}

}

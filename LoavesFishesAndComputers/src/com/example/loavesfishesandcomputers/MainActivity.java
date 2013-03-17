package com.example.loavesfishesandcomputers;


import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;

public class MainActivity extends Activity {

	protected boolean _active = true;
	protected int _splashTime = 3500;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Handler handler = new Handler();
	    handler.postDelayed(new Runnable() {
	        public void run() {
	            finish();
	            Intent i3 = new Intent(MainActivity.this, Language.class);
	                startActivity(i3);
	        }
	    }, _splashTime);
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}


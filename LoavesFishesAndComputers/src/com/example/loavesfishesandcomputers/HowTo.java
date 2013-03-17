package com.example.loavesfishesandcomputers;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;

public class HowTo extends Activity {
	protected Integer how2_id;
	protected String[][] how2_info;
	protected Integer current_step = 0;
	protected Integer total_steps;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

    	Intent i = getIntent();
    	how2_id = i.getIntExtra("id", 0);
    	
		setContentView(R.layout.activity_how_to);
		// Show the Up button in the action bar.
		setupActionBar();
		loadSteps();
		
		TextView t = (TextView) findViewById(R.id.cat1);
		t.setText(how2_info[current_step][2] + "\n\n" + total_steps);
	}
	
	private void loadSteps() {
		LFCDbAdapter db = new LFCDbAdapter(HowTo.this);
		db.open();
		how2_info = db.getHow2info(how2_id);
		total_steps = db.countHow2Steps(how2_id);
		db.close();
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.how_to, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}

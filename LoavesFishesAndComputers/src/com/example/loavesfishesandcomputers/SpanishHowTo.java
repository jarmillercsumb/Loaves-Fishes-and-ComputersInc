package com.example.loavesfishesandcomputers;

//import com.example.loavesfishesandcomputers.MainMenu.ButtonListener;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.os.Build;

public class SpanishHowTo extends Activity {
	protected Integer how2_id;
	protected String[][] how2_info;
	protected Integer current_step = 0;
	protected Integer total_steps;
	Button bp, bn, bt;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

    	Intent i = getIntent();
    	how2_id = i.getIntExtra("id", 0);
    	
		setContentView(R.layout.activity_spanish_how_to);
		// Show the Up button in the action bar.
		setupActionBar();
		loadSteps();
		drawStep();
		setTitle("Loaves, Fishes, & Computers");
		//TextView t = (TextView) findViewById(R.id.cat1);
		//t.setText(how2_info[current_step][3] + "\n\n" + total_steps);
		
		bp = (Button) findViewById(R.id.button_p);
		bp.setOnClickListener(new ButtonListener());
		bn = (Button) findViewById(R.id.button_n);
		bn.setOnClickListener(new ButtonListener());
		bt = (Button) findViewById(R.id.button_t);
		bt.setOnClickListener(new ButtonListener());
	}

	
	private void loadSteps() {
		LFCDbAdapter db = new LFCDbAdapter(SpanishHowTo.this);
		db.open();
		how2_info = db.getHow2info(how2_id);
		total_steps = db.countHow2Steps(how2_id);
		db.close();
	}
	private class ButtonListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			if(v.getId()==R.id.button_p){
				if ( current_step > 0 ) {
					current_step--;
					drawStep();
				}
			}
			
			else if(v.getId()==R.id.button_n){
				if ( current_step < total_steps ) {
					current_step++;
					drawStep();
				}
			}
			else if(v.getId()==R.id.button_t){
				showHowTos();
			}
			
		}
	}
	
	public void showHowTos() {
		startActivity(new Intent(this, SpanishHowTos.class));
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
		getMenuInflater().inflate(R.menu.spanish_how_to, menu);
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
	
	public void drawStep() {
		
		// Draw Title
		TextView topic_text = (TextView) findViewById(R.id.topic_title);
		topic_text.setText(how2_info[current_step][2]);
		
		// Draw Step Text
		TextView content_text= (TextView) findViewById(R.id.topic_content);
		content_text.setText(how2_info[current_step][3]);

		// Disable Previous Button if on first step
		if ( current_step == 0 ) {
			Button myButton = (Button)findViewById(R.id.button_p);
			myButton.setEnabled(false);
		}
		
		else {
			Button myButton = (Button)findViewById(R.id.button_p);
			myButton.setEnabled(true);
		}
		
		// Disable Next Button if on last step
		if ( current_step == total_steps ) {
			Button myButton = (Button)findViewById(R.id.button_n);
			myButton.setEnabled(false);
		}
		
		else {
			Button myButton = (Button)findViewById(R.id.button_n);
			myButton.setEnabled(true);
		}
	}

}


package com.example.loavesfishesandcomputers;
import com.example.loavesfishesandcomputers.R;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class HowTos extends Activity {
	String[][] entries;
	
	public void makeEntries ()
	{
		LFCDbAdapter db = new LFCDbAdapter(HowTos.this);
		db.open();
		entries = db.getHow2s();
		db.close();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_how_tos);
		
		makeEntries();

		LinearLayout mainLayout = (LinearLayout)findViewById(R.id.main_layout_id);

		for (int i = 0; i < entries.length; i++ ) {
			View newView = getLayoutInflater().inflate(R.layout.item_template, mainLayout, false);
			mainLayout.addView(newView);
			

			TextView t = (TextView) newView.findViewById(R.id.cat1);
			t.setCompoundDrawablesWithIntrinsicBounds(R.drawable.pic2, 0, 0, 0);
			t.setText(entries[i][1] + "\n\n"+ entries[i][2]);
			t.setId(Integer.parseInt(entries[i][0]));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void getPage(View view) {
		Intent intent = new Intent(this, HowTo.class);
		intent.putExtra("id", view.getId());
	    startActivity(intent);
	}

}
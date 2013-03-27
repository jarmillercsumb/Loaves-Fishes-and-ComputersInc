
package com.example.loavesfishesandcomputers;

import com.example.loavesfishesandcomputers.R;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.view.Menu;

public class SpanishHowTos extends Activity {
	String[][] entries;
	
	public void makeEntries ()
	{
		LFCDbAdapter db = new LFCDbAdapter(SpanishHowTos.this);
		db.open();
		entries = db.getHow2s();
		db.close();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spanish_how_tos);
		
		makeEntries();
		setTitle("How Tos");
		LinearLayout mainLayout = (LinearLayout)findViewById(R.id.main_layout_id);

		for (int i = 0; i < entries.length; i++ ) {
			View newView = getLayoutInflater().inflate(R.layout.item_template, mainLayout, false);
			mainLayout.addView(newView);
			
			
			TextView t = (TextView) newView.findViewById(R.id.cat1);
			Drawable drawable = null;
			switch(i) {
			case 0:
				drawable = getResources().getDrawable(R.drawable.virus);
				break;
			case 1:
				drawable = getResources().getDrawable(R.drawable.internetspeed);
				break;
			case 2:
				drawable = getResources().getDrawable(R.drawable.lock);
				break;
			case 3:
				drawable = getResources().getDrawable(R.drawable.cheapinternet);
				break;
			case 4:
				drawable = getResources().getDrawable(R.drawable.ehow);
				break;
			case 5:
				drawable = getResources().getDrawable(R.drawable.phone);
				break;
			default:
				drawable = getResources().getDrawable(R.drawable.globe);
				break;
			}
			drawable.setBounds (0,0,96,96);
			ScaleDrawable sd = new ScaleDrawable(drawable, 0, 96, 96);
			t.setCompoundDrawables(sd.getDrawable(), null,  null,  null);
			
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
		Intent intent = new Intent(this, SpanishHowTo.class);
		intent.putExtra("id", view.getId());
	    startActivity(intent);
	}
	
	public void onBackPressed() {
		
		Intent intent = new Intent(this, SpanishMainMenu.class);
	    startActivity(intent);
	}

}

package com.example.loavesfishesandcomputers;
import com.example.loavesfishesandcomputers.R;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class SpanishAbout extends Activity {
	Button b;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spanish_about);
		this.setTitle("Acerca LFC");
		b = (Button) findViewById(R.id.LocateButton);
		b.setOnClickListener(new ButtonListener()); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	private class ButtonListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			if(v.getId()==R.id.LocateButton){
				startAbout();
			}
			
		}
	}
	public void startAbout(){
		startActivity(new Intent(this, SpanishLocation.class));
	}
	public void startDonate(View v){
		Uri uri = Uri.parse("http://www.loavesfishescomputers.org/become-a-tech-angel.html");
		Intent intent = new Intent(Intent.ACTION_VIEW, uri); 
		startActivity(intent);
	}

}
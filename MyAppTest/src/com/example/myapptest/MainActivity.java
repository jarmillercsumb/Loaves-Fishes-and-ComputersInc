package com.example.myapptest;

import com.example.myapptest.R;
import com.example.myapptest.play;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class MainActivity extends Activity{

	Button b;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.setTitle("Welcome");
		b = (Button) findViewById(R.id.ContinueButton);
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
			if(v.getId()==R.id.ContinueButton){
				startApp();
			}
			/*else if(v.getId()==R.id.HowToButton){
				startHelp();
			}*/
			
			
		}
	}
	public void startApp(){
		startActivity(new Intent(this, play.class));
	}
	/*public void startHelp(){
		startHelp(new Intent(this, help1.class));
	}*/

}

package com.example.loavesfishesandcomputers;
import com.example.loavesfishesandcomputers.R;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class MainMenu extends Activity {
	Button b;
	Button b2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mainmenu);
		this.setTitle("Main Menu");
		b = (Button) findViewById(R.id.AboutButton);
		b.setOnClickListener(new ButtonListener());
		b2=(Button) findViewById(R.id.HelpButton);
		b2.setOnClickListener(new ButtonListener());
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
			if(v.getId()==R.id.AboutButton){
				startAbout();
			}
			if(v.getId()==R.id.HelpButton){
				startHelp();
			}
			
		}
	}
	public void showHowTos(View v) {
		startActivity(new Intent(this, HowTos.class));
	}
	public void startAbout(){
		startActivity(new Intent(this, About.class));
	}
	public void startHelp(){
		startActivity(new Intent(this, Help.class));
	}



}
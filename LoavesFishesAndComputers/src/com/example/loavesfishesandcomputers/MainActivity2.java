package com.example.loavesfishesandcomputers;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.content.Intent;

public class MainActivity2 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Thread timer = new Thread(){
			public void run(){
				try{		

					LFCDbAdapter db = new LFCDbAdapter(MainActivity2.this);
					db.open();
					db.urlCall();
					db.close();			
					sleep(5000);
				} catch (InterruptedException e){
					e.printStackTrace();
				}finally{
					Intent intent = new Intent(MainActivity2.this, Language.class);
				    startActivity(intent);
					//View v = null;
					//MainActivity2.this.sendMessage(v);
				}
			}
		};
		timer.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/** Called when the user clicks the Send button */
	public void sendMessage(View view) {
	    Intent intent = new Intent(this, Language.class);
	    startActivity(intent);
	}
	

}


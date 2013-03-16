package com.example.project2;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class HowTo extends Activity {
	String[] how2object;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    	
    	Intent i = getIntent();
    	how2object = i.getStringArrayExtra("how2object");
    	
        setContentView(R.layout.activity_how_to);
		TextView t = (TextView) findViewById(R.id.textView1);
		t.setText(how2object[1]);

		setTitle(how2object[2]);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.how_tos, menu);
        return true;
    }
    
    public void onBackPressed() {
    	Intent myIntent = new Intent(HowTo.this, HowTos.class);
		startActivity(myIntent);
		finish();
    }
    
}
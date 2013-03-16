package com.example.project2;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;

public class HowTos extends Activity {
	
	protected static String[][] how2info = {
			{"12", "award", "Remove Viruses"},
			{"13", "bell", "Is Your Computer Slow?"},
			{"14", "cloud", "Guard Your Children"},
			{"15", "clock", "Find Best Internet"},
			{"16", "engine", "Search Google"},
			{"17", "grid", "View More Options"}
	};
	
	public TextView t; 
	public String text;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to);
        
        t = (TextView) findViewById(R.id.textView1);
        
		startingUp();
        //setupHowTos();
    }
    
    private void startingUp() {
        Thread timer = new Thread() { //new thread         
            public void run() {
                Boolean b = true;
                try {
                    do {
                    	LFCDbAdapter db = new LFCDbAdapter(HowTos.this);
        				db.open();
        				
        				text = db.urlCall(1);
        				
        				db.close();
                        sleep(100);

                        runOnUiThread(new Runnable() {  
	                        @Override
	                        public void run() {
	                            // TODO Auto-generated method stub
	
	            				t.setText(text);
	                        }
	                    });

                        b = false;
                    }
                    while (b == true);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                }
            };
        };
        timer.start();
    }
    
    protected void setupHow2Info() {
    }
    
    public static String[][] getHow2info() {
    	return how2info;
    }

    protected void setupHowTos() {
        
        GridView gridView = (GridView) findViewById(R.id.gridView1);
        
		gridView.setAdapter(new ImageAdapter(this));
		
		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
				int position, long id)
			{
				Intent myIntent = new Intent(HowTos.this, HowTo.class);
				myIntent.putExtra("how2object", how2info[position]);
				startActivity(myIntent);
				finish();
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.how_tos, menu);
        return true;
    }
    
}
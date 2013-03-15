/*	Name:Jared Miller
 * Title:Car Rental Reservation System Main
 * Date: May 9, 2012
 * ID: 4556
 */


package edu.csumb.CtoF;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;

public class CtoFActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
		// Set up a click listener for the Calculate button.
		View createButton = findViewById(R.id.create_button);
		createButton.setOnClickListener(this);	
		
        // Set up a click listener for the about button.
        View makeButton = findViewById(R.id.make_button);
        makeButton.setOnClickListener(this);
        
        View cancelButton = findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(this);
        
//        View manButton = findViewById(R.id.manage_button);
//        manButton.setOnClickListener(this);
    }
    
    public void onClick(View v) {

	   if(v.getId() == R.id.create_button)
	   {
		   Intent i = new Intent(this, About.class);
		   startActivity(i);
	   }
	   if(v.getId() == R.id.make_button)
	   {
		   Intent i = new Intent(this, makeRes.class);
		   startActivity(i);
	   }
	   if(v.getId() == R.id.cancel_button)
	   {
		   Intent i = new Intent(this, cancelRes.class);
		   startActivity(i);
	   }
	}
}
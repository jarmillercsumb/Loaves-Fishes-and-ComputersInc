/*	Name:Jared Miller
 * Title:Car Rental Reservation System Make Reservation Page 2 
 * Date: May 9, 2012
 * ID: 4556
 */


package edu.csumb.CtoF;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class makeRes2 extends Activity implements OnClickListener {

	Customer x = new Customer();
	TextView price; 
	int pickupDay = 0;
	int returnDay = 0;
	int numOfDays =0;
	String subt = null;
	double sub = 0;
	double total;
	
	 public void onCreate(Bundle savedInstanceState)
	 {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.makeres2);
	      
	      View nxtRes2Button = findViewById(R.id.nextRes2_button);
	        nxtRes2Button.setOnClickListener(this);
	        
	      View chkBox1 = findViewById(R.id.checkBox1);
	      chkBox1.setOnClickListener(this);
	      
	     // if(x.checkIfCarAvailable(x.getPickupRes(), x.getReturnRes(), x.getCarType())))
	      
	      View chkBox2 = findViewById(R.id.checkBox2);
	      chkBox2.setOnClickListener(this);
	      
	      View chkBox3 = findViewById(R.id.checkBox3);
	      chkBox3.setOnClickListener(this);
	      
	      x.setCars();
	      
	     
	 }
	 
	public void onClick(View v) 
	{
		// TODO Auto-generated method stub
		   if(v.getId() == R.id.checkBox1)
		   {
			  x.checkDate(x.getPickupRes(), x.getReturnRes(), 1);
			   if(x.getCar1() == true)
			   {
				   sub = 50.00 * x.getNumOfDays();
				   x.setCarType(1);                                 
			   }
			   else
			   {
				   Toast.makeText(this, "Sorry! This Car Is Unavailable For The Selected Dates.", Toast.LENGTH_SHORT).show();
			   }
			
		   } if(v.getId() == R.id.checkBox2)
		   {
			   x.checkDate(x.getPickupRes(), x.getReturnRes(), 2);
			   if(x.getCar1() == true)
			   {
				   sub = 25.00 * x.getNumOfDays();
				   x.setCarType(2);                                
			   }
			   else
			   {
				   Toast.makeText(this, "Sorry! This Car Is Unavailable For The Selected Dates.", Toast.LENGTH_SHORT).show();
			   }
			  

		   } if(v.getId() == R.id.checkBox3)
		   {
			   x.checkDate(x.getPickupRes(), x.getReturnRes(), 3);
			   if(x.getCar1() == true)
			   {
				   sub = 35.00 * x.getNumOfDays();
				   x.setCarType(3);                             
			   }
			   else
			   {
				   Toast.makeText(this, "Sorry! This Car Is Unavailable For The Selected Dates.", Toast.LENGTH_SHORT).show();
			   }
		
		   }
		   
		   if(v.getId() == R.id.nextRes2_button)
		   {
			   Intent i = new Intent(this, makeRes3.class);
			   startActivity(i);
			   total=sub;
			   x.setAmount(total);
		   }
		   total=sub;
//		   x.setAmount(total);
		   subt = Double.toString(total);
		   price = (TextView)findViewById(R.id.about_textview);
		   price.setText("These Vehicles Are Available. 				(Subtotal:   $" + total + "0)");
	}

}

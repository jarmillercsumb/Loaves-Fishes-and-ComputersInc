/*	Name:Jared Miller
 * Title:Car Rental Reservation System Cancel Reservation Page 2
 * ID: 4556
 * Date: May 9, 2012
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

public class cancelRes2 extends Activity implements OnClickListener {

Customer x = new Customer();	
TextView res;
String p ="";


	 public void onCreate(Bundle savedInstanceState)
	 {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.cancelres2);
	      
	      View nxtButton = findViewById(R.id.button1);
	      nxtButton.setOnClickListener(this);
	      
	      res = (TextView)findViewById(R.id.reservat);
	      
	      
	      for(int i=0; i<x.getNumOfRes(); i+=2)
	      {
	    	  p = ("Reservation Number: "+ x.getResNum() +"\n"+"Pickup Date/Time: " + x.getReservation(i) + "\n"+ "Return Date/Time:" + x.getReservation(i+1)+ "\n" + "Car Type: " + x.getCar()+ "\n");
	    	  
	    	 //Toast.makeText(this, x.getCustomerLog(i), Toast.LENGTH_LONG).show();
	      }
	      res.setText(p);
	 }
	 
	public void onClick(View v) 
	{
		// TODO Auto-generated method stub
		
		EditText reservationNumber;
		
		int resNumber;
		
		reservationNumber = (EditText)findViewById(R.id.editText1);
		resNumber = Integer.parseInt(reservationNumber.getText().toString());
		   
		 if(v.getId() == R.id.button1)
		   {
			 if(x.resExist(resNumber) == true)
			 {
				 x.cancelRes(resNumber);
				 Intent i = new Intent(this, CtoFActivity.class);
				   startActivity(i);
			 }
			 else
			 {
				 Toast.makeText(this, "Sorry! There Are No Reservations With The Number " + resNumber + "!", Toast.LENGTH_SHORT).show();
				   Toast.makeText(this, "Please Try Again!", Toast.LENGTH_SHORT).show();
			 } 
		   }
	}
}

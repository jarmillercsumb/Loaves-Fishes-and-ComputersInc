/*	Name:Jared Miller
 * Title:Car Rental Reservation System Make Reservation Page 4
 * ID: 4556
 * Date: May 9, 2012
 */

package edu.csumb.CtoF;

import java.util.Random;

import edu.csumb.CtoF.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class makeRes4 extends Activity implements OnClickListener 
{
	TextView user;
	Customer x = new Customer();
	public makeRes4()
	{
		
	}
	
   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.makeres4);
      
      View confirmButton = findViewById(R.id.confirm_button);
      confirmButton.setOnClickListener(this);	
      
  	TextView pick;
  	TextView ret;
  	TextView user;
  	TextView car;
  	TextView resNum;
  	TextView totalAmount;
  	TextView currentT;
  	
	user = (TextView)findViewById(R.id.userName);
    user.setText("Username: " + x.getTempUser());
    
	car = (TextView)findViewById(R.id.carType);
    car.setText("Car: " + x.getCar());
    
    resNum = (TextView)findViewById(R.id.resNum);
    resNum.setText("Reservation Number: " + x.getResNum());
    
    totalAmount = (TextView)findViewById(R.id.total_amount);
    totalAmount.setText("Total Amount : $ " + x.getAmount() + "0");
    
    currentT = (TextView)findViewById(R.id.currentTime);
    currentT.setText("Current Time: " + x.getCurrentTime());
  	
  	pick = (TextView)findViewById(R.id.pickupDate);
    pick.setText("Pickup Date & Time: " + x.getPickupRes());
      
    ret = (TextView)findViewById(R.id.returnDate);
    ret.setText("Return Date & Time: " + x.getReturnRes());
 
   }

public void onClick(View v) 
{
	
	 if(v.getId() == R.id.confirm_button)
	   {
		 x.addRes(x.getPickupRes());
		 x.addRes(x.getReturnRes());
		 x.addCar(x.getCarType());
		 x.setReservationLog();
//		 for(int i = 0; i<x.getTotalNum(); i++)
//		   {
//			   Toast.makeText(this, x.getReservationLog(i), Toast.LENGTH_LONG).show();
//		   }
		   Intent i = new Intent(this, CtoFActivity.class);
		   startActivity(i);
	   }
    
    
    
	// TODO Auto-generated method stub
	
}
   }
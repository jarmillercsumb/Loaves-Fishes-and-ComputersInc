/*	Name:Jared Miller
 * Title:Car Rental Reservation System Manage System Page
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

public class manageSystem extends Activity implements OnClickListener {

	Customer x = new Customer();
	 public void onCreate(Bundle savedInstanceState)
	 {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.mansystem);
	      
	      String cust = "";
	      String resv = "";
	      String canRes = "";
	      TextView cLog;
	      TextView rLog;
	      TextView canLog;
	      cLog = (TextView)findViewById(R.id.some);
	      rLog = (TextView)findViewById(R.id.some1);
	      canLog = (TextView)findViewById(R.id.some2);
	      for(int i=0; i<x.getNumCustomers() ; i++)
	      {
	    	  cust = cust + (x.getCustomerLog(i) + "\n"+ "\n");
	    	  
	    	 //Toast.makeText(this, x.getCustomerLog(i), Toast.LENGTH_LONG).show();
	      }
	      //Toast.makeText(this, x.getNumOfRes(), Toast.LENGTH_SHORT).show();
	      for(int i = 0; i<x.getTotalNum(); i++)
		   {
			   cust = cust + x.getReservationLog(i) + "\n\n";
		   }
	      for(int i = 0; i<x.getTotalCan(); i++)
		   {
			   canRes = canRes + x.getCancelLog(i) + "\n\n";
		   }
	      cLog.setText(cust);
	      rLog.setText(resv);
	      canLog.setText(canRes);
	      }
	 
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}

}

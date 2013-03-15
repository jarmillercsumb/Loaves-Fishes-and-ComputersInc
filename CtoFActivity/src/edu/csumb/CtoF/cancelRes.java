/*	Name:Jared Miller
 * Title:Car Rental Reservation System Cancel Reservation Page 1
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

public class cancelRes extends Activity implements OnClickListener {

	private int numOfTrys =0;
	
	 public void onCreate(Bundle savedInstanceState)
	 {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.cancelres);
	      
	
	      
	      View submitButton = findViewById(R.id.next_button);
	      submitButton.setOnClickListener(this);
	      

	 }
	 
	public void onClick(View v) 
	{
		
			EditText userName;
		   EditText passWord;
		   String usrName;
		   String pssWord;
		   Customer x = new Customer();
		   char c;
		   
		 
		   userName = (EditText)findViewById(R.id.editText1);
		   usrName = userName.getText().toString();
		   passWord =  (EditText)findViewById(R.id.editText2);
		   pssWord = passWord.getText().toString();
		   x.setTempName(usrName);
		   x.setTempPass(pssWord);
		   
		// TODO Auto-generated method stub
//		if(v.getId() == R.id.manage_button)
//		   {
//			 Intent i = new Intent(this, manageSystem.class);
//			   startActivity(i);
//		
//		   }
		   
		   for(int i =0; i<usrName.length(); i++)
		   {
			   c= usrName.charAt(i);
			   if(Character.isLetterOrDigit(c) == false)
			   {
				   Toast.makeText(this, "Username Can Only Contain Letters and Numbers!", Toast.LENGTH_SHORT).show();
				   numOfTrys++;
			   }
		   }
		   for(int i =0; i<pssWord.length(); i++)
		   {
			   c= pssWord.charAt(i);
			   if(Character.isLetterOrDigit(c) == false)
			   {
				   Toast.makeText(this, "Password Can Only Contain Letters and Numbers!", Toast.LENGTH_SHORT).show();
				   numOfTrys++;
			   }
		   }
		if(v.getId() == R.id.next_button)
		   {
			
			if((usrName.equals("admin2") && pssWord.equals("admin2")) && numOfTrys<2 )
			   {
				 Intent i = new Intent(this, manageSystem.class);
				   startActivity(i);
			   }
			   else if((usrName.length() < 5) && numOfTrys<2 )
			   {
				   Toast.makeText(this, "Username Not Long Enough!", Toast.LENGTH_SHORT).show();
				   Toast.makeText(this, "Please Try Again!", Toast.LENGTH_SHORT).show();
				   numOfTrys++;
			   }
			   else if((pssWord.length() < 5) && numOfTrys<2 )
			   {
				   Toast.makeText(this, "Password Not Long Enough!", Toast.LENGTH_SHORT).show();
				   Toast.makeText(this, "Please Try Again!", Toast.LENGTH_SHORT).show();
				   numOfTrys++;
			   }
			   else if(usrName.length() >=5 && pssWord.length() >=5)
			   {
				   if(x.findCustomerb(usrName, pssWord, x.getNumCustomers()) == true)
				   {
					   Intent i = new Intent(this, cancelRes2.class);
					   startActivity(i);
				   }else
				   {
					   Toast.makeText(this, "Could Not Find An Account With The Information You Entered!", Toast.LENGTH_SHORT).show();
					   Toast.makeText(this, "Please Try Again!", Toast.LENGTH_SHORT).show();
					   numOfTrys++;
				   }
				
		   }
		
	}

}
}

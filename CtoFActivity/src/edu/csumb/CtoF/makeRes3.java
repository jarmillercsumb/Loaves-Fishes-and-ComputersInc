/*	Name:Jared Miller
 * Title:Car Rental Reservation System Make Reservation Page 3
 * ID: 4556
 * Date: May 9, 2012
 */
package edu.csumb.CtoF;

import edu.csumb.CtoF.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class makeRes3 extends Activity implements OnClickListener {
	 private int number =0;
	 Customer x = new Customer();
   @Override
 
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.makeres3);

      
      View nextButton = findViewById(R.id.next_button);
      nextButton.setOnClickListener(this);
   }
   
   public void onClick(View v) {
	   EditText userName;
	   EditText passWord;
	   String usrName;
	   String pssWord;
	   char c;
	   
	   int numOfTrys =0;

		   if(v.getId() == R.id.next_button)
		   {
//			   Intent i = new Intent(this, CtoFActivity.class);
//			   startActivity(i);
			   
			   userName = (EditText)findViewById(R.id.editText1);
			   usrName = userName.getText().toString();
			   passWord =  (EditText)findViewById(R.id.editText2);
			   pssWord = passWord.getText().toString();
			   
			   
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
			   if(usrName.equals("admin2") && numOfTrys <2)
			   {
				   Toast.makeText(this, "You Cannot Access This Account", Toast.LENGTH_SHORT).show();
				   Toast.makeText(this, "Please Try Again!", Toast.LENGTH_SHORT).show();
				   numOfTrys++;
			   }
			   else if(usrName.length() < 5 && numOfTrys <2)
			   {
				   Toast.makeText(this, "Username Not Long Enough!", Toast.LENGTH_SHORT).show();
				   Toast.makeText(this, "Please Try Again!", Toast.LENGTH_SHORT).show();
				   numOfTrys++;
			   }
			   else if(pssWord.length() < 5 && numOfTrys <2)
			   {
				   Toast.makeText(this, "Password Not Long Enough!", Toast.LENGTH_SHORT).show();
				   Toast.makeText(this, "Please Try Again!", Toast.LENGTH_SHORT).show();
				   numOfTrys++;
			   }
			   else if((usrName.length() >=5 && pssWord.length() >=5) && numOfTrys <2)
			   {
				   if(x.findCustomerb(usrName, pssWord, x.getNumCustomers()) == true)
				   {   
					   x.setTempUser(usrName);
					   Intent i = new Intent(this, makeRes4.class);
					   startActivity(i);
				   }else
				
				   Toast.makeText(this, "Could Not Find An Account With The Information You Entered!     " + usrName, Toast.LENGTH_SHORT).show();
				   Toast.makeText(this, "Please Try Again!", Toast.LENGTH_SHORT).show();
			   }   
				 //  String temp = x.findCustomerb(usrName, pssWord, x.getNumCustomers());
				   Toast.makeText(this, "Could Not Find An Account With The Information You Entered!     " , Toast.LENGTH_SHORT).show();
			 
		}  else if(numOfTrys ==2)
		   {
			   Toast.makeText(this, "Error! Too Many Attempts Signing In!", Toast.LENGTH_SHORT).show();
			   Intent i = new Intent(this, CtoFActivity.class);
			   startActivity(i);
		   }
	  
   }
   
   public int getNum()
   {
	   return number;
   }
}

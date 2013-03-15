/*	Name:Jared Miller
 * Title:Car Rental Reservation System Create Page
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

public class About extends Activity implements OnClickListener {
   
	 private  int numOfTrys =0;
	
	@Override
   
  
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.about);
     
      
      View nextButton = findViewById(R.id.next_button);
      nextButton.setOnClickListener(this);
    
   }
   
   public void onClick(View v) {
	   EditText userName;
	   EditText passWord;
	   String usrName;
	   String pssWord;
	   Customer x;
	   char c;
	   
	 
		   if(v.getId() == R.id.next_button)
		   {
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
			
			   if((usrName.equals("admin2") && numOfTrys<2) )
			   {
				   Toast.makeText(this, "Account Cannot Be Created!", Toast.LENGTH_SHORT).show();
				   Toast.makeText(this, "Username already taken!", Toast.LENGTH_SHORT).show();
				   Toast.makeText(this, "Please enter another Username!", Toast.LENGTH_SHORT).show();
				   numOfTrys++;
			   }
			   
			   else if(usrName.length() < 5 && numOfTrys <2) 
			   {
			
				   Toast.makeText(this, "Account Cannot Be Created!", Toast.LENGTH_SHORT).show();
				   Toast.makeText(this, "Username Not Long Enough!", Toast.LENGTH_SHORT).show();
				   Toast.makeText(this, "Please Enter Another Username!", Toast.LENGTH_SHORT).show();
				   numOfTrys++;
			   }
			   else if((pssWord.length() < 5) && numOfTrys<2)
			   {
		
				   Toast.makeText(this, "Account Cannot Be Created!", Toast.LENGTH_SHORT).show();
				   Toast.makeText(this, "Password Not Long Enough!", Toast.LENGTH_SHORT).show();
				   Toast.makeText(this, "Please Enter Another Password!", Toast.LENGTH_SHORT).show();
				   numOfTrys++;
			   }
			   else if((usrName.length() >=5 && pssWord.length() >=5) && numOfTrys<2)
			   {
				   x = new Customer();
				   x.setUser(usrName);
				   x.setPass(pssWord);
				   x.setCustomerLog();
				   if(x.add(x) == false)
				   {
					   Toast.makeText(this, "Account Cannot Be Created!", Toast.LENGTH_SHORT).show();
					   Toast.makeText(this, "Username Already Taken!", Toast.LENGTH_SHORT).show();
					   Toast.makeText(this, "Please Try Again!", Toast.LENGTH_SHORT).show();
					   numOfTrys++;
				   }
				   else
				   {
					   Toast.makeText(this, "Account Created Successfully!", Toast.LENGTH_SHORT).show();
					   
//					   for(int i = 0; i< x.getNumCustomers(); i++)
//					   {
//						   Toast.makeText(this, x.getArray(i).getUser(), Toast.LENGTH_LONG).show();
//					   }
					  
					   numOfTrys++;
					   Intent i = new Intent(this, CtoFActivity.class);
					   startActivity(i);
					   
				   }
				   
			   } 
			
			   
			   
			   else
			   {

				   Toast.makeText(this, "Error! Too Many Attempts To Create An Acount!", Toast.LENGTH_SHORT).show();
			   Intent i = new Intent(this, CtoFActivity.class);
			   startActivity(i);
			   }
			   
		}
	  
   }
}

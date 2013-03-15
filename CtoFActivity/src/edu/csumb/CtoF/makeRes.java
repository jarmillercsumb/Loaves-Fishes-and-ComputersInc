/*	Name:Jared Miller
 * Title:Car Rental Reservation System Make Reservation Page 1
 * Date: May 9, 2012
 * ID: 4556
 */

package edu.csumb.CtoF;


import android.app.Activity;
import android.os.Bundle;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import java.util.Date;
import java.sql.Time;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

public class makeRes extends Activity implements OnClickListener
{
	DateFormat fmtDateAndTime = DateFormat.getDateTimeInstance();
	Date pickupR;
	Date returnR;
	Customer x = new Customer();
	TextView lblDateAndTime;
	TextView rtnLblDateAndTime;
	Calendar myCalendar = Calendar.getInstance();
	Calendar myReturnCalendar = Calendar.getInstance();

	DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() 
	{
	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
	myCalendar.set(Calendar.YEAR, year);
	myCalendar.set(Calendar.MONTH, 5);
	myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
	updateLabel();
	}
	};

	TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
		myCalendar.set(Calendar.MINUTE, minute);
		updateLabel();
	}
	};
	
	DatePickerDialog.OnDateSetListener rd = new DatePickerDialog.OnDateSetListener() 
	{
	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
	myReturnCalendar.set(Calendar.YEAR, year);
	myReturnCalendar.set(Calendar.MONTH, monthOfYear);
	myReturnCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
	updateReturnLabel();
	}
	};
	
	
	TimePickerDialog.OnTimeSetListener rt = new TimePickerDialog.OnTimeSetListener() {
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			myReturnCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
			myReturnCalendar.set(Calendar.MINUTE, minute);
			updateReturnLabel();
		}
		};
	
	private void updateReturnLabel()
	{
		
		rtnLblDateAndTime.setText(fmtDateAndTime.format(myReturnCalendar.getTime()));
		returnR = myReturnCalendar.getTime();
		x.setResNum();
		x.setReturnRes(returnR);
		x.setReturnDay(returnR.getDay());
		
	}

	private void updateLabel() 
	{
		lblDateAndTime.setText(fmtDateAndTime.format(myCalendar.getTime()));
		pickupR = myCalendar.getTime();
		x.setPickupRes(pickupR);
		x.setPickupDay(pickupR.getDay());
	}

	@Override
	public void onCreate(Bundle icicle) 
	{
		super.onCreate(icicle);
		setContentView(R.layout.makeres);
		
		View nextButton = findViewById(R.id.next_button);
		nextButton.setOnClickListener(this);
		
		lblDateAndTime = (TextView) findViewById(R.id.lblDateAndTime);
		rtnLblDateAndTime = (TextView) findViewById(R.id.lblReturnDateAndTime);
		
		Button btnDate = (Button) findViewById(R.id.btnDate);
		btnDate.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View v) 
			{
				new DatePickerDialog(makeRes.this, d, myCalendar
						.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
						myCalendar.get(Calendar.DAY_OF_MONTH)).show();
			}
		});
	
		Button btnTime = (Button) findViewById(R.id.btnTime);
		btnTime.setOnClickListener(new View.OnClickListener() {
			public  void onClick(View v)
			{
				new TimePickerDialog(makeRes.this, t, myCalendar
						.get(Calendar.HOUR_OF_DAY), myCalendar
						.get(Calendar.MINUTE), true).show();
				
			}
		});
		
		Button rtnDate = (Button) findViewById(R.id.rtnDate);
		rtnDate.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View v) 
			{
				new DatePickerDialog(makeRes.this, rd, myReturnCalendar
						.get(Calendar.YEAR), myReturnCalendar.get(Calendar.MONTH),
						myReturnCalendar.get(Calendar.DAY_OF_MONTH)).show();
			}
		});
	
		Button rtnTime = (Button) findViewById(R.id.rtnTime);
		rtnTime.setOnClickListener(new View.OnClickListener() {
			public  void onClick(View v) {
				new TimePickerDialog(makeRes.this, rt, myReturnCalendar
						.get(Calendar.HOUR_OF_DAY), myReturnCalendar
						.get(Calendar.MINUTE), true).show();
			}
		});
		updateLabel();
		updateReturnLabel();
	}//onCreate
	public void onClick(View v)
	{
		if(pickupR.after(returnR))
		{
			Toast.makeText(this, "Error! Return Date Before Pickup Date!", Toast.LENGTH_SHORT).show();
		}
		else if(pickupR.getMonth() != 5 || returnR.getMonth() != 5)
		{
			Toast.makeText(this, "Error! June Is The Only Month Available For Reservations!", Toast.LENGTH_SHORT).show();
		}
		else if(pickupR.getYear() != 112 || returnR.getYear() != 112)
		{
			Toast.makeText(this, "Error! 2012 Is The Only Year Available For Reservations!", Toast.LENGTH_SHORT).show();
		}
		else
		{
			if(v.getId() == R.id.next_button)
			   {
				
				x.setNumOfDays(pickupR.getDate(), returnR.getDate());
				 Intent i = new Intent(this, makeRes2.class);
				   startActivity(i);
				   
			   }
		}
		
		
	}
} // class
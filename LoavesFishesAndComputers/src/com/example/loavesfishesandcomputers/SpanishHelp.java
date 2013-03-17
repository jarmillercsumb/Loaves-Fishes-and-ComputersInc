package com.example.loavesfishesandcomputers;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class SpanishHelp extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spanish_help);
		
		//Linkify.addLinks(text, Linkify.PHONE_NUMBERS);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.help, menu);
		return true;
	}
	public void sendEmail(View view) {
		
		Toast.makeText(getApplicationContext(), "Emailing!", Toast.LENGTH_SHORT).show();
		
		Intent intent = new Intent(Intent.ACTION_SENDTO);
		intent.setType("text/html");
		intent.setData(Uri.parse("mailto:info@loavesfishescomputers.org"));
		intent.putExtra(Intent.EXTRA_SUBJECT, "User Question From LFC Application");
	

		startActivity(Intent.createChooser(intent, "Send Email"));
		
	}
	
	public void sendCall(View view)
	{
		String telephone = "831-393-9260";
		String num = "tel:" + telephone.trim();
		
		Toast.makeText(getApplicationContext(), "Llamando!", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(Intent.ACTION_CALL);
		intent.setData(Uri.parse(num));
		startActivity(intent);
	}
	public void startFB(View v){
		Uri uri = Uri.parse("https://www.facebook.com/loavesfishescomputers?fref=ts");
		Intent intent = new Intent(Intent.ACTION_VIEW, uri); 
		startActivity(intent);

	}
	public void startTwit(View v){
		Uri uri = Uri.parse("https://twitter.com/lfcomputers");
		Intent intent = new Intent(Intent.ACTION_VIEW, uri); 
		startActivity(intent);

	}
	public void startWeb(View v){
		Uri uri = Uri.parse("http://www.loavesfishescomputers.org/index.html");
		Intent intent = new Intent(Intent.ACTION_VIEW, uri); 
		startActivity(intent);

	}
	

}

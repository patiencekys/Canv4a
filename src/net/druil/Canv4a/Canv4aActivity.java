package net.druil.Canv4a;

import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Canv4aActivity extends Activity {
	public static String baseURL = "http://canv.as/";
	public static DefaultHttpClient clt = new DefaultHttpClient();
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button funny = (Button) findViewById(R.id.bfunny_go);
        funny.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				Intent i = new Intent(getBaseContext(), GroupViewActivity.class);
				i.putExtra("CHANNEL", "funny");
		        startActivity(i);
			}
        	
        });
    }
}
package net.druil.Canv4a;

import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    
    /* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inf = getMenuInflater();
		inf.inflate(R.id.settings, menu);
		return super.onCreateOptionsMenu(menu);
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		return super.onOptionsItemSelected(item);
	}

	public static String https2http(String url){
    	Log.v("https2http","url: "+url);
    	if("https".equals(url.substring(0,5))){
    		Log.v("https2http;", "returning: "+"http"+url.substring(5));
    		return "http"+url.substring(5);
    	}
    	else
    		return url;
    }
}
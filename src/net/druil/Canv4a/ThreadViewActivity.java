/**
 * 
 */
package net.druil.Canv4a;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/**
 * @author Paul
 *
 */
public class ThreadViewActivity extends Activity {

	private DefaultHttpClient clt = new DefaultHttpClient();
	private JSONObject res;
	private String baseURL = "http://canv.as/public_api/groups/";
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.thread);
        Log.d("API", "Trying to get CHANNEL from bundle...");
        String url = baseURL+getIntent().getExtras().getString("CHANNEL");
        Log.d("API","OK "+ url);
        try {
			HttpResponse getResponse = clt.execute(new HttpGet(url));
			String resp = EntityUtils.toString(getResponse.getEntity());
			//Log.d("API",resp);
			res = new JSONObject(resp);
			JSONObject posts = res.getJSONObject("posts");
			Log.d("API","================");
			Log.d("API","posts:"+posts.toString());
			Log.d("API","================");
			TextView g = (TextView) findViewById(R.id.Logresult);
			g.setText(resp);
        }
        catch(Exception e){
        	e.printStackTrace();
        	Log.w("API", "Exception lors de la recherche des flux");
        }
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	protected void createPostContainer(){
		/*
		 * Must create: 
		 * {{
		 *    linear view for each post
		 *    medium text view for each post title
		 *    imageview for each post
		 *    small view for each author's post
		 *    small view for each post comment
		 * }}
		 */
	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onDestroy()
	 */
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	
	
	

}

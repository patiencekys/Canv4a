/**
 * 
 */
package net.druil.Canv4a;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * @author Paul
 *
 */
public class ThreadViewActivity extends Activity {

	private DefaultHttpClient clt = new DefaultHttpClient();
	private JSONObject res;
	private String url = new String("http://canv.as/public_api/posts/pt3n8");
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.thread);
        try {
			HttpResponse getResponse = clt.execute(new HttpGet(url));
			Log.d("API",EntityUtils.toString(getResponse.getEntity()));
			res = new JSONObject(EntityUtils.toString(clt.execute(new HttpGet(url)).getEntity()));
			Log.d("API",res.getString("category"));
        }
        catch(Exception e){
        	Log.d("API", "Exception lors de la recherche des flux");
        }
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
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

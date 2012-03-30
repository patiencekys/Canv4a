/**
 * 
 */
package net.druil.Canv4a;

import java.util.LinkedList;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
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
	private JSONObject res;
	protected String baseGroupURL = Canv4aActivity.baseURL+"public_api/groups/";
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.thread);
        try {
			HttpResponse getResponse = Canv4aActivity.clt.execute(new HttpGet(url));
			String resp = EntityUtils.toString(getResponse.getEntity());
			res = new JSONObject(resp);
			try{
				JSONArray posts = res.getJSONArray("posts");
				Log.d("API","================");
				Log.d("API","posts:"+posts.toString());
				Log.d("API","================");
				TextView g = (TextView) findViewById(R.id.Logresult);
				g.setText(posts.toString());
				
			}
			catch(JSONException e){
				Log.w("API","JSONException");
				Log.w("API",e.getMessage());
			}
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
	
	protected String[] getPostList(JSONObject j){
		/*
		 * must return a post list in a thread.
		 */
		return null;
	}
	
	
	

}

package net.druil.Canv4a;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.LinkedList;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class GroupViewActivity extends Activity {
	public LinkedList<CanvasPost> posts;
	public String groupURL;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.thread);
		
		posts = new LinkedList<CanvasPost>();
		
        Log.d("API", "Trying to get CHANNEL from bundle...");
        String groupURL = Canv4aActivity.baseURL+"public_api/groups/"+getIntent().getExtras().getString("CHANNEL");
        Log.d("API","OK "+ groupURL);
        
        	Log.v("CORE","Entering main JSON fetching");
	        try {
				HttpResponse getResponse = Canv4aActivity.clt.execute(new HttpGet(groupURL));
				String resp = EntityUtils.toString(getResponse.getEntity());
				JSONObject res = new JSONObject(resp);
				JSONArray j_posts = res.getJSONArray("posts");
				Log.v("CORE", "Entering group posts fetching");
				for(int i=0; i < j_posts.length(); i++){
					JSONObject res_p = j_posts.getJSONObject(i);
					try {
						Log.v("CORE", "Fetching one object...");
						Log.d("API","posturl: "+Canv4aActivity.baseURL+res_p.getString("api_url"));
						HttpResponse response = Canv4aActivity.clt.execute(new HttpGet(Canv4aActivity.baseURL+res_p.getString("api_url")));
						Log.v("CORE","Converting recieved data to string");
						String resp_p = EntityUtils.toString(response.getEntity());
						Log.v("CORE", "Converting string to JSONObject");
						res_p = new JSONObject(resp_p);
						
						Log.v("CORE", "Constructing CanvasPost object");
						CanvasPost p = new CanvasPost(res_p);
						Log.v("CORE","Reading P...");
						Log.v("CORE","P: "+p.category);
						Log.v("CORE", "Adding new CanvasPost to list of posts");
						posts.add(p);
						Log.v("CORE", "Done.");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						Log.e("CORE","Could not get object");
						e.printStackTrace();
					}
				}
				Log.v("CORE", "Trying to display one item");
				displayOne(0);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void display(){
		// code to display all elements in this.posts
	}
	
	public void displayOne(int i){
		// displays only one element
		Log.d("CORE","Trying to display post#"+i);
		CanvasPost p = posts.get(i);
		Log.d("DISPLAY", "displayOne: p= "+"http://canv.as"+p.api_url);
		try {
			URL url = new URL("http://canv.as"+p.api_url);
			InputStream content = (InputStream)url.getContent();
			Drawable d = Drawable.createFromStream(content , "src");
			ImageView img = (ImageView) findViewById(R.id.postImage);
			img.setBackgroundDrawable(d);
		}
		catch(Exception e){
			Log.e("IMG","Exception: "+e.getMessage());
		}
	}
}

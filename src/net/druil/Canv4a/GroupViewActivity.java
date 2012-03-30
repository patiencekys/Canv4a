package net.druil.Canv4a;

import java.util.LinkedList;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class GroupViewActivity extends Activity {
	public LinkedList<CanvasPost> posts;
	public String groupURL;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
        Log.d("API", "Trying to get CHANNEL from bundle...");
        String groupURL = Canv4aActivity.baseURL+"groups/"+getIntent().getExtras().getString("CHANNEL");
        Log.d("API","OK "+ groupURL);
        
        try {
	        HttpResponse getResponse = Canv4aActivity.clt.execute(new HttpGet(groupURL));
			String resp = EntityUtils.toString(getResponse.getEntity());
			JSONObject res = new JSONObject(resp);
			JSONArray j_posts = res.getJSONArray("posts");
			for(int i=0; i < j_posts.length(); i++){
				posts.add(
						new CanvasPost(j_posts.getJSONObject(i))
						);
			}
        }
        catch(Exception e){
        	
        }
	}
}

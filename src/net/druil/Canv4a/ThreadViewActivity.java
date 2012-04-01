/**
 * 
 */
package net.druil.Canv4a;

import java.io.InputStream;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * @author Paul
 *
 */
public class ThreadViewActivity extends Activity {
	protected String baseGroupURL;
	protected String op_id;
	public CanvasThread thread;
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.thread);
        op_id = getIntent().getExtras().getString("POSTID");
        Log.d("CORE", "Post URL: "+Canv4aActivity.baseURL+"public_api/posts/"+this.op_id);
        try {
        	Log.d("API", "Requesting OP");
	        HttpResponse getResponse = Canv4aActivity.clt.execute(
	        		new HttpGet(
	        				new String(
	        						Canv4aActivity.baseURL+"public_api/posts/"+this.op_id
	        						)
	        				)
	        		);
	        Log.d("API", "converting to string..");
			String resp = EntityUtils.toString(getResponse.getEntity());
			JSONObject res = new JSONObject(resp);
			Log.d("API", "Creating OP with JSONObject...");
			CanvasPost p = new CanvasPost(res);
			Log.d("CORE", "Creating thread with OP Object...");
			thread = new CanvasThread(p);
			Log.d("CORE", "Getting entire Thread...");
			thread.getEntireThread();
			Log.d("DISPLAY", "Displaying thread...");
			display();
			Log.d("CORE","Done");
        }
        catch(Exception e){
        	e.printStackTrace();
        }
	}
	public void display(){
		// code to display all elements in this.posts
		Log.d("DISPLAY", "Getting Scrollview...");
		ScrollView l = new ScrollView(this);
		Log.d("LAYOUT", "creating LinearLayout...");
		LinearLayout lv = new LinearLayout(this);
		lv.setBackgroundColor(Color.WHITE);
		Log.d("DISPLAY", "Setting LinearLayout orientation...");
		lv.setOrientation(LinearLayout.VERTICAL);
		Log.d("DISPLAY", "Adding LinearLayout to ScrollView.....");
		l.addView(lv);
		Log.d("DISPLAY","Creating Title..");
		TextView title = new TextView(this);
		Log.d("DISPLAY", "Setting gravity...");
		title.setGravity(Gravity.CENTER);
		Log.d("DISPLAY", "Setting Text Size..");
		title.setTextSize(30);
		Log.d("DISPLAY", "Changing text to "+thread.posts.getFirst().title);
		title.setText(thread.posts.getFirst().title);
		title.setTextColor(Color.BLACK);
		Log.d("DISPLAY", "Adding to ScrollView...");
		lv.addView(title);
		for(int i=0; i<thread.posts.size(); i++){
			try {
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
				final CanvasPost p = thread.posts.get(i);
				Log.v("DISPLAY", "Trying to set image...");
				if(p.urls.stream != null){
					Log.v("DISPLAY", "Post url is defined");
					URL url = new URL(p.urls.stream.name);
					Log.d("DISPLAY", "Getting URL...");
					InputStream content = (InputStream)url.getContent();
					Log.d("DISPLAY", "Creating Drawable from InputStream...");
					Drawable d = Drawable.createFromStream(content , "src");
					Log.d("DISPLAY", "Creating imageview");
					ImageView im = new ImageView(this);
					Log.d("DISPLAY", "Adding onclicklistener");
					im.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
							Intent i = new Intent(getBaseContext(), ImageViewActivity.class);
							i.putExtra("POSTURL", p.api_url);
							startActivity(i);
						}
					});
					im.setMinimumHeight(p.urls.stream.h);
					im.setMinimumWidth(p.urls.stream.w);
					Log.d("DISPLAY", "Setting Image...");
					im.setImageDrawable(d);
					Log.d("DISPLAY", "Adding view...");
					lv.addView(im);
				}
				else
					Log.i("IMG","imgurl is null. posturl: "+p.url);
				
				if(p.caption!=null){
					try{
						Log.d("DISPLAY", "Creating TextView...");
						TextView titlev = new TextView(this);
						Log.d("DISPLAY", "setting caption text: "+p.caption);
						titlev.setText(p.caption);
						Log.d("DISPLAY", "Settings text size to 20...");
						titlev.setTextSize(10);
						Log.d("DISPLAY", "Settings text color to BLACK...");
						titlev.setTextColor(Color.BLACK);
						Log.d("DISPLAY","Adding view to linear layout");
						lv.addView(titlev);
					}
					catch(Exception e){
						e.printStackTrace();
					}
				}
			}
			catch(Exception e){
				Log.e("DISPLAY", "Error: "+e.getMessage());
			}
		}
		this.setContentView(l);
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

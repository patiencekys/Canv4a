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
			JSONObject top_sticker;
			JSONArray j_posts = res.getJSONArray("posts");
			Log.v("CORE", "Entering group posts fetching");
			for(int i=0; i < j_posts.length(); i++){
				JSONObject res_p = j_posts.getJSONObject(i);
				try {
					Log.v("CORE", "Fetching one object...");
					Log.d("API","posturl: "+Canv4aActivity.https2http(res_p.getString("api_url")));
					HttpResponse response = Canv4aActivity.clt.execute(
							new HttpGet(
									Canv4aActivity.https2http(res_p.getString("api_url"))
									)
							);
					Log.v("CORE","Converting recieved data to string");
					String resp_p = EntityUtils.toString(response.getEntity());
					Log.v("CORE", "Converting string to JSONObject");
					res_p = new JSONObject(resp_p);
					Log.v("CORE", "Getting  top_sticker from JSONObject");
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
			Log.v("CORE", "Trying to display items");
			display();
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
		Log.d("DISPLAY", "Changing text to \"funny\"");
		title.setText("funny");
		title.setTextColor(Color.BLACK);
		Log.d("DISPLAY", "Adding to ScrollView...");
		lv.addView(title);
		for(int i=0; i<posts.size(); i++){
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
				final CanvasPost p = posts.get(i);
				Log.v("DISPLAY", "Trying to set title...");
				if(p.title!= null){
					try{
						Log.d("DISPLAY", "Creating TextView...");
						TextView titlev = new TextView(this);
						Log.d("DISPLAY", "setting title text: "+p.title);
						titlev.setText(p.title);
						Log.d("DISPLAY", "setting top_sticker text: "+p.top_sticker_name);
						titlev.setText(p.top_sticker_name);
						Log.d("DISPLAY", "Settings text size to 20...");
						titlev.setTextSize(20);
						Log.d("DISPLAY", "Settings text color to BLACK...");
						titlev.setTextColor(Color.BLACK);
						Log.d("DISPLAY","Adding view to linear layout");
						lv.addView(titlev);
					}
					catch(Exception e){
						e.printStackTrace();
					}
				}
				else
					Log.i("TITLE", "p.title is null. posturl: "+p.url);
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
							Intent i = new Intent(getBaseContext(), ThreadViewActivity.class);
							i.putExtra("POSTID", p.thread_op_id);
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
						Log.d("DISPLAY", "setting caption text: "+p.title);
						titlev.setText(p.title);
						Log.d("DISPLAY", "setting top_sticker text: "+p.top_sticker_name);
						titlev.setText(p.top_sticker_name);
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

				//=====================
			}
			catch(Exception e){
				Log.e("DISPLAY", "Error: "+e.getMessage());
			}
		}
		this.setContentView(l);
	}
}

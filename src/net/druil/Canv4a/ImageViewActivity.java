package net.druil.Canv4a;

import java.io.InputStream;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ImageViewActivity extends Activity {
	public String purl;
	public CanvasPost p;
	public ImageView i;

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		purl = getIntent().getExtras().getString("POSTURL");
        Log.d("CORE", "Post URL: "+Canv4aActivity.baseURL+"public_api/posts/"+this.purl);
		try{
			HttpResponse getResponse = Canv4aActivity.clt.execute(new HttpGet(new String(purl)));
			String resp = EntityUtils.toString(getResponse.getEntity());
			JSONObject res = new JSONObject(resp);
			p = new CanvasPost(res);
			createUI();
		}
		catch(Exception e){
			Log.d("ImageViewActivity", "Exception: "+e.getMessage());
		}
		// TODO: Get double tap
		// 		change image size, and move picture
	}
	
	public void createUI(){
		LinearLayout v = new LinearLayout(this);
		v.setOrientation(LinearLayout.VERTICAL);
		LinearLayout vh = new LinearLayout(this);
		vh.setOrientation(LinearLayout.HORIZONTAL);
		v.addView(vh);
		i = new ImageView(this);
		vh.addView(i);
		if(p.urls.orig != null){
			try {
				Log.v("DISPLAY", "Post url is defined");
				URL url = new URL(p.urls.orig.name);
				Log.d("DISPLAY", "Getting URL...");
				InputStream content = (InputStream)url.getContent();
				Log.d("DISPLAY", "Creating Drawable from InputStream...");
				Drawable d = Drawable.createFromStream(content , "src");
				i.setMinimumHeight(p.urls.orig.h);
				i.setMinimumWidth(p.urls.orig.w);
				Log.d("DISPLAY", "Setting Image...");
				i.setImageDrawable(d);
				Log.d("DISPLAY", "Done");
			}
			catch(Exception e){
				Log.d("ImageViewActivity", "Exception: "+e.getMessage());
			}
		}
		setContentView(v);
	}
}

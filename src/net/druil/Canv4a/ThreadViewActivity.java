/**
 * 
 */
package net.druil.Canv4a;

import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;

/**
 * @author Paul
 *
 */
public class ThreadViewActivity extends Activity {
	protected String baseGroupURL;
	protected String op_id;
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.thread);
        baseGroupURL = Canv4aActivity.baseURL+"public_api/groups/";
        op_id = getIntent().getExtras().getString("POSTID");
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

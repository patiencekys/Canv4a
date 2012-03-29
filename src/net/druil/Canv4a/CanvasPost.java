package net.druil.Canv4a;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

/**
 * Represents a Canvas Post
 * @author Paul
 *
 */
public class CanvasPost {
	public String api_url;
	public String url;
	public int timestamp;
	public String thread_op_id;
	public String id;
	public String category;
	public String title;
	public String author_name;
	public int parent_comment_id;
	public int parent_comment_reply_count;
	public String img_tiny_url;
	public String img_orig_url;
	
	public CanvasPost(){
		api_url = null;
		url = null;
		timestamp = (Integer) null;
		thread_op_id = null;
		id = null;
		category = null;
		title = null;
		author_name = null;
		parent_comment_id = (Integer) null;
		parent_comment_reply_count = (Integer) null;
		img_tiny_url = null;
		img_orig_url = null;
	}
	
	public CanvasPost(JSONObject j){
		try {
			Log.d("CanvasPost", "Beginning construction from JSONObject...");
			api_url = new String(j.getString("api_url"));
			url = new String(j.getString("url"));
			timestamp = new Integer(j.getInt("timestamp"));
			thread_op_id = new String(j.getString("thread_op_id"));
			id = new String(j.getString("id"));
			category = new String(j.getString("category"));
			title = new String(j.getString("title"));
			author_name = new String(j.getString("author_name"));
			parent_comment_id = new Integer(j.getInt("parent_comment_id"));
			parent_comment_reply_count = new Integer(j.getInt("parent_comment_reply_count"));
			Log.d("CanvasPost","Getting images urls...");
			
			// These two instructions are gross.
			img_tiny_url = new String(j.getString(
					((JSONObject)
							((JSONObject) 
									j.get("reply_content")
									).get("small_column")
									).get("name").toString()
							)
					);
			img_orig_url = new String(j.getString(
					((JSONObject)
							((JSONObject) 
										j.get("reply_content")
										).get("original")
										).get("name").toString()
							)
					);
			Log.d("CanvasPost", "Construction successfull");
		}
		catch(JSONException e){
			Log.e("CanvasPost","JSONException"+e.getMessage());
		}
	}
}

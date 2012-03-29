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
	protected String api_url;
	protected String url;
	protected int timestamp;
	protected String thread_op_id;
	protected String id;
	protected String category;
	protected String title;
	protected String author_name;
	protected int parent_comment_id;
	protected int parent_comment_reply_count;
	
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
			Log.d("CanvasPost", "Construction successfull");
		}
		catch(JSONException e){
			Log.e("CanvasPost","JSONException"+e.getMessage());
		}
	}
}

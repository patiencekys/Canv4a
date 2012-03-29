package net.druil.Canv4a;

import java.util.LinkedList;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

/**
 * Represents a Canvas Post
 * @author Paul
 *
 */
public class CanvasPost {
	
	/**
	 * Represents the different URLs of the images
	 * C-like structs really miss here
	 * @author Paul
	 *
	 */
	public class ImgURLs { 
		public String small; //to display little previews in thread view
		public String orig; // to display if the user wants to get full image
		
		public ImgURLs(){
			small = null;
			orig = null;
		}
		
		public ImgURLs(String s, String orig){
			this.small = s;
			this.orig = orig;
		}
	}
	
	/**
	 * Represents links to replies
	 * @author Paul
	 *
	 */
	public class Reply {
		public String api_url;
		public int timestamp;
		public String id;
		
		public Reply(){
			api_url = null;
			timestamp = (Integer) null;
			id = null;
		}
		
		public Reply(String api_url, int timestamp, String id){
			this.api_url = api_url;
			this.timestamp = timestamp;
			this.id = id;
		}
	}
	
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
	public ImgURLs urls;
	public LinkedList<Reply> replies;
	
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
		urls = new ImgURLs();
		replies = new LinkedList<Reply>();
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
			urls = new ImgURLs(
					j.getString(((JSONObject)((JSONObject) j.get("reply_content")).get("small_column")).get("name").toString()),
					j.getString(((JSONObject)((JSONObject) j.get("reply_content")).get("original")).get("name").toString())
					);
			replies = new LinkedList<Reply>();
			for(int i=0; i<j.getJSONArray("replies").length();i++) {
				JSONObject elem = j.getJSONArray("replies").getJSONObject(i); 
				replies.add(
						new Reply(
								elem.getString("api_url"),
								elem.getInt("timestamp"),
								elem.getString("id")
								)
						);
			}
					
			Log.d("CanvasPost", "Construction successfull");
		}
		catch(JSONException e){
			Log.e("CanvasPost","JSONException"+e.getMessage());
		}
	}
}
